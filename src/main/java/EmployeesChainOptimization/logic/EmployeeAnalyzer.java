package EmployeesChainOptimization.logic;

import EmployeesChainOptimization.model.Employee;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

public class EmployeeAnalyzer {
    private static final BigDecimal PERCENT_20 = new BigDecimal("0.20");
    private static final BigDecimal PERCENT_50 = new BigDecimal("0.50");
    private final List<String> result = new ArrayList<>();

    /**
     * This method analyzes salaries of employees and their hierarchy distance
     * from the CEO and presents results in warnings.
     *
     * @param employees List of employees to analyze
     * @return A String array of warning messages
     */
    public String[] analyze(List<Employee> employees) {
        checkManagersSalaries(employees);
        calculateDistanceCeomanager(employees);
        return result.toArray(String[]::new);
    }

    private void checkManagersSalaries(List<Employee> employees) {
        // Get the subordinates of each manager and group them by the manager's id
        Map<Integer, List<Employee>> managerToEmployees = getSubordinates(employees);

        // Evaluate each manager
        for (Employee manager : employees) {
            // Get the subordinates of the current manager
            List<Employee> subordinates = managerToEmployees.get(manager.id());

            if (subordinates != null) {
                // Calculate the average salary of the subordinates
                BigDecimal averageSubordinateSalary = calculateAverageSubordinateSalary(subordinates);

                // Calculate the salary a manager should earn (20% and 50% more than the average of subordinates)
                BigDecimal twentyPercentMore = averageSubordinateSalary.add(averageSubordinateSalary.multiply(PERCENT_20));
                BigDecimal fiftyPercentMore = averageSubordinateSalary.add(averageSubordinateSalary.multiply(PERCENT_50));

                // Check if the manager earns less or more than they should and add the results to the list
                if (manager.salary().compareTo(twentyPercentMore) < 0) {
                    result.add(manager.firstName() + " " + manager.lastName() + " earns less than they should by "
                            + twentyPercentMore.subtract(manager.salary()));
                } else if (manager.salary().compareTo(fiftyPercentMore) > 0) {
                    result.add(manager.firstName() + " " + manager.lastName() + " earns more than they should by "
                            + manager.salary().subtract(fiftyPercentMore));
                }
            }
        }
    }

    private void calculateDistanceCeomanager(List<Employee> employees) {
        // Calculate the number of managers between each employee and the CEO
        Map<Integer, Integer> employeeToManagerCount = new HashMap<>();

        for (Employee employee : employees) {
            int count = 1;
            Integer managerId = employee.managerID();

            while (managerId != null) {
                Integer finalManagerId = managerId;
                Employee manager = employees.stream()
                        .filter(e -> e.id().equals(finalManagerId))
                        .findFirst()
                        .orElse(null);
                if (manager != null) {
                    count++;
                    managerId = manager.managerID();
                } else {
                    managerId = null;
                }
            }
            employeeToManagerCount.put(employee.id(), count);
        }

        // Check if the number of managers is more than 4 and add the results to the list
        employeeToManagerCount.entrySet().stream()
                .filter(entry -> entry.getValue() > 4)
                .map(entry -> employees.stream().filter(e -> e.id().equals(entry.getKey())).findFirst().orElse(null))
                .filter(Objects::nonNull)
                .forEach(e -> result.add(e.firstName() + " " + e.lastName()
                        + "'s reporting line is too long by " + (employeeToManagerCount.get(e.id()) - 4)));
    }

    private static BigDecimal calculateAverageSubordinateSalary(List<Employee> subordinates) {
        return subordinates.stream()
                .map(Employee::salary)
                .reduce(BigDecimal::add)
                .map(s -> s.divide(BigDecimal.valueOf(subordinates.size()), 2, RoundingMode.HALF_UP))
                .orElse(BigDecimal.ZERO);
    }

    private static Map<Integer, List<Employee>> getSubordinates(List<Employee> employees) {
        return employees.stream()
                .collect(Collectors.groupingBy(Employee::managerID));
    }
}

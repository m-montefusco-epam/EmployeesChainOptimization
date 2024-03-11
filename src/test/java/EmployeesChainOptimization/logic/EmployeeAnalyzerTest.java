package EmployeesChainOptimization.logic;

import EmployeesChainOptimization.model.Employee;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EmployeeAnalyzerTest {

    private final EmployeeAnalyzer employeeAnalyzer = new EmployeeAnalyzer();

    @Test
    public void testManagerEarningLess() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Manager", "Normal", new BigDecimal(42000), 0));
        employees.add(new Employee(2, "Team", "Lead", new BigDecimal(40000), 1));
        employees.add(new Employee(3, "Senior", "Developer", new BigDecimal(30000), 2));
        employees.add(new Employee(4, "Simple", "Developer", new BigDecimal(20000), 3));
        employees.add(new Employee(5, "Jr", "Employee", new BigDecimal(15000), 4));

        String[] result = employeeAnalyzer.analyze(employees);

        assertTrue(result.length > 0);
        assertEquals("Manager Normal earns less than they should by 6000.0000", result[0]);
    }

    @Test
    public void testManagerEarningMore() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Manager", "Normal", new BigDecimal(100000), 0));
        employees.add(new Employee(2, "Team", "Lead", new BigDecimal(40000), 1));
        employees.add(new Employee(3, "Senior", "Developer", new BigDecimal(30000), 2));
        employees.add(new Employee(4, "Simple", "Developer", new BigDecimal(20000), 3));
        employees.add(new Employee(5, "Jr", "Employee", new BigDecimal(15000), 4));

        String[] result = employeeAnalyzer.analyze(employees);

        assertTrue(result.length > 0);
        assertEquals("Manager Normal earns more than they should by 40000.0000", result[0]);
    }

    @Test
    public void testLongReportingLine() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Manager", "Normal", new BigDecimal(50000), 0));
        employees.add(new Employee(2, "Team", "Lead", new BigDecimal(40000), 1));
        employees.add(new Employee(3, "Senior", "Developer", new BigDecimal(30000), 2));
        employees.add(new Employee(4, "Simple", "Developer", new BigDecimal(20000), 3));
        employees.add(new Employee(5, "Jr", "Employee", new BigDecimal(15000), 4));

        String[] result = employeeAnalyzer.analyze(employees);

        assertTrue(result.length > 0);
        assertEquals("Jr Employee's reporting line is too long by 1", result[0]);
    }
}

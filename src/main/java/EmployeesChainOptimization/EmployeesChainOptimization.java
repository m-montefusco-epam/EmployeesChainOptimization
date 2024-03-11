package EmployeesChainOptimization;

import EmployeesChainOptimization.logic.EmployeeAnalyzer;
import EmployeesChainOptimization.model.Employee;
import EmployeesChainOptimization.readers.CsvReader;
import EmployeesChainOptimization.readers.Reader;

import java.util.List;

public class EmployeesChainOptimization {
    private final Reader reader;
    private final EmployeeAnalyzer employeeAnalyzer = new EmployeeAnalyzer();

    public EmployeesChainOptimization(String csvFileName) {
        this.reader = new CsvReader(csvFileName);
    }

    public void start() {
        //Read And Parse File
        List<Employee> employees = reader.readData();

        //Analyze Data
        String[] result = employeeAnalyzer.analyze(employees);

        //TODO Show data
        for(String s : result) {
            System.out.println(s);
        }
        //Show Response
    }
}

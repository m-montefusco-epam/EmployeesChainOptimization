package EmployeesChainOptimization.readers;

import EmployeesChainOptimization.exceptions.ReaderException;
import EmployeesChainOptimization.model.Employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CsvReader implements Reader {

    private final String csvFileName;

    public CsvReader(String csvFileName) {
        this.csvFileName = csvFileName;
    }

    @Override
    public List<Employee> readData() {
        List<Employee> employees = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFileName))) {
            String line = br.readLine(); // The first line contains headers.
            while ((line = br.readLine()) != null) {
                employees.add(parseEmployeeRow(line));
            }
        } catch (IOException | ReaderException e) {
            System.out.println(e.getMessage());
            employees = new ArrayList<>();
        }
        return employees;
    }

    private Employee parseEmployeeRow(String line) throws ReaderException {
        Employee employee;
        String[] values = line.trim().split(",");
        if (values.length < 5) {
            throw new ReaderException("The provided file contains missing data.");
        }
        try{

            employee = new Employee(Integer.parseInt(values[0].trim()), values[1].trim(), values[2].trim(), new BigDecimal(values[3].trim()), Integer.parseInt(values[4].trim()));
        }catch(Exception ex){
            throw new ReaderException("The provided file contains corrupt data.");
        }
        return employee;
    }
}

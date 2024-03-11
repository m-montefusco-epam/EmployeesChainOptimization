package EmployeesChainOptimization.readers;

import EmployeesChainOptimization.exceptions.ReaderException;
import EmployeesChainOptimization.model.Employee;

import java.util.List;

public interface Reader {
    List<Employee> readData();
}

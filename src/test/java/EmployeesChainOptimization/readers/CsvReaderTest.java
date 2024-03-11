package EmployeesChainOptimization.readers;

import EmployeesChainOptimization.exceptions.ReaderException;
import EmployeesChainOptimization.model.Employee;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class CsvReaderTest {

    @Test
    public void readData_OK() {
        Reader reader = new CsvReader("./src/test/resources/employee_OK.csv");
        List<Employee> employeeList = reader.readData();

        // Assert the employee data
        assertEquals(2, employeeList.size());
    }
}


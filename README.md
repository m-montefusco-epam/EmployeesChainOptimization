# Employees Chain Optimization

## Overview
This is a demo project that is meant to optimize the employee chain. It is designed to read employee data from a CSV file, calculate pay discrepancies based on hierarchy and generate comprehensive reports.
## Assumptions
- Employees data are read from a CSV file in the format "ID, FirstName, LastName, Salary, ManagerId".
- Top hierarchy members are assumed to have managerId as '0'.
- Manager's earnings are assumed to be more than their subordinates.
- We assume that a long reporting line is a problem and we calculate this according to a threshold.

### Getting Started
To get this project running you will need Java installed, as well as Maven for dependency management.
1. Clone the repository
2. Navigate to the root project directory
3. Run `mvn install` to install the dependencies
4. Run the project using the correct command for your IDE or CLI.

### Dependencies
This project requires Java 17 along with the JUnit testing suite, and Maven for dependency management.

### Testing
Tests are located inside the test folder. They can be run individually in your IDE or you can use Maven from CLI using `mvn test`.

### Final Notes
The application is simple yet demonstrates some key Object-Oriented Programming Concepts and clean code practices.\
It's perfect to get an understanding of a clean Java architecture.
It's intended to be a teaching tool rather than a production ready system.



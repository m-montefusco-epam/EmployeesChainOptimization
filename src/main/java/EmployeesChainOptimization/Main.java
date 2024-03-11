package EmployeesChainOptimization;

public class Main {

    private static final String DEFAULT_PATH = "./src/main/resources/employees.csv";

    public static void main(String[] args) {
        String filename = "";
        try {
            if (args != null && args[0] != null) {
                filename = args[0];
            }
        }catch (Exception ex ) {
            filename = DEFAULT_PATH;
        }
        EmployeesChainOptimization eco = new EmployeesChainOptimization(filename);
        eco.start();
    }
}
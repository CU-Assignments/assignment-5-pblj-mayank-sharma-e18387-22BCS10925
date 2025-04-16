// src/EmployeeManagementSystem.java
import java.io.*;
import java.util.*;

class Employee implements Serializable {
    private static final long serialVersionUID = 1L;

    int id;
    String name;
    String designation;
    double salary;

    public Employee(int id, String name, String designation, double salary) {
        this.id = id;
        this.name = name;
        this.designation = designation;
        this.salary = salary;
    }

    public void display() {
        System.out.println("Employee ID: " + id + ", Name: " + name +
                ", Designation: " + designation + ", Salary: " + salary);
    }
}

public class EmployeeManagementSystem {
    static final String FILE_NAME = "employees.ser";

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            List<Employee> employees = loadEmployees();

            while (true) {
                System.out.println("\nMenu:");
                System.out.println("1. Add an Employee");
                System.out.println("2. Display All Employees");
                System.out.println("3. Exit");
                System.out.print("Choose an option: ");

                int option = scanner.hasNextInt() ? scanner.nextInt() : -1;
                scanner.nextLine(); // consume newline

                switch (option) {
                    case 1 -> {
                        try {
                            System.out.print("Enter Employee ID: ");
                            int id = scanner.nextInt();
                            scanner.nextLine();

                            System.out.print("Enter Employee Name: ");
                            String name = scanner.nextLine();

                            System.out.print("Enter Designation: ");
                            String designation = scanner.nextLine();

                            System.out.print("Enter Salary: ");
                            double salary = scanner.nextDouble();
                            scanner.nextLine();

                            employees.add(new Employee(id, name, designation, salary));
                            saveEmployees(employees);
                            System.out.println("Employee added successfully!");
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please try again.");
                            scanner.nextLine(); // clear invalid input
                        }
                    }

                    case 2 -> {
                        if (employees.isEmpty()) {
                            System.out.println("No employees found.");
                        } else {
                            for (Employee emp : employees) {
                                emp.display();
                            }
                        }
                    }

                    case 3 -> {
                        System.out.println("Exiting...");
                        return;
                    }

                    default -> System.out.println("Invalid option. Please try again.");
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    private static List<Employee> loadEmployees() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Object obj = ois.readObject();
            if (obj instanceof List<?> list && !list.isEmpty() && list.get(0) instanceof Employee) {
                return (List<Employee>) list;
            } else {
                System.out.println("Warning: Incompatible employee data found.");
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading employees: " + e.getMessage());
        }
        return new ArrayList<>();
    }

    private static void saveEmployees(List<Employee> employees) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(employees);
        } catch (IOException e) {
            System.out.println("Error saving employees: " + e.getMessage());
        }
    }
}

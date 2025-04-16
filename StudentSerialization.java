import java.io.*;

class Student implements Serializable {
     int id;
    String name;
     double gpa;

    public Student(int id, String name, double gpa) {
        this.id = id;
        this.name = name;
        this.gpa = gpa;
    }

    public void display() {
        System.out.println("Student ID: " + id);
        System.out.println("Student Name: " + name);
        System.out.println("Student GPA: " + gpa);
    }
}

public class StudentSerialization {
    public static void main(String[] args) {
        Student student = new Student(101, "Diksha", 9.0);
        String filename = "student.ser";

        // Serialization
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(student);
            System.out.println("Student details saved successfully!");
        } catch (IOException e) {
            System.out.println("Error saving student: " + e.getMessage());
        }

        // Deserialization
        System.out.println("\nReading from file...");
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            Student savedStudent = (Student) ois.readObject();
            savedStudent.display();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading student: " + e.getMessage());
        }
    }
}


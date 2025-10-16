import java.io.*;
import java.util.*;

public class Main {
    private static final String pathName = "src" + File.separator + "students.ser";

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        List<Student> students = loadStudentList(); // Load existing students

        while (true)
        {
            printMenu();
            String choice = scanner.nextLine();

            switch (choice)
            {
                case "1":
                    addNewStudent(scanner, students);
                    break;
                case "2":
                    viewStudents(students);
                    break;
                case "3":
                    students.clear();
                    saveStudentList(students);
                    System.out.println("Student list cleared.");
                    break;
                case "4":
                    saveStudentList(students);
                    System.out.println("Goodbye!");
                    scanner.close(); // Corrected variable name
                    return;
                default:
                    System.out.println("Invalid option. Please enter 1-4.");
            }
        }
    }

    private static void printMenu()
    {
        System.out.println("\n=== Student Manager ===");
        System.out.println("1. Add new student");
        System.out.println("2. View student list");
        System.out.println("3. Clear student list");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void addNewStudent(Scanner scanner, List<Student> students)
    {
        try
        {
            System.out.print("Enter student name: ");
            String name = scanner.nextLine();

            System.out.print("Enter student ID (digits only): ");
            long id = Long.parseLong(scanner.nextLine());

            System.out.print("Enter student grade (0-100): ");
            int grade = Integer.parseInt(scanner.nextLine());

            Student s = new Student(name, id, grade);
            students.add(s);
            saveStudentList(students);

            System.out.println("Student added successfully!");
        } catch (NumberFormatException e)
        {
            System.out.println("Invalid number format. Please try again.");
        }
    }

    private static void viewStudents(List<Student> students)
    {
        if (students.isEmpty())
        {
            System.out.println("No students in the list.");
            return;
        }

        System.out.println("\n--- Student List ---");
        for (Student s : students) {
            System.out.println(s);
            System.out.println("--------------------");
        }
    }

    private static void saveStudentList(List<Student> students)
    {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(pathName)))
        {
            out.writeObject(students);
        } catch (IOException e)
        {
            System.out.println("Error saving student list: " + e.getMessage());
        }
    }

    private static List<Student> loadStudentList()
    {
        File file = new File(pathName);
        if (!file.exists()) return new ArrayList<>();

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file)))
        {
            return (List<Student>) in.readObject();
        } catch (IOException | ClassNotFoundException e)
        {
            System.out.println("Could not load existing student list. Starting fresh.");
            return new ArrayList<>();
        }
    }
}

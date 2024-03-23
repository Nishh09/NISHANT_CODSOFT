import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Course {
    private String courseCode;
    private String title;
    private String description;
    private int capacity;
    private String schedule;

    public Course(String courseCode, String title, String description, int capacity, String schedule) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getSchedule() {
        return schedule;
    }

    @Override
    public String toString() {
        return "Course Code: " + courseCode + "\nTitle: " + title + "\nDescription: " + description
                + "\nCapacity: " + capacity + "\nSchedule: " + schedule;
    }
}

class Student {
    private int studentId;
    private String name;
    private List<Course> registeredCourses;

    public Student(int studentId, String name) {
        this.studentId = studentId;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public int getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public void registerCourse(Course course) {
        if (course.getCapacity() > 0) {
            registeredCourses.add(course);
            course.capacity--;
            System.out.println("Course registered successfully!");
        } else {
            System.out.println("Course is already full!");
        }
    }

    public void dropCourse(Course course) {
        if (registeredCourses.contains(course)) {
            registeredCourses.remove(course);
            course.capacity++;
            System.out.println("Course dropped successfully!");
        } else {
            System.out.println("You are not registered in this course!");
        }
    }

    public void displayRegisteredCourses() {
        if (registeredCourses.isEmpty()) {
            System.out.println("You are not registered in any courses.");
        } else {
            System.out.println("Registered Courses:");
            for (Course course : registeredCourses) {
                System.out.println(course);
                System.out.println("-------------------------");
            }
        }
    }
}

public class CourseRegistrationSystem {
    private static List<Course> courses = new ArrayList<>();
    private static List<Student> students = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeCourses();
        displayCourseList();
        menu();
    }

    private static void initializeCourses() {
        courses.add(new Course("CSE101", "Introduction to Computer Science", "Fundamentals of programming", 30,
                "Mon/Wed/Fri 9:00-10:30"));
        courses.add(
                new Course("MAT101", "Calculus I", "Introduction to differential calculus", 25, "Tue/Thu 11:00-12:30"));
        courses.add(new Course("PHY101", "Physics I", "Mechanics and properties of matter", 20, "Mon/Wed 13:00-14:30"));
    }

    private static void displayCourseList() {
        System.out.println("Available Courses:");
        for (Course course : courses) {
            System.out.println(course);
            System.out.println("-------------------------");
        }
    }

    private static void menu() {
        boolean exit = false;
        while (!exit) {
            System.out.println("\nMenu:");
            System.out.println("1. Register for a course");
            System.out.println("2. Drop a course");
            System.out.println("3. View registered courses");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    registerCourse();
                    break;
                case 2:
                    dropCourse();
                    break;
                case 3:
                    displayRegisteredCourses();
                    break;
                case 4:
                    exit = true;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
        scanner.close();
    }

    private static void registerCourse() {
        System.out.print("Enter your student ID: ");
        int studentId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        Student student = findStudent(studentId, name);
        if (student != null) {
            System.out.print("Enter the course code you want to register for: ");
            String courseCode = scanner.nextLine();
            Course course = findCourse(courseCode);
            if (course != null) {
                student.registerCourse(course);
            } else {
                System.out.println("Course not found!");
            }
        } else {
            System.out.println("Student not found! Please try again.");
        }
    }

    private static void dropCourse() {
        System.out.print("Enter your student ID: ");
        int studentId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        Student student = findStudent(studentId, name);
        if (student != null) {
            student.displayRegisteredCourses();
            System.out.print("Enter the course code you want to drop: ");
            String courseCode = scanner.nextLine();
            Course course = findRegisteredCourse(student, courseCode);
            if (course != null) {
                student.dropCourse(course);
            } else {
                System.out.println("You are not registered in this course!");
            }
        } else {
            System.out.println("Student not found! Please try again.");
        }
    }

    private static void displayRegisteredCourses() {
        System.out.print("Enter your student ID: ");
        int studentId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        Student student = findStudent(studentId, name);
        if (student != null) {
            student.displayRegisteredCourses();
        } else {
            System.out.println("Student not found! Please try again.");
        }
    }

private static Student findStudent(int studentId,

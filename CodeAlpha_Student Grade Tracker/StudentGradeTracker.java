import java.util.Scanner;

/**
 * Student Grade Tracker
 * A simple console application for beginners to track student names and grades,
 * calculate class statistics (average, highest, lowest), and display a summary.
 */
public class StudentGradeTracker {

    // A static Scanner object so it can be shared and used across all methods in the class.
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== Welcome to the Student Grade Tracker ===");
        
        // 1. Get the number of students from the user.
        int numStudents = getNumberOfStudents();
        
        // 2. Initialize arrays to store student names and their corresponding grades.
        // We use the number of students entered to set the size of these arrays.
        String[] names = new String[numStudents];
        double[] grades = new double[numStudents];
        
        // 3. Collect names and grades for each student.
        inputData(names, grades);
        
        // 4. Calculate statistics using helper methods.
        double average = calculateAverage(grades);
        double highest = findHighest(grades);
        double lowest = findLowest(grades);
        
        // 5. Display the final summary report on the console.
        displayReport(names, grades, average, highest, lowest);
        
        // 6. Close the scanner resource to prevent resource leaks.
        scanner.close();
    }
    
    /**
     * Prompt the user for the number of students and validate the input.
     * Ensures that the input is a positive integer.
     */
    public static int getNumberOfStudents() {
        int count = 0;
        
        // Loop indefinitely until we get a valid number of students.
        while (true) {
            System.out.print("Enter the number of students: ");
            
            // Check if the user entered an integer value.
            if (scanner.hasNextInt()) {
                count = scanner.nextInt();
                scanner.nextLine(); // Clear the newline character left in the buffer.
                
                // Validate that the count is positive.
                if (count > 0) {
                    break; // Exit the loop since input is valid.
                } else {
                    System.out.println("Please enter a positive number of students (greater than 0).");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.next(); // Read and discard the invalid non-integer input to avoid infinite loop.
            }
        }
        return count;
    }
    
    /**
     * Loops through the arrays to prompt and input the name and grade for each student.
     * Validates that the grade is a decimal number between 0 and 100.
     */
    public static void inputData(String[] names, double[] grades) {
        for (int i = 0; i < names.length; i++) {
            System.out.println("\nRecording details for Student #" + (i + 1) + ":");
            
            // --- Input and Validate Name ---
            System.out.print("Enter name: ");
            names[i] = scanner.nextLine().trim();
            
            // Keep prompting if the name is empty.
            while (names[i].isEmpty()) {
                System.out.print("Name cannot be empty. Please enter a valid name: ");
                names[i] = scanner.nextLine().trim();
            }
            
            // --- Input and Validate Grade ---
            double grade = -1;
            while (true) {
                System.out.print("Enter grade (0-100): ");
                
                // Check if the input is a valid decimal number.
                if (scanner.hasNextDouble()) {
                    grade = scanner.nextDouble();
                    scanner.nextLine(); // Clear the newline character from the buffer.
                    
                    // Validate that the grade is within the acceptable range.
                    if (grade >= 0 && grade <= 100) {
                        break; // Exit the loop since grade is valid.
                    } else {
                        System.out.println("Invalid grade. Grade must be between 0 and 100. Try again.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a numerical grade value (0-100).");
                    scanner.next(); // Read and discard the invalid non-numeric input.
                    scanner.nextLine(); // Clear any remaining characters in the line.
                }
            }
            // Store the validated grade in our grades array.
            grades[i] = grade;
        }
    }
    
    /**
     * Calculate the average grade of all students.
     */
    public static double calculateAverage(double[] grades) {
        if (grades.length == 0) {
            return 0.0;
        }
        double sum = 0;
        // Loop through each grade and add it to the sum.
        for (double grade : grades) {
            sum += grade;
        }
        // Divide the total sum by the number of students to find the average.
        return sum / grades.length;
    }
    
    /**
     * Find the highest grade in the class.
     */
    public static double findHighest(double[] grades) {
        if (grades.length == 0) {
            return 0.0;
        }
        // Start by assuming the first grade is the highest.
        double highest = grades[0];
        
        // Loop through the rest of the grades to find a higher one.
        for (int i = 1; i < grades.length; i++) {
            if (grades[i] > highest) {
                highest = grades[i]; // Update highest if a larger value is found.
            }
        }
        return highest;
    }
    
    /**
     * Find the lowest grade in the class.
     */
    public static double findLowest(double[] grades) {
        if (grades.length == 0) {
            return 0.0;
        }
        // Start by assuming the first grade is the lowest.
        double lowest = grades[0];
        
        // Loop through the rest of the grades to find a lower one.
        for (int i = 1; i < grades.length; i++) {
            if (grades[i] < lowest) {
                lowest = grades[i]; // Update lowest if a smaller value is found.
            }
        }
        return lowest;
    }
    
    /**
     * Format and display a neat summary report of students and class statistics.
     */
    public static void displayReport(String[] names, double[] grades, double average, double highest, double lowest) {
        System.out.println("\n=================================");
        System.out.println("      STUDENT GRADE SUMMARY      ");
        System.out.println("=================================");
        
        // Print header columns. %-20s means a left-justified string occupying 20 spaces.
        System.out.printf("%-20s | %-10s\n", "Student Name", "Grade");
        System.out.println("---------------------------------");
        
        // Loop through and print each student's name and grade.
        // %.2f formatting ensures grades print with exactly 2 decimal places.
        for (int i = 0; i < names.length; i++) {
            System.out.printf("%-20s | %-10.2f\n", names[i], grades[i]);
        }
        
        System.out.println("---------------------------------");
        System.out.printf("Average Grade: %.2f\n", average);
        System.out.printf("Highest Grade: %.2f\n", highest);
        System.out.printf("Lowest Grade:  %.2f\n", lowest);
        System.out.println("=================================");
    }
}


import java.util.Scanner;

public class task2 {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=".repeat(50));
        System.out.println("     STUDENT GRADE CALCULATOR");
        System.out.println("=".repeat(50));
        
        // Input number of subjects
        System.out.print("Enter number of subjects: ");
        int numSubjects = scanner.nextInt();
        
        int totalMarks = 0;
        
        // Input marks for each subject
        for (int i = 1; i <= numSubjects; i++) {
            System.out.print("Enter marks for Subject " + i + " (out of 100): ");
            int marks = scanner.nextInt();
            
            // Validate input
            while (marks < 0 || marks > 100) {
                System.out.print("Invalid marks! Please enter between 0-100: ");
                marks = scanner.nextInt();
            }
            
            totalMarks += marks;
        }
        
        // Calculate average percentage
        double averagePercentage = (double) totalMarks / numSubjects;
        
        // Determine grade
        String grade;
        if (averagePercentage >= 90) {
            grade = "A+";
        } else if (averagePercentage >= 80) {
            grade = "A";
        } else if (averagePercentage >= 70) {
            grade = "B+";
        } else if (averagePercentage >= 60) {
            grade = "B";
        } else if (averagePercentage >= 50) {
            grade = "C+";
        } else if (averagePercentage >= 40) {
            grade = "C";
        } else {
            grade = "F";
        }
        
        // Display results
        System.out.println("\n" + "=".repeat(40));
        System.out.println("RESULTS");
        System.out.println("=".repeat(40));
        System.out.println("Total Marks: " + totalMarks + " / " + (numSubjects * 100));
        System.out.printf("Average Percentage: %.2f%%\n", averagePercentage);
        System.out.println("Grade: " + grade);
        
        // Additional remarks
        if (grade.equals("F")) {
            System.out.println("Status: FAIL - Needs Improvement!");
        } else if (grade.equals("C") || grade.equals("C+")) {
            System.out.println("Status: PASS - Can do better!");
        } else {
            System.out.println("Status: PASS - Good Performance!");
        }
        
        scanner.close();
    }
}
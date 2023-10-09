import java.util.InputMismatchException;
import java.util.Scanner;

//////////////////////////////////////////////////////////////////////////
//
//  Class name :         StudentGradeCalculator
//  Description :        This program calculates the total marks, average percentage, and assigns a grade
//                based on the marks obtained in each subject (out of 100). It takes input for the
//                number of subjects and the marks obtained in each subject from the user. The program
//                validates the input and provides results including the total marks, average
//                percentage, and grade. ANSI escape codes are used for text formatting to enhance
//                the user interface.
//  Author :             Navnath Jadhav
//  Date :               02/10/2023
//  Update Date :        09/10/2023
//
//////////////////////////////////////////////////////////////////////////

public class StudentGradeCalculator {
    // ANSI escape codes for text formatting
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BOLD = "\u001B[1m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_GREEN = "\u001B[32m";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int totalMarks = 0;
        int totalSubjects = 0;

        try {
            // Input: Take the number of subjects
            System.out.print(ANSI_BOLD + ANSI_CYAN + "Enter the number of subjects: " + ANSI_RESET);
            totalSubjects = scanner.nextInt();

            // Input: Take marks obtained in each subject
            System.out.println(ANSI_CYAN + "Enter marks obtained in each subject (out of 100):" + ANSI_RESET);
            int[] subjectMarks = new int[totalSubjects];

            for (int i = 0; i < totalSubjects; i++) {
                System.out.print(ANSI_BOLD + "Enter marks for subject " + (i + 1) + ": " + ANSI_RESET);
                subjectMarks[i] = scanner.nextInt();

                if (subjectMarks[i] < 0 || subjectMarks[i] > 100) {
                    System.out.println(ANSI_GREEN + "Invalid input! Marks should be between 0 and 100." + ANSI_RESET);
                    return;
                }

                totalMarks += subjectMarks[i];
            }

            // Calculate total marks and average percentage
            double averagePercentage = (double) totalMarks / (totalSubjects * 100) * 100;

            // Grade calculation based on average percentage
            char grade;
            if (averagePercentage >= 90) {
                grade = 'A';
            } else if (averagePercentage >= 80) {
                grade = 'B';
            } else if (averagePercentage >= 70) {
                grade = 'C';
            } else if (averagePercentage >= 60) {
                grade = 'D';
            } else {
                grade = 'F';
            }

            // Display Results with ANSI escape codes
            System.out.println("\n" + ANSI_BOLD + "--------- Results ---------" + ANSI_RESET);
            System.out.println(ANSI_CYAN + "Total Marks: " + ANSI_RESET + totalMarks);
            System.out.printf(ANSI_CYAN + "Average Percentage: %.2f%%\n" + ANSI_RESET, averagePercentage);
            System.out.println(ANSI_CYAN + "Grade: " + ANSI_RESET + grade);
            System.out.println(ANSI_BOLD + "--------------------------" + ANSI_RESET);

        } catch (InputMismatchException e) {
            System.out.println(ANSI_GREEN + "Invalid input! Please enter valid marks." + ANSI_RESET);
        } finally {
            scanner.close();
        }
    }
}

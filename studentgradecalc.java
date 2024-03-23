import java.util.*;

public class studentgradecalc {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the total no.of subjects: ");
        int n = sc.nextInt();

        // ----SUBJECTS MARKS(out of 100)----
        System.out.println("\n----SUBJECTS MARKS(out of 100)----");
        System.out.print("Enter the PHYSICS marks(out of 100): ");
        int PHYSICS = sc.nextInt();

        System.out.print("Enter the CHEMISTRY marks(out of 100): ");
        int CHEMISTRY = sc.nextInt();

        System.out.print("Enter the MATHS marks(out of 100): ");
        int MATHS = sc.nextInt();

        System.out.print("Enter the BIOLOGY marks(out of 100): ");
        int BIOLOGY = sc.nextInt();

        System.out.print("Enter the ENGLISH marks(out of 100): ");
        int ENGLISH = sc.nextInt();

        // ----RESULT----
        System.out.println("\n----RESULT----");
        int totalsum = PHYSICS + CHEMISTRY + MATHS + BIOLOGY + ENGLISH;
        System.out.println("Sum of total marks: " + totalsum);

        float avgPercentage = (float) (totalsum / n);
        System.out.println("The average percentage of total subjects are: " + avgPercentage);

        System.out.println("\n----GRADE----");
        char Grade;
        if (avgPercentage > 90) {
            System.out.print("O Grade");
        } else if (avgPercentage >= 70 && avgPercentage < 90) {
            System.out.print("Grade is: A");
        } else if (avgPercentage >= 50 && avgPercentage < 70) {
            System.out.print("Grade is: B");
        } else if (avgPercentage >= 35 && avgPercentage < 50) {
            System.out.print("Grade is: C");
        } else {
            System.out.print("Grade is: F(FAIL) ");
        }
    }
}
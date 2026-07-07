import java.util.Scanner;

/**
 * Payment.java
 * Simulates a payment transaction for booking confirmation.
 */
public class Payment {

    /**
     * Simulates payment process.
     * Displays the total cost, asks for user confirmation, and outputs the result.
     * 
     * @param amount The total cost of the booking.
     * @param scanner Scanner object to read user input.
     * @return true if the payment was successful, false if cancelled.
     */
    public static boolean processPayment(double amount, Scanner scanner) {
        System.out.println("\n==================================");
        System.out.println("        PAYMENT SIMULATION        ");
        System.out.println("==================================");
        System.out.println("Amount to Pay: $" + amount);
        System.out.print("Do you confirm payment? (yes/no): ");
        
        String input = scanner.nextLine().trim();
        
        if (input.equalsIgnoreCase("yes") || input.equalsIgnoreCase("y")) {
            System.out.println("\nProcessing payment...");
            System.out.println(">>> Payment Successful! <<<");
            return true;
        } else {
            System.out.println("\n>>> Payment Cancelled. <<<");
            return false;
        }
    }
}

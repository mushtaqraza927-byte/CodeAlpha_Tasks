import java.util.Scanner;

/**
 * Main.java
 * The entry point of the Hotel Reservation System application.
 * Manages the main menu loop and processes user actions.
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HotelManager manager = new HotelManager();

        System.out.println("==================================================");
        System.out.println("      WELCOME TO THE HOTEL RESERVATION SYSTEM     ");
        System.out.println("==================================================");

        boolean exit = false;

        // Loop until user decides to exit
        while (!exit) {
            printMenu();
            System.out.print("Enter your choice (1-6): ");
            int choice = readInteger(scanner);

            switch (choice) {
                case 1:
                    // View rooms
                    manager.displayAllRooms();
                    break;

                case 2:
                    // Book a room
                    System.out.println("\n--- Book a Room ---");
                    System.out.print("Enter Customer Name: ");
                    String customerName = scanner.nextLine().trim();
                    while (customerName.isEmpty()) {
                        System.out.print("Name cannot be empty. Enter Customer Name: ");
                        customerName = scanner.nextLine().trim();
                    }

                    // Display available rooms to help the user choose
                    manager.displayAvailableRooms();

                    System.out.print("\nEnter Room Number to Book: ");
                    int roomNum = readInteger(scanner);

                    System.out.print("Enter Number of Nights: ");
                    int nights = readInteger(scanner);
                    while (nights <= 0) {
                        System.out.print("Nights must be 1 or more. Enter Number of Nights: ");
                        nights = readInteger(scanner);
                    }

                    manager.bookRoom(customerName, roomNum, nights);
                    break;

                case 3:
                    // Cancel a reservation
                    System.out.println("\n--- Cancel a Reservation ---");
                    System.out.println("1. Cancel by Reservation ID");
                    System.out.println("2. Cancel by Room Number");
                    System.out.print("Choose cancellation method (1-2): ");
                    int cancelChoice = readInteger(scanner);

                    if (cancelChoice == 1) {
                        System.out.print("Enter Reservation ID: ");
                        int resId = readInteger(scanner);
                        manager.cancelReservation(resId);
                    } else if (cancelChoice == 2) {
                        System.out.print("Enter Room Number: ");
                        int roomToCancel = readInteger(scanner);
                        manager.cancelReservationByRoom(roomToCancel);
                    } else {
                        System.out.println("Invalid choice. Returning to Main Menu.");
                    }
                    break;

                case 4:
                    // View bookings
                    manager.viewBookings();
                    break;

                case 5:
                    // Make a payment
                    System.out.println("\n--- Make a Payment ---");
                    System.out.print("Enter Reservation ID to Pay: ");
                    int payId = readInteger(scanner);
                    manager.processPaymentForReservation(payId, scanner);
                    break;

                case 6:
                    // Exit
                    System.out.println("\nThank you for using the Hotel Reservation System. Goodbye!");
                    exit = true;
                    break;

                default:
                    System.out.println("\n[Error] Invalid choice! Please enter a number between 1 and 6.");
                    break;
            }
        }
        
        scanner.close();
    }

    /**
     * Helper method to print the CLI menu.
     */
    private static void printMenu() {
        System.out.println("\n==================================");
        System.out.println("            MAIN MENU             ");
        System.out.println("==================================");
        System.out.println("1. View All Rooms & Availability");
        System.out.println("2. Book a Room");
        System.out.println("3. Cancel a Reservation");
        System.out.println("4. View Booking Details");
        System.out.println("5. Make a Payment (Simulation)");
        System.out.println("6. Exit");
        System.out.println("==================================");
    }

    /**
     * Helper method to read an integer from the scanner safely.
     * Prevents the application from crashing if the user enters letters or invalid inputs.
     * Also consumes the newline character to avoid the standard Scanner buffer issue.
     */
    private static int readInteger(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a valid whole number: ");
            }
        }
    }
}

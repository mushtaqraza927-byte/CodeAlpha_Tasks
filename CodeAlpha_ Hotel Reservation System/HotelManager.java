import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * HotelManager.java
 * Manages hotel operations: stores rooms and reservations, handles booking logic,
 * cancellation logic, payment processing, and File I/O.
 */
public class HotelManager {
    // List to store all hotel rooms
    private ArrayList<HotelRoom> rooms;
    // List to store all active reservations
    private ArrayList<Reservation> reservations;
    // Variable to automatically generate unique reservation IDs
    private int nextReservationId;
    // File name used for storing reservation details
    private final String DATA_FILE = "reservations.txt";

    /**
     * Constructor to initialize the HotelManager.
     * Sets up default rooms and loads existing reservations from file if available.
     */
    public HotelManager() {
        rooms = new ArrayList<>();
        reservations = new ArrayList<>();
        nextReservationId = 1001; // Start reservation IDs from 1001

        // Setup the predefined rooms list
        initializeRooms();

        // Load reservations from file at application startup
        loadReservationsFromFile();
    }

    /**
     * Helper method to populate the hotel with a set of predefined rooms.
     */
    private void initializeRooms() {
        // Standard Rooms (100 range, price $100.00/night)
        rooms.add(new HotelRoom(101, "Standard", 100.00));
        rooms.add(new HotelRoom(102, "Standard", 100.00));
        rooms.add(new HotelRoom(103, "Standard", 100.00));

        // Deluxe Rooms (200 range, price $150.00/night)
        rooms.add(new HotelRoom(201, "Deluxe", 150.00));
        rooms.add(new HotelRoom(202, "Deluxe", 150.00));
        rooms.add(new HotelRoom(203, "Deluxe", 150.00));

        // Suites (300 range, price $250.00/night)
        rooms.add(new HotelRoom(301, "Suite", 250.00));
        rooms.add(new HotelRoom(302, "Suite", 250.00));
        rooms.add(new HotelRoom(303, "Suite", 250.00));
    }

    /**
     * View all available rooms in the hotel.
     */
    public void displayAvailableRooms() {
        System.out.println("\n--- Available Rooms ---");
        boolean found = false;
        for (int i = 0; i < rooms.size(); i++) {
            HotelRoom room = rooms.get(i);
            if (room.isAvailable()) {
                room.displayRoomDetails();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No rooms are currently available.");
        }
    }

    /**
     * Displays all rooms in the hotel, showing their booked/available status.
     */
    public void displayAllRooms() {
        System.out.println("\n--- All Hotel Rooms ---");
        for (int i = 0; i < rooms.size(); i++) {
            rooms.get(i).displayRoomDetails();
        }
    }

    /**
     * Handles the logic of booking a room.
     * 
     * @param customerName Name of the guest.
     * @param roomNumber The number of the room to book.
     * @param nights The duration of the stay.
     */
    public void bookRoom(String customerName, int roomNumber, int nights) {
        // Find the room first
        HotelRoom selectedRoom = findRoom(roomNumber);

        if (selectedRoom == null) {
            System.out.println("\n[Error] Room number " + roomNumber + " does not exist.");
            return;
        }

        // Check if the room is available
        if (!selectedRoom.isAvailable()) {
            System.out.println("\n[Error] Room number " + roomNumber + " is already booked.");
            return;
        }

        // Create the Customer object
        Customer customer = new Customer(customerName);

        // Create the Reservation object
        Reservation newReservation = new Reservation(nextReservationId, customer, selectedRoom, nights);

        // Mark room as booked
        selectedRoom.setAvailable(false);

        // Add reservation to our list
        reservations.add(newReservation);

        System.out.println("\n>>> Room Booked Successfully! <<<");
        System.out.println("Reservation ID: " + nextReservationId);
        System.out.println("Total Amount Due: $" + newReservation.getTotalCost());

        // Increment the ID generator for the next booking
        nextReservationId++;

        // Save updated data to file
        saveReservationsToFile();
    }

    /**
     * Cancels an existing reservation.
     * Marks the associated room as available again and removes the reservation.
     * 
     * @param reservationId The ID of the reservation to cancel.
     */
    public void cancelReservation(int reservationId) {
        Reservation reservationToCancel = findReservation(reservationId);

        if (reservationToCancel == null) {
            System.out.println("\n[Error] Reservation ID " + reservationId + " not found.");
            return;
        }

        // Free up the room
        HotelRoom room = reservationToCancel.getRoom();
        room.setAvailable(true);

        // Remove from list
        reservations.remove(reservationToCancel);
        System.out.println("\n>>> Reservation ID " + reservationId + " cancelled successfully. Room " + room.getRoomNumber() + " is now available! <<<");

        // Save updated list to file
        saveReservationsToFile();
    }

    /**
     * Cancels an existing reservation by room number.
     * 
     * @param roomNumber Room number to release.
     */
    public void cancelReservationByRoom(int roomNumber) {
        Reservation reservationToCancel = null;
        for (int i = 0; i < reservations.size(); i++) {
            Reservation r = reservations.get(i);
            if (r.getRoom().getRoomNumber() == roomNumber) {
                reservationToCancel = r;
                break;
            }
        }

        if (reservationToCancel == null) {
            System.out.println("\n[Error] No active reservation found for Room Number " + roomNumber + ".");
            return;
        }

        // Free the room
        HotelRoom room = reservationToCancel.getRoom();
        room.setAvailable(true);

        // Remove reservation
        reservations.remove(reservationToCancel);
        System.out.println("\n>>> Reservation for Room " + roomNumber + " cancelled successfully. <<<");

        // Save updated list to file
        saveReservationsToFile();
    }

    /**
     * Displays all current bookings and details.
     */
    public void viewBookings() {
        System.out.println("\n==================================");
        System.out.println("        CURRENT RESERVATIONS      ");
        System.out.println("==================================");
        if (reservations.isEmpty()) {
            System.out.println("No active reservations found.");
            return;
        }
        for (int i = 0; i < reservations.size(); i++) {
            reservations.get(i).displayReservationDetails();
        }
    }

    /**
     * Handles payment processing for a booking that is currently Unpaid.
     * 
     * @param reservationId The ID of the reservation.
     * @param scanner Scanner instance for text input in payment process.
     */
    public void processPaymentForReservation(int reservationId, Scanner scanner) {
        Reservation reservation = findReservation(reservationId);

        if (reservation == null) {
            System.out.println("\n[Error] Reservation ID " + reservationId + " not found.");
            return;
        }

        if (reservation.getPaymentStatus().equalsIgnoreCase("Paid")) {
            System.out.println("\n[Info] Reservation ID " + reservationId + " is already paid.");
            return;
        }

        // Perform simulation
        boolean success = Payment.processPayment(reservation.getTotalCost(), scanner);

        if (success) {
            reservation.setPaymentStatus("Paid");
            System.out.println("Booking is now fully confirmed and marked as Paid.");
            // Save updated payment status to file
            saveReservationsToFile();
        }
    }

    // --- Helper Methods to Find Rooms and Reservations ---

    /**
     * Helper method to search for a room object by its number.
     * 
     * @param roomNumber Room number to find.
     * @return The HotelRoom object, or null if not found.
     */
    private HotelRoom findRoom(int roomNumber) {
        for (int i = 0; i < rooms.size(); i++) {
            if (rooms.get(i).getRoomNumber() == roomNumber) {
                return rooms.get(i);
            }
        }
        return null;
    }

    /**
     * Helper method to search for a reservation by its ID.
     * 
     * @param reservationId The reservation ID.
     * @return The Reservation object, or null if not found.
     */
    private Reservation findReservation(int reservationId) {
        for (int i = 0; i < reservations.size(); i++) {
            if (reservations.get(i).getReservationId() == reservationId) {
                return reservations.get(i);
            }
        }
        return null;
    }

    // --- File I/O Operations (Bonus Requirement) ---

    /**
     * Saves all current reservations to a text file.
     * Saves data in a simple CSV format: ID,CustomerName,RoomNumber,Nights,TotalCost,PaymentStatus
     */
    public void saveReservationsToFile() {
        try {
            FileWriter writer = new FileWriter(DATA_FILE);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            for (int i = 0; i < reservations.size(); i++) {
                Reservation r = reservations.get(i);
                // Save format: ID,Name,RoomNumber,Nights,TotalCost,PaymentStatus
                bufferedWriter.write(r.getReservationId() + "," +
                                     r.getCustomer().getName() + "," +
                                     r.getRoom().getRoomNumber() + "," +
                                     r.getNights() + "," +
                                     r.getTotalCost() + "," +
                                     r.getPaymentStatus());
                bufferedWriter.newLine();
            }

            bufferedWriter.close();
            writer.close();
        } catch (IOException e) {
            System.out.println("[Warning] Failed to save reservations to file: " + e.getMessage());
        }
    }

    /**
     * Loads reservations from the text file.
     * Parses the CSV file line by line and populates the reservations arraylist.
     */
    public void loadReservationsFromFile() {
        File file = new File(DATA_FILE);
        
        // If file doesn't exist, we just start fresh
        if (!file.exists()) {
            return;
        }

        try {
            FileReader reader = new FileReader(DATA_FILE);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                // Split the comma-separated line
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    int roomNum = Integer.parseInt(parts[2]);
                    int nights = Integer.parseInt(parts[3]);
                    double totalCost = Double.parseDouble(parts[4]);
                    String status = parts[5];

                    // Find the matching room structure
                    HotelRoom room = findRoom(roomNum);
                    if (room != null) {
                        // Mark room as booked since it is in the active reservations file
                        room.setAvailable(false);

                        // Construct objects
                        Customer customer = new Customer(name);
                        Reservation reservation = new Reservation(id, customer, room, nights);
                        reservation.setTotalCost(totalCost);
                        reservation.setPaymentStatus(status);

                        // Add to our list
                        reservations.add(reservation);

                        // Ensure our ID generator is always higher than loaded IDs
                        if (id >= nextReservationId) {
                            nextReservationId = id + 1;
                        }
                    }
                }
            }

            bufferedReader.close();
            reader.close();
        } catch (IOException e) {
            System.out.println("[Warning] Failed to load reservations from file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("[Warning] Error parsing reservations data: " + e.getMessage());
        }
    }
}

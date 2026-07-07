/**
 * HotelRoom.java
 * Represents a single hotel room.
 * Stores room number, type, price per night, and availability status.
 */
public class HotelRoom {
    // Member variables for room details
    private int roomNumber;
    private String roomType;     // "Standard", "Deluxe", or "Suite"
    private double pricePerNight;
    private boolean isAvailable;  // true if available, false if booked

    /**
     * Constructor to initialize a HotelRoom object.
     * @param roomNumber The unique room number.
     * @param roomType The category/type of the room.
     * @param pricePerNight The cost per night.
     */
    public HotelRoom(int roomNumber, String roomType, double pricePerNight) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.pricePerNight = pricePerNight;
        this.isAvailable = true; // By default, all new rooms are available
    }

    // --- Getter and Setter Methods ---

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    /**
     * Helper method to display room details in a neat format.
     */
    public void displayRoomDetails() {
        String availabilityStr = isAvailable ? "Available" : "Booked";
        System.out.println("Room Number: " + roomNumber + 
                           " | Type: " + roomType + 
                           " | Price: $" + pricePerNight + "/night" + 
                           " | Status: " + availabilityStr);
    }
}

/**
 * Reservation.java
 * Represents a booking/reservation in the hotel.
 * Links a Customer and a HotelRoom together with details like duration, total cost, and payment status.
 */
public class Reservation {
    private int reservationId;
    private Customer customer;
    private HotelRoom room;
    private int nights;
    private double totalCost;
    private String paymentStatus; // "Unpaid" or "Paid"

    /**
     * Constructor to initialize a Reservation object.
     * @param reservationId Unique identifier for the reservation.
     * @param customer The Customer object making the booking.
     * @param room The HotelRoom object being booked.
     * @param nights The number of nights the customer will stay.
     */
    public Reservation(int reservationId, Customer customer, HotelRoom room, int nights) {
        this.reservationId = reservationId;
        this.customer = customer;
        this.room = room;
        this.nights = nights;
        this.totalCost = room.getPricePerNight() * nights;
        this.paymentStatus = "Unpaid"; // Default status when booked
    }

    // --- Getter and Setter Methods ---

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public HotelRoom getRoom() {
        return room;
    }

    public void setRoom(HotelRoom room) {
        this.room = room;
    }

    public int getNights() {
        return nights;
    }

    public void setNights(int nights) {
        this.nights = nights;
        // Recalculate total cost if nights change
        this.totalCost = this.room.getPricePerNight() * nights;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    /**
     * Helper method to print details of this reservation.
     */
    public void displayReservationDetails() {
        System.out.println("Reservation ID: " + reservationId);
        System.out.println("Customer Name : " + customer.getName());
        System.out.println("Room Number   : " + room.getRoomNumber() + " (" + room.getRoomType() + ")");
        System.out.println("Nights        : " + nights);
        System.out.println("Total Cost    : $" + totalCost);
        System.out.println("Payment Status: " + paymentStatus);
        System.out.println("----------------------------------------");
    }
}

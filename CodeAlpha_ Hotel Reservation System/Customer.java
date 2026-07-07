/**
 * Customer.java
 * Represents a customer who books a room in the hotel.
 * This class is designed to be simple and easy to understand for beginners.
 */
public class Customer {
    // Member variable to store the customer's name
    private String name;

    /**
     * Constructor to initialize a Customer object with a name.
     * @param name The name of the customer.
     */
    public Customer(String name) {
        this.name = name;
    }

    /**
     * Getter method to retrieve the customer's name.
     * @return The name of the customer.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method to update the customer's name.
     * @param name The new name of the customer.
     */
    public void setName(String name) {
        this.name = name;
    }
}

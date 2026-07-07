/**
 * The Stock class represents a company's stock listed on the stock market.
 * It contains details like the stock name, ticker symbol, and its current price.
 * 
 * This is a fundamental building block of our Object-Oriented project.
 */
public class Stock {
    // Private variables (encapsulation) to protect data from direct modification
    private String name;
    private String symbol;
    private double currentPrice;

    /**
     * Constructor to initialize a new Stock object.
     * 
     * @param name         The full name of the company (e.g., "Apple Inc.")
     * @param symbol       The stock ticker symbol (e.g., "AAPL")
     * @param currentPrice The starting price of the stock
     */
    public Stock(String name, String symbol, double currentPrice) {
        this.name = name;
        this.symbol = symbol.toUpperCase(); // Convert symbol to uppercase for consistency
        this.currentPrice = currentPrice;
    }

    // --- Getters and Setters ---
    // These methods allow other classes to safely view and update stock details.

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol.toUpperCase();
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    /**
     * Updates the current market price of the stock.
     * Used when simulating market changes or loading data.
     * 
     * @param currentPrice The new price of the stock
     */
    public void setCurrentPrice(double currentPrice) {
        // Simple validation: Stock price cannot be negative
        if (currentPrice >= 0.0) {
            this.currentPrice = currentPrice;
        }
    }
}

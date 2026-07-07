import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The Transaction class records a buy or sell activity on the platform.
 * Storing transaction records is helpful for record-keeping and tax audits.
 */
public class Transaction {
    private String type;            // "BUY" or "SELL"
    private String stockSymbol;     // Ticker symbol (e.g., "TSLA")
    private int quantity;           // Shares traded
    private double pricePerShare;   // Price per share at transaction time
    private double totalAmount;     // Total cash exchanged
    private String timestamp;       // Date and time of the transaction

    /**
     * Constructor to record a transaction.
     * Generates a timestamp automatically.
     * 
     * @param type          The transaction type ("BUY" or "SELL")
     * @param stockSymbol   The ticker symbol of the stock
     * @param quantity      The number of shares traded
     * @param pricePerShare The price per share
     */
    public Transaction(String type, String stockSymbol, int quantity, double pricePerShare) {
        this.type = type.toUpperCase();
        this.stockSymbol = stockSymbol.toUpperCase();
        this.quantity = quantity;
        this.pricePerShare = pricePerShare;
        this.totalAmount = quantity * pricePerShare;
        
        // Auto-generate timestamp when transaction is created
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.timestamp = formatter.format(new Date());
    }

    // --- Getters ---

    public String getType() {
        return type;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPricePerShare() {
        return pricePerShare;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getTimestamp() {
        return timestamp;
    }

    /**
     * Formats the transaction log for clean console printouts.
     */
    @Override
    public String toString() {
        return String.format("[%s] %-4s %-6s | Quantity: %-4d | Price: $%-8.2f | Total: $%-9.2f", 
                timestamp, type, stockSymbol, quantity, pricePerShare, totalAmount);
    }
}

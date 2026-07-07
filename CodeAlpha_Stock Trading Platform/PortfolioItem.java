/**
 * The PortfolioItem class represents a stock that is owned by the user.
 * It tracks the stock object, the quantity owned, and the average price 
 * at which the shares were purchased.
 * 
 * Having a separate class for portfolio items helps keep the code clean
 * and makes it easy to calculate weighted averages for multiple purchases.
 */
public class PortfolioItem {
    private Stock stock;
    private int quantity;
    private double averagePurchasePrice;

    /**
     * Constructor to initialize a portfolio item with a stock purchase.
     * 
     * @param stock         The Stock object being purchased
     * @param quantity      Number of shares bought
     * @param purchasePrice The price per share at the time of purchase
     */
    public PortfolioItem(Stock stock, int quantity, double purchasePrice) {
        this.stock = stock;
        this.quantity = quantity;
        this.averagePurchasePrice = purchasePrice;
    }

    // --- Core Logic Methods ---

    /**
     * Updates the holdings when more shares of the same stock are bought.
     * Calculates the new weighted average purchase price.
     * 
     * Formula: (Old Total Cost + New Total Cost) / Total Quantity
     * 
     * @param qty   Number of new shares bought
     * @param price Price per share of the new purchase
     */
    public void buyMore(int qty, double price) {
        double currentTotalCost = this.quantity * this.averagePurchasePrice;
        double newTotalCost = qty * price;
        
        this.quantity += qty;
        this.averagePurchasePrice = (currentTotalCost + newTotalCost) / this.quantity;
    }

    /**
     * Updates the holdings when shares are sold.
     * Note: Selling shares does not change the average purchase price of the remaining shares.
     * 
     * @param qty Number of shares sold
     */
    public void sellShares(int qty) {
        if (qty <= this.quantity) {
            this.quantity -= qty;
        }
    }

    // --- Helper Getters and Calculation Methods ---

    public Stock getStock() {
        return stock;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getAveragePurchasePrice() {
        return averagePurchasePrice;
    }

    /**
     * Calculates the total amount invested in this stock holding.
     * 
     * @return Quantity * Average Purchase Price
     */
    public double getTotalCost() {
        return this.quantity * this.averagePurchasePrice;
    }

    /**
     * Calculates the current market value of this stock holding.
     * 
     * @return Quantity * Current Market Price
     */
    public double getCurrentValue() {
        return this.quantity * this.stock.getCurrentPrice();
    }

    /**
     * Calculates the total profit or loss for this stock holding.
     * 
     * @return Current market value minus the total purchase cost
     */
    public double getProfitLoss() {
        return getCurrentValue() - getTotalCost();
    }
}

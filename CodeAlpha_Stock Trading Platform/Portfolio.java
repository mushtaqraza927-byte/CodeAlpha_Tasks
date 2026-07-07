import java.util.ArrayList;

/**
 * The Portfolio class manages a collection of PortfolioItem objects.
 * It provides methods to buy, sell, and view stocks, as well as
 * calculate overall portfolio performance (Total Cost, Current Value, Profit/Loss).
 */
public class Portfolio {
    // List to store all stock holdings
    private ArrayList<PortfolioItem> holdings;

    /**
     * Constructor to initialize an empty portfolio.
     */
    public Portfolio() {
        holdings = new ArrayList<>();
    }

    // --- Core Operations ---

    /**
     * Adds stock shares to the portfolio. If the stock is already owned,
     * it merges the purchase and updates the average price.
     * 
     * @param stock         The Stock being purchased
     * @param quantity      The number of shares
     * @param purchasePrice The price per share of the purchase
     */
    public void buyStock(Stock stock, int quantity, double purchasePrice) {
        PortfolioItem existingItem = getHolding(stock.getSymbol());

        if (existingItem != null) {
            // Already own this stock, merge the purchase
            existingItem.buyMore(quantity, purchasePrice);
        } else {
            // New stock purchase, add new holding
            holdings.add(new PortfolioItem(stock, quantity, purchasePrice));
        }
    }

    /**
     * Reduces stock shares in the portfolio. If all shares are sold,
     * the stock is removed from the holdings list.
     * 
     * @param stock    The Stock being sold
     * @param quantity The number of shares to sell
     */
    public void sellStock(Stock stock, int quantity) {
        PortfolioItem existingItem = getHolding(stock.getSymbol());

        if (existingItem != null) {
            existingItem.sellShares(quantity);
            // If we now own 0 shares, remove it completely from our holdings
            if (existingItem.getQuantity() == 0) {
                holdings.remove(existingItem);
            }
        }
    }

    /**
     * Helper method to search for a stock holding by its ticker symbol.
     * 
     * @param symbol The stock ticker symbol (e.g., "AAPL")
     * @return The PortfolioItem if found, or null if the user does not own it
     */
    public PortfolioItem getHolding(String symbol) {
        for (PortfolioItem item : holdings) {
            if (item.getStock().getSymbol().equalsIgnoreCase(symbol)) {
                return item;
            }
        }
        return null; // Not found
    }

    /**
     * Returns the complete list of holdings.
     * Useful for saving/loading data.
     * 
     * @return ArrayList of holdings
     */
    public ArrayList<PortfolioItem> getHoldings() {
        return holdings;
    }

    // --- Performance Calculations ---

    /**
     * Calculates the total money invested in the portfolio (the cost basis).
     */
    public double calculateTotalInvested() {
        double total = 0.0;
        for (PortfolioItem item : holdings) {
            total += item.getTotalCost();
        }
        return total;
    }

    /**
     * Calculates the current market value of all stock holdings combined.
     */
    public double calculateCurrentValue() {
        double total = 0.0;
        for (PortfolioItem item : holdings) {
            total += item.getCurrentValue();
        }
        return total;
    }

    /**
     * Calculates total profit or loss across the entire portfolio.
     */
    public double calculateTotalProfitLoss() {
        return calculateCurrentValue() - calculateTotalInvested();
    }

    // --- Display Methods ---

    /**
     * Displays the portfolio in a clean, tabular format.
     */
    public void displayPortfolio() {
        if (holdings.isEmpty()) {
            System.out.println("\n------------------------------------------------");
            System.out.println(" Your portfolio is currently empty.");
            System.out.println(" Start trading to see your holdings here!");
            System.out.println("------------------------------------------------");
            return;
        }

        System.out.println("\n================================================ YOUR PORTFOLIO ================================================");
        System.out.printf("%-15s %-10s %-10s %-15s %-15s %-15s %-15s %-15s\n", 
                "Stock Name", "Symbol", "Qty Owned", "Avg Buy Price", "Current Price", "Total Cost", "Current Value", "Profit/Loss");
        System.out.println("----------------------------------------------------------------------------------------------------------------");

        for (PortfolioItem item : holdings) {
            double profitLoss = item.getProfitLoss();
            // Prefix positive profit with a '+' sign for clarity
            String sign = profitLoss > 0 ? "+" : "";

            System.out.printf("%-15s %-10s %-10d $%-14.2f $%-14.2f $%-14.2f $%-14.2f %s$%-14.2f\n",
                    item.getStock().getName(),
                    item.getStock().getSymbol(),
                    item.getQuantity(),
                    item.getAveragePurchasePrice(),
                    item.getStock().getCurrentPrice(),
                    item.getTotalCost(),
                    item.getCurrentValue(),
                    sign,
                    profitLoss);
        }
        System.out.println("================================================================================================================");
        
        // Show summary totals
        double totalInvested = calculateTotalInvested();
        double currentValue = calculateCurrentValue();
        double totalProfitLoss = calculateTotalProfitLoss();
        String totalSign = totalProfitLoss > 0 ? "+" : "";

        System.out.printf("Total Capital Invested: $%,.2f\n", totalInvested);
        System.out.printf("Current Portfolio Value: $%,.2f\n", currentValue);
        System.out.printf("Overall Profit/Loss:    %s$%,.2f\n", totalSign, totalProfitLoss);
        System.out.println("================================================================================================================");
    }
}

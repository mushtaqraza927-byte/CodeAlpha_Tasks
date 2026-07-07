import java.util.ArrayList;

/**
 * The User class represents an investor on our stock trading platform.
 * It manages the user's name, cash balance, portfolio holdings, and transaction history.
 */
public class User {
    private String name;
    private double balance;
    private Portfolio portfolio;
    private ArrayList<Transaction> transactionHistory;

    /**
     * Constructor to initialize a user.
     * 
     * @param name           The user's name
     * @param initialBalance The initial cash money available to invest
     */
    public User(String name, double initialBalance) {
        this.name = name;
        this.balance = initialBalance;
        this.portfolio = new Portfolio();
        this.transactionHistory = new ArrayList<>();
    }

    // --- Trading Logic ---

    /**
     * Executes a stock purchase if the user has sufficient cash balance.
     * 
     * @param stock    The Stock to buy
     * @param quantity The number of shares to buy
     * @return true if the purchase was successful, false otherwise
     */
    public boolean buyStock(Stock stock, int quantity) {
        if (quantity <= 0) {
            System.out.println("Error: Quantity must be greater than 0.");
            return false;
        }

        double totalCost = quantity * stock.getCurrentPrice();

        // Check if user has enough cash balance
        if (this.balance < totalCost) {
            System.out.printf("Transaction Rejected: Insufficient balance. Cost: $%,.2f, Balance: $%,.2f\n", 
                    totalCost, this.balance);
            return false;
        }

        // Deduct cash and update portfolio holdings
        this.balance -= totalCost;
        this.portfolio.buyStock(stock, quantity, stock.getCurrentPrice());

        // Log the transaction
        Transaction txn = new Transaction("BUY", stock.getSymbol(), quantity, stock.getCurrentPrice());
        this.transactionHistory.add(txn);

        System.out.printf("Successfully purchased %d shares of %s (%s) for $%,.2f per share.\n", 
                quantity, stock.getName(), stock.getSymbol(), stock.getCurrentPrice());
        System.out.printf("Total Cost: $%,.2f | Remaining Balance: $%,.2f\n", totalCost, this.balance);

        return true;
    }

    /**
     * Executes a stock sale if the user owns enough shares of the stock.
     * 
     * @param stock    The Stock to sell
     * @param quantity The number of shares to sell
     * @return true if the sale was successful, false otherwise
     */
    public boolean sellStock(Stock stock, int quantity) {
        if (quantity <= 0) {
            System.out.println("Error: Quantity must be greater than 0.");
            return false;
        }

        // Check if user owns the stock and has enough shares
        PortfolioItem holding = this.portfolio.getHolding(stock.getSymbol());

        if (holding == null || holding.getQuantity() < quantity) {
            int owned = (holding != null) ? holding.getQuantity() : 0;
            System.out.printf("Transaction Rejected: Insufficient shares. You want to sell %d, but only own %d.\n", 
                    quantity, owned);
            return false;
        }

        double totalRevenue = quantity * stock.getCurrentPrice();

        // Add revenue to balance and update portfolio holdings
        this.balance += totalRevenue;
        this.portfolio.sellStock(stock, quantity);

        // Log the transaction
        Transaction txn = new Transaction("SELL", stock.getSymbol(), quantity, stock.getCurrentPrice());
        this.transactionHistory.add(txn);

        System.out.printf("Successfully sold %d shares of %s (%s) for $%,.2f per share.\n", 
                quantity, stock.getName(), stock.getSymbol(), stock.getCurrentPrice());
        System.out.printf("Amount Earned: $%,.2f | New Cash Balance: $%,.2f\n", totalRevenue, this.balance);

        return true;
    }

    // --- Helper Display Methods ---

    /**
     * Prints a clean log of all transactions done by the user.
     */
    public void displayTransactionHistory() {
        System.out.println("\n============================= TRANSACTION HISTORY =============================");
        if (transactionHistory.isEmpty()) {
            System.out.println(" No transactions recorded yet.");
        } else {
            for (Transaction txn : transactionHistory) {
                System.out.println(txn);
            }
        }
        System.out.println("================================================================================");
    }

    // --- Getters & Setters ---

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public ArrayList<Transaction> getTransactionHistory() {
        return transactionHistory;
    }
}

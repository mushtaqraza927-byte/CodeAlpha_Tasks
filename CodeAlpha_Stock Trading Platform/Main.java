import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * The Main class serves as the entry point and controller of our Stock Trading Platform.
 * It manages the market stock list, user interaction, CLI menu, and input validation.
 */
public class Main {
    // File name where portfolio data will be saved
    private static final String DATA_FILE = "portfolio_data.txt";
    
    // Market Stock List
    private static ArrayList<Stock> market = new ArrayList<>();
    
    // Random object for simulating stock price fluctuations
    private static Random random = new Random();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // 1. Initialize stock market with a few predefined stocks
        initializeMarket();

        System.out.println("=================================================");
        System.out.println("     Welcome to the Stock Trading Platform!     ");
        System.out.println("=================================================");
        
        // 2. Prompt for user's name
        System.out.print("Enter your name to begin: ");
        String userName = scanner.nextLine().trim();
        if (userName.isEmpty()) {
            userName = "Investor";
        }

        // 3. Create a User with $10,000 cash balance
        User user = new User(userName, 10000.00);

        // 4. Load saved portfolio and cash data if file exists
        FileHandler.loadData(user, market, DATA_FILE);

        // 5. Main Application Menu Loop
        boolean running = true;
        while (running) {
            printMenu(user);
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    displayMarket();
                    break;
                case "2":
                    buyStockMenu(scanner, user);
                    break;
                case "3":
                    sellStockMenu(scanner, user);
                    break;
                case "4":
                    user.getPortfolio().displayPortfolio();
                    break;
                case "5":
                    user.displayTransactionHistory();
                    break;
                case "6":
                    simulatePriceChanges();
                    break;
                case "7":
                    System.out.println("\nSaving your portfolio data...");
                    FileHandler.saveData(user, DATA_FILE);
                    System.out.println("Thank you for using the Stock Trading Platform. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option! Please enter a number between 1 and 7.");
            }
        }
        
        scanner.close();
    }

    /**
     * Fills the stock market with default blue-chip stocks.
     */
    private static void initializeMarket() {
        market.add(new Stock("Apple Inc.", "AAPL", 175.50));
        market.add(new Stock("Alphabet Inc. (Google)", "GOOGL", 150.25));
        market.add(new Stock("Tesla Inc.", "TSLA", 220.80));
        market.add(new Stock("Microsoft Corp.", "MSFT", 380.10));
        market.add(new Stock("Nvidia Corp.", "NVDA", 485.60));
    }

    /**
     * Prints the primary user interface menu showing account overview.
     */
    private static void printMenu(User user) {
        System.out.println("\n====================== MAIN MENU ======================");
        System.out.printf(" Investor: %-20s Cash Balance: $%,.2f\n", user.getName(), user.getBalance());
        System.out.println("-------------------------------------------------------");
        System.out.println(" 1. View Market Stocks");
        System.out.println(" 2. Buy Stock");
        System.out.println(" 3. Sell Stock");
        System.out.println(" 4. View Portfolio Performance");
        System.out.println(" 5. View Transaction History");
        System.out.println(" 6. Simulate Market Price Changes");
        System.out.println(" 7. Save and Exit");
        System.out.println("=======================================================");
    }

    /**
     * Displays all available stocks on the market in a clean layout.
     */
    private static void displayMarket() {
        System.out.println("\n==================== CURRENT STOCK MARKET ====================");
        System.out.printf("%-10s %-25s %-15s\n", "Symbol", "Company Name", "Current Price");
        System.out.println("--------------------------------------------------------------");
        for (Stock stock : market) {
            System.out.printf("%-10s %-25s $%-14.2f\n", 
                    stock.getSymbol(), stock.getName(), stock.getCurrentPrice());
        }
        System.out.println("==============================================================");
    }

    /**
     * Prompts the user to buy a stock.
     */
    private static void buyStockMenu(Scanner scanner, User user) {
        System.out.println("\n--- BUY STOCK ---");
        System.out.print("Enter stock ticker symbol (e.g. AAPL): ");
        String symbol = scanner.nextLine().trim().toUpperCase();

        Stock stock = findStock(symbol);
        if (stock == null) {
            System.out.println("Stock symbol not found in the market. Check spelling and try again!");
            return;
        }

        System.out.printf("Selected: %s | Current Price: $%,.2f\n", stock.getName(), stock.getCurrentPrice());
        System.out.print("Enter quantity to buy: ");
        String qtyInput = scanner.nextLine().trim();

        try {
            int quantity = Integer.parseInt(qtyInput);
            user.buyStock(stock, quantity);
        } catch (NumberFormatException e) {
            System.out.println("Invalid quantity! Please enter a whole number.");
        }
    }

    /**
     * Prompts the user to sell a stock.
     */
    private static void sellStockMenu(Scanner scanner, User user) {
        System.out.println("\n--- SELL STOCK ---");
        System.out.print("Enter stock ticker symbol (e.g. AAPL): ");
        String symbol = scanner.nextLine().trim().toUpperCase();

        Stock stock = findStock(symbol);
        if (stock == null) {
            System.out.println("Stock symbol not found in the market.");
            return;
        }

        PortfolioItem holding = user.getPortfolio().getHolding(symbol);
        if (holding == null) {
            System.out.println("You do not own any shares of " + symbol + "!");
            return;
        }

        System.out.printf("You own %d shares of %s. Current Price: $%,.2f\n", 
                holding.getQuantity(), stock.getName(), stock.getCurrentPrice());
        System.out.print("Enter quantity to sell: ");
        String qtyInput = scanner.nextLine().trim();

        try {
            int quantity = Integer.parseInt(qtyInput);
            user.sellStock(stock, quantity);
        } catch (NumberFormatException e) {
            System.out.println("Invalid quantity! Please enter a whole number.");
        }
    }

    /**
     * Simulates stock price movements by generating random percent changes
     * for every stock in the market.
     */
    private static void simulatePriceChanges() {
        System.out.println("\n--- SIMULATING MARKET PRICE CHANGES ---");
        System.out.printf("%-10s %-15s %-15s %-10s\n", "Symbol", "Old Price", "New Price", "Change %");
        System.out.println("-------------------------------------------------------");

        for (Stock stock : market) {
            double oldPrice = stock.getCurrentPrice();
            
            // Generate a random double between -8.0% and +8.0%
            double percentChange = -8.0 + (random.nextDouble() * 16.0);
            double newPrice = oldPrice * (1.0 + (percentChange / 100.0));
            
            // Round to 2 decimal places
            newPrice = Math.round(newPrice * 100.0) / 100.0;
            
            // Ensure stock price doesn't drop to 0
            if (newPrice < 1.00) {
                newPrice = 1.00;
            }

            stock.setCurrentPrice(newPrice);

            String changeSign = percentChange >= 0 ? "+" : "";
            System.out.printf("%-10s $%-14.2f $%-14.2f %s%.2f%%\n", 
                    stock.getSymbol(), oldPrice, newPrice, changeSign, percentChange);
        }
        System.out.println("-------------------------------------------------------");
        System.out.println("Market updated. Portfolio values will adjust accordingly.");
    }

    /**
     * Helper method to search the market list for a stock by symbol.
     */
    private static Stock findStock(String symbol) {
        for (Stock stock : market) {
            if (stock.getSymbol().equalsIgnoreCase(symbol)) {
                return stock;
            }
        }
        return null;
    }
}

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The FileHandler class handles loading and saving investor data 
 * to and from a local text file.
 * 
 * This enables simple persistence (data is saved even when the program closes).
 * It uses standard beginner-friendly Java IO classes like File, FileReader,
 * FileWriter, BufferedReader, and BufferedWriter.
 */
public class FileHandler {

    /**
     * Saves the user's balance and portfolio holdings to a text file.
     * Format:
     * Line 1: BALANCE: <double>
     * Remaining lines: <Symbol>,<Quantity>,<AveragePurchasePrice>
     * 
     * @param user     The User object whose data is to be saved
     * @param filepath The filename/path to save to
     */
    public static void saveData(User user, String filepath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepath))) {
            // 1. Save cash balance
            writer.write("BALANCE:" + user.getBalance());
            writer.newLine();

            // 2. Save each stock holding in the portfolio
            for (PortfolioItem item : user.getPortfolio().getHoldings()) {
                writer.write(item.getStock().getSymbol() + "," 
                           + item.getQuantity() + "," 
                           + item.getAveragePurchasePrice());
                writer.newLine();
            }
            
            System.out.println("Data saved successfully to " + filepath);
        } catch (IOException e) {
            System.out.println("Error saving portfolio data: " + e.getMessage());
        }
    }

    /**
     * Loads the user's balance and portfolio holdings from a text file.
     * Reconstructs the portfolio by matching symbols with stocks in the market.
     * 
     * @param user     The User object to load data into
     * @param market   The list of available market Stocks
     * @param filepath The filename/path to load from
     */
    public static void loadData(User user, ArrayList<Stock> market, String filepath) {
        File file = new File(filepath);
        if (!file.exists()) {
            // File does not exist yet (first time running), so nothing to load.
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }

                if (line.startsWith("BALANCE:")) {
                    // Parse balance value
                    double loadedBalance = Double.parseDouble(line.substring(8));
                    user.setBalance(loadedBalance);
                } else {
                    // Parse stock holding: Symbol,Quantity,AveragePurchasePrice
                    String[] parts = line.split(",");
                    if (parts.length == 3) {
                        String symbol = parts[0];
                        int quantity = Integer.parseInt(parts[1]);
                        double avgPrice = Double.parseDouble(parts[2]);

                        // Find corresponding Stock object in market
                        Stock marketStock = findStockInMarket(symbol, market);
                        if (marketStock != null) {
                            // Load holding directly into user's portfolio
                            user.getPortfolio().buyStock(marketStock, quantity, avgPrice);
                        }
                    }
                }
            }
            System.out.println("Data loaded successfully from " + filepath);
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error loading portfolio data or file corrupted. Starting fresh. Error: " + e.getMessage());
        }
    }

    /**
     * Helper method to find a stock in the market list by its symbol.
     */
    private static Stock findStockInMarket(String symbol, ArrayList<Stock> market) {
        for (Stock stock : market) {
            if (stock.getSymbol().equalsIgnoreCase(symbol)) {
                return stock;
            }
        }
        return null;
    }
}

import java.util.Scanner;

/**
 * The Main class serves as the entry point of the application.
 * It lets the user choose whether they want to run the chatbot in the
 * terminal/console or open a graphical window (Swing GUI).
 */
public class Main {

    /**
     * The main method that the Java Virtual Machine (JVM) calls to start the program.
     *
     * @param args Command-line arguments (not used here).
     */
    public static void main(String[] args) {
        // Create a Scanner object to get user choice for the run mode
        Scanner selectionScanner = new Scanner(System.in);
        
        System.out.println("================================================");
        System.out.println("       Welcome to SimpleBot Launcher!          ");
        System.out.println("================================================");
        System.out.println("Please select how you would like to chat:");
        System.out.println("1. Console Mode (Text inside the terminal)");
        System.out.println("2. GUI Mode (Graphical window popup)");
        System.out.print("Enter your choice (1 or 2): ");

        // Read user input
        String choice = selectionScanner.nextLine().trim();

        // Evaluate the choice
        if (choice.equals("1")) {
            System.out.println("\nStarting Console Mode...\n");
            // Instantiate our ChatBot class (OOP)
            ChatBot consoleBot = new ChatBot();
            // Start the interactive console chat loop
            consoleBot.start();
        } 
        else if (choice.equals("2")) {
            System.out.println("\nLaunching Graphical GUI Mode...");
            // Call the static helper method in ChatBotGUI to show the window
            ChatBotGUI.launch();
        } 
        else {
            // Fallback for invalid selections
            System.out.println("\nInvalid choice. Launching Console Mode by default...\n");
            ChatBot consoleBot = new ChatBot();
            consoleBot.start();
        }

        // We close selectionScanner if we didn't start the console bot. 
        // Note: inside ChatBot.java, we close the scanner used there, 
        // so we don't need to close it here if consoleBot runs (to avoid closing System.in prematurely).
    }
}

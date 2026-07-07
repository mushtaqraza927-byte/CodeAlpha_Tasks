import java.util.Scanner;

/**
 * The ChatBot class coordinates the console-based chat loop.
 * It manages reading user inputs, processing them, getting responses from
 * ResponseManager, and displaying those responses back to the user.
 */
public class ChatBot {
    
    // We instantiate the ResponseManager to act as our bot's logic handler
    private final ResponseManager responseManager;

    /**
     * Constructor for the ChatBot class.
     * Initializes the ResponseManager.
     */
    public ChatBot() {
        this.responseManager = new ResponseManager();
    }

    /**
     * Starts the main chat loop.
     * Continuously prompts the user for input until they type "exit".
     */
    public void start() {
        // Create a Scanner object to read input from the console
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("================================================");
        System.out.println("   Welcome to SimpleBot (Console Chat Mode)     ");
        System.out.println("   Type 'exit' at any time to leave the chat.   ");
        System.out.println("================================================");
        System.out.println("SimpleBot: Hello! I am an AI-inspired chatbot.");
        System.out.println("SimpleBot: Ask me anything! (e.g., 'What is Java?', 'What time is it?')");

        // The running flag controls our continuous chat loop
        boolean running = true;

        while (running) {
            // 1. Get input from the user
            String rawInput = getUserInput(scanner);

            // 2. Clean/process the input (remove extra spaces, make lowercase)
            String processedInput = processInput(rawInput);

            // Check if the user wants to quit
            if (processedInput.equalsIgnoreCase("exit")) {
                running = false;
                displayResponse("Goodbye! Have a great day studying programming!");
            } else {
                // 3. Generate a response using the ResponseManager
                String response = generateResponse(processedInput);

                // 4. Display the response back to the user
                displayResponse(response);
            }
        }
        
        // Close the scanner resource when the chat ends
        scanner.close();
    }

    /**
     * Reads a line of input typed by the user in the console.
     *
     * @param scanner The Scanner object reading from System.in.
     * @return The raw user input String.
     */
    private String getUserInput(Scanner scanner) {
        System.out.print("\nYou: ");
        return scanner.nextLine();
    }

    /**
     * Processes raw user input by converting it to lowercase and removing 
     * extra spaces from both ends (trimming).
     *
     * @param rawInput The raw input from the console.
     * @return The cleaned, lowercase input.
     */
    private String processInput(String rawInput) {
        if (rawInput == null) {
            return "";
        }
        // convert to lowercase and remove leading/trailing whitespace
        return rawInput.toLowerCase().trim();
    }

    /**
     * Generates a response by passing the clean input to the ResponseManager.
     *
     * @param processedInput The processed (lowercase, trimmed) user message.
     * @return The reply message from the ResponseManager.
     */
    private String generateResponse(String processedInput) {
        return responseManager.getResponse(processedInput);
    }

    /**
     * Prints the chatbot's response to the console.
     *
     * @param response The message to print.
     */
    private void displayResponse(String response) {
        System.out.println("SimpleBot: " + response);
    }
}

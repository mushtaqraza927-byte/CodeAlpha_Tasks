import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The ChatBotGUI class provides a simple graphical user interface (GUI) using Swing.
 * This class allows the user to interact with the chatbot in a windowed application.
 * It demonstrates how the same ResponseManager can be reused for both console and GUI views (OOP principle of reuse).
 */
public class ChatBotGUI extends JFrame {

    // GUI Components
    private JTextArea chatArea;
    private JTextField inputField;
    private JButton sendButton;
    
    // Core logic runner
    private final ResponseManager responseManager;

    /**
     * Constructor for ChatBotGUI.
     * Sets up the window layout, styles, and event listeners.
     */
    public ChatBotGUI() {
        // Initialize the logic manager
        this.responseManager = new ResponseManager();

        // 1. Setup basic window properties
        setTitle("SimpleBot - Java AI-Inspired Chatbot");
        setSize(450, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centers the window on the screen
        setLayout(new BorderLayout(10, 10)); // Gives layout margins

        // 2. Create the chat display area (non-editable, scrollable)
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true); // Wraps text to the next line when it reaches the edge
        chatArea.setWrapStyleWord(true); // Wraps at word boundaries rather than characters
        chatArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        chatArea.setMargin(new Insets(10, 10, 10, 10)); // Adds padding inside the text box

        // Wrap the chat area in a scroll pane to enable scrolling
        JScrollPane scrollPane = new JScrollPane(chatArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder()); // Removes ugly borders

        // 3. Create the input area panel (holds text field and button)
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout(5, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10)); // Bottom & side margins

        // Input text field
        inputField = new JTextField();
        inputField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        
        // Send button
        sendButton = new JButton("Send");
        sendButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        sendButton.setBackground(new Color(70, 130, 180)); // Soft blue color
        sendButton.setForeground(Color.WHITE); // White text
        sendButton.setFocusPainted(false); // Removes the focus border outline

        // Add input field and send button to the input panel
        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);

        // 4. Assemble the components in the frame
        add(scrollPane, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);

        // 5. Setup Action Listeners (what happens when the user clicks 'Send' or presses Enter)
        ActionListener sendListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSendAction();
            }
        };

        // Bind the listener to both button click and pressing Enter in the input field
        sendButton.addActionListener(sendListener);
        inputField.addActionListener(sendListener);

        // 6. Display a welcome message in the chat area
        chatArea.append("SimpleBot: Hello! I am an AI-inspired chatbot.\n");
        chatArea.append("SimpleBot: Ask me anything! Or type 'exit' to quit.\n");
        chatArea.append("---------------------------------------------------------------------------\n");
    }

    /**
     * Handles reading user input from the text field, processing it, getting 
     * a response from ResponseManager, and updating the GUI chat log.
     */
    private void handleSendAction() {
        // Read text from input field
        String rawInput = inputField.getText();
        
        // Trim spaces and convert to lowercase for processing
        String processedInput = rawInput.trim().toLowerCase();

        // If the input is empty, do nothing
        if (processedInput.isEmpty()) {
            return;
        }

        // Display user message in the chat log
        chatArea.append("You: " + rawInput + "\n");

        // Clear the input text field so the user can type the next message
        inputField.setText("");

        // Check if the user wants to quit
        if (processedInput.equalsIgnoreCase("exit")) {
            chatArea.append("SimpleBot: Goodbye! Closing window...\n");
            // Set a timer to close the window after 1.5 seconds so they see the goodbye message
            Timer timer = new Timer(1500, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });
            timer.setRepeats(false);
            timer.start();
        } else {
            // Get response from our OOP ResponseManager
            String response = responseManager.getResponse(processedInput);
            
            // Display bot response in the chat log
            chatArea.append("SimpleBot: " + response + "\n");
            chatArea.append("---------------------------------------------------------------------------\n");
        }

        // Auto-scroll to the bottom of the chat log
        chatArea.setCaretPosition(chatArea.getDocument().getLength());
    }

    /**
     * Helper method to initialize and show the GUI window on the Swing Event Dispatch Thread (EDT).
     */
    public static void launch() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ChatBotGUI gui = new ChatBotGUI();
                gui.setVisible(true);
            }
        });
    }
}

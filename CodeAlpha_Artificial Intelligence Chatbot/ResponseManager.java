import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * The ResponseManager class is responsible for evaluating user input and generating
 * appropriate replies. It acts as the chatbot's "brain" using a rule-based system
 * of keyword matching.
 */
public class ResponseManager {

    /**
     * Generates a response based on the processed user input.
     * Uses simple string matching methods to identify keywords.
     *
     * @param input The clean, lowercase user message without leading/trailing spaces.
     * @return The chatbot's reply.
     */
    public String getResponse(String input) {
        // If the user didn't enter anything, ask them to say something.
        if (input.isEmpty()) {
            return "Please type something! I am listening.";
        }

        // Rule 1: Greeting responses
        if (input.contains("hello") || input.contains("hi") || input.contains("hey")) {
            return "Hello there! How can I help you today?";
        }

        // Rule 2: How are you?
        else if (input.contains("how are you") || input.contains("how's it going")) {
            return "I'm doing great, thank you for asking! I am ready to answer your questions.";
        }

        // Rule 3: Chatbot's name
        else if (input.contains("your name") || input.contains("who are you")) {
            return "My name is SimpleBot. I am a beginner-friendly Java chatbot!";
        }

        // Rule 4: What is Java?
        else if (input.contains("what is java")) {
            return "Java is a popular, high-level, object-oriented programming language created in 1995. " +
                   "It is famous for its 'Write Once, Run Anywhere' (WORA) capability.";
        }

        // Rule 5: Who created Java?
        else if (input.contains("who created java") || input.contains("creator of java")) {
            return "Java was created by James Gosling while working at Sun Microsystems (which was later acquired by Oracle).";
        }

        // Rule 6: What is OOP?
        else if (input.contains("what is oop") || input.contains("object oriented")) {
            return "OOP stands for Object-Oriented Programming. It is a programming model based on the concept " +
                   "of 'objects' which contain data (attributes) and code (methods). The four main pillars are " +
                   "Inheritance, Polymorphism, Encapsulation, and Abstraction.";
        }

        // Rule 7: What is AI?
        else if (input.contains("what is ai") || input.contains("artificial intelligence")) {
            return "AI, or Artificial Intelligence, is the simulation of human intelligence by computers. " +
                   "While I look like an AI, I am actually a simple rule-based program using keyword matching!";
        }

        // Rule 8: What time is it?
        else if (input.contains("time") || input.contains("current time")) {
            // Get the current system time
            LocalTime now = LocalTime.now();
            // Format the time using HH:mm:ss (Hours:Minutes:Seconds)
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            String formattedTime = now.format(formatter);
            return "The current system time is: " + formattedTime;
        }

        // Rule 9: Thank you responses
        else if (input.contains("thank you") || input.contains("thanks")) {
            return "You're very welcome! I'm happy to help.";
        }

        // Rule 10: Goodbye responses
        else if (input.contains("goodbye") || input.contains("bye")) {
            return "Goodbye! Have a wonderful day studying Java!";
        }

        // Default response: If no keywords are matched
        else {
            return "Sorry, I don't understand that. Please ask another question, or type 'exit' to quit.";
        }
    }
}

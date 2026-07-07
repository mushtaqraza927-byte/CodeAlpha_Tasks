# 🤖 Artificial Intelligence Chatbot (SimpleBot)

[![Java Version](https://img.shields.io/badge/Java-17%2B-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![GUI Framework](https://img.shields.io/badge/GUI-Swing-orange?style=for-the-badge)](https://docs.oracle.com/javase/tutorial/uiswing/)
[![License](https://img.shields.io/badge/License-MIT-green.svg?style=for-the-badge)](https://opensource.org/licenses/MIT)
[![Build](https://img.shields.io/badge/Build-Passed-success?style=for-the-badge)](#)

A versatile, rule-based chatbot application featuring dual user interface modes: a classic command-line console session and a rich, modern Swing-based Graphical User Interface (GUI).

---

## 📌 Table of Contents
1. [Project Description](#-project-description)
2. [Objective](#-objective)
3. [Features](#-features)
4. [Technologies Used](#-technologies-used)
5. [Java Concepts Implemented](#-java-concepts-implemented)
6. [Project Structure](#-project-structure)
7. [Installation & Prerequisites](#-installation--prerequisites)
8. [How to Build and Run](#-how-to-build-and-run)
9. [Sample Usage](#-sample-usage)
10. [Screenshots](#-screenshots)
11. [Future Enhancements](#-future-enhancements)
12. [Learning Outcomes](#-learning-outcomes)
13. [Author](#-author)
14. [License](#-license)

---

## 📝 Project Description
The **Artificial Intelligence Chatbot** (SimpleBot) serves as an interactive learning tool and a practical helper bot. Leveraging a robust rule-based keyword-matching logic engine, the bot identifies questions regarding Java programming, Object-Oriented Programming (OOP), general computer science, and real-time clock data. The core engine is decoupled from the representation layer, allowing the exact same bot "brain" to run synchronously inside a terminal environment or asynchronously inside a window-based GUI.

---

## 🎯 Objective
- **Demonstrate UI Versatility:** Show how the same backend logic (`ResponseManager`) can support completely different front-end representations (Console CLI vs. Desktop GUI).
- **Simulate Basic NLP:** Implement rule-based NLP triggers using string tokenization and keyword mapping.
- **Learn Event-Driven Programming:** Gain practical experience building interactive Swing components and handling user action inputs dynamically.

---

## ✨ Features
* **Dual Run Modes:** Select Console (CLI) mode or Graphical (GUI) mode upon launch.
* **Intelligent Keyword Engine:** Detects intents and returns customized, relevant knowledge responses.
* **Dynamic Time Fetching:** Synchronizes with system hardware to return the exact current system time (`HH:mm:ss`) upon user request.
* **Sleek Desktop GUI:**
  * Clean, borderless scrolling pane for chat history.
  * Word and line wraps for a conversational layout.
  * Styled modern elements (soft blue accents, specific user vs. bot labels).
  * Auto-scrolling viewport focus shifts to the latest message.
* **Polished Session Exiting:** Includes a Swing Timer to display a warm farewell message before automatically closing the window in GUI mode.

---

## 💻 Technologies Used
* **Language:** Java SE (Standard Edition)
* **SDK:** Version 17 or higher
* **GUI API:** Java Foundation Classes Swing (`javax.swing.*`), Abstract Window Toolkit (`java.awt.*`)
* **Time API:** Java Date-Time utilities (`java.time.LocalTime`, `java.time.format.DateTimeFormatter`)

---

## 🧠 Java Concepts Implemented

### 1. Object-Oriented Programming (OOP)
* **Inheritance:** `ChatBotGUI` inherits directly from `JFrame` to extend graphical window capabilities.
* **Separation of Concerns (MVC-lite):** Decouples core conversation parsing logic (`ResponseManager` - Model) from CLI (`ChatBot` - View) and GUI (`ChatBotGUI` - View/Controller) structures.
* **Reusability:** Reuses the exact same `ResponseManager` instance in both CLI and GUI pipelines without rewriting rule trees.

### 2. Graphical User Interface (GUI) & Event Handling
* **Layout Managers:** Uses `BorderLayout` to organize panels and components cleanly.
* **Swing Components:** Integrates `JTextArea`, `JTextField`, `JButton`, and `JScrollPane` to provide standard chat interface flows.
* **Action Listeners:** Uses anonymous inner class event handlers to respond to send-triggers (both button clicks and pressing the `Enter` key on the input keyboard).
* **EDT Thread Safety:** Utilizes `SwingUtilities.invokeLater()` to launch the GUI securely on the Event Dispatch Thread, preventing concurrent paint errors.

### 3. Exception Handling
* **Resource Leak Prevention:** Closes CLI `Scanner` inputs during normal loop terminations.
* **Graceful GUI System Shutdown:** Binds closing parameters (`JFrame.EXIT_ON_CLOSE`) and implements system delay timers safely to avoid background execution hang-ups.

### 4. Collections Framework
* **N/A:** SimpleBot leverages localized condition arrays and conditional control blocks. It is designed to demonstrate basic matching rules without complex collection maps for accessibility.

### 5. Multithreading & Timers
* **Swing Timer:** Implements an asynchronous `javax.swing.Timer` to create a 1.5-second delayed thread task. This allows the GUI thread to render the goodbye text before terminating `System.exit(0)`.

---

## 📂 Project Structure
```text
CodeAlpha_Artificial Intelligence Chatbot/
│
├── .idea/                           # IDE Configuration Folder
├── out/                             # Compiled Java Class Bytecode Target
├── ChatBot.class                    # Compiled Bytecode for Console Coordinator
├── ChatBot.java                     # Console-based Chat Loop Manager
├── ChatBotGUI$1.class               # Compiled Anonymous Listener classes
├── ChatBotGUI$2.class               
├── ChatBotGUI$3.class               
├── ChatBotGUI.class                 # Compiled Bytecode for GUI Window
├── ChatBotGUI.java                  # Swing GUI Implementation and Window Setup
├── Main.class                       # Compiled Launcher Bytecode
├── Main.java                        # Entry Point (Launcher script for console/GUI mode)
├── ResponseManager.class            # Compiled Parsing Brain Bytecode
├── ResponseManager.java             # Conversational logic parsing and keyword mapping
└── Task-03.iml                      # IntelliJ Project Metadata
```

---

## ⚙️ Installation & Prerequisites
1. **JDK 17+:** Verify Java development kit installation.
2. **Graphical Environment:** A desktop environment capable of launching GUI windows (X11 forwarding, Windows, macOS Desktop).

---

## 🛠️ How to Build and Run

### Step 1: Compile all source files
Compile the entry point launcher and its dependencies:
```bash
javac Main.java ChatBot.java ChatBotGUI.java ResponseManager.java
```

### Step 2: Launch the application
Run the launcher class file:
```bash
java Main
```
You will be prompted to choose a mode:
```text
================================================
       Welcome to SimpleBot Launcher!          
================================================
Please select how you would like to chat:
1. Console Mode (Text inside the terminal)
2. GUI Mode (Graphical window popup)
Enter your choice (1 or 2): 
```

---

## 💡 Sample Usage

### 💬 Console Mode Interaction
```text
Starting Console Mode...

================================================
   Welcome to SimpleBot (Console Chat Mode)     
   Type 'exit' at any time to leave the chat.   
================================================
SimpleBot: Hello! I am an AI-inspired chatbot.
SimpleBot: Ask me anything! (e.g., 'What is Java?', 'What time is it?')

You: What is Java?
SimpleBot: Java is a popular, high-level, object-oriented programming language created in 1995. It is famous for its 'Write Once, Run Anywhere' (WORA) capability.

You: What time is it?
SimpleBot: The current system time is: 22:15:30

You: exit
SimpleBot: Goodbye! Have a great day studying programming!
```

---

## 🖼️ Screenshots
*(Visual previews of Console CLI and Swing GUI Chat interfaces)*

| Dual Mode Launcher Menu | Graphical Swing Interface |
| :---: | :---: |
| ![CLI Launch Mode Menu](https://via.placeholder.com/400x250?text=CLI+Launch+Mode+Selector) | ![Desktop Chatbot GUI Window](https://via.placeholder.com/400x250?text=Desktop+Swing+Chat+Interface) |

---

## 🚀 Future Enhancements
- [ ] **Hash Mapping Responses:** Replace the cascading `if-else` matching system in `ResponseManager` with a `HashMap<String, String>` mapping for standard lookups.
- [ ] **API Integration:** Integrate a web API to fetch live weather forecasts or Wikipedia summaries.
- [ ] **Regex Matching:** Leverage Java's `java.util.regex` patterns to improve parsing flexibility.
- [ ] **Dark Mode GUI:** Implement a custom look-and-feel stylesheet supporting light/dark theme switches.

---

## 🎓 Learning Outcomes
- Understood and applied the MVC (Model-View-Controller) programming pattern by separating UI rendering from response evaluation.
- Mastered Java Swing window layout, custom coloring, text wrapping, and caret positioning.
- Handled UI threads safely via the Event Dispatch Thread (EDT) and utilized `javax.swing.Timer` for asynchronous delays.
- Strengthened string search capability using case-insensitive token lookups.

---

## 👤 Author
* **Mushtaq Raza**
* **GitHub Profile:** [mushtaqraza927](https://github.com/mushtaqraza927)

---

## 📄 License
This project is licensed under the MIT License - see the [LICENSE](https://opensource.org/licenses/MIT) page for details.

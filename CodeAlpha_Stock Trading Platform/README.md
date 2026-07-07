# 📈 Stock Trading Platform

[![Java Version](https://img.shields.io/badge/Java-17%2B-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![Data Persistence](https://img.shields.io/badge/Persistence-File%20I%2FO-brightgreen?style=for-the-badge)](https://docs.oracle.com/javase/tutorial/essential/io/)
[![Collections Framework](https://img.shields.io/badge/Collections-ArrayList-blueviolet?style=for-the-badge)](https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html)
[![License](https://img.shields.io/badge/License-MIT-green.svg?style=for-the-badge)](https://opensource.org/licenses/MIT)

A console-based stock trading simulation platform allowing users to buy and sell market stocks, track dynamic portfolio values, monitor transaction logs, and automatically persist data to disk.

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
The **Stock Trading Platform** simulates the operations of a brokerage account. Investors start with a virtual cash balance (e.g., $10,000.00) and can trade stock listings (like Apple, Tesla, Google, Microsoft, and Amazon). The system recalculates weighted averages upon purchasing additional shares of existing holdings, manages cash flows, generates formatted transaction receipts, and handles data persistence. By saving balances and holding profiles to `portfolio_data.txt`, users can shut down the simulator and resume trading later without losing their progress.

---

## 🎯 Objective
- **Simulate Real Financial Operations:** Implement standard trading mathematics, including cash deductions, share volume requirements, and weighted average price adjustments.
- **Master Data Persistence:** Leverage Java I/O streams to persist user financials and stock assets securely to a local file.
- **Leverage Object-Oriented Design:** Design a domain model where each real-world concept (investor, stock, portfolio, transactions) maps to a distinct class.

---

## ✨ Features
* **Real-time Portfolio Tabular Display:** Displays active stock names, symbols, volumes owned, purchase averages, current prices, cost bases, market values, and individual net profits/losses.
* **Weighted-Average Purchase Pricing:** Buying additional shares of a stock recalculates the average buy-in cost:
  $$\text{New Average} = \frac{(\text{Current Qty} \times \text{Old Price}) + (\text{New Qty} \times \text{New Price})}{\text{Current Qty} + \text{New Qty}}$$
* **Transactional Auditing:** Generates receipt logs for every buy/sell action, timestamped with date and time of the execution.
* **Automated Serialization:** 
  * Automatically loads cash balances and holdings upon startup.
  * Automatically saves data to disk whenever a transaction occurs.
* **Simulated Market Fluctuations:** Built-in routine to simulate random market shifts, allowing testing of financial outcomes.

---

## 💻 Technologies Used
* **Language:** Java SE (Standard Edition)
* **SDK:** Version 17 or higher
* **File Persistence API:** Java I/O package (`java.io.*`)
* **Core Utilities:** Standard collections (`java.util.ArrayList`, `java.util.Random`), Time library (`java.util.Date`, `java.text.SimpleDateFormat`)

---

## 🧠 Java Concepts Implemented

### 1. Object-Oriented Programming (OOP)
* **Domain Model Separation:** Separates system duties across dedicated classes:

| Class | Purpose |
| :--- | :--- |
| `Stock` | Holds ticker details, stock names, and current prices. |
| `PortfolioItem` | Tracks volume, average price, total cost, and profit/loss calculation for a specific stock. |
| `Portfolio` | Aggregates holdings and calculates overall net worth. |
| `Transaction` | Captures receipt metadata (symbol, timestamp, buy/sell flags, quantity, totals). |
| `User` | Manages investor profile, cash balances, and logs histories. |
| `FileHandler` | Manages file serialization logic. |

* **Data Encapsulation:** Member variables are declared `private` and accessed via standard getter/setter routines. Setters include safety constraints (e.g., preventing negative stock pricing).

### 2. Collections Framework
* **Dynamic Collections:** Employs `ArrayList<T>` instead of raw fixed arrays to allow dynamic resizing:
  * `ArrayList<PortfolioItem>`: Holds a variable list of stock investments.
  * `ArrayList<Transaction>`: Logs transaction history records dynamically.
  * `ArrayList<Stock>`: Tracks listings available on the market database.

### 3. File I/O
* **Buffered Streams:** Implements `BufferedReader` and `BufferedWriter` wrapped around `FileReader` and `FileWriter` to optimize file read/write operations.
* **CSV Data Structuring:** Writes fields in structured text formats separated by commas, parsed using the `.split(",")` method upon load.

### 4. Exception Handling
* **Try-With-Resources:** Utilizes automatic resource management to close file streams safely, avoiding memory leaks.
* **Multi-Catch Strategy:** Intercepts `IOException` (for access errors) and `NumberFormatException` (for corrupted numerical balances) to prevent runtime failures.

### 5. Multithreading
* **N/A:** Synchronous CLI execution thread.

---

## 📂 Project Structure
```text
CodeAlpha_Stock Trading Platform/
│
├── .idea/                           # IDE Configuration Folder
├── out/                             # Compiled Java Class Bytecode Target
├── FileHandler.class                # Compiled Bytecode for File Management
├── FileHandler.java                 # Serializes and deserializes balances & holdings
├── Main.class                       # Main App Runner Bytecode
├── Main.java                        # Runs the interactive trading menu loops
├── Portfolio.class                  # Compiled Portfolio Manager Bytecode
├── Portfolio.java                   # Aggregates individual holdings & calculates margins
├── PortfolioItem.class              # Compiled Portfolio Asset Bytecode
├── PortfolioItem.java               # Represents stock assets owned by the investor
├── Stock.class                      # Compiled Stock Bytecode
├── Stock.java                       # Represents company stock tickers & price values
├── Task-02.iml                      # IntelliJ Project Metadata
├── Transaction.class                # Compiled Transaction Log Bytecode
├── Transaction.java                 # Records timestamps, trade volumes, and receipt lines
├── User.class                       # Compiled User Entity Bytecode
├── User.java                        # Coordinates user account, cash flow, and portfolios
└── portfolio_data.txt               # Local flat-file database (generated at run-time)
```

---

## ⚙️ Installation & Prerequisites
1. **JDK 17+:** Ensure Java runtime is active.
2. **Read/Write Permissions:** Ensure terminal session has disk access in its workspace folder.

---

## 🛠️ How to Build and Run

### Step 1: Compile all source files
```bash
javac Main.java Stock.java PortfolioItem.java Transaction.java Portfolio.java User.java FileHandler.java
```

### Step 2: Run the application launcher
```bash
java Main
```

---

## 💡 Sample Usage

```text
==================================================
      Welcome to the Stock Trading Platform!      
==================================================
Enter your name to load your profile: Mushtaq
Data loaded successfully from portfolio_data.txt

--- PROFILE DETAILS ---
Investor Name: Mushtaq
Cash Balance : $10,500.00

================ MENU ================
1. View Market Stocks & Prices
2. Buy Stocks
3. Sell Stocks
4. View My Portfolio
5. View Transaction History
6. Simulate Market Fluctuations
7. Exit
======================================
Enter your choice: 4

================================================ YOUR PORTFOLIO ================================================
Stock Name      Symbol     Qty Owned  Avg Buy Price   Current Price   Total Cost      Current Value   Profit/Loss
----------------------------------------------------------------------------------------------------------------
Apple Inc.      AAPL       10         $175.00        $180.00        $1,750.00      $1,800.00      +$50.00
Tesla Inc.      TSLA       5          $210.00        $195.00        $1,050.00      $975.00        -$75.00
================================================================================================================
Total Capital Invested: $2,800.00
Current Portfolio Value: $2,775.00
Overall Profit/Loss:    -$25.00
================================================================================================================
```

---

## 🖼️ Screenshots
*(Visual layouts of the command-line stock tracker)*

| Market Quotes View | Portfolio Summary Table |
| :---: | :---: |
| ![CLI Market Quote list](https://via.placeholder.com/400x250?text=CLI+Market+Tickers+Grid) | ![CLI Detailed Portfolio](https://via.placeholder.com/400x250?text=CLI+Asset+Portfolio+Report) |

---

## 🚀 Future Enhancements
- [ ] **Graphical Swing Dashboard:** Develop a GUI to display stock trend lines and trading indicators.
- [ ] **REST API Market Feed:** Connect the application to a real stock API (e.g., Alpha Vantage or Yahoo Finance) for live trading.
- [ ] **Limit Order Bids:** Implement buying and selling at predefined price triggers.
- [ ] **Relational Database Migration:** Replace CSV flat-files with JDBC and an SQLite database to improve relational integrity.

---

## 🎓 Learning Outcomes
- Applied Object-Oriented relationships (Aggregations & Compositions) across multiple classes.
- Used the Java Collections Framework to manipulate and audit structured records.
- Implemented file serialization and CSV string parsing to persist application state.
- Handled input validation and exception handling for user financial data inputs.

---

## 👤 Author
* **Mushtaq Raza**
* **GitHub Profile:** [mushtaqraza927](https://github.com/mushtaqraza927)

---

## 📄 License
This project is licensed under the MIT License - see the [LICENSE](https://opensource.org/licenses/MIT) page for details.

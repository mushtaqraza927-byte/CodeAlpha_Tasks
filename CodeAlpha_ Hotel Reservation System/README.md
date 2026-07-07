# 🏨 Hotel Reservation System

[![Java Version](https://img.shields.io/badge/Java-17%2B-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![Data Persistence](https://img.shields.io/badge/Persistence-File%20I%2FO-brightgreen?style=for-the-badge)](https://docs.oracle.com/javase/tutorial/essential/io/)
[![Collections Framework](https://img.shields.io/badge/Collections-ArrayList-blueviolet?style=for-the-badge)](https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html)
[![License](https://img.shields.io/badge/License-MIT-green.svg?style=for-the-badge)](https://opensource.org/licenses/MIT)

A console-based hotel management and booking system designed to track room availability, record guest reservations, process mock payments, and persist schedules to a local flat-file storage database.

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
The **Hotel Reservation System** offers a mock hospitality booking solution. The application handles a predefined list of hotel rooms segmented across categories (Standard, Deluxe, and Suite) with variable nightly rates. Guests can search for available rooms, book stays for a designated number of nights, cancel active bookings by reservation ID or room number, inspect current schedules, and simulate payments. The reservation records are automatically synchronized with a local data file (`reservations.txt`), ensuring persistence across application restarts.

---

## 🎯 Objective
- **Automate Hotel Bookings:** Establish an intuitive booking and cancellation engine that manages real-time room availability.
- **Implement Robust State Control:** Maintain accurate availability flags for rooms (e.g., booked rooms must remain unavailable until cancelled).
- **Leverage Multi-Entity OOP Relationships:** Design structured interactions between `HotelRoom`, `Customer`, `Reservation`, `Payment`, and `HotelManager` classes.

---

## ✨ Features
* **Categorized Room Setup:** Manages distinct room categories and pricing:

| Room Numbers | Room Category | Price per Night |
| :--- | :--- | :--- |
| `101 - 103` | Standard | $100.00 |
| `201 - 203` | Deluxe | $150.00 |
| `301 - 303` | Suite | $250.00 |

* **Real-time Availability Tracking:** Checks availability before booking, ensuring no room is double-booked.
* **Flexible Cancellation System:** Cancel bookings by providing either a Reservation ID or a specific Room Number.
* **Payment Processing Simulation:** Mock checkout payment validation, changing reservation billing status from "Unpaid" to "Paid".
* **Flat-File Database Persistence:** Writes current reservations to `reservations.txt` in CSV format, loading them back on launch to restore state.
* **Failsafe Menu Traps:** Continuous input validation to prevent crashes from invalid keystrokes (like typing letters into numeric input menus).

---

## 💻 Technologies Used
* **Language:** Java SE (Standard Edition)
* **SDK:** Version 17 or higher
* **File Persistence API:** Java I/O library (`java.io.FileReader`, `java.io.FileWriter`, `java.io.BufferedReader`, `java.io.BufferedWriter`)
* **Core Utilities:** Standard collections (`java.util.ArrayList`, `java.util.Scanner`)

---

## 🧠 Java Concepts Implemented

### 1. Object-Oriented Programming (OOP)
* **Encapsulation:** Access modifiers (`private`) restrict variable modification, forcing interaction through standard getter and setter methods.
* **Modular Domain Layout:** Separates responsibilities cleanly:
  * `Customer`: Stores customer details.
  * `HotelRoom`: Represents rooms and handles price lookup.
  * `Reservation`: Links customer and room details with stay length and billing status.
  * `Payment`: Handles interactive confirmation for payment simulation.
  * `HotelManager`: Orchestrates rooms initialization, booking rules, cancellations, and I/O.

### 2. Collections Framework
* **Dynamic Arrays:** Utilizes `ArrayList<T>` collections to manage active data streams:
  * `ArrayList<HotelRoom>`: Maintains the list of hotel rooms.
  * `ArrayList<Reservation>`: Stores active reservations.

### 3. File I/O
* **CSV Data Storage:** Records are stored in a comma-separated format:
  `ID,CustomerName,RoomNumber,Nights,TotalCost,PaymentStatus`
* **Buffer Streaming:** Implements `BufferedReader` and `BufferedWriter` to perform efficient line-by-line reading and writing.

### 4. Exception Handling
* **Input Parse Try-Catch:** Catching `NumberFormatException` when converting user menu selections to integers.
* **Stream Exception Traps:** Handles `IOException` during file loading or writing to prevent system crashes when the file is missing or corrupted.

### 5. Multithreading
* **N/A:** Synchronous CLI execution thread.

---

## 📂 Project Structure
```text
CodeAlpha_ Hotel Reservation System/
│
├── .idea/                           # IDE Configuration Folder
├── out/                             # Compiled Java Class Bytecode Target
├── Customer.class                   # Compiled Customer Bytecode
├── Customer.java                    # Represents a guest profile (Name)
├── HotelManager.class               # Compiled HotelManager Bytecode
├── HotelManager.java                # Main controller handling room inventory and bookings
├── HotelRoom.class                  # Compiled HotelRoom Bytecode
├── HotelRoom.java                   # Represents a room (Number, Type, Price, Availability)
├── Main.class                       # Compiled CLI Runner Bytecode
├── Main.java                        # System Entry Point (Main loop & menus CLI)
├── Payment.class                    # Compiled Payment Bytecode
├── Payment.java                     # Mock billing checkout simulation
├── Reservation.class                # Compiled Reservation Bytecode
├── Reservation.java                 # Links Customer, Room, Nights, Cost, and Paid status
├── Task-04.iml                      # IntelliJ Project Metadata
├── input.txt                        # Temporary testing script inputs (if any)
└── reservations.txt                 # Local database storage for bookings
```

---

## ⚙️ Installation & Prerequisites
1. **JDK 17+:** Verify your local Java development kit installation.
2. **Text Reader:** Access to a standard command-line terminal.

---

## 🛠️ How to Build and Run

### Step 1: Compile all source files
```bash
javac Main.java Customer.java HotelRoom.java Reservation.java Payment.java HotelManager.java
```

### Step 2: Run the compiled application
```bash
java Main
```

---

## 💡 Sample Usage

```text
==================================================
      WELCOME TO THE HOTEL RESERVATION SYSTEM     
==================================================

==================================
            MAIN MENU             
==================================
1. View All Rooms & Availability
2. Book a Room
3. Cancel a Reservation
4. View Booking Details
5. Make a Payment (Simulation)
6. Exit
==================================
Enter your choice (1-6): 2

--- Book a Room ---
Enter Customer Name: John Doe

--- Available Rooms ---
Room Number: 101 | Type: Standard | Price: $100.0/night | Status: Available
Room Number: 102 | Type: Standard | Price: $100.0/night | Status: Available
Room Number: 201 | Type: Deluxe | Price: $150.0/night | Status: Available
Room Number: 301 | Type: Suite | Price: $250.0/night | Status: Available

Enter Room Number to Book: 201
Enter Number of Nights: 3

>>> Room Booked Successfully! <<<
Reservation ID: 1001
Total Amount Due: $450.0
```

---

## 🖼️ Screenshots
*(Previews of CLI interactive flows)*

| Predefined Room Setup | Booking Success Summary |
| :---: | :---: |
| ![CLI Predefined Room Matrix](https://via.placeholder.com/400x250?text=CLI+Room+Inventory+List) | ![CLI Successful Booking Screen](https://via.placeholder.com/400x250?text=CLI+Booking+Invoice+Details) |

---

## 🚀 Future Enhancements
- [ ] **Room Search Filters:** Support searching rooms by price range or specific room category (e.g. Deluxe only).
- [ ] **Dynamic Pricing Rules:** Implement peak season surcharges and weekend pricing logic.
- [ ] **Desktop GUI Interface:** Design a Swing layout showing a floor plan grid of rooms with green/red indicator boxes.
- [ ] **Database Integration:** Connect to an external database engine using JDBC for scalable persistence.

---

## 🎓 Learning Outcomes
- Developed modular relationships between classes representing actors (`Customer`), inventory (`HotelRoom`), and transactions (`Reservation`).
- Mastered state validation (preventing bookings of occupied rooms, handling cancellations of non-existent stays).
- Implemented transactional database emulation by storing and loading structured records in comma-separated values (CSV).
- Refined input parsing to capture and resolve standard console exceptions without crashing the terminal thread.

---

## 👤 Author
* **Mushtaq Raza**
* **GitHub Profile:** [mushtaqraza927](https://github.com/mushtaqraza927)

---

## 📄 License
This project is licensed under the MIT License - see the [LICENSE](https://opensource.org/licenses/MIT) page for details.

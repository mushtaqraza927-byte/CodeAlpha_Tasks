# 🎓 Student Grade Tracker

[![Java Version](https://img.shields.io/badge/Java-17%2B-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![OOP Paradigm](https://img.shields.io/badge/Paradigm-Structured%20%2F%20Modular-blue?style=for-the-badge)](https://en.wikipedia.org/wiki/Structured_programming)
[![License](https://img.shields.io/badge/License-MIT-green.svg?style=for-the-badge)](https://opensource.org/licenses/MIT)
[![Build](https://img.shields.io/badge/Build-Passed-success?style=for-the-badge)](#)

An interactive, command-line utility designed to record student names and their corresponding grades, calculate comprehensive class statistics, and output a detailed academic report.

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
The **Student Grade Tracker** is a robust console-based application written in Java. It allows educators and academic administrators to input student profiles, log numerical grades, validate inputs dynamically to prevent run-time errors, and automatically compute essential class statistics (such as average, highest performance, and lowest performance). The program is optimized for raw console interactions with custom-tailored string formatting to present reports professionally.

---

## 🎯 Objective
- **Simplify Grade Tracking:** Provide an intuitive CLI tool for tracking student academic records without complex setup.
- **Automate Statistics:** Remove manual calculations by automating class averages, highest score tracking, and lowest score tracking.
- **Robust User Validation:** Ensure input cleanliness and run-time safety via aggressive scanner validations.

---

## ✨ Features
* **Dynamic Student Count Selection:** Initialize tracking for any positive number of students.
* **Input Sanitization & Validation:** 
  * Prevents empty names.
  * Validates double-type numerical grades specifically within the range `[0.0 - 100.0]`.
  * Protects against input mismatch crashes (e.g., passing characters where numbers are expected).
* **Statistical Insights:** 
  * **Class Average:** Computes real-time average value of all entered grades.
  * **Top Performer Search:** Scans all inputs to pinpoint the highest grade in the class.
  * **Low Performer Search:** Scans all inputs to pinpoint the lowest grade in the class.
* **Tabular Summarized Report:** Outputs a neatly formatted table showing student names side-by-side with their grades (formatted to exactly 2 decimal places), complete with a bottom boundary presenting overall class analytics.

---

## 💻 Technologies Used
* **Language:** Java SE (Standard Edition)
* **SDK:** Version 17 or higher
* **APIs:** Standard Java Core Utility Library (`java.util.Scanner`)

---

## 🧠 Java Concepts Implemented

### 1. Object-Oriented Programming (OOP)
* **Encapsulation:** Program entry point handles clean workflow orchestration. Helper routines encapsulate specific tasks such as input loops, array iteration, and calculations.
* **Modular Codebase:** Features separate, dedicated static helper methods to implement SRP (Single Responsibility Principle) at the functional level.

### 2. Data Structures & Collections
* **Fixed-size Arrays:** Uses primitive one-dimensional arrays (`String[] names` and `double[] grades`) to store student identifiers and scores in matching indices.

| Array | Data Type | Purpose |
| :--- | :--- | :--- |
| `names` | `String` | Stores names of the recorded students |
| `grades` | `double` | Stores grades corresponding to the student index |

### 3. Exception Handling & Input Validation
* **Scanner Input Checking:** Implements `scanner.hasNextInt()` and `scanner.hasNextDouble()` checks to handle invalid formatting proactively, bypassing standard input exceptions.
* **Buffer Clearing:** Employs `scanner.nextLine()` to clear scanner buffers after parsing integers/doubles, avoiding trailing newline bugs.
* **Loop-based Re-prompting:** Uses infinite loops containing break-conditions that prompt users repeatedly until clean inputs are acquired.

### 4. File I/O & JDBC
* **N/A:** Memory storage model. Holds session records strictly in run-time memory buffer arrays.

### 5. Multithreading
* **N/A:** Single-threaded console execution thread.

---

## 📂 Project Structure
```text
CodeAlpha_Student Grade Tracker/
│
├── .idea/                           # IDE Configuration Folder
├── out/                             # Compiled Java Class Bytecode Target
├── StudentGradeTracker.class        # Compiled Bytecode
├── StudentGradeTracker.java         # Main source code file containing tracker logic
└── Task-01.iml                      # IntelliJ Project Metadata
```

---

## ⚙️ Installation & Prerequisites
1. **Java Development Kit (JDK) 17+:** Ensure JDK is installed. Confirm by running:
   ```bash
   java -version
   ```
2. **Terminal Access:** Access to a CLI or Command Prompt.

---

## 🛠️ How to Build and Run

### Step 1: Compile the source file
Open terminal/cmd at the root directory of this project and run:
```bash
javac StudentGradeTracker.java
```

### Step 2: Launch the application
Execute the compiled bytecode class:
```bash
java StudentGradeTracker
```

---

## 💡 Sample Usage

```text
=== Welcome to the Student Grade Tracker ===
Enter the number of students: 3

Recording details for Student #1:
Enter name: Alice Smith
Enter grade (0-100): 88.5

Recording details for Student #2:
Enter name: Bob Johnson
Enter grade (0-100): abc
Invalid input. Please enter a numerical grade value (0-100).
Enter grade (0-100): 95.0

Recording details for Student #3:
Enter name: Charlie Brown
Enter grade (0-100): 72.3

=================================
      STUDENT GRADE SUMMARY      
=================================
Student Name         | Grade     
---------------------------------
Alice Smith          | 88.50     
Bob Johnson          | 95.00     
Charlie Brown        | 72.30     
---------------------------------
Average Grade: 85.27
Highest Grade: 95.00
Lowest Grade:  72.30
=================================
```

---

## 🖼️ Screenshots
*(Screenshots representing user interaction and the console report structure)*

| Welcome and Data Entry | Statistical Output Summary |
| :---: | :---: |
| ![CLI Input Placeholder](https://via.placeholder.com/400x250?text=CLI+Data+Input+Process) | ![CLI Summary Table Placeholder](https://via.placeholder.com/400x250?text=CLI+Statistical+Report) |

---

## 🚀 Future Enhancements
- [ ] **Dynamic Array Transition:** Migrate storage from primitive arrays to `ArrayList<Student>` objects to support adding and removing students dynamically.
- [ ] **Data Persistence:** Integrate local JSON or CSV file storage to load and save grade summaries.
- [ ] **Swing Graphical UI:** Build a simple window-based application layer to display grade lists visually.
- [ ] **Letter Grade Conversion:** Add logic to translate numerical values into standard letter grades (A, B, C, D, F) and GPA formats.

---

## 🎓 Learning Outcomes
- Gained deep familiarity with user input manipulation and standard Java `Scanner` utilities.
- Mastered structural array manipulation and alignment (parallel array index matching).
- Developed robust logic pipelines using continuous validation loops to prevent run-time input mismatch anomalies.
- Practiced text alignment formatting with `printf` using precision values and fixed-width spacing columns.

---

## 👤 Author
* **Mushtaq Raza**
* **GitHub Profile:** [mushtaqraza927](https://github.com/mushtaqraza927)

---

## 📄 License
This project is licensed under the MIT License - see the [LICENSE](https://opensource.org/licenses/MIT) page for details.

# 📘 Java MVC Calculator

A simple **Java Calculator** built using the **Model–View–Controller (MVC)** architecture.
This project demonstrates clean separation of logic, presentation, and user interaction while supporting basic arithmetic operations.

## ✨ Features
- ➕ Addition
- ➖ Subtraction
- ✖️ Multiplication
- ➗ Division (with custom DivisionByZeroException)
- 🔄 Conversion utilities for safe string ↔ number handling
- 📦 Modular MVC structure for clean, maintainable code

## 🚀 Getting Started
### 1. Compile the program Navigate to the src directory:
bash
javac Main.java

2. Run the program
bash
java Main

🧩 How It Works (MVC Overview)
Model
Handles the core operations:

add

subtract

multiply

divide (throws DivisionByZeroException)

View
Provides console-based user interaction (input/output).

Controller
Acts as the middle layer:

reads user input

performs conversions

invokes model methods

returns results to the view

⚠️ Error Handling
This calculator includes:

custom DivisionByZeroException

safe numeric parsing via converter utilities

🛠️ Future Improvements
Graphical UI (Swing or JavaFX)

Scientific functions (√, exponentiation, parentheses)

Expression parsing (2 + 3 * 5)

Memory functions (M+, M-, MR)

# CalcApp Calculator

Hello everyone! So CalcApp is an Android application that mimics the look and feel of the iPhone calculator. It features a beautifully designed user interface built with XML and implements basic arithmetic operations using Java.

---

## Features

- **iOS-Inspired UI:**  
  - Clean, modern design with a black background.
  - Rounded buttons with custom colors:
    - **Light gray** for functions (AC, +/-, %).
    - **Dark gray** for numbers.
    - **Orange** for operators (÷, ×, −, +, =).
  - A large display at the top for current input and results.

- **Calculator Functions:**  
  - Basic arithmetic operations: addition, subtraction, multiplication, and division.
  - Special functions:
    - **AC (Clear):** Resets the calculator.
    - **+/- (Toggle Sign):** Switches the sign of the current input.
    - **% (Percentage):** Converts the current input to a percentage.

---

## Project Structure

- **activity_main.xml:**  
  Defines the UI layout, including a `TextView` for the display and multiple rows of buttons organized using `LinearLayouts`. The XML design makes the interface look prettier and closely matches the iOS calculator style.

- **MainActivity.java:**  
  Contains the core logic for the calculator:
  - Handles user input for numbers and operators.
  - Implements the calculation logic.
  - Manages special functions like clear, sign toggle, and percentage.

- **Drawable Resources:**  
  Custom XML drawables are used to give buttons rounded corners and achieve the desired aesthetic.

---

## Requirements

- Android Studio (latest version recommended)
- Java
- Android SDK

---

## Code Explanation

- **UI Implementation:**  
  The user interface is defined in `activity_main.xml` to achieve a polished and attractive layout. Buttons are arranged in rows, with custom styles for rounded corners and specific colors to replicate the iOS calculator design.

- **Calculator Logic:**  
  In `MainActivity.java`, the app manages input through button click listeners:
  - When you press a number, it appends the digit to the current input.
  - Pressing an operator stores the current input as the first operand and waits for the next number.
  - Pressing equals computes the result based on the stored operator and operands.
  - Additional buttons handle clearing input (AC), toggling the sign (+/-), and converting the number to a percentage (%).

---

Have a nice day! <3 

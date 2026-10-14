## **Step-by-Step Implementation Guide**

- **Topic:** Answer Key

- **Instructor: RAHUL KP**

#### **Step 1: Set Up the Main Loop & Input**

- Initialize a scanner or input reader to accept user values from the console.
- Create a loop that keeps the calculator running until the user types an exit command (e.g., `'q'` or `'exit'`).

#### **Step 2: Define Operation Methods**

Write distinct methods that accept two numbers as parameters and return the computed result:

- `public static double add(double a, double b)`
- `public static double subtract(double a, double b)`
- `public static double multiply(double a, double b)`
- `public static double divide(double a, double b)`

#### **Step 3: Process Choice Using `Switch` or `If-Else`**

Prompt the user to enter an operator (`+`, `-`, `*`, `/`, `%`) and two operands. Use a `switch` statement to evaluate the operator and call the corresponding method.

---

```java
import java.util.Scanner;

public class Calculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean keepRunning = true;

        System.out.println("=== Console Calculator ===");

        while (keepRunning) {
            System.out.print("\nEnter first number: ");
            double num1 = scanner.nextDouble();

            System.out.print("Enter an operator (+, -, *, /, %): ");
            char operator = scanner.next().charAt(0);

            System.out.print("Enter second number: ");
            double num2 = scanner.nextDouble();

            double result = 0;
            boolean valid = true;

            // Using a switch statement for control flow
            switch (operator) {
                case '+':
                    result = add(num1, num2);
                    break;
                case '-':
                    result = subtract(num1, num2);
                    break;
                case '*':
                    result = multiply(num1, num2);
                    break;
                case '/':
                    if (num2 == 0) {
                        System.out.println("Error: Division by zero is not allowed.");
                        valid = false;
                    } else {
                        result = divide(num1, num2);
                    }
                    break;
                case '%':
                    result = modulus(num1, num2);
                    break;
                default:
                    System.out.println("Error: Invalid operator.");
                    valid = false;
            }

            if (valid) {
                System.out.println("Result: " + num1 + " " + operator + " " + num2 + " = " + result);
            }

            System.out.print("\nDo you want to calculate again? (y/n): ");
            char choice = scanner.next().charAt(0);
            if (choice == 'n' || choice == 'N') {
                keepRunning = false;
                System.out.println("Exiting calculator. Goodbye!");
            }
        }
        scanner.close();
    }

    // Method definitions
    public static double add(double a, double b) { return a + b; }
    public static double subtract(double a, double b) { return a - b; }
    public static double multiply(double a, double b) { return a * b; }
    public static double divide(double a, double b) { return a / b; }
    public static double modulus(double a, double b) { return a % b; }
}
```

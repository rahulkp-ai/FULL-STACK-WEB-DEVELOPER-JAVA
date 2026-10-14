# Topic: Java Methods

## 1. Anatomy of a Java Method

A standard method declaration consists of several components:

```java
public static int addNumbers(int a, int b) {
    // Method body
    int sum = a + b;
    return sum; // Return statement
}
```

- **Access Modifier (`public`, `private`, etc.):** Defines the visibility of the method.
- **Static Keyword (`static`):** Optional. Indicates that the method belongs to the class itself rather than an instance of the class.
- **Return Type (`int`, `void`, `String`, etc.):** The data type of the value the method returns. Use `void` if the method doesn't return anything.
- **Method Name (`addNumbers`):** A descriptive identifier used to call the method.
- **Parameters (`int a, int b`):** Inputs passed into the method (enclosed in parentheses). Can be empty.
- **Method Body (`{ ... }`):** The block of code enclosed in curly braces that executes the task.

---

## 2. Types of Methods

- **Instance Methods:** Associated with an object of a class. You must create an object using the `new` keyword to call them.

  ```java
  // Example of an Instance Method
  public void displayMessage() {
      System.out.println("This is an instance method.");
  }
  ```

- **Static Methods:** Associated with the class as a whole. You can call them directly using the class name without creating an object (e.g., `Math.max()`).

  ```java
  // Example of a Static Method
  public static int square(int x) {
      return x * x;
  }
  ```

- **Parameterized Methods:** Methods that accept one or more arguments to customize their behavior.
  ```java
  // Example of a Parameterized Method
  public void printUserInfo(String username, int age) {
      System.out.println("User: " + username + ", Age: " + age);
  }
  ```

---

## 3. Complete Code Example

Here is a practical example demonstrating static, instance, and parameterized methods:

```java
public class Calculator {

    // Static method (no object needed to call)
    public static int multiply(int a, int b) {
        return a * b;
    }

    // Instance method (requires an object to call)
    public void printGreeting(String name) {
        System.out.println("Hello, " + name + "!");
    }

    // Parameterized instance method
    public void calculateTotal(double price, int quantity) {
        double total = price * quantity;
        System.out.println("Total Price: $" + total);
    }

    public static void main(String[] args) {
        // Calling the static method directly via the class name
        int product = Calculator.multiply(4, 5);
        System.out.println("Product: " + product);

        // Creating an object to call the instance methods
        Calculator calc = new Calculator();
        calc.printGreeting("Developer");
        calc.calculateTotal(19.99, 3);
    }
}
```

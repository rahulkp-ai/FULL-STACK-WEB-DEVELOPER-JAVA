# Integrated Development Environment (IDE)

- **Instructor: RAHUL KP**

  An **Integrated Development Environment (IDE)** is a software application that combines essential developer tools into a single graphical user interface (GUI). Instead of using separate tools for writing, compiling, running, and debugging code, an IDE unifies them into a streamlined workspace.

---

### Core Components of an IDE

- **Source Code Editor:** Text editor designed for writing code, featuring syntax highlighting, auto-completion, bracket matching, and real-time error detection.
- **Compiler / Interpreter:** Tools integrated directly into the workspace to convert source code into executable programs or run script files without leaving the application.
- **Debugger:** Diagnostic tool that lets you set breakpoints, inspect variables, step through code execution line-by-line, and trace execution flows to fix bugs.
- **Build Automation Tools:** Systems (like Maven, Gradle, or Make) built into the IDE to automate compilation, packaging, testing, and deployment processes.
- **Version Control Integration:** Built-in tools for Git operations (commit, push, pull, branch management, and conflict resolution visualizers).

---

### Key Benefits

1. **Efficiency & Speed:** Code completion (IntelliSense) and live template expansion reduce repetitive typing and syntax lookup.
2. **Automated Refactoring:** Safe, codebase-wide changes like renaming symbols, extracting methods, or updating function signatures across hundreds of files simultaneously.
3. **Contextual Navigation:** Jump directly to variable declarations, class definitions, interface implementations, or usages with single hotkeys or mouse clicks.

---

### Popular IDEs by Use Case

| IDE / Environment      | Primary Languages & Use Cases            | Key Characteristics                                                                           |
| ---------------------- | ---------------------------------------- | --------------------------------------------------------------------------------------------- |
| **IntelliJ IDEA**      | Java, Kotlin, Scala                      | Industry standard for JVM platforms with deep static analysis and refactoring capabilities.   |
| **Visual Studio Code** | Polyglot (JS, TS, Python, Go, Rust, C++) | Extensible, lightweight editor/IDE hybrid driven by an extensive ecosystem of plugins.        |
| **PyCharm**            | Python                                   | Tailored for Python development, data science, and web frameworks (Django, FastAPI, Flask).   |
| **Visual Studio**      | C#, C++, .NET                            | Full-featured IDE for Windows application development, enterprise software, and game engines. |
| **Xcode**              | Swift, Objective-C                       | Apple's official IDE for iOS, macOS, watchOS, and visionOS development.                       |
| **CLION**              | C, C++                                   | JetBrains' IDE designed for native desktop development, embedded systems, and CMake builds.   |

---

## 1. Project Directory Structure

In IntelliJ IDEA's **Project Tool Window** (`Alt + 1` / `Cmd + 1`), set up your project structure as follows:

```text
src/
├── com.ecommerce.model/
│   ├── Product.java
│   └── Customer.java
├── com.ecommerce.service/
│   └── OrderProcessor.java
└── com.ecommerce.app/
    └── Main.java
```

---

## 2. Source Code Files

### File 1: `Product.java` (Package: `com.ecommerce.model`)

```java
package com.ecommerce.model;

import java.math.BigDecimal;

public class Product {
    private final String id;
    private final String name;
    private final BigDecimal price;

    public Product(String id, String name, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public BigDecimal getPrice() { return price; }
}
```

---

### File 2: `Customer.java` (Package: `com.ecommerce.model`)

```java
package com.ecommerce.model;

public class Customer {
    private final String customerId;
    private final String name;
    private final String email;

    public Customer(String customerId, String name, String email) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
    }

    public String getCustomerId() { return customerId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
}
```

---

### File 3: `OrderProcessor.java` (Package: `com.ecommerce.service`)

This file imports the classes from `com.ecommerce.model`.

```java
package com.ecommerce.service;

import com.ecommerce.model.Customer;
import com.ecommerce.model.Product;
import java.math.BigDecimal;

public class OrderProcessor {

    public void processOrder(Customer customer, Product product, int quantity) {
        BigDecimal total = product.getPrice().multiply(new BigDecimal(quantity));

        System.out.println("========================================");
        System.out.println("          ORDER CONFIRMATION            ");
        System.out.println("========================================");
        System.out.println("Customer Name : " + customer.getName());
        System.out.println("Customer Email: " + customer.getEmail());
        System.out.println("Product       : " + product.getName() + " (ID: " + product.getId() + ")");
        System.out.println("Quantity      : " + quantity);
        System.out.println("Unit Price    : $" + product.getPrice());
        System.out.println("----------------------------------------");
        System.out.println("Total Amount  : $" + total);
        System.out.println("Status        : ORDER PROCESSED SUCCESSFULLY");
        System.out.println("========================================");
    }
}
```

---

### File 4: `Main.java` (Package: `com.ecommerce.app`)

This is the application entry point containing the `main` method. It imports both model and service classes.

```java
package com.ecommerce.app;

// Package imports
import com.ecommerce.model.Customer;
import com.ecommerce.model.Product;
import com.ecommerce.service.OrderProcessor;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        // 1. Instantiating Objects from imported package classes
        Customer customer = new Customer("CUST-101", "Alice Smith", "alice@example.com");
        Product laptop = new Product("PROD-889", "MacBook Pro 16\"", new BigDecimal("2499.99"));

        // 2. Invoking Service Class method
        OrderProcessor processor = new OrderProcessor();
        processor.processOrder(customer, laptop, 2);
    }
}
```

---

## 3. How to Run This Program in IntelliJ IDEA

### Method 1: The Green Play Button (Fastest)

1. Open `Main.java` in the editor.
2. Look at line 10 next to `public static void main(String[] args)`.
3. Click the green **Play ($\triangleright$) icon** in the left gutter.
4. Select **Run 'Main.main()'**.

### Method 2: Keyboard Shortcut

- Press `Ctrl + Shift + F10` (Windows/Linux) or `Ctrl + Option + R` (macOS) while `Main.java` is focused.

### Method 3: Top Navigation Bar

- Select **Main** from the run configuration dropdown at the top right of the IDE, then click the green **Run** button (or press `Shift + F10` / `Control + R`).

---

## 4. Console Output

When you run `Main.java`, IntelliJ IDEA compiles all dependent classes across packages and produces the following output in the **Run Terminal Window** at the bottom:

```text
========================================
          ORDER CONFIRMATION
========================================
Customer Name : Alice Smith
Customer Email: alice@example.com
Product       : MacBook Pro 16" (ID: PROD-889)
Quantity      : 2
Unit Price    : $2499.99
----------------------------------------
Total Amount  : $4999.98
Status        : ORDER PROCESSED SUCCESSFULLY
========================================

Process finished with exit code 0
```

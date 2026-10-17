### Question 1: Student Grade Evaluator (`if-else` & Methods)

**Problem Statement:**

Create a `Student` class that stores a student's name and score. Write a method called `calculateGrade()` that uses an **`if-else`** ladder to return a letter grade based on the score:

- Score $\ge$ 90: "A"
- Score $\ge$ 80: "B"
- Score $\ge$ 70: "C"
- Below 70: "F"

#### Solution:

```java
class Student {
    String name;
    int score;

    // Constructor to initialize the object
    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }

    // Method using if-else to determine grade
    public String calculateGrade() {
        if (score >= 90) {
            return "A";
        } else if (score >= 80) {
            return "B";
        } else if (score >= 70) {
            return "C";
        } else {
            return "F";
        }
    }
}

public class Main {
    public static void main(String[] args) {
        // Creating objects without terminal input
        Student student1 = கூறுக -> new Student("Alice", 85);
        // Direct instantiation:
        Student s1 = new Student("Alice", 85);
        Student s2 = new Student("Bob", 62);

        System.out.println(s1.name + " got grade: " + s1.calculateGrade()); // Output: B
        System.out.println(s2.name + " got grade: " + s2.calculateGrade()); // Output: F
    }
}

```

---

### Question 2: Traffic Light Action (`switch` & Objects)

**Problem Statement:**

Create a `TrafficLight` class with a `color` field. Write a method named `getAction()` that uses a **`switch`** statement to return the corresponding action:

- "red" $\rightarrow$ "Stop"
- "yellow" $\rightarrow$ "Prepare to stop"
- "green" $\rightarrow$ "Go"
- Anything else $\rightarrow$ "Invalid light color"

#### Solution:

```java
class TrafficLight {
    String color;

    // Constructor
    public TrafficLight(String color) {
        this.color = color.toLowerCase();
    }

    // Method using switch statement
    public String getAction() {
        switch (color) {
            case "red":
                return "Stop";
            case "yellow":
                return "Prepare to stop";
            case "green":
                return "Go";
            default:
                return "Invalid light color";
        }
    }
}

public class Main {
    public static void main(String[] args) {
        TrafficLight light1 = new TrafficLight("Green");
        TrafficLight light2 = new TrafficLight("RED");

        System.out.println("Light 1 action: " + light1.getAction()); // Output: Go
        System.out.println("Light 2 action: " + light2.getAction()); // Output: Stop
    }
}

```

---

### Question 3: Simple Bank Account (`if-else` & Objects)

**Problem Statement:**

Create a `BankAccount` class that tracks an account holder's name and balance. Write a method called `processTransaction(String type, double amount)` that handles deposits and withdrawals using **`if-else`**:

- If type is "deposit", add the amount to the balance and return true.
- If type is "withdraw", check if the balance is sufficient. If it is, subtract the amount and return true. Otherwise, return false.

#### Solution:

```java
class BankAccount {
    String accountHolder;
    double balance;

    // Constructor
    public BankAccount(String accountHolder, double initialBalance) {
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
    }

    // Method using if-else for logic
    public boolean processTransaction(String type, double amount) {
        if (type.equalsIgnoreCase("deposit")) {
            balance += amount;
            return true;
        } else if (type.equalsIgnoreCase("withdraw")) {
            if (balance >= amount) {
                balance -= amount;
                return true;
            } else {
                return false; // Insufficient funds
            }
        } else {
            return false; // Unknown transaction type
        }
    }
}

public class Main {
    public static void main(String[] args) {
        BankAccount myAccount = new BankAccount("John Doe", 500.0);

        boolean t1 = myAccount.processTransaction("deposit", 200.0);
        System.out.println("Deposit successful? " + t1 + ". New Balance: " + myAccount.balance);
        // Output: true, 700.0

        boolean t2 = myAccount.processTransaction("withdraw", 1000.0);
        System.out.println("Withdrawal successful? " + t2 + ". Balance: " + myAccount.balance);
        // Output: false, 700.0
    }
}

```

Here are 7 additional practice questions (making a complete set of 10) focusing on **`if`**, **`if-else`**, **`switch`**, **methods**, and **objects**, with no terminal input (`Scanner`).

---

### Question 4: Basic Calculator (`switch` & Methods)

**Problem Statement:**

Create a `Calculator` class. Write a method named `calculate(double a, double b, String operator)` that uses a **`switch`** statement to perform basic arithmetic operations (`"+"`, `"-"`, `"*"`, `"/"`). If an invalid operator is passed, return `0.0`.

#### Solution:

```java
class Calculator {
    public double calculate(double a, double b, String operator) {
        switch (operator) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                if (b != 0) {
                    return a / b;
                } else {
                    return 0.0; // Division by zero guard
                }
            default:
                return 0.0;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Calculator calc = new Calculator();
        System.out.println("Result: " + calc.calculate(10, 5, "+")); // Output: 15.0
        System.out.println("Result: " + calc.calculate(20, 4, "/")); // Output: 5.0
    }
}

```

---

### Question 5: Even or Odd Number Checker (`if-else` & Objects)

**Problem Statement:**

Create a `NumberChecker` class that stores an integer. Write a method called `isEven()` that uses an **`if-else`** statement to return `true` if the number is even, and `false` if it is odd.

#### Solution:

```java
class NumberChecker {
    int value;

    public NumberChecker(int value) {
        this.value = value;
    }

    public boolean isEven() {
        if (value % 2 == 0) {
            return true;
        } else {
            return false;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        NumberChecker n1 = new NumberChecker(7);
        NumberChecker n2 = new NumberChecker(12);

        System.out.println(n1.value + " is even? " + n1.isEven()); // Output: false
        System.out.println(n2.value + " is even? " + n2.isEven()); // Output: true
    }
}

```

---

### Question 6: Season Finder (`switch` & Objects)

**Problem Statement:**

Create a `SeasonFinder` class with a `month` field. Write a method named `getSeason()` that uses a **`switch`** statement to return the season:

- "December", "January", "February" $\rightarrow$ "Winter"
- "March", "April", "May" $\rightarrow$ "Spring"
- "June", "July", "August" $\rightarrow$ "Summer"
- "September", "October", "November" $\rightarrow$ "Autumn"
- Anything else $\rightarrow$ "Invalid month"

#### Solution:

```java
class SeasonFinder {
    String month;

    public SeasonFinder(String month) {
        this.month = month;
    }

    public String getSeason() {
        switch (month.toLowerCase()) {
            case "december": case "january": case "february":
                return "Winter";
            case "march": case "april": case "may":
                return "Spring";
            case "june": case "july": case "august":
                return "Summer";
            case "september": case "october": case "november":
                return "Autumn";
            default:
                return "Invalid month";
        }
    }
}

public class Main {
    public static void main(String[] args) {
        SeasonFinder sf = new SeasonFinder("July");
        System.out.println("Season: " + sf.getSeason()); // Output: Summer
    }
}

```

---

### Question 7: Store Discount Calculator (`if-else` & Objects)

**Problem Statement:**

Create a `Purchase` class with fields for `membershipType` and `amount`. Write a method `calculateFinalPrice()` that uses **`if-else`** to apply discounts:

- "VIP": 20% discount
- "Member": 10% discount
- Regular/Anything else: No discount (0%)

#### Solution:

```java
class Purchase {
    String membershipType;
    double amount;

    public Purchase(String membershipType, double amount) {
        this.membershipType = membershipType;
        this.amount = amount;
    }

    public double calculateFinalPrice() {
        if (membershipType.equalsIgnoreCase("VIP")) {
            return amount * 0.80; // 20% off
        } else if (membershipType.equalsIgnoreCase("Member")) {
            return amount * 0.90; // 10% off
        } else {
            return amount; // No discount
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Purchase p1 = new Purchase("VIP", 200.0);
        System.out.println("VIP Final Price: " + p1.calculateFinalPrice()); // Output: 160.0
    }
}

```

---

### Question 8: Rectangle Validator (`if` & Objects)

**Problem Statement:**

Create a `Rectangle` class with `length` and `width`. Write a method `isValid()` that uses a simple **`if`** statement to return `true` only if both length and width are greater than `0`.

#### Solution:

```java
class Rectangle {
    double length;
    double width;

    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    public boolean isValid() {
        if (length > 0 && width > 0) {
            return true;
        }
        return false;
    }
}

public class Main {
    public static void main(String[] args) {
        Rectangle rect = new Rectangle(5.5, -2.0);
        System.out.println("Is valid rectangle? " + rect.isValid()); // Output: false
    }
}

```

---

### Question 9: Shipping Cost Calculator (`switch` & Objects)

**Problem Statement:**

Create a `Shipping` class with a `zone` field ("ZoneA", "ZoneB", "ZoneC"). Write a method `getCost()` using a **`switch`** statement:

- "ZoneA" $\rightarrow$ 5.0
- "ZoneB" $\rightarrow$ 10.0
- "ZoneC" $\rightarrow$ 15.0
- Default $\rightarrow$ 20.0

#### Solution:

```java
class Shipping {
    String zone;

    public Shipping(String zone) {
        this.zone = zone;
    }

    public double getCost() {
        switch (zone) {
            case "ZoneA":
                return 5.0;
            case "ZoneB":
                return 10.0;
            case "ZoneC":
                return 15.0;
            default:
                return 20.0;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Shipping ship = new Shipping("ZoneB");
        System.out.println("Shipping Cost: $" + ship.getCost()); // Output: 10.0
    }
}

```

---

### Question 10: Voting Eligibility Checker (`if-else` & Objects)

**Problem Statement:**

Create a `Person` class with `name` and `age`. Write a method `checkVotingEligibility()` that uses an **`if-else`** statement to return a message:

- If age $\ge$ 18: "[Name] is eligible to vote."
- Else: "[Name] is not eligible to vote."

#### Solution:

```java
class Person {
    String name;
    int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String checkVotingEligibility() {
        if (age >= 18) {
            return name + " is eligible to vote.";
        } else {
            return name + " is not eligible to vote.";
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Person p1 = new Person("Alex", 16);
        Person p2 = new Person("Sarah", 22);

        System.out.println(p1.checkVotingEligibility()); // Output: Alex is not eligible to vote.
        System.out.println(p2.checkVotingEligibility()); // Output: Sarah is eligible to vote.
    }
}

```

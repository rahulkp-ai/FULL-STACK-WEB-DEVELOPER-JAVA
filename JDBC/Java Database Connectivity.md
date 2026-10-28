# Java Database Connectivity (JDBC)

- **Instructor: RAHUL KP**
- **Topic:** Java Database Connectivity (JDBC)

---

## Introduction to JDBC & Driver Architecture

### What is JDBC?

- **Definition:** Java Database Connectivity (JDBC) is an industry-standard Java API that defines how a Java application can interact with relational databases (such as MySQL, PostgreSQL, Oracle, SQL Server).
- **Role in Architecture:** It acts as a bridge between Java applications and disparate database management systems (DBMS), providing a uniform interface so developers do not need to write database-specific native code.
- **Core Package:** Contained primarily within the `java.sql` and `javax.sql` packages.

### Core Components of JDBC Architecture

1. **JDBC API:** The application-facing interface (`Connection`, `Statement`, `PreparedStatement`, `ResultSet`) used by Java code to execute queries and manage transactions.
2. **DriverManager:** The backbone management layer that tracks installed drivers and establishes the physical connection between the Java application and the appropriate database driver.
3. **Driver Interface:** Handles communications with the database server, acting as an abstraction layer for vendor-specific database protocols.
4. **Database-Specific Drivers:** Third-party libraries (e.g., MySQL Connector/J) supplied by database vendors to translate JDBC calls into native database wire protocols.

### The Four Types of JDBC Drivers

#### Type 1: JDBC-ODBC Bridge Driver

- **Mechanism:** Translates JDBC calls into ODBC (Open Database Connectivity) calls, which then route to the database.
- **Drawbacks:** Requires ODBC binary drivers to be installed on the client machine; slow performance; deprecated and removed in modern Java versions.

#### Type 2: Native-API / Partly-Java Driver

- **Mechanism:** Converts JDBC calls into database-specific native client API calls (written in C/C++).
- **Drawbacks:** Requires client-side native libraries to be installed on every machine running the application. Not platform-independent.

#### Type 3: Network Protocol / Middleware Driver

- **Mechanism:** Uses a three-tier approach where JDBC calls are sent to a middle-tier application server, which translates them into database-independent or database-specific network protocols.
- **Advantages:** No client-side installation needed; highly scalable.
- **Drawbacks:** Requires intensive network configuration and a dedicated middleware server.

#### Type 4: Thin Driver / Native-Protocol Pure Java Driver (Industry Standard)

- **Mechanism:** Written entirely in Java. Directly converts JDBC calls into the vendor-specific network protocol used by the DBMS.
- **Advantages:**
  - Completely platform-independent ("Write Once, Run Anywhere").
  - Requires no native client libraries or middleware installation.
  - Direct socket connection offers high performance.
- **Example:** MySQL Connector/J (`com.mysql.cj.jdbc.Driver`).

---

## Advantages of JDBC

- **Platform Independence:** Built on Java, allowing database-agnostic code to run on Windows, Linux, macOS, or cloud containers without recompilation.
- **Standardized API:** Provides a consistent, unified set of interfaces (`java.sql`) across all relational databases. Switching from MySQL to PostgreSQL requires minimal code changes (primarily connection URLs and driver classes).
- **Database Agnosticism:** Decouples application logic from database specifics via the `DriverManager` and Driver pattern.
- **Connection Pooling Compatibility:** Integrates seamlessly with advanced connection pool frameworks (HikariCP, Apache DBCP) to optimize high-concurrency enterprise application performance.
- **Robust Exception Handling:** Utilizes `SQLException` hierarchies to cleanly catch and diagnose database errors, connection drops, and syntax violations.
- **Security & Parameterization:** Through `PreparedStatement`, JDBC natively supports parameterized queries to safeguard against SQL Injection attacks.

---

## Environment Setup & Configuration

### Prerequisites

- **Java Development Kit (JDK):** Version 11 or higher installed and added to system environmental variables (`JAVA_HOME`, `PATH`).
- **MySQL Server:** Installed and running locally (default port: `3306`) with an active administrative user.
- **Build Tool / IDE:** IntelliJ IDEA, Eclipse, or Maven/Gradle configured for dependency management.

### Obtaining the MySQL JDBC Driver (Connector/J)

For modern projects, include the MySQL Connector/J dependency in your `pom.xml` (Maven):

```xml
<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <version>8.3.0</version>
</dependency>
```

_Alternatively for manual builds:_ Download the platform-independent `.jar` file (`mysql-connector-j-8.x.x.jar`) from the official MySQL website and add it to your project's build path or classpath.

### Verifying the MySQL Connection URL Structure

The JDBC URL format for MySQL follows this pattern:

```text
jdbc:mysql://[host]:[port]/[database_name]?[property_name]=[property_value]

```

- **Example:** `jdbc:mysql://localhost:3306/university_db?useSSL=false&serverTimezone=UTC`

---

## MySQL Connectivity & Practical Implementation

### The 5 Core Steps of a JDBC Program

1. **Load/Register Driver:** (Optional in JDBC 4.0+ via Service Provider Mechanism, but good practice to understand: `Class.forName("com.mysql.cj.jdbc.Driver")`).
2. **Open Connection:** Call `DriverManager.getConnection(url, username, password)`.
3. **Create Statement:** Construct a `Statement` or `PreparedStatement` object via connection methods.
4. **Execute Query:** Run `executeUpdate()` for DML (INSERT, UPDATE, DELETE) or `executeQuery()` for DQL (SELECT).
5. **Process Results & Close Resources:** Iterate through the `ResultSet` and close resources using `try-with-resources` blocks to prevent memory leaks.

### Complete Java Example: CRUD Operations with MySQL

```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCMySQLDemo {

    // Database Credentials
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/school_db?useSSL=false&serverTimezone=UTC";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "secure_password";

    public static void main(String[] args) {
        // 1. Insert a new record
        insertStudent("Aarav Sharma", "Computer Science", 8.9);

        // 2. Fetch and display all records
        fetchAllStudents();
    }

    public static void insertStudent(String name, String major, double gpa) {
        String sql = "INSERT INTO students (name, major, gpa) VALUES (?, ?, ?)";

        // Try-with-resources ensures automatic closure of Connection and PreparedStatement
        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setString(2, major);
            pstmt.setDouble(3, gpa);

            int rowsAffected = pstmt.executeUpdate();
            System.out.println("Successfully inserted " + rowsAffected + " record(s).");

        } catch (SQLException e) {
            System.err.println("Database Error during Insert: " + e.getMessage());
        }
    }

    public static void fetchAllStudents() {
        String sql = "SELECT id, name, major, gpa, enrollment_date FROM students";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            System.out.println("\n--- Student Records Directory ---");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String major = rs.getString("major");
                double gpa = rs.getDouble("gpa");
                String date = rs.getString("enrollment_date");

                System.out.printf("ID: %d | Name: %s | Major: %s | GPA: %.2f | Enrolled: %s%n",
                        id, name, major, gpa, date);
            }

        } catch (SQLException e) {
            System.err.println("Database Error during Query: " + e.getMessage());
        }
    }
}

```

### Best Practices & Troubleshooting

- **Always use `PreparedStatement`:** Avoid string concatenation for SQL statements to protect against SQL Injection and improve query performance through caching.
- **Manage Resource Leaks:** Always wrap `Connection`, `Statement`, and `ResultSet` objects in `try-with-resources` or explicitly close them in `finally` blocks.
- **Common Exceptions:**
- `ClassNotFoundException`: Driver JAR is missing from the classpath.
- `CommunicationsException`: MySQL service is not running or incorrect port/host specified.
- `SQLException (Access Denied)`: Incorrect username or password credentials.

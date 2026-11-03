# Student

- **Instructor: RAHUL KP**

---

### Prerequisites & Database Setup (MySQL Workbench)

Before opening IntelliJ, open MySQL Workbench and run the following script to create your database and table:

```sql
CREATE DATABASE IF NOT EXISTS school_db;
USE school_db;

CREATE TABLE IF NOT EXISTS students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    course VARCHAR(50) NOT NULL
);

```

---

### Project Structure & Package Layout

Create a standard **Java** project in IntelliJ IDEA with the following package structure inside `src/`:

```text
src/
└── com/
    └── studentapp/
        ├── db/
        │   └── DatabaseConnection.java
        ├── model/
        │   └── Student.java
        ├── ui/
        │   └── StudentWindow.java
        └── Main.java

```

---

### Step 0: Add the MySQL JDBC Driver (`mysql-connector-j`)

To connect Java to MySQL, you need the MySQL JDBC driver JAR:

1. In IntelliJ IDEA, go to **File** > **Project Structure** (`Ctrl + Alt + Shift + S` / `Cmd + ;`).
2. Select **Libraries** under Project Settings, click the **`+`** icon, and select **Java**.
3. Download or attach the **MySQL Connector/J** JAR file (or configure it via your `pom.xml` / `build.gradle` if using Maven/Gradle).

---

### Step 1: Create Database Connector (`DatabaseConnection.java`)

In package `com.studentapp.db`, create `DatabaseConnection.java`. Update the `URL`, `USER`, and `PASSWORD` to match your local MySQL Workbench credentials:

```java
package com.studentapp.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // Update URL, username, and password to match your MySQL Workbench configuration
    private static final String URL = "jdbc:mysql://localhost:3306/school_db";
    private static final String USER = "root";
    private static final String PASSWORD = "your_mysql_password";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

```

---

### Step 2: Create the Data Model (`Student.java`)

In package `com.studentapp.model`, create `Student.java` to represent a row in your database:

```java
package com.studentapp.model;

public class Student {
    private final int id;
    private final String name;
    private final String email;
    private final String course;

    public Student(int id, String name, String email, String course) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.course = course;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCourse() {
        return course;
    }

    @Override
    public String toString() {
        return String.format("[ID: %d] %s (%s) - Course: %s", id, name, email, course);
    }
}

```

---

### Step 3: Build the Graphical UI & SQL Integration (`StudentWindow.java`)

In package `com.studentapp.ui`, create `StudentWindow.java` to handle user interactions and execute live SQL queries (`SELECT`, `INSERT`, `DELETE`):

```java
package com.studentapp.ui;

import com.studentapp.db.DatabaseConnection;
import com.studentapp.model.Student;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class StudentWindow extends JFrame {
    private final JTextField nameField;
    private final JTextField emailField;
    private final JTextField courseField;
    private final DefaultListModel<Student> listModel;
    private final JList<Student> studentList;

    public StudentWindow() {
        setTitle("IntelliJ MySQL Student Directory");
        setSize(550, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Input Form Panel
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 8, 8));
        inputPanel.setBorder(BorderFactory.createTitledBorder(" Register New Student (MySQL Connected) "));

        nameField = new JTextField();
        emailField = new JTextField();
        courseField = new JTextField();

        inputPanel.add(new JLabel("  Full Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("  Email Address:"));
        inputPanel.add(emailField);
        inputPanel.add(new JLabel("  Enrolled Course:"));
        inputPanel.add(courseField);

        // Buttons Panel
        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton addButton = new JButton("Save to Database");
        JButton deleteButton = new JButton("Delete Selected");
        JButton refreshButton = new JButton("Refresh Records");

        actionPanel.add(addButton);
        actionPanel.add(deleteButton);
        actionPanel.add(refreshButton);

        JPanel topContainer = new JPanel(new BorderLayout());
        topContainer.add(inputPanel, BorderLayout.CENTER);
        topContainer.add(actionPanel, BorderLayout.SOUTH);

        // Center List View
        listModel = new DefaultListModel<>();
        studentList = new JList<>(listModel);
        studentList.setFont(new Font("Monospaced", Font.PLAIN, 13));
        JScrollPane scrollPane = new JScrollPane(studentList);
        scrollPane.setBorder(BorderFactory.createTitledBorder(" Database Records (students table) "));

        // Assembly
        add(topContainer, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Listeners
        addButton.addActionListener(e -> addStudentToDatabase());
        deleteButton.addActionListener(e -> deleteSelectedStudent());
        refreshButton.addActionListener(e -> loadStudentsFromDatabase());

        // Initial Data Load
        loadStudentsFromDatabase();
    }

    private void loadStudentsFromDatabase() {
        listModel.clear();
        String query = "SELECT id, name, email, course FROM students";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String course = rs.getString("course");

                listModel.addElement(new Student(id, name, email, course));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage(),
                    "Connection Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addStudentToDatabase() {
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String course = courseField.getText().trim();

        if (name.isEmpty() || email.isEmpty() || course.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled out.",
                    "Input Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String query = "INSERT INTO students (name, email, course) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, course);
            pstmt.executeUpdate();

            nameField.setText("");
            emailField.setText("");
            courseField.setText("");

            loadStudentsFromDatabase(); // Refresh UI view
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Failed to insert record: " + ex.getMessage(),
                    "SQL Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteSelectedStudent() {
        Student selected = studentList.getSelectedValue();
        if (selected == null) {
            JOptionPane.showMessageDialog(this, "Please select a student record to delete.",
                    "Selection Warning", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String query = "DELETE FROM students WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, selected.getId());
            pstmt.executeUpdate();

            loadStudentsFromDatabase(); // Refresh UI view
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Failed to delete record: " + ex.getMessage(),
                    "SQL Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void display() {
        SwingUtilities.invokeLater(() -> setVisible(true));
    }
}

```

---

### Step 4: Launch the Application (`Main.java`)

In package `com.studentapp`, create `Main.java`:

```java
package com.studentapp;

import com.studentapp.ui.StudentWindow;

public class Main {
    public static void main(String[] args) {
        StudentWindow window = new StudentWindow();
        window.display();
    }
}

```

---

### How to Run & Verify in MySQL Workbench

1. Ensure your local MySQL server is running.
2. In IntelliJ IDEA, run `Main.java` (**Shift + F10**).
3. Fill in the form fields (e.g., Name: `Alex Turner`, Email: `alex@example.com`, Course: `Java Full-Stack`) and click **Save to Database**.
4. Open **MySQL Workbench**, run `SELECT * FROM school_db.students;`, and verify that the row has been instantly written to your database table!

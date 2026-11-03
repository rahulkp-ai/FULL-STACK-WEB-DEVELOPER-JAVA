## To-Do App

- **Instructor: RAHUL KP**

### Project Structure & Package Layout

Create a standard **Java** project (JDK 17+) in IntelliJ IDEA with the following package structure inside the `src/` directory:

```text
src/
└── com/
    └── taskapp/
        ├── model/
        │   └── Task.java
        ├── ui/
        │   └── TaskWindow.java
        └── Main.java

```

---

### Step 1: Create the Data Model (`Task.java`)

In package `com.taskapp.model`, create `Task.java` to handle individual task data:

```java
package com.taskapp.model;

public class Task {
    private final String description;
    private final String priority;

    public Task(String description, String priority) {
        this.description = description;
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public String getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return "[" + priority.toUpperCase() + "] " + description;
    }
}

```

---

### Step 2: Build the Graphical UI (`TaskWindow.java`)

In package `com.taskapp.ui`, create `TaskWindow.java` to set up the interactive window with text fields, dropdowns, buttons, and a scrollable list:

```java
package com.taskapp.ui;

import com.taskapp.model.Task;

import javax.swing.*;
import java.awt.*;

public class TaskWindow extends JFrame {
    private final JTextField taskInputField;
    private final JComboBox<String> priorityBox;
    private final DefaultListModel<Task> listModel;
    private final JList<Task> taskList;

    public TaskWindow() {
        // Window Configuration
        setTitle("IntelliJ Task Manager");
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center on screen
        setLayout(new BorderLayout(10, 10));

        // Top Panel: Input Controls
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 8));

        taskInputField = new JTextField(15);
        String[] priorities = {"Low", "Medium", "High"};
        priorityBox = new JComboBox<>(priorities);
        JButton addButton = new JButton("Add Task");

        inputPanel.add(new JLabel("Task:"));
        inputPanel.add(taskInputField);
        inputPanel.add(priorityBox);
        inputPanel.add(addButton);

        // Center Panel: Task List Display
        listModel = new DefaultListModel<>();
        taskList = new JList<>(listModel);
        taskList.setFont(new Font("SansSerif", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(taskList);

        // Bottom Panel: Action Buttons
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton deleteButton = new JButton("Remove Selected");
        bottomPanel.add(deleteButton);

        // Assembly
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // Event Listeners
        addButton.addActionListener(e -> addTask());
        deleteButton.addActionListener(e -> removeSelectedTask());
    }

    private void addTask() {
        String text = taskInputField.getText().trim();
        if (!text.isEmpty()) {
            String priority = (String) priorityBox.getSelectedItem();
            Task newTask = new Task(text, priority);
            listModel.addElement(newTask);
            taskInputField.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Please enter a task description.",
                                          "Input Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void removeSelectedTask() {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex != -1) {
            listModel.remove(selectedIndex);
        } else {
            JOptionPane.showMessageDialog(this, "Select a task to remove.",
                                          "Selection Error", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void display() {
        SwingUtilities.invokeLater(() -> setVisible(true));
    }
}

```

---

### Step 3: Create the Application Entry Point (`Main.java`)

In package `com.taskapp`, create `Main.java` to launch the GUI:

```java
package com.taskapp;

import com.taskapp.ui.TaskWindow;

public class Main {
    public static void main(String[] args) {
        // Instantiate and display the swing UI
        TaskWindow window = new TaskWindow();
        window.display();
    }
}

```

---

### How to Run in IntelliJ IDEA

1. **Locate `Main.java**`in the`Project` view panel on the left (`src/com/taskapp/Main.java`).
2. Click the **Green Play Icon** ($\triangleright$) right next to `public class Main` or `public static void main(...)` in the editor margin.
3. Select **Run 'Main.main()'** (or press **Shift + F10** on Windows/Linux, **Control + R** on macOS).

---

### Expected Application Output & Behavior

When launched, a native desktop GUI window opens:

- **Header Controls:** Enter a task title (e.g., _"Finish MERN API Docs"_), select a priority from the dropdown (_High_), and click **Add Task**.
- **List Box:** The task instantly appends to the central list formatted as `[HIGH] Finish MERN API Docs`.
- **Interactivity:** Selecting any row in the list and clicking **Remove Selected** removes it dynamically without closing the app.

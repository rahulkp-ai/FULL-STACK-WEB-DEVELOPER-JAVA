# Expense

- **Instructor: RAHUL KP**

### Project Structure & Package Layout

Create a standard **Java** project in IntelliJ IDEA with the following package structure inside `src/`:

```text
src/
└── com/
    └── expensetracker/
        ├── model/
        │   └── Expense.java
        ├── ui/
        │   └── TrackerWindow.java
        └── Main.java

```

---

### Step 1: Create the Data Model (`Expense.java`)

In package `com.expensetracker.model`, create `Expense.java` to model individual financial entries:

```java
package com.expensetracker.model;

public class Expense {
    private final String title;
    private final double amount;
    private final String category;

    public Expense(String title, double amount, String category) {
        this.title = title;
        this.amount = amount;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s - $%.2f", category, title, amount);
    }
}

```

---

### Step 2: Build the Graphical UI (`TrackerWindow.java`)

In package `com.expensetracker.ui`, create `TrackerWindow.java` to render the input fields, tabular list, and dynamically updating summary labels:

```java
package com.expensetracker.ui;

import com.expensetracker.model.Expense;

import javax.swing.*;
import java.awt.*;

public class TrackerWindow extends JFrame {
    private final JTextField titleField;
    private final JTextField amountField;
    private final JComboBox<String> categoryBox;
    private final DefaultListModel<Expense> listModel;
    private final JList<Expense> expenseList;

    // Summary Labels
    private final JLabel totalLabel;
    private final JLabel countLabel;
    private final JLabel avgLabel;

    public TrackerWindow() {
        setTitle("IntelliJ Expense Tracker");
        setSize(520, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Form Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 8, 8));
        inputPanel.setBorder(BorderFactory.createTitledBorder(" Add New Expense "));

        titleField = new JTextField();
        amountField = new JTextField();
        String[] categories = {"Food", "Transport", "Utilities", "Entertainment", "Other"};
        categoryBox = new JComboBox<>(categories);

        inputPanel.add(new JLabel("  Item Title:"));
        inputPanel.add(titleField);
        inputPanel.add(new JLabel("  Amount ($):"));
        inputPanel.add(amountField);
        inputPanel.add(new JLabel("  Category:"));
        inputPanel.add(categoryBox);

        // Action Buttons Panel
        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton addButton = new JButton("Add Expense");
        JButton removeButton = new JButton("Remove Selected");
        actionPanel.add(addButton);
        actionPanel.add(removeButton);

        // Combined Top Panel
        JPanel topContainer = new JPanel(new BorderLayout());
        topContainer.add(inputPanel, BorderLayout.CENTER);
        topContainer.add(actionPanel, BorderLayout.SOUTH);

        // Center List View
        listModel = new DefaultListModel<>();
        expenseList = new JList<>(listModel);
        expenseList.setFont(new Font("Monospaced", Font.PLAIN, 13));
        JScrollPane scrollPane = new JScrollPane(expenseList);
        scrollPane.setBorder(BorderFactory.createTitledBorder(" Expense Ledger "));

        // Bottom Summary Dashboard
        JPanel summaryPanel = new JPanel(new GridLayout(1, 3, 10, 5));
        summaryPanel.setBorder(BorderFactory.createEtchedBorder());

        totalLabel = new JLabel("Total: $0.00", SwingConstants.CENTER);
        countLabel = new JLabel("Items: 0", SwingConstants.CENTER);
        avgLabel = new JLabel("Average: $0.00", SwingConstants.CENTER);

        totalLabel.setFont(new Font("SansSerif", Font.BOLD, 13));
        countLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
        avgLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));

        summaryPanel.add(totalLabel);
        summaryPanel.add(countLabel);
        summaryPanel.add(avgLabel);

        // Assembly
        add(topContainer, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(summaryPanel, BorderLayout.SOUTH);

        // Listeners
        addButton.addActionListener(e -> addExpense());
        removeButton.addActionListener(e -> removeSelectedExpense());
    }

    private void addExpense() {
        String title = titleField.getText().trim();
        String rawAmount = amountField.getText().trim();

        if (title.isEmpty() || rawAmount.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please complete all input fields.",
                                          "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            double amount = Double.parseDouble(rawAmount);
            if (amount <= 0) throw new NumberFormatException();

            String category = (String) categoryBox.getSelectedItem();
            Expense entry = new Expense(title, amount, category);

            listModel.addElement(entry);
            titleField.setText("");
            amountField.setText("");

            updateSummary();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid positive numerical amount.",
                                          "Invalid Amount", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removeSelectedExpense() {
        int selectedIndex = expenseList.getSelectedIndex();
        if (selectedIndex != -1) {
            listModel.remove(selectedIndex);
            updateSummary();
        } else {
            JOptionPane.showMessageDialog(this, "Select an entry from the ledger to remove.",
                                          "Selection Error", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void updateSummary() {
        double total = 0;
        int count = listModel.getSize();

        for (int i = 0; i < count; i++) {
            total += listModel.getElementAt(i).getAmount();
        }

        double average = (count > 0) ? total / count : 0.0;

        totalLabel.setText(String.format("Total: $%.2f", total));
        countLabel.setText("Items: " + count);
        avgLabel.setText(String.format("Average: $%.2f", average));
    }

    public void display() {
        SwingUtilities.invokeLater(() -> setVisible(true));
    }
}

```

---

### Step 3: Launch the Application (`Main.java`)

In package `com.expensetracker`, create `Main.java`:

```java
package com.expensetracker;

import com.expensetracker.ui.TrackerWindow;

public class Main {
    public static void main(String[] args) {
        TrackerWindow window = new TrackerWindow();
        window.display();
    }
}

```

---

### How to Run in IntelliJ IDEA

1. Navigate to `src/com/expensetracker/Main.java`.
2. Click the **Green Play Icon** ($\triangleright$) beside `public class Main` or inside `main()`.
3. Press **Shift + F10** (Windows/Linux) or **Control + R** (macOS).

---

### Expected Output & Behavior

- **Input Form:** Enter an Item Title (_"Coffee & Bagel"_), Amount (_"12.50"_), select Category (_"Food"_), and click **Add Expense**.
- **Ledger View:** The expense prints to the list with uniform formatting: `[Food] Coffee & Bagel - $12.50`.
- **Real-time Dashboard:** The bottom panel recalculates totals live. Adding another item (_"Bus Pass"_, _$45.00_) automatically updates the labels to:
- `Total: $57.50`
- `Items: 2`
- `Average: $28.75`

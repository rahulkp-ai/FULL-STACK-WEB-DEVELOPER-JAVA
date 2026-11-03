### Problem Statement

- **Instructor: RAHUL KP**

You manage an employee database and need to find the total number of employees and the **average salary** for each department, but _only_ for departments where the average salary is **greater than ₹50,000**, ordered from highest average salary to lowest.

---

### 1. SQL Script

Copy and paste the following script into your MySQL Workbench query editor:

```sql
-- Step 1: Ensure you are using the correct database
CREATE DATABASE IF NOT EXISTS company_db;
USE company_db;

-- Step 2: Create the employees table
CREATE TABLE IF NOT EXISTS employees (
    emp_id INT AUTO_INCREMENT PRIMARY KEY,
    emp_name VARCHAR(100) NOT NULL,
    department VARCHAR(50) NOT NULL,
    salary DECIMAL(10, 2) NOT NULL
);

-- Step 3: Insert sample employee data
INSERT INTO employees (emp_name, department, salary) VALUES
('Aarav Sharma', 'Engineering', 85000.00),
('Ananya Patel', 'Engineering', 92000.00),
('Rahul Verma', 'Marketing', 45000.00),
('Priya Nair', 'Marketing', 48000.00),
('Vikram Singh', 'Engineering', 78000.00),
('Neha Gupta', 'HR', 55000.00),
('Rohan Das', 'HR', 52000.00);

-- Step 4: Aggregate data using GROUP BY and filter with HAVING
SELECT
    department,
    COUNT(emp_id) AS employee_count,
    ROUND(AVG(salary), 2) AS average_salary
FROM employees
GROUP BY department
HAVING AVG(salary) > 50000.00
ORDER BY average_salary DESC;

```

---

### 2. How to Execute in MySQL Workbench

1. **Open your SQL Workspace:** Open MySQL Workbench and click your active connection.
2. **Open a New Query Tab:** Press `Ctrl + T` (Windows) or `Cmd + T` (Mac) to open a fresh script tab.
3. **Paste & Run:** Paste the script above into the window and click the **Lightning Bolt icon** (⚡) in the top toolbar to execute all statements.

---

### 3. Expected Result

In the **Result Grid** at the bottom of your workspace, you will see the filtered and aggregated summary. _(Notice that the Marketing department is automatically excluded because its average salary is below ₹50,000)._

| department  | employee_count | average_salary |
| ----------- | -------------- | -------------- |
| Engineering | 3              | 85000.00       |
| HR          | 2              | 53500.00       |

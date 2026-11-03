### Problem Statement

- **Instructor: RAHUL KP**

You manage a store inventory and need to find all products that cost **more than ₹1,000**, sorted from highest price to lowest.

---

### 1. SQL Script

In your MySQL Workbench query editor:

```sql
-- Step 1: Create a temporary database and select it
CREATE DATABASE IF NOT EXISTS store_db;
USE store_db;

-- Step 2: Create the products table
CREATE TABLE IF NOT EXISTS products (
    product_id INT AUTO_INCREMENT PRIMARY KEY,
    product_name VARCHAR(100) NOT NULL,
    price DECIMAL(10, 2) NOT NULL
);

-- Step 3: Insert sample product data
INSERT INTO products (product_name, price) VALUES
('Wireless Mouse', 850.00),
('Mechanical Keyboard', 3500.00),
('USB-C Hub', 1200.00),
('Mouse Pad', 450.00),
('Gaming Headset', 2800.00);

-- Step 4: Run the query to fetch items priced above 1000
SELECT product_name, price
FROM products
WHERE price > 1000.00
ORDER BY price DESC;

```

---

### 2. How to Execute in MySQL Workbench

1. **Open your SQL Workspace:** Double-click your connection (e.g., `Local Instance 3306`) from the MySQL Workbench home screen.
2. **Open a New Query Tab:** Click the **`+ SQL`** button in the top-left toolbar (or press `Ctrl + T` / `Cmd + T`).
3. **Paste the Code:** Paste the SQL script above into the main editor window.
4. **Run the Script:** Click the **Lightning Bolt icon** (⚡) on the top toolbar to execute the entire script.

---

### 3. Expected Result

After running the script, look at the **Result Grid** tab at the bottom of the main query window:

| product_name        | price   |
| ------------------- | ------- |
| Mechanical Keyboard | 3500.00 |
| Gaming Headset      | 2800.00 |
| USB-C Hub           | 1200.00 |

_Note: The **Action Output** panel at the very bottom will show green checkmarks for each executed command._

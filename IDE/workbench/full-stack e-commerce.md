### Problem Statement

- **Instructor: RAHUL KP**

You are building an inventory and order management subsystem for a full-stack e-commerce or retail application (similar to a modular architecture like _IntervalBite_). Instead of writing everything into a single file or script, production systems separate database schema definitions, data seeding, and analytical reporting queries across multiple files to maintain clean separation of concerns, version control, and modular execution.

We will split this scenario into **3 distinct files**:

1. `01_schema.sql` — Defines the relational database structure (Tables, Foreign Keys, Indexes).
2. `02_seed_data.sql` — Inserts realistic transactional sample data.
3. `03_analytics_queries.sql` — Runs complex business queries joining multiple tables with aggregations and window functions.

---

### File 1: `01_schema.sql` (Database Architecture)

_This file sets up the relational structure with foreign key constraints to maintain referential integrity between categories, products, customers, and orders._

```sql
-- Create and select the database
CREATE DATABASE IF NOT EXISTS retail_system_db;
USE retail_system_db;

-- Drop tables if they exist to allow clean re-runs
DROP TABLE IF EXISTS order_items;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS customers;

-- 1. Categories Table
CREATE TABLE categories (
    category_id INT AUTO_INCREMENT PRIMARY KEY,
    category_name VARCHAR(50) NOT NULL UNIQUE
);

-- 2. Products Table
CREATE TABLE products (
    product_id INT AUTO_INCREMENT PRIMARY KEY,
    product_name VARCHAR(100) NOT NULL,
    category_id INT NOT NULL,
    unit_price DECIMAL(10, 2) NOT NULL,
    stock_qty INT DEFAULT 0,
    FOREIGN KEY (category_id) REFERENCES categories(category_id)
);

-- 3. Customers Table
CREATE TABLE customers (
    customer_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    city VARCHAR(50) NOT NULL
);

-- 4. Orders Table
CREATE TABLE orders (
    order_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT NOT NULL,
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    order_status VARCHAR(20) DEFAULT 'Completed',
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);

-- 5. Order Items (Bridge Table for Many-to-Many relationship)
CREATE TABLE order_items (
    order_item_id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL,
    line_total DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders(order_id),
    FOREIGN KEY (product_id) REFERENCES products(product_id)
);

```

---

### File 2: `02_seed_data.sql` (Initial Data Population)

_This script populates the relational tables with baseline test data._

```sql
USE retail_system_db;

-- Insert Categories
INSERT INTO categories (category_name) VALUES
('Electronics'),
('Apparel'),
('Home & Kitchen');

-- Insert Products
INSERT INTO products (product_name, category_id, unit_price, stock_qty) VALUES
('Wireless Mechanical Keyboard', 1, 3500.00, 25),
('Ergonomic Mouse', 1, 1200.00, 40),
('Cotton Oversized Hoodie', 2, 1800.00, 60),
('Stainless Steel Insulated Flask', 3, 950.00, 30);

-- Insert Customers
INSERT INTO customers (customer_name, email, city) VALUES
('Aarav Sharma', 'aarav.sharma@example.com', 'Bengaluru'),
('Priya Nair', 'priya.nair@example.com', 'Kochi'),
('Rohan Das', 'rohan.das@example.com', 'Chennai');

-- Insert Orders
INSERT INTO orders (customer_id, order_status) VALUES
(1, 'Completed'),
(2, 'Completed'),
(1, 'Processing');

-- Insert Order Items
INSERT INTO order_items (order_id, product_id, quantity, line_total) VALUES
(1, 1, 1, 3500.00), -- Aarav bought 1 Keyboard
(1, 2, 2, 2400.00), -- Aarav bought 2 Mice
(2, 3, 1, 1800.00), -- Priya bought 1 Hoodie
(3, 4, 2, 1900.00); -- Aarav bought 2 Flasks

```

---

### File 3: `03_analytics_queries.sql` (Complex Business Intelligence)

_This script runs advanced multi-table joins, conditional aggregations, and ranking functions to analyze customer lifetime value and product performance._

```sql
USE retail_system_db;

-- Query 1: Multi-table join calculating total spending per customer with order count
SELECT
    c.customer_id,
    c.customer_name,
    c.city,
    COUNT(DISTINCT o.order_id) AS total_orders,
    SUM(oi.line_total) AS lifetime_spend
FROM customers c
JOIN orders o ON c.customer_id = o.customer_id
JOIN order_items oi ON o.order_id = oi.order_id
GROUP BY c.customer_id, c.customer_name, c.city
ORDER BY lifetime_spend DESC;

-- Query 2: Product sales performance summary using category lookup
SELECT
    cat.category_name,
    p.product_name,
    SUM(oi.quantity) AS total_units_sold,
    SUM(oi.line_total) AS total_revenue
FROM products p
JOIN categories cat ON p.category_id = cat.category_id
LEFT JOIN order_items oi ON p.product_id = oi.product_id
GROUP BY cat.category_name, p.product_name
ORDER BY total_revenue DESC;

```

---

### How to Execute Multiple Files in MySQL Workbench

When working with multi-file architectures in Workbench, you have two primary execution options:

#### Method A: Sequential Tab Execution (Interactive)

1. Open MySQL Workbench and ensure your connection is active.
2. Open **File > Open Script...** (or press `Ctrl + Shift + O` / `Cmd + Shift + O`).
3. Select and open **`01_schema.sql`**, then click the **Lightning Bolt (⚡)** to execute it. Verify the green checkmarks in the Action Output.
4. Open **`02_seed_data.sql`**, and execute it using the Lightning Bolt.
5. Open **`03_analytics_queries.sql`**, and execute it to inspect the final Result Grid.

#### Method B: Using MySQL Source Command (Terminal / Workbench Script Execution)

If you prefer running them as script batches, you can open a single query tab and use the `SOURCE` command pointing to your local directory paths:

```sql
SOURCE /path/to/your/folder/01_schema.sql;
SOURCE /path/to/your/folder/02_seed_data.sql;
SOURCE /path/to/your/folder/03_analytics_queries.sql;

```

---

### Expected Result (From Query 1 & 2 in File 3)

When you execute **Query 1** (`lifetime_spend`), the Result Grid will output:

| customer_id | customer_name | city      | total_orders | lifetime_spend |
| ----------- | ------------- | --------- | ------------ | -------------- |
| 1           | Aarav Sharma  | Bengaluru | 2            | 7800.00        |
| 2           | Priya Nair    | Kochi     | 1            | 1800.00        |

When you execute **Query 2** (`total_revenue`), you will see how products and categories aggregate across sales:

| category_name  | product_name                    | total_units_sold | total_revenue |
| -------------- | ------------------------------- | ---------------- | ------------- |
| Electronics    | Wireless Mechanical Keyboard    | 1                | 3500.00       |
| Electronics    | Ergonomic Mouse                 | 2                | 2400.00       |
| Apparel        | Cotton Oversized Hoodie         | 1                | 1800.00       |
| Home & Kitchen | Stainless Steel Insulated Flask | 2                | 1900.00       |

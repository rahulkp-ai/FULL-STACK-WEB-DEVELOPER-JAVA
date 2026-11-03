## Introduction

- **Instructor: RAHUL KP**

MySQL Workbench is a unified visual tool for database architects, developers, and DBAs. It provides data modeling, SQL development, and comprehensive administration tools for server configuration, user administration, and backup.

Here is a complete instruction guide to setting up and using MySQL Workbench effectively.

---

### Step 1: Connecting to a MySQL Server

Before running queries or managing schemas, you must establish a connection to your local or remote database instance.

1. **Open MySQL Workbench** on your machine.
2. On the home screen (**MySQL Connections** tab), click the **`+` (plus)** icon next to **MySQL Connections** to create a new connection.
3. Configure your connection settings in the setup window:

- **Connection Name:** Give it a recognizable name (e.g., `Local Development`).
- **Connection Method:** Choose `Standard (TCP/IP)` for standard local or remote server connections.
- **Hostname:** Enter `127.0.0.1` or `localhost` (default for local servers).
- **Port:** Enter `3306` (default MySQL port).
- **Username:** Enter your database user account (typically `root` for local setups).

4. Click **Test Connection** to verify your credentials. Enter your password when prompted.
5. Click **OK** to save the connection. It will now appear as a clickable card on your home screen. Click it to open the workspace.
   _Verification:_ The SQL Editor workspace loads, showing the **Navigator** pane on the left and the **Query 1** editor window in the center.

---

### Step 2: Creating and Managing Schemas (Databases)

Databases in MySQL are referred to as schemas. You can manage them using the visual interface or raw SQL commands.

1. In the left-hand **Navigator** pane, switch from the **Administration** tab to the **Schemas** tab.
2. **Create a new schema:** Right-click anywhere in the blank space of the Schemas tab and select **Create Schema...**
3. Enter your desired schema name (e.g., `inventory_db`) in the name field and click **Apply**.
4. Review the generated SQL script in the preview window and click **Apply**, then **Finish**.
   _Verification:_ The new schema appears in the sidebar list. Double-clicking it expands tables, views, stored procedures, and functions.

---

### Step 3: Writing and Executing SQL Queries

The SQL Editor allows you to run DDL (Data Definition Language) and DML (Data Manipulation Language) statements.

1. Inside your active connection workspace, ensure your new schema is set as the default (right-click the schema name in the sidebar and select **Set as Default Schema**).
2. Type or paste your SQL commands into the **Query 1** editor window:

```sql
CREATE TABLE products (
    product_id INT AUTO_INCREMENT PRIMARY KEY,
    product_name VARCHAR(100) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    stock_quantity INT DEFAULT 0
);

INSERT INTO products (product_name, price, stock_quantity)
VALUES ('Wireless Mouse', 1299.00, 45);

SELECT * FROM products;

```

3. **Execute the query:**

- To run all statements in the editor, click the **lightning bolt icon** (⚡) in the top toolbar.
- To run a single statement, place your text cursor on that specific line and click the **lightning bolt with a cursor icon** (⚡ with vertical line).
  _Verification:_ The **Action Output** panel at the bottom displays green checkmarks indicating successful execution, and the **Result Grid** tab displays the returned rows for `SELECT` queries.

---

### Step 4: Exporting and Importing Data (Backups)

Workbench includes built-in tools via MySQL Shell integration to dump and restore database files (`.sql`).

1. In the top navigation menu, go to **Server** > **Data Export**.
2. Under **Select Objects**, check the boxes next to the schemas or specific tables you want to export.
3. Choose your export options:

- Select **Export to Dump Project Folder** or **Export to Self-Contained File** (generates a single `.sql` file).
- Check **Include Create Schema** to ensure table structures are recreated on import.

4. Click **Start Export** in the bottom-right corner.
   _Verification:_ The progress bar reaches 100% and the log indicates "Export completed successfully."

To restore a database, navigate to **Server** > **Data Import**, select **Import from Self-Contained File**, choose your `.sql` file, pick the target schema, and click **Start Import**.

---

### Step 5: Visual Database Design (EER Modeling)

MySQL Workbench allows you to design databases visually using Enhanced Entity-Relationship (EER) diagrams.

1. From the Workbench home screen, click **File** > **New Model**.
2. Click **Add Diagram** under the Physical Schemas section.
3. Use the sidebar tools to add tables, define columns, set data types, and draw foreign key relationships between tables visually.
4. **Forward Engineer to Server:** Once your design is complete, go to **Database** > **Forward Engineer...**, enter your connection credentials, and Workbench will automatically generate and execute the DDL script to build the live database tables from your visual model.
   _Verification:_ The generated tables appear in your live database schema via the Schemas tab.

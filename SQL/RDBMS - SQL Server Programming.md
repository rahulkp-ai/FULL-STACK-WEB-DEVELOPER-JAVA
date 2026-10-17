# RDBMS & SQL Server Programming

---

- **Topic:** RDBMS & SQL Server Programming

- **Instructor: RAHUL KP**

## SQL RDBMS Concepts

### 1. What is an RDBMS?

A Relational Database Management System (RDBMS) is software that manages databases using a relational model—meaning data is organized into tables (relations) consisting of rows (tuples/records) and columns (attributes/fields).

- **Key Characteristics:**
  - **Tabular Structure:** Data is stored in two-dimensional grids.
  - **Schema-Driven:** Structure, data types, and constraints must be defined upfront before inserting data.
  - **Set-Oriented:** Operations affect entire sets of rows rather than iterating record-by-record (unlike procedural loops).

### 2. Core Relational Terminology

- **Table (Entity):** Represents a specific subject or object (e.g., `Customers`, `Orders`).
- **Row (Record):** A single, distinct instance of an entity.
- **Column (Attribute):** A specific property or characteristic of an entity.
- **Primary Key (PK):** A column or set of columns that uniquely identifies each row in a table. It cannot be `NULL` and must contain unique values.
- **Foreign Key (FK):** A column or combination of columns that establishes a link between the data in two tables, enforcing referential integrity.

### 3. ACID Properties (Data Reliability)

SQL Server guarantees data reliability through ACID transactions:

- **Atomicity:** "All or nothing." If any part of a transaction fails, the entire transaction is rolled back.
- **Consistency:** A transaction must take the database from one valid state to another, maintaining all predefined rules and constraints.
- **Isolation:** Concurrent transactions execute independently without interfering with each other.
- **Durability:** Once a transaction is committed, its changes are permanent, even in the event of a system crash.

---

## SQL Server Data Types

Choosing the correct data type is critical for performance, storage optimization, and data integrity. SQL Server categorizes data types into several core families.

### 1. Numeric Data Types

- **Exact Numerics:**
  - `INT`: 4 bytes. Range: $-2,147,483,648$ to $2,147,483,647$.
  - `BIGINT`: 8 bytes. For very large integer values.
  - `DECIMAL(p, s)` / `NUMERIC(p, s)`: Exact numeric data with fixed precision ($p$) and scale ($s$). Example: `DECIMAL(10, 2)` can store up to 10 digits total, with 2 digits after the decimal point (ideal for financial data).
- **Approximate Numerics:**
  - `FLOAT` / `REAL`: Floating precision number data for scientific calculations where absolute precision is less critical.

### 2. Character and String Data Types

- **Non-Unicode (ASCII - 1 byte per character):**
  - `CHAR(n)`: Fixed-length string. Pads with spaces if the input is shorter than $n$.
  - `VARCHAR(n)`: Variable-length string. Only uses storage space for the characters entered (up to $n$).
  - `VARCHAR(MAX)`: Stores up to $2 \text{ GB}$ of character data.
- **Unicode (UTF-16 - 2 bytes per character, supports international languages):**
  - `NCHAR(n)`: Fixed-length Unicode string.
  - `NVARCHAR(n)`: Variable-length Unicode string (industry standard for text).
  - `NVARCHAR(MAX)`: Stores up to $1 \text{ GB}$ of Unicode text.

### 3. Date and Time Data Types

- `DATE`: Stores date only (YYYY-MM-DD). Range: 0001-01-01 to 9999-12-31.
- `TIME`: Stores time only (HH:MM:SS[.nnnnnnn]).
- `DATETIME2`: Combines date and time with fractional seconds precision. Recommended over legacy `DATETIME`.
- `DATETIMEOFFSET`: Includes time zone awareness (UTC offset).

### 4. Binary and Other Data Types

- `BIT`: Stores Boolean states (0, 1, or `NULL`).
- `UNIQUEIDENTIFIER`: Stores a Globally Unique Identifier (GUID / UUID) via `NEWID()`.
- `VARBINARY(n)` / `VARBINARY(MAX)`: Stores raw binary data (images, documents, compiled code).

---

## Introduction to SQL Server Programming & T-SQL

Transact-SQL (T-SQL) is Microsoft's proprietary extension to SQL, adding procedural programming capabilities, variables, and error handling.

### 1. Variables and Flow Control

```sql
-- Declaring and setting variables
DECLARE @CustomerID INT = 5;
DECLARE @CustomerName NVARCHAR(100);

-- Conditional logic (IF...ELSE)
IF @CustomerID IS NOT NULL
BEGIN
    SELECT @CustomerName = CompanyName
    FROM Customers
    WHERE CustomerID = @CustomerID;

    PRINT 'Found customer: ' + @CustomerName;
END
ELSE
BEGIN
    PRINT 'Invalid Customer ID.';
END
```

### 2. Stored Procedures

Stored procedures are precompiled collections of T-SQL statements stored inside the database engine.

```sql
-- Creating a stored procedure
CREATE PROCEDURE sp_GetCustomerOrders
    @CustomerID INT
AS
BEGIN
    SET NOCOUNT ON;

    SELECT OrderID, OrderDate, TotalAmount
    FROM Orders
    WHERE CustomerID = @CustomerID
    ORDER BY OrderDate DESC;
END;
GO

-- Executing the stored procedure
EXEC sp_GetCustomerOrders @CustomerID = 5;
```

### 3. Error Handling (`TRY...CATCH`)

Robust SQL Server scripts handle runtime errors gracefully using structured exception handling.

```sql
BEGIN TRY
    BEGIN TRANSACTION;

    -- Simulate an operation that might fail (e.g., divide by zero)
    DECLARE @Result INT = 10 / 0;

    COMMIT TRANSACTION;
END TRY
BEGIN CATCH
    IF @@TRANCOUNT > 0
        ROLLBACK TRANSACTION;

    -- Capture error details
    SELECT
        ERROR_NUMBER() AS ErrorNumber,
        ERROR_MESSAGE() AS ErrorMessage;
END CATCH;
```

---

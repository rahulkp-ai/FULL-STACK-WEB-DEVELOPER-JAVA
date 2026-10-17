# Lecture Notes: SQL Operators, Syntax & Joins (SQL Server / T-SQL)

- **Topic:** RDBMS & SQL Server Programming
- **Instructor: RAHUL KP**

---

## SQL Syntax & Fundamentals

### 1. Introduction to T-SQL in SQL Server

- **T-SQL (Transact-SQL):** Microsoft's proprietary extension to the SQL standard used in SQL Server. It includes procedural programming, local variables, and localized error handling.
- **Case Sensitivity:** SQL Server is generally **case-insensitive** for keywords and object names (depending on collation), but case-sensitive for data values depending on database settings.

### 2. The Core `SELECT` Statement Structure

The basic skeleton of a query executed in SQL Server:

```sql
SELECT TOP 10 [Column1], [Column2]
FROM [SchemaName].[TableName]
WHERE [Condition]
GROUP BY [Column1]
HAVING [AggregateCondition]
ORDER BY [Column1] ASC;

```

#### Logical Processing Order of a Query

While you write a query starting with `SELECT`, SQL Server logically processes the clauses in a specific sequence:

```mermaid
flowchart RL
    A[1. FROM / JOINs] --> B[2. WHERE]
    B --> C[3. GROUP BY]
    C --> D[4. HAVING]
    D --> E[5. SELECT / Expressions]
    E --> F[6. DISTINCT]
    F --> G[7. ORDER BY]
    G --> H[8. TOP / OFFSET-FETCH]

```

### 3. Essential Clauses & Modifiers

- **`DISTINCT`:** Removes duplicate rows from the result set.

```sql
SELECT DISTINCT DepartmentID FROM Employees;

```

- **`TOP` (SQL Server Specific):** Limits the number of rows returned (unlike `LIMIT` in MySQL/PostgreSQL).

```sql
SELECT TOP 5 FirstName, Salary
FROM Employees
ORDER BY Salary DESC;

```

- **`AS` (Aliases):** Renames columns or tables for readability in the output.

```sql
SELECT FirstName AS GivenName, Salary * 12 AS AnnualSalary
FROM Employees;

```

---

## SQL Operators

Operators are used to specify conditions in SQL statements and to perform operations on data.

### 1. Comparison Operators

Used in the `WHERE` clause to filter records.

| Operator     | Description                   | Example                      |
| ------------ | ----------------------------- | ---------------------------- |
| `=`          | Equal to                      | `WHERE Department = 'IT'`    |
| `<>` or `!=` | Not equal to                  | `WHERE Status <> 'Inactive'` |
| `>` / `<`    | Greater than / Less than      | `WHERE Age > 30`             |
| `>=` / `<=`  | Greater/Less than or equal to | `WHERE Salary >= 50000`      |

### 2. Logical Operators

Combine multiple conditions in a `WHERE` or `HAVING` clause.

- **`AND`:** Returns TRUE if all conditions are true.
- **`OR`:** Returns TRUE if at least one condition is true.
- **`NOT`:** Reverses the value of any other operator.

```sql
SELECT *
FROM Employees
WHERE Department = 'Sales' AND Salary > 60000;

```

### 3. Special Operators in SQL Server

- **`BETWEEN`:** Checks if a value is within a range (inclusive).

```sql
SELECT *
FROM Products WHERE Price BETWEEN 10 AND 50;

```

- **`IN`:** Checks if a value matches any value in a specified list.

```sql
SELECT *
FROM Employees WHERE City IN ('New York', 'London', 'Tokyo');

```

- **`LIKE` (Pattern Matching):** Uses wildcard characters (`%` for multi-character, `_` for single character).

```sql
-- Finds names starting with 'J'
SELECT *
FROM Employees WHERE FirstName LIKE 'J%';

```

- **`IS NULL` / `IS NOT NULL`:** Tests for missing or null values (cannot use `= NULL`).

```sql
SELECT *
FROM Employees WHERE MiddleName IS NULL;

```

---

## SQL Joins

Joins are used to combine rows from two or more tables based on a related column between them.

### 1. Visualizing Relational Data

Databases normalize data into multiple tables to reduce redundancy. Joins bring this data back together logically.

```mermaid
graph LR
    subgraph "Table A (Employees)"
        A1[Employee ID 1: Rahul]
        A2[Employee ID 2: Priya]
    end
    subgraph "Table B (Departments)"
        B1[Department ID 1: IT]
        B2[Department ID 2: HR]
    end
    A1 -->|DepartmentID = 1| B1
    A2 -->|DepartmentID = 1| B1

```

### 2. Types of Joins (Set & Venn Visualizations)

#### A. INNER JOIN

Returns records that have matching values in **both** tables. Unmatched rows are excluded.

```sql
SELECT * FROM A INNER JOIN B ON A.Key = B.Key;

```

```mermaid
graph LR
    subgraph "INNER JOIN (Intersection)"
        A["Table A (Excluded)"] --- AB((Intersection<br/>Matched Only))
        AB --- B["Table B (Excluded)"]
    end
    style AB fill:#ffeb3b,stroke:#333,stroke-width:2px
    style A fill:#f9f9f9,stroke:#333,stroke-dasharray: 5 5
    style B fill:#f9f9f9,stroke:#333,stroke-dasharray: 5 5

```

#### B. LEFT (OUTER) JOIN

Returns **all** records from the left table, and the matched records from the right table. If no match is found, the right side contains `NULL`.

```sql
SELECT * FROM A LEFT JOIN B ON A.Key = B.Key;

```

```mermaid
graph LR
    subgraph "LEFT JOIN (All A + Matches)"
        A["Table A (All Kept)"] --- AB((Intersection<br/>Matched))
        AB --- B["Table B (Unmatched Dropped)"]
    end
    style A fill:#ffeb3b,stroke:#333,stroke-width:2px
    style AB fill:#ffeb3b,stroke:#333,stroke-width:2px
    style B fill:#f9f9f9,stroke:#333,stroke-dasharray: 5 5

```

#### C. RIGHT (OUTER) JOIN

Returns **all** records from the right table, and the matched records from the left table. If no match is found, the left side contains `NULL`.

```sql
SELECT * FROM A RIGHT JOIN B ON A.Key = B.Key;

```

```mermaid
graph LR
    subgraph "RIGHT JOIN (All B + Matches)"
        A["Table A (Unmatched Dropped)"] --- AB((Intersection<br/>Matched))
        AB --- B["Table B (All Kept)"]
    end
    style A fill:#f9f9f9,stroke:#333,stroke-dasharray: 5 5
    style AB fill:#ffeb3b,stroke:#333,stroke-width:2px
    style B fill:#ffeb3b,stroke:#333,stroke-width:2px

```

#### D. FULL OUTER JOIN

Returns all records when there is a match in either the left or the right table. Rows with no match in either table will show `NULL` for the missing side.

```sql
SELECT * FROM A FULL OUTER JOIN B ON A.Key = B.Key;

```

```mermaid
graph LR
    subgraph "FULL OUTER JOIN (Everything)"
        A["Table A (All Kept)"] --- AB((Intersection<br/>Matched))
        AB --- B["Table B (All Kept)"]
    end
    style A fill:#ffeb3b,stroke:#333,stroke-width:2px
    style AB fill:#ffeb3b,stroke:#333,stroke-width:2px
    style B fill:#ffeb3b,stroke:#333,stroke-width:2px

```

#### E. CROSS JOIN

Produces a **Cartesian product**—every row from the first table is combined with every row from the second table ($\text{Rows}_A \times \text{Rows}_B$).

```sql
SELECT t1.Size, t2.Color FROM Sizes AS t1 CROSS JOIN Colors AS t2;

```

```mermaid
graph TD
    subgraph "Table A (Rows)"
        A1[Row A1]
        A2[Row A2]
    end
    subgraph "Table B (Rows)"
        B1[Row B1]
        B2[Row B2]
    end
    A1 -->|Combined with all| B1
    A1 -->|Combined with all| B2
    A2 -->|Combined with all| B1
    A2 -->|Combined with all| B2

```

#### F. SELF JOIN

A regular join, but the table is joined with itself. Useful for hierarchical data (e.g., Employee-Manager relationships).

```sql
SELECT emp.EmployeeName AS Employee, mgr.EmployeeName AS Manager
FROM Employees AS emp
LEFT JOIN Employees AS mgr ON emp.ManagerID = mgr.EmployeeID;

```

```mermaid
graph TD
    subgraph "Employees Table (Self-Referencing)"
        E1[Employee Instance]
        E2[Manager Instance]
    end
    E1 -->|ManagerID matches EmployeeID| E2

```

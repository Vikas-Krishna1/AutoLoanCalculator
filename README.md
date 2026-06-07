# Auto Loan Management System

## Overview

The Auto Loan Management System is a Java-based desktop application that streamlines the vehicle financing process by allowing users to create, calculate, and manage auto loan applications. The system captures applicant information, vehicle details, and loan request data, automatically computes loan amounts and monthly payments, and stores application records in a MySQL database for future review and processing.

The project demonstrates object-oriented design principles, database integration, user interface development, and financial calculation logic within a layered software architecture.

---

## Features

### Applicant Management

* Capture applicant information

  * Full Name
  * Email
  * Phone Number
  * Address
  * Date of Birth
  * Social Security Number
  * Employer Information

### Vehicle Management

* Store vehicle details

  * Make
  * Model
  * Year

### Loan Processing

* Auto price input
* Down payment tracking
* Interest rate calculations
* Sales tax handling
* Additional fees
* Cash incentive adjustments
* Monthly payment calculations
* Total loan amount calculations

### Database Persistence

* Store applicant records
* Store vehicle records
* Store loan applications
* Maintain relationships using foreign keys

### Future Enhancements

* Loan approval workflow
* Credit score integration
* Application status tracking
* Reporting dashboard
* Role-based access control
* Export to PDF and Excel

---

## System Architecture

```text
Applicant
    │
    ▼
Applicant Form
    │
    ▼
Loan Calculator Service
    │
    ▼
Database Manager
    │
    ▼
MySQL Database
```

### Project Structure

```text
AutoLoanCalc
│
├── Main.java
│
└── src
    ├── db
    │   └── DatabaseManager.java
    │
    ├── models
    │   ├── Applicant.java
    │   ├── Vehicle.java
    │   ├── AutoLoan.java
    │   └── LoanApplication.java
    │
    ├── services
    │   └── LoanCalculator.java
    │
    └── ui
        └── LoanForm.java
```

---

## Technology Stack

| Component             | Technology         |
| --------------------- | ------------------ |
| Programming Language  | Java 21            |
| User Interface        | Java Swing         |
| Database              | MySQL 9            |
| Database Connectivity | JDBC               |
| Version Control       | Git                |
| Repository Hosting    | GitHub             |
| IDE                   | Visual Studio Code |

---

## Database Design

### Applicant Table

```sql
CREATE TABLE applicant (
    applicant_id INT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(100),
    email VARCHAR(100),
    phone VARCHAR(20),
    address VARCHAR(255),
    date_of_birth DATE,
    ssn VARCHAR(20),
    employer_name VARCHAR(100)
);
```

### Vehicle Table

```sql
CREATE TABLE vehicle (
    vehicle_id INT AUTO_INCREMENT PRIMARY KEY,
    make VARCHAR(50),
    model VARCHAR(50),
    year INT
);
```

### Loan Application Table

```sql
CREATE TABLE loan_application (
    application_id INT AUTO_INCREMENT PRIMARY KEY,
    applicant_id INT,
    vehicle_id INT,
    auto_price DECIMAL(12,2),
    down_payment DECIMAL(12,2),
    loan_term INT,
    interest_rate DECIMAL(5,2),
    sales_tax DECIMAL(5,2),
    fees DECIMAL(12,2),
    cash_incentive DECIMAL(12,2),
    loan_amount DECIMAL(12,2),
    monthly_payment DECIMAL(12,2),
    application_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (applicant_id)
        REFERENCES applicant(applicant_id),

    FOREIGN KEY (vehicle_id)
        REFERENCES vehicle(vehicle_id)
);
```

---

## Financial Calculations

### Loan Amount

```text
Loan Amount =
(Auto Price - Cash Incentive)
- Down Payment
```

### Monthly Payment

The system uses the standard amortized loan payment formula:

```text
M = P × r / (1 - (1 + r)^(-n))
```

Where:

* M = Monthly Payment
* P = Principal Loan Amount
* r = Monthly Interest Rate
* n = Number of Payments

---

## Installation

### Clone Repository

```bash
git clone https://github.com/your-username/AutoLoanManagementSystem.git
cd AutoLoanManagementSystem
```

### Compile

```bash
javac -cp ".:lib/*" $(find src -name "*.java") Main.java
```

### Run

```bash
java -cp ".:lib/*:src" Main
```

---

## Sample Test Data

### Applicant

```text
Name: John Smith
Email: john.smith@email.com
Phone: 555-123-4567
Address: 123 Main Street
DOB: 1990-05-12
SSN: 123-45-6789
Employer: Tech Solutions Inc.
```

### Vehicle

```text
Make: Toyota
Model: Camry
Year: 2024
```

### Loan

```text
Auto Price: 35000
Down Payment: 5000
Loan Term: 5
Interest Rate: 6.5
Sales Tax: 8.25
Fees: 500
Cash Incentive: 1000
```

---

## Learning Outcomes

This project demonstrates:

* Object-Oriented Programming (OOP)
* Java Swing GUI Development
* JDBC Database Connectivity
* Relational Database Design
* Financial Calculation Algorithms
* Software Architecture and Layering
* CRUD Operations
* Version Control with Git and GitHub

---

## Author

Vikas Krishna

Computer Science Student

Developed as a practical software engineering and database systems project to model a real-world auto loan processing workflow.

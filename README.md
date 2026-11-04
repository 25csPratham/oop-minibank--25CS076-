# MiniBank - Console Banking Application (CEUE203 Part B)

**Student Name:** Pratham Patel  
**Student ID:** 25CS076  
**Subject:** Object-Oriented Programming (CEUE203) - Semester 3rd (ODD)  
**Institution:** Faculty of Technology and Engineering (FTE) / CSPIT, CHARUSAT  
**GitHub Repository:** https://github.com/25csPratham/oop-minibank--25CS076-

---

## 📌 Project Overview
MiniBank is an enterprise-grade console banking application built incrementally across the semester. It integrates core Object-Oriented Programming principles with modern Java features including encapsulation, inheritance, polymorphism, custom annotations with reflection, custom exception hierarchies, multithreading and synchronization, executor thread pools, inter-thread communication, NIO.2 persistence, Java Collections Framework, and generic repositories.

---

## 🏗️ Architecture & Practical Progress (Part B)

| Practical | Module / Milestone | Key Concepts Implemented |
|:---:|:---|:---|
| **01** | **Menu Shell & Header** | Record `BankInfo`, enum `MenuOption`, switch expressions, interactive CLI shell. |
| **02** | **Core Domain Entities** | `Customer` and `Account` classes, strict encapsulation, static ID generation (`CUST101`, `AC0001`). |
| **03** | **Object Identity & Nesting** | Overriding `equals`, `hashCode`, `toString`, `clone()`, static nested `Customer.Address`. |
| **04** | **Input Validation & Parsing** | Compiled regex `Validator` (Mobile, Email, PAN, IFSC), `Command` record & parser, `StatementFormatter`. |
| **05** | **Account Hierarchy & Polymorphism** | Abstract `Account`, concrete `SavingsAccount`, `CurrentAccount`, `FixedDepositAccount`, pattern matching `instanceof`. |
| **06** | **Interface-Based Design** | `Transactable`, `InterestBearing` (default methods), `WithdrawRule` (lambdas), marker `Premium`. |
| **07** | **Metadata & Reflection** | Custom annotations (`@Id`, `@Positive`, `@MaxLength`), runtime validation engine `AnnotationValidator`. |
| **08** | **Fault Tolerance & Exceptions** | Hierarchy: `BankException`, `InsufficientFundsException` (shortfall), `try-catch-finally`, `AutoCloseable` resource. |
| **09** | **Concurrency & Thread Safety** | `AccountWorker` Runnable, race condition simulation, `synchronized` deposit/withdraw, thread lifecycle. |
| **10** | **Transaction Engine & Deadlock** | `TransactionProcessor` with `ExecutorService` (4 workers), `TransactionBuffer` (`wait/notify`), consistent lock ordering. |
| **11** | **State Persistence & Audit** | `StatePersister` (Serialization), `TransactionLog` (NIO.2 append), `ReportGenerator` (DirectoryStream, summary). |
| **12** | **Collections & Sorting** | `AccountStore` (`HashMap`), transaction history (`ArrayList`), `sortedByBalance()` (`Comparator`), `Comparable<Account>`. |
| **13** | **Generic Repository** | Reusable `Repository<T>` with CRUD, `@Id` extraction by reflection, `@SafeVarargs saveAll()`, bounded generic `average()`. |
| **14** | **Capstone Application & Testing** | `BankService` layered wiring, automated `BankServiceTest` suite, executable `MiniBank.jar` packaging. |

---

## 🚀 How to Build and Run

### 1. Compile all Java sources:
```bash
javac *.java
```

### 2. Run the Capstone Application:
```bash
java MiniBank
```

### 3. Run via Executable JAR:
```bash
java -jar MiniBank.jar
```

### 4. Run Automated Unit Tests:
```bash
java BankServiceTest
```

### 5. Run Individual Practical Demonstration Drivers:
* **Practical 01:** `java DriverPractical1`
* **Practical 02:** `java DriverPractical2`
* **Practical 03:** `java DriverPractical3`
* **Practical 04:** `java DriverPractical4`
* **Practical 05:** `java DriverPractical5`
* **Practical 06:** `java DriverPractical6`
* **Practical 07:** `java DriverPractical7`
* **Practical 08:** `java DriverPractical8`
* **Practical 09:** `java DriverPractical9`
* **Practical 10:** `java DriverPractical10`
* **Practical 11:** `java DriverPractical11`
* **Practical 12:** `java DriverPractical12`
* **Practical 13:** `java DriverPractical13`
* **Practical 14:** `java MiniBank`
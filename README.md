# 📐 Low Level Design (LLD) with Java

A comprehensive repository for learning **Low Level Design** concepts using Java — covering **SOLID Principles**, popular **Design Patterns**, and real-world **System Design** case studies.

---

## 📁 Repository Structure

```
LLD-with-java/
├── SOLID/                    # SOLID Principles with Java examples
├── BuilderPattern/           # Builder Design Pattern
├── Singgalton/               # Singleton Design Pattern
├── EvelatorSystem/           # Elevator System LLD
├── Library Management System/# Library Management System LLD
├── parkingLotSystem/         # Parking Lot System LLD
└── out/production/           # Compiled output
```

---

## 🧱 SOLID Principles

SOLID is an acronym for five object-oriented design principles that help write **clean, maintainable, and scalable** code.

---

### 1. S — Single Responsibility Principle (SRP)

> *"A class should have only one reason to change."*

A class should do **one thing and do it well**. If a class handles multiple concerns, changes in one area may unexpectedly break another.

**❌ Violation:**
```java
class Invoice {
    public void calculateTotal() { /* business logic */ }
    public void printInvoice()   { /* UI concern */     }
    public void saveToDatabase() { /* persistence */    }
}
```

**✅ Correct:**
```java
class Invoice          { public void calculateTotal() { } }
class InvoicePrinter   { public void print(Invoice i) { } }
class InvoiceRepository{ public void save(Invoice i)  { } }
```

---

### 2. O — Open/Closed Principle (OCP)

> *"Software entities should be open for extension, but closed for modification."*

You should be able to **add new behaviour** without changing existing tested code — use abstraction and polymorphism.

**❌ Violation:**
```java
class DiscountCalculator {
    public double calculate(String type, double price) {
        if (type.equals("SUMMER"))  return price * 0.10;
        if (type.equals("WINTER"))  return price * 0.20;
        return 0;
    }
}
```

**✅ Correct:**
```java
interface Discount { double apply(double price); }

class SummerDiscount implements Discount {
    public double apply(double price) { return price * 0.10; }
}
class WinterDiscount implements Discount {
    public double apply(double price) { return price * 0.20; }
}

class DiscountCalculator {
    public double calculate(Discount discount, double price) {
        return discount.apply(price);
    }
}
```

---

### 3. L — Liskov Substitution Principle (LSP)

> *"Objects of a subclass should be substitutable for objects of the superclass without breaking the application."*

A subclass must honour the **contract** of its parent class — it should not throw unexpected exceptions or change expected behaviour.

**❌ Violation:**
```java
class Bird {
    public void fly() { System.out.println("Flying..."); }
}
class Penguin extends Bird {
    @Override
    public void fly() {
        throw new UnsupportedOperationException("Penguins can't fly!");
    }
}
```

**✅ Correct:**
```java
interface Bird    { void eat(); }
interface FlyingBird extends Bird { void fly(); }

class Sparrow implements FlyingBird {
    public void eat() { }
    public void fly() { System.out.println("Flying..."); }
}
class Penguin implements Bird {
    public void eat() { System.out.println("Eating..."); }
}
```

---

### 4. I — Interface Segregation Principle (ISP)

> *"A client should not be forced to implement interfaces it does not use."*

Split large, fat interfaces into **smaller, focused** ones so that classes only implement what they actually need.

**❌ Violation:**
```java
interface Worker {
    void work();
    void eat();
    void sleep();
}
class Robot implements Worker {
    public void work()  { }
    public void eat()   { /* Robots don't eat! */ }
    public void sleep() { /* Robots don't sleep! */ }
}
```

**✅ Correct:**
```java
interface Workable  { void work();  }
interface Eatable   { void eat();   }
interface Sleepable { void sleep(); }

class Human implements Workable, Eatable, Sleepable {
    public void work()  { }
    public void eat()   { }
    public void sleep() { }
}
class Robot implements Workable {
    public void work() { }
}
```

---

### 5. D — Dependency Inversion Principle (DIP)

> *"High-level modules should not depend on low-level modules. Both should depend on abstractions."*

Depend on **interfaces/abstractions**, not on concrete implementations. This decouples your code and makes testing easier.

**❌ Violation:**
```java
class MySQLDatabase {
    public void save(String data) { /* MySQL specific */ }
}
class UserService {
    private MySQLDatabase db = new MySQLDatabase(); // tightly coupled
    public void saveUser(String data) { db.save(data); }
}
```

**✅ Correct:**
```java
interface Database {
    void save(String data);
}
class MySQLDatabase implements Database {
    public void save(String data) { /* MySQL logic */ }
}
class MongoDatabase implements Database {
    public void save(String data) { /* Mongo logic */ }
}
class UserService {
    private Database db;
    public UserService(Database db) { this.db = db; } // injected
    public void saveUser(String data) { db.save(data); }
}
```

---

## 🎨 Design Patterns

### 🔨 Builder Pattern (`BuilderPattern/`)
Used to **construct complex objects step-by-step**. Useful when an object has many optional parameters.

```java
Person person = new Person.Builder("Jaivy")
                    .age(22)
                    .email("jaivy@example.com")
                    .build();
```

---

### 🔒 Singleton Pattern (`Singgalton/`)
Ensures a class has **only one instance** and provides a global access point to it.

```java
class Singleton {
    private static Singleton instance;
    private Singleton() {}
    public static Singleton getInstance() {
        if (instance == null) instance = new Singleton();
        return instance;
    }
}
```

---

## 🏗️ System Design Case Studies

### 🛗 Elevator System (`EvelatorSystem/`)
Models a real-world elevator with floors, requests, and scheduling logic.

**Key Classes:**
- `Elevator` — state (current floor, direction, doors)
- `ElevatorController` — request dispatcher
- `Request` — floor + direction

---

### 📚 Library Management System (`Library Management System/`)
Manages books, members, and borrowing operations.

**Key Classes:**
- `Book`, `Member`, `Librarian`
- `BorrowRecord`
- `LibraryService`

---

### 🅿️ Parking Lot System (`parkingLotSystem/`)
Simulates a multi-level parking lot with different vehicle types.

**Key Classes:**
- `ParkingLot`, `ParkingFloor`, `ParkingSpot`
- `Vehicle` (Car, Bike, Truck)
- `Ticket`, `EntryPanel`, `ExitPanel`

---

## 🚀 Getting Started

### Prerequisites
- Java 11+
- IntelliJ IDEA (recommended) or any Java IDE

### Clone the Repository
```bash
git clone https://github.com/CODEWITH-JAIVY/LLD-with-java.git
cd LLD-with-java
```

### Run any module
Open the project in IntelliJ IDEA and run the `Main.java` of the desired module.

---

## 🤝 Contributing

Contributions are welcome! Feel free to:
- Add new design patterns
- Add new system design problems
- Improve existing code with better LLD practices

---

## 📌 Topics Covered

| Topic | Status |
|-------|--------|
| SOLID Principles | ✅ Complete |
| Builder Pattern | ✅ Complete |
| Singleton Pattern | ✅ Complete |
| Elevator System | ✅ Complete |
| Library Management System | ✅ Complete |
| Parking Lot System | ✅ Complete |
| Factory Pattern | 🔜 Coming Soon |
| Observer Pattern | 🔜 Coming Soon |
| Strategy Pattern | 🔜 Coming Soon |

---

## 👨‍💻 Author

**CODEWITH-JAIVY**
- GitHub: [@CODEWITH-JAIVY](https://github.com/CODEWITH-JAIVY)

---

⭐ If you found this helpful, please give the repo a **star**!

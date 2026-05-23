# 🏗️ Builder Pattern — Complete Beginner's Guide

> **Design Patterns Series | Pattern #1 — Builder Pattern**
> *"Build complex objects step by step, without losing your mind."*

---

## 📌 What is a Design Pattern?

Before jumping into Builder Pattern, understand this:

> A **Design Pattern** is a **proven, reusable solution** to a **commonly occurring problem** in software design.
> It is NOT a code — it is a **blueprint/template** you follow while writing code.

Think of it like a **recipe** 🍳. The recipe doesn't cook the food for you — but it tells you the **right steps** to follow so the result is always good.

---

## 🎯 Why Builder Pattern?

Builder Pattern solves one specific problem:

> **"How do I create an object that has too many fields/attributes — cleanly, safely, and readably?"**

To understand WHY we need Builder Pattern, let's walk through the **journey of problems** that leads us there.

---

## 🚶 The Journey — From Problem to Solution

---

### 🔴 STAGE 1 — The Problem: Constructor Explosion

Suppose you have a `Product` class with **3 attributes**:

```java
public class Product {
    private String name;
    private double price;
    private String category;
}
```

Now to create objects, you write constructors. But think about this:

- Maybe `name` is required, but `price` and `category` are optional.
- Users might create a Product with just `name`
- Or with `name` + `price`
- Or with `name` + `category`
- Or with all three
- Or with just `price` + `category`...

So you end up writing **ALL possible combinations**:

```java
// 2^3 = 8 possible constructors for just 3 fields!
public Product() { }
public Product(String name) { }
public Product(double price) { }
public Product(String category) { }
public Product(String name, double price) { }
public Product(String name, String category) { }
public Product(double price, String category) { }
public Product(String name, double price, String category) { }
```

### ❌ Problems with this approach:

| Problem | Explanation |
|---|---|
| **Constructor Explosion** | 3 fields = 8 constructors. 10 fields = 1024 constructors! |
| **Unreadable** | `new Product("Phone", 999.99, "Electronics")` — what is `999.99`? What is the 3rd parameter? |
| **Unmaintainable** | Add one new field → rewrite all constructors |
| **Error-prone** | Easy to swap parameter positions accidentally |

---

### 🟡 STAGE 2 — Attempted Fix: Getter & Setter

To avoid constructor explosion, developers tried using **setters**:

```java
public class Product {
    private String name;
    private double price;
    private String category;

    // Getters
    public String getName()     { return name; }
    public double getPrice()    { return price; }
    public String getCategory() { return category; }

    // Setters
    public void setName(String name)         { this.name = name; }
    public void setPrice(double price)       { this.price = price; }
    public void setCategory(String category) { this.category = category; }
}

// Creating object:
Product p = new Product();
p.setName("iPhone");
p.setPrice(79999.99);
p.setCategory("Electronics");
```

### ✅ Benefits:
- More readable than long constructors
- No constructor explosion
- Easy to add new fields

### ❌ But Big Problems Remain:

**Problem 1 — Object is MUTABLE (can be changed anytime)**
```java
Product p = new Product();
p.setName("iPhone");
p.setPrice(79999.99);

// Somewhere else in code — accidentally changes the object!
p.setPrice(-500);  // Negative price! Nobody stops this.
p.setName(null);   // Null name! No validation.
```

**Problem 2 — Validation happens AFTER object creation**
```java
// Object is already created with wrong data
// You only find out later — too late!
Product p = new Product();
p.setPrice(-9999);   // Object is created. Price is negative. WRONG.
```

**Problem 3 — Inconsistent State**
```java
Product p = new Product();
p.setName("iPhone");
// At THIS moment — object exists but is INCOMPLETE
// If someone uses `p` right here → name exists but no price, no category!
// This is called an "inconsistent state"
```

---

### 🟠 STAGE 3 — Attempted Fix 2: HashMap

Some developers tried using a `Map` to pass values:

```java
Map<String, Object> params = new HashMap<>();
params.put("name", "iPhone");
params.put("price", 79999.99);
params.put("category", "Electronics");

Product p = new Product(params);
```

### ✅ Small benefits:
- Flexible — pass only what you need
- No explosion of constructors

### ❌ New Problems:
| Problem | Explanation |
|---|---|
| **No type safety** | Key is always `String`. Value is always `Object`. Compiler won't catch wrong types. |
| **Typo errors** | `params.put("nmae", "iPhone")` — typo in key. No error at compile time! |
| **No IDE support** | IDE can't auto-suggest field names |
| **Still no validation** | Same problem as setters |
| **Unreadable** | What keys are valid? You need to read documentation every time. |

---

### 🟢 STAGE 4 — THE REAL SOLUTION: Builder Pattern ✅

Builder Pattern solves ALL the above problems. Here's how it works:

**Core Idea:**
> Create a separate **`Builder` class** (inside the original class) that:
> 1. Has the **same fields** as the original class
> 2. Lets you **set fields one by one** in a readable way
> 3. **Validates everything BEFORE** the object is created
> 4. Creates the **final immutable object** only when you call `.build()`

---

## 💻 Complete Code — Step by Step

### Step 1 — Create the Product class with private constructor

```java
public class Product {

    // Step 1: All fields are FINAL (immutable — cannot be changed after creation)
    private final String name;          // required
    private final double price;         // required
    private final String category;      // optional
    private final String description;   // optional
    private final int stockQuantity;    // optional

    // Step 2: Private constructor — ONLY Builder can call this
    private Product(Builder builder) {
        this.name          = builder.name;
        this.price         = builder.price;
        this.category      = builder.category;
        this.description   = builder.description;
        this.stockQuantity = builder.stockQuantity;
    }

    // Step 3: Only Getters — NO setters (object is immutable)
    public String getName()        { return name; }
    public double getPrice()       { return price; }
    public String getCategory()    { return category; }
    public String getDescription() { return description; }
    public int getStockQuantity()  { return stockQuantity; }

    @Override
    public String toString() {
        return "Product{" +
            "name='"        + name        + '\'' +
            ", price="      + price             +
            ", category='"  + category    + '\'' +
            ", description='"+ description + '\'' +
            ", stock="      + stockQuantity      +
            '}';
    }

    // ─────────────────────────────────────────────────────
    // Step 4: BUILDER CLASS (static inner class)
    // ─────────────────────────────────────────────────────
    public static class Builder {

        // Same fields as Product
        private String name;           // required
        private double price;          // required
        private String category;       // optional
        private String description;    // optional
        private int stockQuantity;     // optional

        // Step 5: Builder constructor takes REQUIRED fields only
        public Builder(String name, double price) {
            // ✅ VALIDATION happens HERE — before object is even created
            if (name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException("❌ Product name cannot be null or empty!");
            }
            if (price <= 0) {
                throw new IllegalArgumentException("❌ Price must be greater than 0! Got: " + price);
            }
            this.name  = name;
            this.price = price;
        }

        // Step 6: Each optional field has its own method
        // Each method RETURNS the Builder itself → this allows METHOD CHAINING
        public Builder category(String category) {
            this.category = category;
            return this;   // ← KEY: returns Builder so you can chain calls
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder stockQuantity(int quantity) {
            if (quantity < 0) {
                throw new IllegalArgumentException("❌ Stock cannot be negative!");
            }
            this.stockQuantity = quantity;
            return this;
        }

        // Step 7: build() method creates the final Product object
        public Product build() {
            // Any final cross-field validations here
            return new Product(this);  // calls private constructor
        }
    }
}
```

---

### Step 2 — Using the Builder Pattern

```java
public class Main {
    public static void main(String[] args) {

        // ✅ Creating a FULL product — readable, clean!
        Product iPhone = new Product.Builder("iPhone 15 Pro", 134900.00)
                            .category("Electronics")
                            .description("Latest Apple flagship with titanium design")
                            .stockQuantity(50)
                            .build();

        System.out.println(iPhone);


        // ✅ Creating a PARTIAL product — only required fields
        Product basicItem = new Product.Builder("Mystery Box", 499.00)
                                .build();  // No optional fields needed

        System.out.println(basicItem);


        // ✅ Validation kicks in BEFORE object is created
        try {
            Product wrong = new Product.Builder("", -100)  // invalid!
                                .build();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            // Output: ❌ Product name cannot be null or empty!
        }
    }
}
```

**Output:**
```
Product{name='iPhone 15 Pro', price=134900.0, category='Electronics', description='Latest Apple flagship...', stock=50}
Product{name='Mystery Box', price=499.0, category='null', description='null', stock=0}
❌ Product name cannot be null or empty!
```

---

## 🔍 How Method Chaining Works — Visualized

```
new Product.Builder("iPhone", 134900)   ← Returns Builder object
    .category("Electronics")            ← Returns same Builder object
    .description("Flagship phone")      ← Returns same Builder object
    .stockQuantity(50)                  ← Returns same Builder object
    .build()                            ← Returns final Product object ✅
```

Each method call passes the Builder along like a **relay baton** 🏃, and at the end `.build()` converts it into a real Product.

---

## ⚖️ Full Comparison Table

| Feature | Constructor | Getter/Setter | HashMap | Builder Pattern |
|---|---|---|---|---|
| Readable | ❌ No | ✅ Yes | ❌ No | ✅ Yes |
| Type Safe | ✅ Yes | ✅ Yes | ❌ No | ✅ Yes |
| Validation before creation | ✅ Yes | ❌ No | ❌ No | ✅ Yes |
| Immutable object | ✅ Yes | ❌ No | ❌ No | ✅ Yes |
| Handles optional fields | ❌ No (explosion) | ✅ Yes | ✅ Yes | ✅ Yes |
| IDE autocomplete | ✅ Yes | ✅ Yes | ❌ No | ✅ Yes |
| Consistent state | ✅ Yes | ❌ No | ❌ No | ✅ Yes |
| Scalable (add new fields) | ❌ No | ✅ Yes | ✅ Yes | ✅ Yes |

**Builder Pattern wins on ALL important counts ✅**

---

## 🌍 Real World Usage

Builder Pattern is used EVERYWHERE in production systems:

```java
// Lombok @Builder (popular Java library)
@Builder
public class Product { ... }

// StringBuilder (Java built-in)
String result = new StringBuilder()
    .append("Hello")
    .append(" ")
    .append("World")
    .toString();

// HttpRequest (Java 11+)
HttpRequest request = HttpRequest.newBuilder()
    .uri(URI.create("https://api.example.com"))
    .header("Content-Type", "application/json")
    .GET()
    .build();

// Spring MockMvc (testing)
mockMvc.perform(MockMvcRequestBuilders
    .post("/products")
    .contentType(MediaType.APPLICATION_JSON)
    .content(json))
    .andExpect(status().isOk());
```

---

## 🧠 When to Use Builder Pattern?

✅ Use Builder when:
- Class has **4 or more fields**
- Many fields are **optional**
- You want **immutable objects**
- You need **validation before object creation**
- You want **readable object construction**

❌ Don't use Builder when:
- Class has only **1-2 fields** (overkill)
- All fields are always **required** (simple constructor is fine)
- Object needs to be **mutable** frequently

---

## 📝 Quick Recap

```
Problem 1: Constructor Explosion   → Too many constructors for combinations
Fix Tried: Getter/Setter           → Mutable + validation AFTER creation
Fix Tried: HashMap                 → No type safety, typo errors
✅ Real Fix: Builder Pattern       → Readable + Immutable + Validated BEFORE creation
```

---

## 🔖 Key Terms to Remember

| Term | Meaning |
|---|---|
| **Immutable** | Object cannot be changed after it is created |
| **Method Chaining** | Each method returns `this` so you can call methods one after another |
| **Constructor Explosion** | Too many constructors needed for all field combinations |
| **Consistent State** | Object is always complete and valid — never half-built |
| **Inner Static Class** | A class defined inside another class, marked `static` |

---

*Design Patterns Series — Next up: Factory Pattern 🏭*
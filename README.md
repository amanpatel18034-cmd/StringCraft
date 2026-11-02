# StringCraft – String Manipulation Toolkit 🛠️

An interactive Data Structures and Algorithms (DSA) project built with **Java (Spring Boot 3)** and modern **HTML5, CSS3, JavaScript**.

---

## 🌟 Features & Implemented Operations

StringCraft includes 7 core string manipulation operations implemented using efficient DSA concepts:

1. **Reverse String** – Two-pointer in-place array swap algorithm ($O(n)$ time, $O(1)$ auxiliary space).
2. **Count Vowels** – Efficient linear iteration for case-insensitive `[a, e, i, o, u]`.
3. **Count Consonants** – Linear iteration filtering English alphabetic consonants.
4. **Count Digits** – Fast digit counter using `Character.isDigit()`.
5. **Count Spaces** – Whitespace counter supporting tabs, spaces, and newlines.
6. **Remove Spaces** – In-place StringBuilder filtering of whitespace characters.
7. **Convert Case** – Mode-based converter for **UPPERCASE**, **lowercase**, and **tOGGLE cASE**.

---

## 🏗️ Architecture & Tech Stack

- **Backend**: Java 17, Spring Boot 3.5.x, Maven, JUnit 5
- **Frontend**: Responsive Glassmorphism UI, HTML5, CSS3 (CSS Variables for Light/Dark themes), Vanilla ES6 JavaScript
- **API Standard**: RESTful endpoints with unified JSON envelope (`StringResponse`) and `@CrossOrigin` support

---

## 🚀 How to Run the Project

### 1. Start the Java Spring Boot Backend
Open your terminal in the `backend` folder:

```bash
cd backend
./mvnw spring-boot:run
```
*(Or import the backend directory into IntelliJ IDEA / Eclipse / VS Code and run `StringcraftApplication.java`)*

The backend will start at `http://localhost:8080`.

### 2. Open the Frontend Dashboard
Open `frontend/index.html` directly in any web browser (or use Live Server / VS Code extension).

---

## 🧪 Unit Tests

Run the backend unit tests:
```bash
cd backend
./mvnw test
```
The test suite in `StringServiceTest.java` verifies all 7 operations against edge cases and invalid parameters.

---

## 👤 Developer
- **Name:** Aman Patel
- **Domain:** Java Full-Stack & DSA


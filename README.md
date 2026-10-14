# Library Management System

A console-based Library Management System developed using Core Java as my first practical Java project.

The project was built to put the Java fundamentals I had recently learned into practice, turning them into a working application with book management, member registration, borrowing operations, and library statistics.

## Features

### Book Management
- Add books with a title, author, publication year, category, and quantity.
- Search for books by ID or title.
- Display all books and available books.
- Filter books by category.
- Sort books alphabetically.
- Track total, available, and borrowed copies.

### Member Management
- Register new members.
- Search for members and display their information.
- Display registered members and their currently borrowed books.

### Borrowing and Returning
- Borrow and return books.
- Check book availability before borrowing.
- Limit members to a maximum of three active borrowings.
- Prevent members from borrowing the same book twice simultaneously.
- Record borrowing and return dates.

### Statistics
- Display library inventory statistics.
- Identify members with active borrowings.
- Find the member with the highest number of borrowings.

## Technologies and Concepts

- **Java (Core Java)** — Main programming language.
- **Object-Oriented Programming (OOP)** — Classes, objects, encapsulation, inheritance, polymorphism, and interfaces.
- **Java Collections Framework** — Managing and organizing books, members, and borrowing records.
- **Java Streams API** — Processing and filtering collections, transforming data, sorting results, and calculating statistics.
- **Exception Handling** — Managing exceptional situations and invalid operations.
- **Enums** — Representing book categories.
- **Java Date and Time API** — Recording borrowing and return dates.
- **Multithreading Fundamentals** — Applying thread-safety considerations to ID generation.
- **Git and GitHub** — Version control and source code hosting.

## Limitations

This is a small educational console application focused on practicing Core Java. Its scope is intentionally limited.

- Data is stored in memory and is lost when the application closes.
- The application uses a console-based interface.
- Input validation is limited to selected operations.
- It does not include database integration or persistent storage.

These limitations reflect the scope of the project rather than an attempt to reproduce every feature of a real-world library management system.

## How to Run

### Requirements
- Java Development Kit (JDK).
- A terminal or Java IDE.

### Steps
1. Clone the repository:

   ```bash
   git clone https://github.com/achannar06/Library-Management-System.git
   ```

2. Open the project directory.
3. Compile the Java source files according to their package structure.
4. Run the class containing the `main` method.

## Purpose

The goal of this project was to apply recently acquired Core Java knowledge through hands-on development, strengthen my understanding of object-oriented programming and the Collections and Streams APIs, and build a functional application from scratch.

## Author

Developed as a personal learning project while studying Core Java.

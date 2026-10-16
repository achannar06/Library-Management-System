# Library Management System

A console-based Library Management System developed using Core Java as my first practical Java project.

The project was built to put Java fundamentals into practice by developing a working application for managing books, registering members, handling borrowing and returning operations, and generating library statistics. Throughout development, I improved the code structure, reduced code repetition, introduced exception handling, and expanded the application from 19 to 31 menu operations.

## Features

### Book Management

- Add new books with a title, author, publication year, category, and quantity.
- Update book information and quantities.
- Soft-delete books and restore previously deleted books.
- Search for books by ID or title.
- Display all books and currently available books.
- Filter books by category.
- Sort books alphabetically by title.
- Track total, available, and borrowed copies.
- Prevent book deletion while copies are borrowed.

### Member Management

- Register new library members.
- Update member information.
- Soft-delete members and restore previously deleted members.
- Search for members by ID.
- Display registered members.
- Display the books currently borrowed by a specific member.
- Prevent member deletion while they have active borrowings.

### Borrowing and Returning

- Borrow books for registered members.
- Return individual books or return all books borrowed by a specific member.
- Check book availability before borrowing.
- Limit members to a maximum of three active borrowings.
- Prevent members from borrowing the same book more than once simultaneously.
- Record borrowing and return dates.
- Maintain borrowing records for both active and completed borrowings.
- Display complete borrowing history for a specific member.
- Search borrowing records by member and book.
- Handle invalid operations using exception handling.

### Statistics and Reports

- Display the total number of book copies.
- Display available and currently borrowed copies.
- Identify members with active borrowings.
- Find the member with the highest number of active borrowings.
- Display all borrowing records.
- Display active borrowing records.
- Display returned borrowing records.

### Console Interface

- Interactive, menu-driven interface with 31 operations.
- Repeatable workflows that allow users to perform an operation multiple times before returning to the main menu.
- Exception handling to manage failed operations without unnecessarily interrupting the workflow.
- Basic input validation for menu choices and selected operations.

## Technologies and Concepts

- **Java (Core Java)** — Main programming language.
- **Object-Oriented Programming (OOP)** — Classes, objects, encapsulation, inheritance, polymorphism, and interfaces.
- **Java Collections Framework** — Managing books, members, and borrowing records using collections.
- **Java Streams API** — Filtering, sorting, and processing collections.
- **Exception Handling** — Managing exceptional situations and invalid operations, including custom exceptions.
- **Enums** — Representing book categories.
- **Java Date and Time API** — Recording borrowing and return dates.
- **Multithreading Fundamentals** — Applying thread-safety considerations to ID generation.
- **Git and GitHub** — Version control and source code hosting.

## Limitations

This is an educational application focused on practicing Core Java and object-oriented design.

- **In-memory storage:** All application data is lost when the program terminates.
- **Console interface:** The application does not include a graphical or web interface.
- **No database integration:** Books, members, and borrowing records are not persisted between program executions.
- **Single-user workflow:** The application is designed for one librarian operating the console rather than simultaneous use by multiple users.
- **Limited data validation:** Input exceptions are handled, but validation of certain fields, such as email addresses and phone numbers, could be improved.

These limitations reflect the project's current scope and provide opportunities for future improvements.

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

*Note: Compilation and execution commands may vary depending on the project's package structure and IDE configuration.*

## Purpose

The goal of this project was to apply Core Java knowledge through hands-on development, strengthen my understanding of object-oriented programming and the Collections and Streams APIs, practice exception handling, and build a functional application from scratch.

The project also provided practical experience in designing application workflows, enforcing business rules, testing functionality, debugging logical errors, and improving code maintainability.

## Future Improvements

Potential improvements include database integration, persistent storage, a graphical or web interface, and rebuilding the application using Spring Boot to explore backend development and database interaction.

## Author

Developed as a personal learning project while studying Core Java.
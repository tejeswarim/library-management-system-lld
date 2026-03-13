# Library Management System

A comprehensive Library Management System implemented in Java demonstrating Object-Oriented Programming principles, SOLID principles, and design patterns.

## Features

### Core Features
- **Book Management**: Add, remove, update, and search books by title, author, or ISBN
- **Patron Management**: Manage library members with different membership tiers
- **Lending Process**: Checkout and return books with borrowing history tracking
- **Inventory Management**: Track available and borrowed books in real-time
- **Reservation System**: Reserve books and receive notifications when available

### Advanced Features
- **Multi-tier Patron System**: Regular and Premium patrons with different borrowing limits
- **Search Strategies**: Flexible search using Strategy pattern (Title, Author, ISBN)
- **Notification System**: Observer pattern for book availability notifications

## Design Patterns Implemented

1. **Singleton Pattern**: LibraryManager ensures single instance coordination
2. **Factory Pattern**: PatronFactory creates different patron types
3. **Strategy Pattern**: SearchStrategy for flexible book search algorithms
4. **Observer Pattern**: Notification system for book reservations

## SOLID Principles

- **Single Responsibility**: Each class has one clear purpose (BookService, PatronService, LendingService)
- **Open/Closed**: Extensible through interfaces (SearchStrategy, Observer) without modifying existing code
- **Liskov Substitution**: Patron subclasses (RegularPatron, PremiumPatron) can substitute base class
- **Interface Segregation**: Small, focused interfaces (Observer, Subject, SearchStrategy)
- **Dependency Inversion**: Services depend on abstractions, not concrete implementations

## Class Diagram

```
┌─────────────────────┐
│   LibraryManager    │ (Singleton)
│─────────────────────│
│ - instance          │
│ - bookService       │
│ - patronService     │
│ - lendingService    │
└──────────┬──────────┘
           │
           │ manages
           ▼
┌──────────────────────────────────────────────────────────┐
│                                                          │
│  ┌─────────────┐    ┌──────────────┐   ┌─────────────┐ │
│  │ BookService │    │PatronService │   │LendingService│ │
│  │─────────────│    │──────────────│   │─────────────│ │
│  │ + addBook() │    │ + addPatron()│   │ + checkout()│ │
│  │ + search()  │    │ + update()   │   │ + return()  │ │
│  └─────────────┘    └──────────────┘   └─────────────┘ │
│         │                   │                   │        │
└─────────┼───────────────────┼───────────────────┼────────┘
          │                   │                   │
          ▼                   ▼                   │
    ┌─────────┐         ┌─────────┐              │
    │  Book   │         │ Patron  │◄─────────────┘
    │─────────│         │─────────│ (abstract)
    │ - isbn  │         │ - id    │
    │ - title │         │ - name  │
    │ - author│         └────┬────┘
    │ - status│              │
    └─────────┘              │ extends
                             │
              ┌──────────────┴──────────────┐
              ▼                             ▼
      ┌───────────────┐            ┌───────────────┐
      │RegularPatron  │            │PremiumPatron  │
      │───────────────│            │───────────────│
      │ MAX_BOOKS: 5  │            │ MAX_BOOKS: 10 │
      └───────────────┘            └───────────────┘

┌──────────────────────────────────────────────────────────┐
│              Design Patterns                             │
├──────────────────────────────────────────────────────────┤
│                                                          │
│  ┌────────────────┐         ┌──────────────────┐        │
│  │PatronFactory   │         │ SearchStrategy   │◄───┐   │
│  │────────────────│         │──────────────────│    │   │
│  │+ createPatron()│         │+ search()        │    │   │
│  └────────────────┘         └──────────────────┘    │   │
│                                     △               │   │
│                                     │ implements    │   │
│                    ┌────────────────┼────────────┐  │   │
│                    │                │            │  │   │
│            ┌───────┴──────┐  ┌─────┴─────┐  ┌───┴──┴───┐
│            │TitleSearch   │  │AuthorSearch│  │ISBNSearch│
│            └──────────────┘  └────────────┘  └──────────┘
│                                                          │
│  ┌─────────┐         ┌──────────────────┐               │
│  │Subject  │         │    Observer      │               │
│  │─────────│         │──────────────────│               │
│  │+ attach()│◄───────│+ update()        │               │
│  │+ notify()│        └────────△─────────┘               │
│  └─────────┘                 │                          │
│      △                       │ implements               │
│      │                       │                          │
│      │              ┌────────┴──────────┐               │
│      │              │ PatronObserver    │               │
│      │              └───────────────────┘               │
│      │ implements                                       │
│      │                                                  │
│ ┌────┴──────────┐                                       │
│ │LendingService │                                       │
│ └───────────────┘                                       │
└──────────────────────────────────────────────────────────┘
```

## Project Structure

```
src/
├── main/
│   ├── java/com/library/
│   │   ├── LibraryManager.java          # Singleton coordinator
│   │   ├── Main.java                    # Demo application
│   │   ├── model/
│   │   │   ├── Book.java
│   │   │   ├── BookStatus.java
│   │   │   ├── Patron.java              # Abstract base class
│   │   │   ├── RegularPatron.java
│   │   │   ├── PremiumPatron.java
│   │   │   └── LendingRecord.java
│   │   ├── service/
│   │   │   ├── BookService.java
│   │   │   ├── PatronService.java
│   │   │   └── LendingService.java
│   │   ├── pattern/
│   │   │   ├── PatronFactory.java       # Factory pattern
│   │   │   ├── SearchStrategy.java      # Strategy pattern
│   │   │   ├── TitleSearchStrategy.java
│   │   │   ├── AuthorSearchStrategy.java
│   │   │   ├── ISBNSearchStrategy.java
│   │   │   ├── Observer.java            # Observer pattern
│   │   │   ├── Subject.java
│   │   │   └── PatronObserver.java
│   │   └── exception/
│   │       ├── BookNotFoundException.java
│   │       ├── PatronNotFoundException.java
│   │       ├── BookNotAvailableException.java
│   │       └── BorrowLimitExceededException.java
└── test/
    └── java/com/library/                # Test directory
```

## Technologies Used

- **Java 11**: Core programming language

## Building and Running

### Prerequisites
- Java 11 or higher

### Compile
```bash
javac -d bin -sourcepath src/main/java src/main/java/com/library/**/*.java
```

### Run
```bash
java -cp bin com.library.Main
```

## Usage Examples

### Adding Books
```java
BookService bookService = LibraryManager.getInstance().getBookService();
Book book = new Book("978-0134685991", "Effective Java", "Joshua Bloch", 2018);
bookService.addBook(book);
```

### Creating Patrons
```java
Patron patron = PatronFactory.createPatron(
    PatronFactory.PatronType.REGULAR, 
    "P001", 
    "John Doe", 
    "john@example.com"
);
patronService.addPatron(patron);
```

### Searching Books
```java
List<Book> results = bookService.searchBooks(
    new TitleSearchStrategy(), 
    "Effective"
);
```

### Checkout/Return
```java
lendingService.checkoutBook("978-0134685991", "P001");
lendingService.returnBook("978-0134685991", "P001");
```

### Reservations with Notifications
```java
PatronObserver observer = new PatronObserver(patron);
lendingService.reserveBook("978-0134685991", observer);
lendingService.attach(observer);
```

## Key Design Decisions

1. **Immutable ISBN**: Book ISBN is final to maintain data integrity
2. **Abstract Patron**: Allows different patron types with varying privileges
3. **Service Layer**: Separates business logic from models
4. **Strategy Pattern**: Enables flexible search without modifying BookService
5. **Observer Pattern**: Decouples notification logic from lending operations
6. **Exception Handling**: Custom exceptions for domain-specific errors

## Future Enhancements

- Multi-branch support with book transfers
- Fine calculation for overdue books
- Advanced recommendation system based on borrowing history
- Persistent storage with database integration
- REST API for web/mobile clients

## Author

Teja Malipeddi

## License

This project is for educational purposes.

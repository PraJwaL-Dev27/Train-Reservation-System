# 🚆 Train Reservation System

A console-based Train Reservation System developed in **Java** that allows users to search trains, reserve seats, view booking history, and cancel bookings. The application follows Object-Oriented Programming (OOP) principles and uses **JSON files** for persistent data storage, providing a simple simulation of a real-world railway reservation system.

---

## ✨ Features

### 👤 User Management
- User Registration (Sign Up)
- Secure Login with BCrypt password hashing
- Logout functionality

### 🚆 Train Management
- Search trains by source and destination
- Display available trains
- Select a train from search results
- View seat layout
- Validate seat selection
- Check seat availability

### 🎟️ Ticket Booking
- Book available seats
- Enter journey date
- Automatic Ticket ID generation using UUID
- Automatic seat number calculation
- Displays booking confirmation with ticket details

### 📋 Booking Management
- View booking history
- Cancel bookings using booking number
- Automatically free the reserved seat after cancellation

### 💾 Data Persistence
- Stores users and bookings in `users.json`
- Stores train and seat information in `trains.json`
- Data remains available even after restarting the application

---

## 🛠️ Technologies Used

- Java
- Gradle
- Jackson Databind
- BCrypt (Password Hashing)
- JSON (Local Database)
- IntelliJ IDEA

---

## 📂 Project Structure

```
src
└── main
    └── java
        └── ticket
            └── booking
                ├── entities
                ├── services
                ├── utility
                ├── local_db
                └── App.java
```

---

## 🔑 Key Concepts Implemented

- Object-Oriented Programming (OOP)
- Encapsulation
- Constructors
- Collections (List)
- Java Streams & Optional
- Exception Handling
- File Handling
- JSON Serialization & Deserialization
- Service Layer Architecture

---

## 🚀 How to Run

1. Clone this repository.
2. Open the project in IntelliJ IDEA.
3. Allow Gradle to download all dependencies.
4. Run `App.java`.
5. Register a new account or log in using an existing account.
6. Start searching and booking train tickets.

---

## 📸 Sample Workflow

```
Sign Up / Login
        │
        ▼
Search Train
        │
        ▼
Select Train
        │
        ▼
View Seat Layout
        │
        ▼
Choose Seat
        │
        ▼
Enter Journey Date
        │
        ▼
Book Ticket
        │
        ▼
View Booking History
        │
        ▼
Cancel Booking (Optional)
```

---

## 📷 Screenshots


Example:

- Main Screen
![Main interface.png](Screenshots/Main%20interface.png)
- Login Screen
![Login interface.png](Screenshots/Login%20interface.png)
- User Menu

![User Menu.png](Screenshots/User%20Menu.png)
- Train Search

![Train Search.png](Screenshots/Train%20Search.png)
- Seat Layout

![Seat layout.png](Screenshots/Seat%20layout.png)
- Ticket Booking

![Booking successful with ticket.png](Screenshots/Booking%20successful%20with%20ticket.png)
- Booking History
![My Bookings.png](Screenshots/My%20Bookings.png)
- Ticket Cancellation
![Cancel Booking.png](Screenshots/Cancel%20Booking.png)

---

## 🔮 Future Improvements

- Admin Panel
- Train Management (Add/Update/Delete Trains)
- Fare Calculation
- Waiting List & RAC
- PNR Status
- Database Integration (MySQL/PostgreSQL)
- GUI using JavaFX or Swing
- Email/SMS Ticket Confirmation

---

## 👨‍💻 Author

**Prajwal Pathak**

B.Tech CSE (AI & ML)

---

## 📄 License

This project is developed for educational and learning purposes.
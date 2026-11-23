# Ticket Booking Transportation System

A Java Swing desktop application for booking bus and ferry tickets between towns across Sabah, Malaysia.

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Usage](#usage)
- [Project Structure](#project-structure)
- [Known Limitations](#known-limitations)
- [Roadmap](#roadmap)
- [Contributing](#contributing)
- [License](#license)

## Overview

This project simulates an end-to-end ticket booking workflow for intercity bus and ferry travel within Sabah. Users can register an account, log in, select a route and schedule, and complete a simulated payment to confirm a booking.

## Features

- **Account management** - user registration and login with basic credential validation.
- **Transport selection** - choose between Bus or Ferry, and a one-way or round trip.
- **Route selection** - origin and destination from 24 towns across Sabah (e.g. Kota Kinabalu, Sandakan, Tawau, Labuan, Semporna).
- **Scheduling** - departure and return date/time selection via dropdown controls.
- **Passenger count** - supports bookings of 1 to 10 passengers.
- **Fare calculation** - Bus: RM15 per passenger · Ferry: RM5 per passenger.
- **Simulated payment** - basic card number, expiry, and CVV format validation.
- **Booking persistence** - confirmed bookings are appended to and reloaded from booking.txt.

## Tech Stack

- Java (JDK 11+)
- Java Swing (javax.swing, java.awt) for the graphical user interface

## Prerequisites

- Java Development Kit (JDK) 11 or later installed and available on your PATH

## Installation

Clone the repository:

```bash
git clone https://github.com/abigailsadom1903/ticket-booking-transportation-system.git
cd ticket-booking-transportation-system
```

Compile the application:

```bash
javac test.java
```

Run the application:

```bash
java test
```

## Usage

1. Launch the application and select **Register** to create an account, or **Login** if you already have one.
2. From the main menu, select **Book Transportation**.
3. Choose a transport type (Bus or Ferry), trip type (One Way or Round Trip), origin, destination, dates, times, and number of passengers.
4. Review the booking summary and confirm.
5. Enter simulated payment details to complete the booking. A confirmation message is displayed and the booking is saved.

## Project Structure

```
.
├── test.java      # Application source: UI screens, booking logic, and the User model
├── booking.txt    # Generated at runtime; stores confirmed booking records
└── README.md
```

## Known Limitations

- Registered user accounts are held in memory for the current session only; they are not persisted to disk or a database.
- Payment processing is simulated for demonstration purposes; no real transactions are made and no card data is stored.
- Card validation checks formatting only (16-digit number, 3-digit CVV, MM/YY expiry), not authenticity.

## Roadmap

- Persist user accounts to a file or database
- Replace simulated payment with a mock payment gateway integration
- Add automated tests for booking and fare logic
- Externalize the list of towns and fares into a configuration file

## Contributing

Contributions are welcome. Please open an issue to discuss proposed changes, or submit a pull request.

## License

No license has been specified for this project. All rights reserved by the repository owner unless stated otherwise.

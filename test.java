import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.HashMap;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class test {
    private JFrame currentFrame;
    private HashMap<String, User> userDatabase = new HashMap<>(); // Stores registered users
    private final String BOOKING_FILE = "booking.txt";
    private List<String> bookingRecords = new ArrayList<>(); // Stores booking records
    
    public static void main(String[] args) {
        new test().showInitialMenu();
    }

    // Display the initial menu
    private void showInitialMenu() {
        JFrame initialMenuFrame = new JFrame("Initial Menu");
        initialMenuFrame.setSize(400, 300);
        initialMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton loginButton = new JButton("Login 🛸");
        JButton registerButton = new JButton("Register 🚀");

        loginButton.addActionListener(e -> {
            initialMenuFrame.dispose();
            showLoginMenu();
        });

        registerButton.addActionListener(e -> {
            initialMenuFrame.dispose();
            showRegistrationMenu();
        });

        JPanel panel = new JPanel();
        panel.add(new JLabel("Welcome to the Ticket Booking Transportation System! 🚗🎫 To get started, please register for an account and log in."));
        panel.add(loginButton);
        panel.add(registerButton);
        initialMenuFrame.add(panel, BorderLayout.CENTER);

        initialMenuFrame.setVisible(true);
        currentFrame = initialMenuFrame;
    }

    private void showRegistrationMenu() {
        JFrame registrationFrame = new JFrame("Registration");
        registrationFrame.setSize(400, 400);
        registrationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextField firstNameField = new JTextField(15);
        JTextField lastNameField = new JTextField(15);
        JTextField mobilePhoneField = new JTextField(15);
        JTextField emailField = new JTextField(15);
        JTextField usernameField = new JTextField(15);
        JPasswordField passwordField = new JPasswordField(15);

        JButton registerButton = new JButton("Register 🚀");
        registerButton.addActionListener(e -> {
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String mobilePhone = mobilePhoneField.getText();
            String email = emailField.getText();
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (firstName.isEmpty() || lastName.isEmpty() || mobilePhone.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(registrationFrame, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (userDatabase.containsKey(username)) {
                JOptionPane.showMessageDialog(registrationFrame, "Username already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            userDatabase.put(username, new User(firstName, lastName, mobilePhone, email, username, password));
            JOptionPane.showMessageDialog(registrationFrame, "Registration successful! 🎉");
            registrationFrame.dispose();
            showInitialMenu();
        });

        JPanel panel = new JPanel(new GridLayout(7, 2));
        panel.add(new JLabel("First Name:"));
        panel.add(firstNameField);
        panel.add(new JLabel("Last Name:"));
        panel.add(lastNameField);
        panel.add(new JLabel("Mobile Phone:"));
        panel.add(mobilePhoneField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(new JLabel(""));
        panel.add(registerButton);

        registrationFrame.add(panel, BorderLayout.CENTER);
        registrationFrame.setVisible(true);
        currentFrame = registrationFrame;
    }

    private void showLoginMenu() {
        JFrame loginFrame = new JFrame("Login");
        loginFrame.setSize(400, 300);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextField usernameField = new JTextField(15);
        JPasswordField passwordField = new JPasswordField(15);

        JButton loginButton = new JButton("Login 🛸");
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (userDatabase.containsKey(username) && userDatabase.get(username).getPassword().equals(password)) {
                JOptionPane.showMessageDialog(loginFrame, "Login successful! 🎉");
                loginFrame.dispose();
                showMainMenu(username);
            } else {
                JOptionPane.showMessageDialog(loginFrame, "Invalid username or password!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(new JLabel(""));
        panel.add(loginButton);

        loginFrame.add(panel, BorderLayout.CENTER);
        loginFrame.setVisible(true);
        currentFrame = loginFrame;
    }

    private void showMainMenu(String username) {
        JFrame mainMenuFrame = new JFrame("Main Menu");
        mainMenuFrame.setSize(400, 300);
        mainMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton bookingButton = new JButton("Book Transportation 🚗");
        bookingButton.addActionListener(e -> {
            mainMenuFrame.dispose();
            showTransportationBooking(username);
        });

        JButton logoutButton = new JButton("Logout 🚪");
        logoutButton.addActionListener(e -> {
            mainMenuFrame.dispose();
            showInitialMenu();
        });

        JPanel panel = new JPanel();
        panel.add(new JLabel("Welcome, " + username + "! 🌟"));
        panel.add(bookingButton);
        panel.add(logoutButton);
        mainMenuFrame.add(panel, BorderLayout.CENTER);

        mainMenuFrame.setVisible(true);
        currentFrame = mainMenuFrame;
    }

    private void showTransportationBooking(String username) {
        JFrame bookingFrame = new JFrame("Transportation Booking");
        bookingFrame.setSize(500, 500);
        bookingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel transportLabel = new JLabel("Choose Transportation:");
        String[] transportOptions = {"Bus", "Ferry"};
        JComboBox<String> transportComboBox = new JComboBox<>(transportOptions);

        JLabel tripTypeLabel = new JLabel("Trip Type:");
        JRadioButton oneWayButton = new JRadioButton("One Way");
        JRadioButton roundTripButton = new JRadioButton("Round Trip");
        ButtonGroup tripTypeGroup = new ButtonGroup();
        tripTypeGroup.add(oneWayButton);
        tripTypeGroup.add(roundTripButton);

        JLabel fromLabel = new JLabel("From:");
        JComboBox<String> fromComboBox = new JComboBox<>(new String[]{"Kota Kinabalu", "Sipitang", "Papar", "Keningau", "Beaufort", "Tawau", "Sandakan", "Kudat", "Ranau", "Labuan", "Menumbok", "Tuaran", "Kota Marudu", "Kota Belud", "Kundasang", "Tambunan", "Penampang", "Pitas", "Tenom", "Lahad Datu", "Semporna", "Kunak", "Tamparuli", "Kuala Penyu"});
        JLabel toLabel = new JLabel("To:");
        JComboBox<String> toComboBox = new JComboBox<>(new String[]{"Kota Kinabalu", "Sipitang", "Papar", "Keningau", "Beaufort", "Tawau", "Sandakan", "Kudat", "Ranau", "Labuan", "Menumbok", "Tuaran", "Kota Marudu", "Kota Belud", "Kundasang", "Tambunan", "Penampang", "Pitas", "Tenom", "Lahad Datu", "Semporna", "Kunak", "Tamparuli", "Kuala Penyu"});

        // Date selection using combo boxes
        JLabel departureDateLabel = new JLabel("Departure Date:");
        JComboBox<String> dayComboBox = new JComboBox<>();
        for (int i = 1; i <= 31; i++) {
            dayComboBox.addItem(String.format("%02d", i));
        }
        JComboBox<String> monthComboBox = new JComboBox<>(new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"});
        JComboBox<String> yearComboBox = new JComboBox<>();
        for (int i = 2025; i <= 2030; i++) {
            yearComboBox.addItem(String.valueOf(i));
        }

        // Time selection for departure date
        JComboBox<String> hourComboBox = new JComboBox<>();
        for (int i = 0; i < 24; i++) {
            hourComboBox.addItem(String.format("%02d", i));
        }

        JComboBox<String> minuteComboBox = new JComboBox<>();
        for (int i = 0; i < 60; i++) {
            minuteComboBox.addItem(String.format("%02d", i));
        }

        // For the return date, only show if round trip is selected
        JLabel returnDateLabel = new JLabel("Return Date (if round trip):");
        JComboBox<String> returnDayComboBox = new JComboBox<>();
        for (int i = 1; i <= 31; i++) {
            returnDayComboBox.addItem(String.format("%02d", i));
        }
        JComboBox<String> returnMonthComboBox = new JComboBox<>(new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"});
        JComboBox<String> returnYearComboBox = new JComboBox<>();
        for (int i = 2025; i <= 2030; i++) {
            returnYearComboBox.addItem(String.valueOf(i));
        }

        // Time selection for return date
        JComboBox<String> returnHourComboBox = new JComboBox<>();
        for (int i = 0; i < 24; i++) {
            returnHourComboBox.addItem(String.format("%02d", i));
        }

        JComboBox<String> returnMinuteComboBox = new JComboBox<>();
        for (int i = 0; i < 60; i++) {
            returnMinuteComboBox.addItem(String.format("%02d", i));
        }

        JLabel paxLabel = new JLabel("Number of Passengers:");
        JSpinner paxSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));

        JButton confirmButton = new JButton("Confirm Booking 🎫");
        confirmButton.addActionListener(e -> {
            String transport = (String) transportComboBox.getSelectedItem();
            String tripType = oneWayButton.isSelected() ? "One Way" : "Round Trip";
            String from = (String) fromComboBox.getSelectedItem();
            String to = (String) toComboBox.getSelectedItem();
            String departureDate = dayComboBox.getSelectedItem() + " " + monthComboBox.getSelectedItem() + " " + yearComboBox.getSelectedItem();
            String departureTime = hourComboBox.getSelectedItem() + ":" + minuteComboBox.getSelectedItem();
            String returnDate = (roundTripButton.isSelected()) ? returnDayComboBox.getSelectedItem() + " " + returnMonthComboBox.getSelectedItem() + " " + returnYearComboBox.getSelectedItem() : "";
            String returnTime = (roundTripButton.isSelected()) ? returnHourComboBox.getSelectedItem() + ":" + returnMinuteComboBox.getSelectedItem() : "";
            int pax = (int) paxSpinner.getValue();

            if (from.equals(to)) {
                JOptionPane.showMessageDialog(bookingFrame, "Origin and destination must be different.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (departureDate.isEmpty() || (roundTripButton.isSelected() && returnDate.isEmpty())) {
                JOptionPane.showMessageDialog(bookingFrame, "Please fill all required dates.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double amount = transport.equals("Bus") ? 15.0 : 5.0;

            int confirmPayment = JOptionPane.showConfirmDialog(bookingFrame,
                "Booking Confirmed! 🎉\n" +
                "Transport: " + transport + "\n" +
                "Trip Type: " + tripType + "\n" +
                "From: " + from + "\n" +
                "To: " + to + "\n" +
                "Departure: " + departureDate + " at " + departureTime + "\n" +
                (roundTripButton.isSelected() ? "Return: " + returnDate + " at " + returnTime + "\n" : "") +
                "Passengers: " + pax + "\n" +
                "Total Amount: RM" + amount * pax + "\n\n" +
                "Do you want to proceed with payment?", "Confirm Payment", JOptionPane.YES_NO_OPTION);

            if (confirmPayment == JOptionPane.YES_OPTION) {
                showPaymentDialog(bookingFrame, transport, amount, pax, username);
            }
        });

        JPanel panel = new JPanel(new GridLayout(10, 2));
        panel.add(transportLabel);
        panel.add(transportComboBox);
        panel.add(tripTypeLabel);
        JPanel tripTypePanel = new JPanel(new FlowLayout());
        tripTypePanel.add(oneWayButton);
        tripTypePanel.add(roundTripButton);
        panel.add(tripTypePanel);
        panel.add(fromLabel);
        panel.add(fromComboBox);
        panel.add(toLabel);
        panel.add(toComboBox);
        panel.add(departureDateLabel);
        JPanel datePanel = new JPanel(new FlowLayout());
        datePanel.add(dayComboBox);
        datePanel.add(monthComboBox);
        datePanel.add(yearComboBox);
        panel.add(datePanel);
        panel.add(new JLabel("Departure Time:"));
        JPanel timePanel = new JPanel(new FlowLayout());
        timePanel.add(hourComboBox);
        timePanel.add(minuteComboBox);
        panel.add(timePanel);
        panel.add(returnDateLabel);
        JPanel returnDatePanel = new JPanel(new FlowLayout());
        returnDatePanel.add(returnDayComboBox);
        returnDatePanel.add(returnMonthComboBox);
        returnDatePanel.add(returnYearComboBox);
        panel.add(returnDatePanel);
        panel.add(new JLabel("Return Time:"));
        JPanel returnTimePanel = new JPanel(new FlowLayout());
        returnTimePanel.add(returnHourComboBox);
        returnTimePanel.add(returnMinuteComboBox);
        panel.add(returnTimePanel);
        panel.add(paxLabel);
        panel.add(paxSpinner);
        panel.add(new JLabel(""));
        panel.add(confirmButton);

        bookingFrame.add(panel, BorderLayout.CENTER);
        bookingFrame.setVisible(true);
        currentFrame = bookingFrame;
    }
    
    // Method to save bookings to a file 
    private void saveBooking(String record) { 
        bookingRecords.add(record); 
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(BOOKING_FILE, true))) { 
            writer.write(record); 
            writer.newLine(); 
        } catch (IOException e) { 
            JOptionPane.showMessageDialog(currentFrame, "Error saving booking!", "Error", JOptionPane.ERROR_MESSAGE); 
        } 
    } 
 
    // Method to load bookings from a file 
    private void loadBookings() { 
        try (BufferedReader reader = new BufferedReader(new FileReader(BOOKING_FILE))) { 
            String line; 
            while ((line = reader.readLine()) != null) { 
                bookingRecords.add(line); 
            } 
        } catch (IOException e) { 
            // File might not exist initially, ignore the error 
        } 
    }
    
    private void showPaymentDialog(JFrame parentFrame, String transport, double amount, int pax, String username) {
        // Payment Dialog
        JFrame paymentFrame = new JFrame("Payment");
        paymentFrame.setSize(400, 300);
        paymentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel cardNumberLabel = new JLabel("Card Number:");
        JTextField cardNumberField = new JTextField(16);
        JLabel expiryLabel = new JLabel("Expiry Date (MM/YY):");
        JTextField expiryField = new JTextField(5);
        JLabel cvvLabel = new JLabel("CVV:");
        JTextField cvvField = new JTextField(3);

        JButton payButton = new JButton("Pay Now 🏦");
        payButton.addActionListener(e -> {
            String cardNumber = cardNumberField.getText();
            String expiry = expiryField.getText();
            String cvv = cvvField.getText();

            // Simple validation for card details
            if (cardNumber.isEmpty() || expiry.isEmpty() || cvv.isEmpty()) {
                JOptionPane.showMessageDialog(paymentFrame, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (cardNumber.length() != 16 || cvv.length() != 3 || !expiry.matches("\\d{2}/\\d{2}")) {
                JOptionPane.showMessageDialog(paymentFrame, "Invalid card details.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Process payment (simulated)
            JOptionPane.showMessageDialog(paymentFrame, "Payment Successful! 🎉\n" +
                    "Transport: " + transport + "\n" +
                    "Amount: RM" + amount * pax + "\n" +
                    "Passengers: " + pax + "\n" +
                    "Thank you for booking with us, " + username + "!", "Payment Confirmation", JOptionPane.INFORMATION_MESSAGE);
            paymentFrame.dispose();
            showMainMenu(username);
        });

        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(cardNumberLabel);
        panel.add(cardNumberField);
        panel.add(expiryLabel);
        panel.add(expiryField);
        panel.add(cvvLabel);
        panel.add(cvvField);
        panel.add(new JLabel(""));
        panel.add(payButton);

        paymentFrame.add(panel, BorderLayout.CENTER);
        paymentFrame.setVisible(true);
    }

    static class User {
        private final String firstName;
        private final String lastName;
        private final String mobilePhone;
        private final String email;
        private final String username;
        private final String password;

        public User(String firstName, String lastName, String mobilePhone, String email, String username, String password) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.mobilePhone = mobilePhone;
            this.email = email;
            this.username = username;
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }
    }
}

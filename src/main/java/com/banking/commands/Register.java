package com.banking.commands;

import com.banking.model.UserData;
import com.banking.model.UserEntity;
import com.banking.menus.Menu;
import com.banking.service.UserService;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Register implements Menu {
    private final Scanner input;
    private final UserService userService;

    private static final Pattern USERNAME_REGEX = Pattern.compile("^[a-zA-Z0-9_]{3,30}$");
    private static final Pattern PASSWORD_REGEX = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d).{8,}$");
    private static final Pattern EMAIL_REGEX = Pattern.compile("^[\\w.-]+@[\\w.-]+\\.[A-Za-z]{2,6}$");
    private static final Pattern PHONE_REGEX = Pattern.compile("^\\+\\d{7,15}$");
    private static final Pattern DATE_REGEX = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");

    public Register(UserService userService, Scanner scanner) {
        this.userService = userService;
        this.input = scanner;
    }

    @Override
    public void show() {
        UserData userData = promptForCredentials();
        UserEntity userEntity = promptForGeneralInfo(userData.getUsername());
        userService.registerUserData(userData, userEntity);
        System.out.println("\nRegistration successful!");
    }

    private UserData promptForCredentials() {
        String username;
        String password;

        // --- Username ---
        while (true) {
            System.out.print("Enter desired username [3–30 letters/digits/_]: ");
            username = input.nextLine().trim();
            if (username.isEmpty()) {
                System.out.println("Username must not be empty.\n");
                continue;
            }
            if (!USERNAME_REGEX.matcher(username).matches()) {
                System.out.println("Invalid username format. Use 3–30 letters, digits or underscores.\n");
                continue;
            }
            if (userService.isUserExist(username)) {
                System.out.println("Username already taken. Choose another.\n");
                continue;
            }
            break;
        }

        // --- Password + confirmation ---
        while (true) {
            System.out.print("Enter password [min 8 chars, at least one letter & digit]: ");
            password = input.nextLine();
            if (password.trim().isEmpty()) {
                System.out.println("Password must not be empty.\n");
                continue;
            }
            if (!PASSWORD_REGEX.matcher(password).matches()) {
                System.out.println("Weak password. It must be at least 8 chars, contain letters and digits.\n");
                continue;
            }

            System.out.print("Confirm password: ");
            String confirm = input.nextLine();
            if (confirm.trim().isEmpty()) {
                System.out.println("Confirmation cannot be empty.\n");
                continue;
            }
            if (!password.equals(confirm)) {
                System.out.println("Passwords do not match. Try again.\n");
                continue;
            }
            break;
        }

        // Hashing happens inside UserData constructor
        return new UserData(username, password);
    }

    private UserEntity promptForGeneralInfo(String username) {
        String fullName, fullSurname, email, phone, dob;

        System.out.println("\n--- General Information ---");

        // First name
        while (true) {
            System.out.print("First name: ");
            fullName = input.nextLine().trim();
            if (fullName.isEmpty()) {
                System.out.println("First name must not be empty.\n");
                continue;
            }
            break;
        }

        // Last name
        while (true) {
            System.out.print("Last name: ");
            fullSurname = input.nextLine().trim();
            if (fullSurname.isEmpty()) {
                System.out.println("Last name must not be empty.\n");
                continue;
            }
            break;
        }

        // Email
        while (true) {
            System.out.print("Email address: ");
            email = input.nextLine().trim();
            if (email.isEmpty()) {
                System.out.println("Email must not be empty.\n");
                continue;
            }
            if (!EMAIL_REGEX.matcher(email).matches()) {
                System.out.println("Invalid email format. Try again.\n");
                continue;
            }
            break;
        }

        // Phone number
        while (true) {
            System.out.print("Phone number [+<countrycode><digits>]: ");
            phone = input.nextLine().trim();
            if (phone.isEmpty()) {
                System.out.println("Phone number must not be empty.\n");
                continue;
            }
            if (!PHONE_REGEX.matcher(phone).matches()) {
                System.out.println("Invalid phone format. Use + and 7–15 digits.\n");
                continue;
            }
            break;
        }

        // Date of birth
        while (true) {
            System.out.print("Date of birth (YYYY-MM-DD): ");
            dob = input.nextLine().trim();
            if (dob.isEmpty()) {
                System.out.println("Date of birth must not be empty.\n");
                continue;
            }
            if (!DATE_REGEX.matcher(dob).matches()) {
                System.out.println("Invalid date format. Use YYYY-MM-DD.\n");
                continue;
            }
            break;
        }

        return new UserEntity(username, fullName, fullSurname, email, phone, dob);
    }
}

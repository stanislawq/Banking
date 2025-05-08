package com.banking.model;

public class UserEntity {
    private final String username;
    private final String fullName;
    private final String fullSurname;
    private final String email;
    private final String phoneNumber;
    private final String dateOfBirth;

    public UserEntity(String username, String name, String surname, String email, String phoneNumber, String dateOfBirth) {
        this.username = username;
        this.fullName = name;
        this.fullSurname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
    }


    public String getUsername() {
        return username;
    }

    public String getFullName() {
        return fullName;
    }

    public String getFullSurname() {
        return fullSurname;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }
}
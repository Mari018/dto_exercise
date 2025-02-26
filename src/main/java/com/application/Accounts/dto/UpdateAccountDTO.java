package com.application.Accounts.dto;

import jakarta.validation.constraints.NotBlank;

public class UpdateAccountDTO {
    @NotBlank(message = "you must have a first name")
    private String firstName;
    @NotBlank(message = "you must have a last name")
    private String lastName;

    @Override
    public String toString() {
        return "\n" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}

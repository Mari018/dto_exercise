package com.application.Accounts.dto;

import com.application.Accounts.entity.Vehicle;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AccountDTO {
    @NotBlank(message = "you must have a first name")
    private String firstName;
    @NotBlank(message = "you must have a last name")
    private String lastName;

    private int age;
    @Email(message = "you must fill the email")
    @NotBlank(message = "you need to have a email")
    private String email;
    @NotBlank(message = "you need a address")
    private String address;
    private List<Vehicle> vehicles;

    @Override
    public String toString() {
        return  "\n" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", address='" + address + '\'';
    }
}

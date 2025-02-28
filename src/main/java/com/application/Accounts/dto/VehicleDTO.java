package com.application.Accounts.dto;

import com.application.Accounts.entity.Account;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class VehicleDTO {
    @NotBlank(message = "must have a name")
    private String name;
   @NotBlank(message = "this car have a year")
    private String year;
   @NotBlank(message = "the car must have a color")
    private String color;
    @NotBlank(message = "you need a plate to register the car")
    private String plate;
    private Account account;

    @Override
    public String toString() {
        return "\n" +
                "name='" + name + '\'' +
                ", year='" + year + '\'' +
                ", color='" + color + '\'' +
                ", plate='" + plate + '\'' +
                '}';
    }
}

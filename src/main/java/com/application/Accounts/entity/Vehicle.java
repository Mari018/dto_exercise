package com.application.Accounts.entity;

import jakarta.persistence.*;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "year")
    private String year;

    @Column(name = "color")
    private String color;

    @Column(name = "plate", unique = true)
    private String plate;

    @Column(name = "active", columnDefinition = "boolean default false")
    private boolean active;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}

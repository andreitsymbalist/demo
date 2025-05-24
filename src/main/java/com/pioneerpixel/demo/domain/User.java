package com.pioneerpixel.demo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "\"user\"")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", length = 500)
    private String name;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "password", length = 500)
    private String password;

    @OneToOne(mappedBy = "user")
    private Account account;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<PhoneData> phones;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<EmailData> emails;
}
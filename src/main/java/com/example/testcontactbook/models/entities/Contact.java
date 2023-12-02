package com.example.testcontactbook.models.entities;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@Data
@Entity
@Table(name = "contacts", schema = "public")
@SequenceGenerator(name = "seqGenContact", allocationSize = 1, sequenceName = "contacts_seq")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqGenContact")
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "phone")
    private String phone;
    @Column(name = "email")
    private String email;
    @Column(name = "address")
    private String address;
    @Column(name = "user_id")
    private Long userId;
}

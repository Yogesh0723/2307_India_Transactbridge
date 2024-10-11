package com.example.transactbridge.entity;

import lombok.Builder;
import lombok.Data;
import jakarta.persistence.*;

@Data
@Builder
@Entity
@Table(name = "students", uniqueConstraints = @UniqueConstraint(columnNames = {"uniqueKey"}))
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String studentTitle;
    private String studentFirstName;
    private String studentSecondName;
    private String studentLastName;
    private String studentRollNumber;
    private String studentObtainedMarks;
    private String totalMarks;
    private String transactionType;

    @Column(unique = true)
    private String uniqueKey;

    @PrePersist
    @PreUpdate
    private void prepareUniqueKey() {
        this.uniqueKey = studentFirstName + studentLastName + studentRollNumber;
    }
}
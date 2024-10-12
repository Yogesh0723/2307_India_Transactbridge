package com.example.transactbridge.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

@Data
@Builder(toBuilder = true) // Allow building from an existing instance
@NoArgsConstructor // This will generate a default constructor
@AllArgsConstructor // This will generate a constructor with all fields
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
    public void prepareUniqueKey() {
        // Ensure uniqueKey is generated from non-null values
        this.uniqueKey = (studentFirstName != null ? studentFirstName : "") +
                (studentLastName != null ? studentLastName : "") +
                (studentRollNumber != null ? studentRollNumber : "");
    }
}
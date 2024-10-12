package com.example.transactbridge.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "studentInfo", uniqueConstraints = @UniqueConstraint(columnNames = {"uniqueKey"}))
public class Studentinfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String studentName;
    private String studentLastName;
    private String studentRollNumber;
    private String address;
    private double height;
    private double weight;
    private String email;
    private String phoneNumber;
    private String dateOfBirth;
    private String gender;
    private String course;

    @Column(unique = true)
    private String uniqueKey;

    @PrePersist
    @PreUpdate
    public void prepareUniqueKey() {
        this.uniqueKey = (studentName != null ? studentName : "") +
                (studentLastName != null ? studentLastName : "") +
                (studentRollNumber != null ? studentRollNumber : "");
    }
}
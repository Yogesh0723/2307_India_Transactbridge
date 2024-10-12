package com.example.transactbridge.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentInfoDTO {

    @NotBlank(message = "Student name is required")
    private String studentName;

    @NotBlank(message = "Student last name is required")
    private String studentLastName;

    @NotBlank(message = "Student roll number is required")
    private String studentRollNumber;

    @NotBlank(message = "Address is required")
    private String address;

    @Min(value = 0, message = "Height must be a positive number")
    @Max(value = 300, message = "Height must be less than or equal to 300 cm")
    private double height; // in centimeters

    @Min(value = 0, message = "Weight must be a positive number")
    @Max(value = 500, message = "Weight must be less than or equal to 500 kg")
    private double weight; // in kilograms

    // Additional fields
    @Email(message = "Email should be valid")
    private String email;

    @Pattern(regexp = "^\\d{10}$", message = "Phone number must be 10 digits")
    private String phoneNumber;

    @NotBlank(message = "Date of birth is required")
    private String dateOfBirth; // Format: YYYY-MM-DD

    private String gender; // e.g., Male, Female, Other

    @NotBlank(message = "Course is required")
    private String course; // e.g., Computer Science, Mathematics, etc.
}
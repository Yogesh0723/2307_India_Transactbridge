package com.example.transactbridge.service;

import com.example.transactbridge.entity.Student;
import com.example.transactbridge.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublishToDBImpl implements PublishToDB {

    @Autowired
    StudentRepository studentRepository;

    @Override
    @Transactional
    public void publishStudent(Student student) {
        String uniqueKey = student.getUniqueKey();
        try {
            switch (student.getTransactionType().toUpperCase()) {
                case "I": // Insert
                    if (studentRepository.findByUniqueKey(uniqueKey) == null) {
                        studentRepository.save(student);
                        System.out.println("Inserted student: " + student);
                    } else {
                        System.out.println("Student with unique key already exists: " + uniqueKey);
                    }
                    break;

                case "A": // Amend
                    // Check if the student exists
                    Student existingStudent = studentRepository.findByUniqueKey(uniqueKey);
                    if (existingStudent != null) {
                        // Delete the existing student
                        studentRepository.delete(existingStudent);
                        System.out.println("Deleted existing student: " + existingStudent);
                    }
                    // Insert the new student record
                    studentRepository.save(student);
                    System.out.println("Inserted amended student: " + student);
                    break;

                case "D": // Delete
                    existingStudent = studentRepository.findByUniqueKey(uniqueKey);
                    if (existingStudent != null) {
                        studentRepository.delete(existingStudent);
                        System.out.println("Deleted student: " + existingStudent);
                    } else {
                        System.out.println("No student found to delete with unique key: " + uniqueKey);
                    }
                    break;

                default:
                    System.out.println("Invalid transaction type: " + student.getTransactionType());
            }
        } catch (Exception e) {
            System.err.println("Error processing transaction for student: " + student);
            System.err.println("Error message: " + e.getMessage());
            e.printStackTrace(); // Print the stack trace for debugging
        }
    }
}
package com.example.transactbridge.service;

import com.example.transactbridge.entity.Student;
import com.example.transactbridge.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PublishToDBImpl implements PublishToDB {

    private static final Logger logger = LoggerFactory.getLogger(PublishToDBImpl.class);

    @Autowired
    private StudentRepository studentRepository;

    @Override
    @Transactional
    public void publishStudent(Student student) {
        // Validate required fields
        if (isStudentValid(student)) {
            // Prepare unique key
            student.prepareUniqueKey();
            String uniqueKey = student.getUniqueKey();

            try {
                switch (student.getTransactionType().toUpperCase()) {
                    case "I": // Insert
                        handleInsert(student, uniqueKey);
                        break;

                    case "A": // Amend
                        handleAmend(student, uniqueKey);
                        break;

                    case "D": // Delete
                        handleDelete(uniqueKey);
                        break;

                    default:
                        logger.warn("Invalid transaction type: {}", student.getTransactionType());
                }
            } catch (Exception e) {
                logger.error("Error processing transaction for student: {}", student, e);
            }
        } else {
            logger.warn("Cannot process student: required fields are missing.");
        }
    }

    private boolean isStudentValid(Student student) {
        return student.getStudentFirstName() != null &&
                student.getStudentLastName() != null &&
                student.getStudentRollNumber() != null;
    }

    private void handleInsert(Student student, String uniqueKey) {
        Optional<Student> existingStudent = Optional.ofNullable(studentRepository.findByUniqueKey(uniqueKey));
        if (existingStudent.isEmpty()) {
            studentRepository.save(student);
            logger.info("Inserted student: {}", student);
        } else {
            logger.warn("Student with unique key already exists: {}", uniqueKey);
        }
    }

    private void handleAmend(Student student, String uniqueKey) {
        handleDelete(uniqueKey);
        handleInsert(student, uniqueKey);
    }

    private void handleDelete(String uniqueKey) {
        if (uniqueKey != null) {
            Optional<Student> existingStudent = Optional.ofNullable(studentRepository.findByUniqueKey(uniqueKey));
            if (existingStudent.isPresent()) {
                studentRepository.delete(existingStudent.get());
                logger.info("Deleted student: {}", existingStudent.get());
            } else {
                logger.warn("No student found to delete with unique key: {}", uniqueKey);
            }
        } else {
            logger.warn("Cannot delete student: unique key is null.");
        }
    }
}
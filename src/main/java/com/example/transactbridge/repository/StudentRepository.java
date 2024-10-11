package com.example.transactbridge.repository;

import com.example.transactbridge.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, String> {
    Student findByUniqueKey(String uniqueKey);
}
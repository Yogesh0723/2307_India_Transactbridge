package com.example.transactbridge.repository;

import com.example.transactbridge.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByUniqueKey(String uniqueKey);

    // Optional: Method to find by uniqueKey and handle null or empty cases
    @Query("SELECT s FROM Student s WHERE s.uniqueKey = :uniqueKey OR (:uniqueKey IS NULL AND s.uniqueKey IS NULL)")
    Student findByUniqueKeyOrNull(@Param("uniqueKey") String uniqueKey);
}
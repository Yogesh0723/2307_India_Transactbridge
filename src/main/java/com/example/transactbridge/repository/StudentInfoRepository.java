package com.example.transactbridge.repository;

import com.example.transactbridge.entity.Studentinfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudentInfoRepository extends JpaRepository<Studentinfo, Long> {
    Studentinfo findByUniqueKey(String uniqueKey);

    // Optional: Method to find by uniqueKey and handle null or empty cases
    @Query("SELECT s FROM Student s WHERE s.uniqueKey = :uniqueKey OR (:uniqueKey IS NULL AND s.uniqueKey IS NULL)")
    Studentinfo findByUniqueKeyOrNull(@Param("uniqueKey") String uniqueKey);
}
package com.example.transactbridge.service;

import com.example.transactbridge.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.IntStream;

@Service
public class ProcessRequestImpl implements ProcessRequest {

    private static final int RECORD_LENGTH = 45;
    private static final int TOTAL_RECORDS = 100;

    @Autowired
    PublishToDB publishToDB;

    @Override
    public String processData(String inputData) {
        long processedRecords = IntStream.range(0, TOTAL_RECORDS)
                .mapToObj(i -> extractRecord(inputData, i))
                .takeWhile(record -> !record.isEmpty())
                .filter(this::isValidRecord)
                .map(this::buildStudentFromRecord)
                .peek(publishToDB::publishStudent)
                .count();
        return "Processed " + processedRecords + " valid student records";
    }

    private String extractRecord(String inputData, int index) {
        int start = index * RECORD_LENGTH;
        return (start + RECORD_LENGTH <= inputData.length())
                ? inputData.substring(start, start + RECORD_LENGTH)
                : "";
    }

    private boolean isValidRecord(String record) {
        String studentTitle = record.substring(0, 3).trim();
        String studentFirstName = record.substring(3, 13).trim();
        return !studentTitle.isEmpty() && !studentFirstName.isEmpty();
    }

    private Student buildStudentFromRecord(String record) {
        return Student.builder()
                .studentTitle(record.substring(0, 3).trim())
                .studentFirstName(record.substring(3, 13).trim())
                .studentSecondName(record.substring(13, 23).trim())
                .studentLastName(record.substring(23, 33).trim())
                .studentRollNumber(record.substring(33, 38).trim())
                .studentObtainedMarks(record.substring(38, 41).trim())
                .totalMarks(record.substring(41, 44).trim())
                .transactionType(record.substring(44, 45).trim())
                .build();
    }
}
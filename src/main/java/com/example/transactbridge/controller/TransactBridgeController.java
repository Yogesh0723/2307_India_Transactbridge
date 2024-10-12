package com.example.transactbridge.controller;

import com.example.transactbridge.service.StudentInfoDTO;
import com.example.transactbridge.service.ProcessStudentInfo;
import com.transactbridge.BRIDGEOperationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@RestController
@RequestMapping("/transactbridge")
public class TransactBridgeController {

    @Autowired
    private ProcessStudentInfo processStudentInfo;

    @PostMapping("/bridge-operation")
    public ResponseEntity<BRIDGEOperationResponse> handleBRIDGEOperation(@Valid @RequestBody StudentInfoDTO studentInfo) {

        BRIDGEOperationResponse response = new BRIDGEOperationResponse();

        // Log the input data
        System.out.println("Received Student Info: " + studentInfo);

        // Process the student information
        processStudentInfo.processStudent(studentInfo);

        // Set the response
        response.setLSOUTPUT("Processed");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
package com.example.transactbridge.controller;

import com.example.transactbridge.entity.Student;
import com.example.transactbridge.service.ProcessRequest;
import com.transactbridge.BRIDGEOperationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.transactbridge.repository.StudentRepository;

import java.util.Optional;

@RestController
@RequestMapping("/transactbridge") // Base URL for all TransactBridge-related endpoints
public class TransactBridgeController {

    @Autowired
    private ProcessRequest processRequest;

    // Handle BRIDGEOperation requests with a query parameter
    @PostMapping("/bridge-operation")
    public ResponseEntity<BRIDGEOperationResponse> handleBRIDGEOperation(@RequestParam String inputData) {
        BRIDGEOperationResponse response = new BRIDGEOperationResponse();

        // Log the input data
        System.out.println("Input Data: " + inputData);

        // Process the data
        processRequest.processData(inputData);

        // Set the response
        response.setLSOUTPUT("Processed");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
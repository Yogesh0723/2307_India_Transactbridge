package com.example.transactbridge.soap;

import com.example.transactbridge.service.ProcessRequest;
import com.transactbridge.BRIDGEOperation;
import com.transactbridge.BRIDGEOperationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class TransactBridgeEndpoint {

    private static final String NAMESPACE_URI = "http://www.transactbridge.com";

    @Autowired
    ProcessRequest processRequest;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "BRIDGEOperation")
    @ResponsePayload
    public BRIDGEOperationResponse handleBRIDGEOperation(@RequestPayload BRIDGEOperation request) {
        BRIDGEOperationResponse response = new BRIDGEOperationResponse();
        String requestData = request.getLSSRCHMESSAGE().getLSSRCHDATA();
        System.out.println(requestData);

        processRequest.processData(requestData);

        // Set the response
        response.setLSOUTPUT("Processed");
        return response;
    }
}
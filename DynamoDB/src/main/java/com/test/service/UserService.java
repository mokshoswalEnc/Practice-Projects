package com.test.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest;
import software.amazon.awssdk.services.dynamodb.model.GetItemResponse;

@Service
public class UserService {
	@Autowired
    private  DynamoDbClient dynamoDbClient;
    

   

    public String getUser(String userId) {
        GetItemRequest request = GetItemRequest.builder()
                .tableName("users")
                .key(Map.of("userId", AttributeValue.builder().s(userId).build()))
                .build();

        GetItemResponse response = dynamoDbClient.getItem(request);

        if (response.hasItem()) {
            return "User found: " + response.item();
        } else {
            return "User not found";
        }
    }
}

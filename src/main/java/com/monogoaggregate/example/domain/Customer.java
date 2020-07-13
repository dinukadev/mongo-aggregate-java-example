package com.monogoaggregate.example.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Document(collection = "customer")
public class Customer implements Serializable {
    @Id
    private String id;

    private String firstName;

    private String lastName;

    private Map<String, FileInfo> fileInfoMap = new HashMap<>();

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void setFileInfoMap(Map<String, FileInfo> fileInfoMap) {
        this.fileInfoMap = fileInfoMap;
    }

    public Map<String, FileInfo> getFileInfoMap() {
        return fileInfoMap;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}

package com.monogoaggregate.example.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public final class CustomerDto implements Serializable {

    @JsonProperty("id")
    private final String id;

    @JsonProperty("firstName")
    private final String firstName;

    @JsonProperty("lastName")
    private final String lastName;

    @JsonProperty("fileInfo")
    private final Map<String, FileInfoDto> fileInfo;


    public CustomerDto(@JsonProperty("id") String id, @JsonProperty("firstName") String firstName,
                       @JsonProperty("lastName") String lastName,
                       @JsonProperty("fileInfo") Map<String, FileInfoDto> fileInfo) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.fileInfo = fileInfo;
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

    public Map<String, FileInfoDto> getFileInfo() {
        return fileInfo;
    }
}

package com.monogoaggregate.example.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public final class FileInfoDto implements Serializable {

    @JsonProperty("id")
    private final String id;

    @JsonProperty("fileName")
    private final String fileName;

    @JsonProperty("fileType")
    private final String fileType;

    public FileInfoDto(@JsonProperty("id")String id, @JsonProperty("fileName")String fileName,
                       @JsonProperty("fileType")String fileType) {
        this.id = id;
        this.fileName = fileName;
        this.fileType = fileType;
    }

    public String getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileType() {
        return fileType;
    }
}

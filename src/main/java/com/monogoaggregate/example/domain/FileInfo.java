package com.monogoaggregate.example.domain;

import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

public class FileInfo implements Serializable {

    @Field("id")
    private String id;

    private String fileName;

    private String fileType;


    public FileInfo(String id, String fileName, String fileType) {
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

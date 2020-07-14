# Introduction

This repository is used to demonstrate the mongo aggregation pipeline capability to query a mongo collection with a map like structure. A sample collection is as follows;

```aidl
{
    "_id" : ObjectId("5f0c07a36aea9125527d80f1"),
    "firstName" : "Bruce",
    "lastName" : "Wayne",
    "fileInfoMap" : {
        "5a09fa70-1aa1-4c71-b938-64dde74eba79" : {
            "id" : "5a09fa70-1aa1-4c71-b938-64dde74eba79",
            "fileName" : "test",
            "fileType" : "image/jpeg"
        }
    }
}

```

The accompanying blog post can be found [here](https://dimashup.com/exploring-mongos-aggregate-pipelines/)

# Pre-requisites
- Java 8
- Maven
- MongoDB

# Running the tests
```aidl
mvn clean install
```
Make sure MongoDB is running locally before you run as the tests depend on it.


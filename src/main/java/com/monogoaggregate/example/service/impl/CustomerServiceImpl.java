package com.monogoaggregate.example.service.impl;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.model.Aggregates;
import com.monogoaggregate.example.domain.Customer;
import com.monogoaggregate.example.domain.FileInfo;
import com.monogoaggregate.example.repository.CustomerMongoRepository;
import com.monogoaggregate.example.service.CustomerService;
import org.bson.BsonDocument;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomerServiceImpl implements CustomerService {

    private static final Logger log = LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    private CustomerMongoRepository customerMongoRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Customer createCustomer(Customer customer) {
        log.info("Received a request to save a customer");
        Customer savedCustomer =  customerMongoRepository.insert(customer);
        log.info("Successfully saved the customer");
        return savedCustomer;
    }

    @Override
    public List<Customer> getCustomersByFileType(String fileType) {
        log.info("Request to retrieve the customers by file type : {}",fileType);
        AggregateIterable<Document>customerAggregateIterable =  mongoTemplate.getDb().getCollection("customer")
                .aggregate(
                        Arrays.asList(
                                Aggregates.project(BsonDocument.parse("{ \"map\": { \"$objectToArray\": \"$fileInfoMap\" }, 'firstName': 1, 'lastName': 1}")),
                                Aggregates.match(BsonDocument.parse(String.format("{'map.v.fileType': '%s' }",fileType))),
                                Aggregates.project(BsonDocument.parse("{ \"fileInfoMap\": { \"$arrayToObject\": \"$map\" }, 'firstName': 1, 'lastName': 1 }"))

                        ));
        List<Customer>retrievedCustomers = new LinkedList<>();
        Iterator<Document>customerIterator = customerAggregateIterable.iterator();
        while(customerIterator.hasNext()){
            Document document = customerIterator.next();
            Customer customer = new Customer(document.getString("firstName"),
                    document.getString("lastName"));
            customer.setId(document.getObjectId("_id").toString());
            Map<String, Document>fileMap = (Map<String, Document>) document.get("fileInfoMap");
            for(String key : fileMap.keySet()){
                Document fileDoc = fileMap.get(key);
                customer.addFileInfo(key,new FileInfo(fileDoc.getString("id"),
                        fileDoc.getString("fileName"),fileDoc.getString("fileType")));
            }

            retrievedCustomers.add(customer);
        }
        log.info("Successfully retrieved the customers by file type : {}",fileType);
        return retrievedCustomers;
    }
}

package com.monogoaggregate.example.controller;

import com.monogoaggregate.example.constants.APIConstant;
import com.monogoaggregate.example.constants.QueryParamConstant;
import com.monogoaggregate.example.domain.Customer;
import com.monogoaggregate.example.dto.CustomerDto;
import com.monogoaggregate.example.dto.assembler.CustomerDtoAssembler;
import com.monogoaggregate.example.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = APIConstant.API_V1 + APIConstant.API_CUSTOMERS)
public class CustomerController {

    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    /**
     * POST /v1/customers
     *
     * @param customerDto
     * @return
     */
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDto createCustomer(@RequestBody CustomerDto customerDto) {
        log.info("Received a request to create a customer");
        Customer customerToCreate = CustomerDtoAssembler.disassemble(customerDto);
        Customer createdCustomer = customerService.createCustomer(customerToCreate);
        CustomerDto createdCustomerDto = CustomerDtoAssembler.assemble(createdCustomer);
        log.info("Successfully created the customer");
        return createdCustomerDto;
    }


    /**
     * GET /v1/customers?fileType=xxx
     *
     * @param fileType
     * @return
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerDto> getCustomersByFileType(@RequestParam(QueryParamConstant.FILE_TYPE_PARAM) String fileType) {
        log.info("Received a request to fetch customers with file type : {}", fileType);
        List<Customer> customersWithFileTypeList = customerService.getCustomersByFileType(fileType);
        List<CustomerDto> customerDtoToReturn =
                customersWithFileTypeList.stream().map(CustomerDtoAssembler::assemble).collect(Collectors.toList());
        log.info("Successfully retrieved customers with file type : {}", fileType);
        return customerDtoToReturn;
    }
}

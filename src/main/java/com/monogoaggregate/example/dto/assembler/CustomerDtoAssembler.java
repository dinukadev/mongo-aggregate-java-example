package com.monogoaggregate.example.dto.assembler;

import com.monogoaggregate.example.domain.Customer;
import com.monogoaggregate.example.domain.FileInfo;
import com.monogoaggregate.example.dto.CustomerDto;

import java.util.Map;
import java.util.stream.Collectors;

public class CustomerDtoAssembler {

    public static Customer disassemble(CustomerDto customerDto) {
        Customer customer = new Customer(customerDto.getFirstName(), customerDto.getLastName());
        if (customerDto.getFileInfo() != null) {
            Map<String, FileInfo> fileMap = customerDto.getFileInfo().values().stream()
                    .map(FileInfoDtoAssembler::disassemble).collect(Collectors.toMap((file -> file.getId()), (file -> file)));
            customer.setFileInfoMap(fileMap);

        }
        return customer;
    }

    public static CustomerDto assemble(Customer customer) {
        Map<String, FileInfo> fileInfoMap = customer.getFileInfoMap();
        return new CustomerDto(customer.getId(), customer.getFirstName(), customer.getLastName(),
                fileInfoMap.values().stream().map(FileInfoDtoAssembler::assemble).collect(Collectors.toMap((file -> file.getId()), (file -> file))));
    }
}

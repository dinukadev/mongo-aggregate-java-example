package com.monogoaggregate.example.rest;

import com.monogoaggregate.example.common.IntegrationTest;
import com.monogoaggregate.example.constants.APIConstant;
import com.monogoaggregate.example.constants.QueryParamConstant;
import com.monogoaggregate.example.domain.Customer;
import com.monogoaggregate.example.dto.CustomerDto;
import com.monogoaggregate.example.dto.FileInfoDto;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CustomerControllerTest extends IntegrationTest {


    @Test
    public void shouldCreateCustomer() throws Exception {
        String response = createCustomer("Bruce","Wayne","test","image/jpeg");

        CustomerDto actual = mapper.readValue(response, CustomerDto.class);
        assertNotNull(actual);

        Customer savedCustomer = mongoTemplate.findById(actual.getId(), Customer.class);
        assertNotNull(savedCustomer);
    }

    private String createCustomer(String firstName, String lastName, String filename, String fileType) throws Exception {
        Map<String, FileInfoDto> fileInfoDtoMap = new HashMap<>();
        FileInfoDto fileInfoDto = new FileInfoDto(UUID.randomUUID().toString(),
                filename, fileType);
        fileInfoDtoMap.put(fileInfoDto.getId(), fileInfoDto);
        CustomerDto customerDto = new CustomerDto(null, firstName, lastName, fileInfoDtoMap);

        return mockMvc.perform(post(APIConstant.API_V1 + APIConstant.API_CUSTOMERS)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(customerDto))).andExpect(status().isCreated()).andReturn().getResponse().getContentAsString();
    }

    @Test
    public void shouldGetCustomersWithFileType()throws Exception{
        createCustomer("Bruce","Wayne","test","image/jpeg");
        createCustomer("Clark","Kent","test","image/jpeg");
        //The type pdf which we will not query for so Arthur Curry should not be returned
        createCustomer("Arthur","Curry","test","application/pdf");

        String endpoint = UriComponentsBuilder.fromUriString(APIConstant.API_V1 + APIConstant.API_CUSTOMERS)
                .queryParam(QueryParamConstant.FILE_TYPE_PARAM,"image/jpeg").toUriString();

        String response = mockMvc.perform(MockMvcRequestBuilders.get(endpoint)
        .contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn().getResponse()
                .getContentAsString();

        List<CustomerDto>actual = mapper.readValue(response,List.class);
        assertEquals(2, actual.size());
    }
}

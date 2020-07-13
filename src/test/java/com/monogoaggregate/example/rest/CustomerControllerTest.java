package com.monogoaggregate.example.rest;

import com.monogoaggregate.example.common.IntegrationTest;
import com.monogoaggregate.example.constants.APIConstant;
import com.monogoaggregate.example.dto.CustomerDto;
import com.monogoaggregate.example.dto.FileInfoDto;
import org.junit.Test;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CustomerControllerTest extends IntegrationTest {


    @Test
    public void shouldCreateCustomer() throws Exception {
        Map<String, FileInfoDto> fileInfoDtoMap = new HashMap<>();
        FileInfoDto fileInfoDto = new FileInfoDto(UUID.randomUUID().toString(),
                "testFile", "testFile.pdf");
        fileInfoDtoMap.put(fileInfoDto.getId(), fileInfoDto);
        CustomerDto customerDto = new CustomerDto(null, "Bruce", "Wayne", fileInfoDtoMap);

        String response = mockMvc.perform(post(APIConstant.API_V1 + APIConstant.API_CUSTOMER)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(customerDto))).andExpect(status().isCreated()).andReturn().getResponse().getContentAsString();

        CustomerDto actual = mapper.readValue(response, CustomerDto.class);
        assertNotNull(actual);
    }
}

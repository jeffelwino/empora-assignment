package com.example.empora.services;


import com.example.empora.model.Address;
import com.example.empora.model.AddressDTO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class USAddressServiceTest {
    // This is the object we will mock:
    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private USAddressService service = new USAddressService();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // This method loads the dummy JSON response from a text file
    public String readTestFile() {

        String filePath = "src/test/resources/testAddressResponses.json";
        String content = null;
        try {
            content = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    // Helper method to compare the value of two AddressDTO objects
    public boolean dtoCompare(AddressDTO expectedDTO, AddressDTO actualDTO) {

        if (expectedDTO.getPrimaryNumber() != actualDTO.getPrimaryNumber()) {
            return false;
        }
        if (!expectedDTO.getStreetDirection().equals(actualDTO.getStreetDirection())) {
            return false;
        }
        if (!expectedDTO.getStreetName().equals(actualDTO.getStreetName())) {
            return false;
        }
        if (!expectedDTO.getStreetSuffix().equals(actualDTO.getStreetSuffix())) {
            return false;
        }
        if (!expectedDTO.getCityName().equals(actualDTO.getCityName())) {
            return false;
        }
        if (expectedDTO.getZipcode() != actualDTO.getZipcode()) {
            return false;
        }
        if (expectedDTO.getPlus4Code() != actualDTO.getPlus4Code()) {
            return false;
        }

        return true;
    }

    public List<Address> makeAddressList() {
        List<Address> addresses = new ArrayList<>();
        addresses.add(new Address("143 e Maine Street", "Columbus", 43215));
        addresses.add(new Address("1 Empora St", "Title", 11111));
        return addresses;
    }


    @Test
    void test_getSmartyAddress_returns_correct_value_with_valid_address_and_correct_size() {

        ResponseEntity<String> mockedResponse = new ResponseEntity<>(readTestFile(),
                HttpStatus.OK);
        when(
                restTemplate.exchange(
                        ArgumentMatchers.contains("/street-address"),
                        ArgumentMatchers.eq(HttpMethod.GET),
                        ArgumentMatchers.any(),
                        ArgumentMatchers.<Class<String>>any(),
                        ArgumentMatchers.<Object>any()
                )
        ).thenReturn(mockedResponse);

        AddressDTO testDTO = service.getSmartyAddress(makeAddressList().get(0));
//        assertEquals(2,dtoList.size());

        AddressDTO expected1 = new AddressDTO(143, "E","Main","St","Columbus",43215,5370);

        boolean objectsTheSame = dtoCompare(expected1, testDTO);
        System.out.println(testDTO.getFullStreetAddress());
        assertTrue(objectsTheSame, "Objects don't match");
    }

    @Test
    void test_getSmartyAddress_returns_null_values_with_invalid_address() {

        ResponseEntity<String> mockedResponse = new ResponseEntity<>(readTestFile(),
                HttpStatus.OK);

        when(
                restTemplate.exchange(
                        contains("/street-address"),
                        eq(HttpMethod.GET),
                        any(HttpEntity.class),
                        eq(String.class)
                )
        ).thenReturn(mockedResponse);

        AddressDTO nullDTO = service.getSmartyAddress(makeAddressList().get(1));


        assertEquals (0, nullDTO.getPrimaryNumber());
        assertNull(nullDTO.getStreetDirection(), "Null St direction");
        assertNull(nullDTO.getStreetName(), "Null Street Name");
        assertNull(nullDTO.getStreetSuffix(), "Null Street Suffix");
        assertNull(nullDTO.getCityName(), "Null City Name");
        assertEquals(0,nullDTO.getZipcode());
        assertEquals(0,nullDTO.getPlus4Code());

    }


}


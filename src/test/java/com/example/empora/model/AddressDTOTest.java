package com.example.empora.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressDTOTest {

    AddressDTO testDTO = new AddressDTO(1,"W","Test","Avenue","Test Town", 12345,6789);

    AddressDTO testDTO2 = new AddressDTO(1,null,"Test","Avenue","Test Town", 12345,6789);

    @Test
    void getFullZipcode_returns_expected() {
        String expected = "12345-6789";
        String actual = testDTO.getFullZipcode();

        assertEquals(expected,actual);
    }

    @Test
    void getFullStreetAddress_returns_expected() {
        String expected = "1 W Test Avenue";
        String actual = testDTO.getFullStreetAddress();

        assertEquals(expected,actual);
    }
    @Test
    void getFullStreetAddress_without_predirection_returns_expected_() {
        String expected = "1 Test Avenue";
        String actual = testDTO2.getFullStreetAddress();

        assertEquals(expected,actual);
    }
}
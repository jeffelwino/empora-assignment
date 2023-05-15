package com.example.empora;

import com.example.empora.model.Address;
import com.example.empora.model.AddressDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OutputBuilderTest {
    OutputBuilder output = new OutputBuilder();
    Address testAddress = new Address("1 w Test Avenue", "test town", 12345);

    AddressDTO testDTO = new AddressDTO(1,"W","Test","Ave","Test Town", 12345,6789);

    AddressDTO nullDTO = new AddressDTO(0,null,null,null,null,0,0);


    @Test
    void buildAddressOutput_returns_correct_address_as_string() {
        String expected = "1 w Test Avenue, test town, 12345";

        String actual = output.buildAddressOutput(testAddress);

        assertEquals(expected,actual);

    }

    @Test
    void buildDTOOutput_correctly_outputs_valid_dto() {
        String expected = "1 W Test Ave, Test Town, 12345-6789";
        String actual = output.buildDTOOutput(testDTO);
        assertEquals(expected,actual);
    }

    @Test
    void buildDTOOutput_correctly_outputs_invalid_dto() {
        String expected = "Invalid Address";
        String actual = output.buildDTOOutput(nullDTO);
        assertEquals(expected,actual);

    }

    @Test
    void buildFullOutput_correctly_formats_full_output() {
        String expectedValid = "1 w Test Avenue, test town, 12345 -> 1 W Test Ave, Test Town, 12345-6789";
        String actualValid = output.buildFullOutput(testAddress,testDTO);

        String expectedInvalid = "1 w Test Avenue, test town, 12345 -> Invalid Address";
        String actualInvalid = output.buildFullOutput(testAddress,nullDTO);

        assertEquals(expectedValid,actualValid,"Valid output is incorrect");
        assertEquals(expectedInvalid,actualInvalid,"Invalid Output is incorrect");
    }
}
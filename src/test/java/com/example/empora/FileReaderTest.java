package com.example.empora;

import com.example.empora.model.Address;
import com.example.empora.util.FileReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FileReaderTest {

    FileReader fileReader = new FileReader();
    final String FILE_PATH = "empora_test_data.csv";

    @Test
    void scanAddress_returns_correct_address(){
        Address expected = new Address("143 e Maine Street", "Columbus", 43215);
        String csvRow = "143 e Maine Street, Columbus,43215";
        Address actual = fileReader.scanAddress(csvRow);

        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);

    }


    @Test
    void readFile_returns_correct_sized_List() {

        fileReader.readFile(FILE_PATH);
        List<Address>testAddresses = fileReader.getAddresses();
        assertEquals(2,testAddresses.size());

    }
}
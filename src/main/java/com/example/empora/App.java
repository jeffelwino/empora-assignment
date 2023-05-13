package com.example.empora;

import com.example.empora.model.Address;
import com.example.empora.model.AddressDTO;
import com.example.empora.services.USAddressService;
import com.example.empora.util.FileReader;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class App {

//    String filePath = "empora_test_data.csv";
    FileReader reader = new FileReader();
    USAddressService addressService = new USAddressService();
    Output output = new Output();

    public void run(String filePath){

        reader.readFile(filePath);
        List<Address> addresses = reader.getAddresses();

        List<AddressDTO> dtoList = addressService.getAddressDTOs(addresses);

        for(int i = 0; i < addresses.size(); i++){
            System.out.println(output.buildOutput(addresses.get(i),dtoList.get(i)));
        }

    }
}

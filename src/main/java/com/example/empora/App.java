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

    FileReader reader = new FileReader();
    USAddressService addressService = new USAddressService();
    OutputBuilder output = new OutputBuilder();

    public void run(){

        reader.readFile(retrieveUserFile());

        List<Address> addresses = reader.getAddresses();

        List<AddressDTO> dtoList = addressService.getAddressDTOs(addresses);

        for(int i = 0; i < addresses.size(); i++){
            System.out.println(output.buildFullOutput(addresses.get(i),dtoList.get(i)));
        }
        System.out.println(" ");
        System.exit(0);

    }

    public String retrieveUserFile(){
        Scanner userInput = new Scanner(System.in);
        System.out.println("Please enter the path of the file to be read:");

        String filePath = userInput.next();

        System.out.println("Thank you");
        System.out.println(" ");
        return filePath;
    }
}

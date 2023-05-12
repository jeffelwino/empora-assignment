package com.example.empora;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class AddressController {
    @Autowired
    USAddressService addressService = new USAddressService();

    @Value("${input.file.path}")
    private String inputFilePath;
    FileReader fileReader = new FileReader();

    public AddressController(USAddressService addressService) {
        this.addressService = addressService;

    }

    @RequestMapping(path="/test", method= RequestMethod.GET)
    public List<AddressDTO> getAddressResults(){
        fileReader.readFile(inputFilePath);
        List<Address> addresses = fileReader.getAddresses();

        List<AddressDTO> dtos = new ArrayList<>();

        for(Address entry: addresses){
                AddressDTO dto = addressService.getSmartyAddress(entry);
                dtos.add(dto);
        }
        return dtos;
    }




}

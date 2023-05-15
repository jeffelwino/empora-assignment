package com.example.empora;

import com.example.empora.model.Address;
import com.example.empora.model.AddressDTO;

public class OutputBuilder implements Outputable{

    /*
    * Returns String of First half of response for output, based on csv file input
    */
    @Override
    public String buildAddressOutput(Address address) {
        return address.getStreet() + ", " + address.getCity() + ", " + address.getZipCode();
    }

    /*
     * Builds 2nd half of response for output, based on Smarty response
     */
    @Override
    public String buildDTOOutput(AddressDTO dto) {
        if(dto.getCityName() == null){
            return "Invalid Address";
        }
        return dto.getFullStreetAddress() + ", "
                       + dto.getCityName() + ", "
                       + dto.getFullZipcode();
    }

    /*
    * Builds out the full line from the address and DTO output
    */
    @Override
    public String buildFullOutput(Address address, AddressDTO dto) {
        return buildAddressOutput(address) + " -> " + buildDTOOutput(dto);
    }


}

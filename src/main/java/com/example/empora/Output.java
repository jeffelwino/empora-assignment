package com.example.empora;

import com.example.empora.model.Address;
import com.example.empora.model.AddressDTO;

public class Output implements Outputable{

    /*
    * Builds First half of response for output, base don csv file input
    */

    @Override
    public String buildAddressOutput(Address address) {
        return address.getStreet() + "," + address.getCity() + ", " + address.getZipCode();
    }

    /*
     * Builds 2nd half of response for output, based on Smarty response
     */
    @Override
    public String buildDTOOutput(AddressDTO dto) {
        if(dto.getPrimaryNumber() == null){
            return "Invalid Address";
        }
        String street ="";

        if(dto.getStreetDirection() == null){
            street += dto.getPrimaryNumber() + " " + dto.getStreetName() + " " + dto.getStreetSuffix();
        }
        else{
            street += dto.getPrimaryNumber() + " " + dto.getStreetDirection() + " " + dto.getStreetName() + " " + dto.getStreetSuffix();
        }

        return street + ", " + dto.getCityName() + ", " + dto.getZipcode();
    }

    @Override
    public String buildOutput(Address address, AddressDTO dto) {
        return buildAddressOutput(address) + " -> " + buildDTOOutput(dto);
    }


}

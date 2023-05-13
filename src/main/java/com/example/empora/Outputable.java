package com.example.empora;

import com.example.empora.model.Address;
import com.example.empora.model.AddressDTO;

public interface Outputable {

String buildAddressOutput(Address address);

String buildDTOOutput(AddressDTO addressDTO);

String buildOutput(Address address, AddressDTO dto);




}

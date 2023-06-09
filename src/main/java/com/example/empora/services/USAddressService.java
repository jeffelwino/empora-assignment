package com.example.empora.services;

import com.example.empora.util.SmartyConfig;
import com.example.empora.model.Address;
import com.example.empora.model.AddressDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpEntity;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


import java.net.URI;

import java.util.*;


@Service
public class USAddressService {

    @Autowired
    SmartyConfig smarty = new SmartyConfig();

    public USAddressService(SmartyConfig smarty) {
        this.smarty = smarty;
    }

    public USAddressService() {
    }

    public Map<Address,AddressDTO> getAddressDTOs(List<Address> addresses){
        Map<Address,AddressDTO> addressesMap = new HashMap<>();

        for(Address entry: addresses){
            addressesMap.put(entry,getSmartyAddress(entry));
        }
        return addressesMap;
    }

    public AddressDTO getSmartyAddress(Address entry) {

        UriComponentsBuilder builder =
                UriComponentsBuilder.fromHttpUrl(smarty.getApiURL()).
                        queryParam("street", entry.getStreet()).
                        queryParam("city", entry.getCity()).
                        queryParam("zipcode", entry.getZipCode()).
                        queryParam("auth-id", smarty.getAuthId()).
                        queryParam("auth-token",smarty.getAuthToken());

        URI url = builder.build().toUri();


        AddressDTO addressDTO = new AddressDTO();

        HttpEntity<String> httpEntity = new HttpEntity<>("");
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode;
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);

        try {
            jsonNode = objectMapper.readTree(responseEntity.getBody());
            if (jsonNode.isArray()) {
                for (JsonNode root : jsonNode) {
                    JsonNode components = root.path("components");
                    String primaryNumber = components.path("primary_number").asText();
                    String streetDirection = components.path("street_predirection").asText();
                    String streetName = components.path("street_name").asText();
                    String streetSuffix = components.path("street_suffix").asText();
                    String cityName = components.path("city_name").asText();
                    String zipcode = components.path("zipcode").asText();
                    String plus4Code = components.path("plus4_code").asText();

                    addressDTO = new AddressDTO(Integer.parseInt(primaryNumber), streetDirection, streetName, streetSuffix, cityName, Integer.parseInt(zipcode), Integer.parseInt(plus4Code));
                }
            }

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return addressDTO;

    }


}

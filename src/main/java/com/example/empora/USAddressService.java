package com.example.empora;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


@Component
public class USAddressService {
    @Value("${smarty.api.key}")
    private String key;

    @Value("${smarty.api.authid}")
    private String authId;

    @Value("${smarty.api.token}")
    private String authToken;

    @Value("${smarty.api.url}")
    private String apiURL;

public AddressDTO getSmartyAddress(Address entry) {

    String query = this.apiURL + "street=" + entry.getStreet() + "&city=" + entry.getCity() + "&zipcode=" + entry.getZipCode() + "&auth-id=" + this.authId + "&auth-token=" + this.authToken;

    String url = query.replaceAll(" ","+");


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
                JsonNode analysisNode = root.path("analysis");

                String primaryNumber = components.path("primary_number").asText();
                String streetName = components.path("street_name").asText();
                String streetSuffix = components.path("street_suffix").asText();
                String cityName = components.path("city_name").asText();
                String zipcode = components.path("zipcode").asText();
                String plus4Code = components.path("plus4_code").asText();
                boolean isValid = true;

                if(jsonNode.at("/analysis/dpv_footnotes") == null || jsonNode.at("/analysis/dpv_footnotes").asText().equals("A1")){
                    isValid = false;
                }

                addressDTO = new AddressDTO(primaryNumber,streetName,streetSuffix,cityName,zipcode,plus4Code,isValid);


            }
        }

        JsonNode components = jsonNode.at("/components");







    }catch (JsonProcessingException e) {
        e.printStackTrace();
    }

    return addressDTO;


}
//request query should contain street + city + zipcode
//Response will be object array. Want, within 'components' object: "primary_number","street_name","street_suffix","city_name", "zipcode", "plus4_code"
    //within "analysis" -> "dpv_footnotes". if it equals "A1", then it's an invalid match

}

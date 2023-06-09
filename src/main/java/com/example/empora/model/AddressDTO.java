package com.example.empora.model;

public class AddressDTO {

    private int primaryNumber = 0;
    //North, South, etc
    private String streetDirection;
    private String streetName;
    //St, Ave, etc
    private String streetSuffix;
    private String cityName;
    private int zipcode = 0;
    private int plus4Code = 0;


    public AddressDTO(int primaryNumber, String streetDirection, String streetName, String streetSuffix,
                      String cityName, int zipcode, int plus4Code) {
        this.primaryNumber = primaryNumber;
        this.streetDirection = streetDirection;
        this.streetName = streetName;
        this.streetSuffix = streetSuffix;
        this.cityName = cityName;
        this.zipcode = zipcode;
        this.plus4Code = plus4Code;
    }

    public AddressDTO() {
    }

    public String getStreetDirection() {
        return streetDirection;
    }


    public int getPrimaryNumber() {
        return primaryNumber;
    }


    public String getStreetName() {
        return streetName;
    }


    public String getStreetSuffix() {
        return streetSuffix;
    }


    public String getCityName() {
        return cityName;
    }


    public int getZipcode() {
        return zipcode;
    }


    public int getPlus4Code() {
        return plus4Code;
    }


    /**
     * @return zip and plus4 code
     */
    public String getFullZipcode() {
        return zipcode + "-" + plus4Code;
    }

    /**
     * @return full street address (primary number, street direction(if exists), street name, street suffix)
     */
    public String getFullStreetAddress() {
        if (streetDirection == null) {
            return primaryNumber + " " + streetName + " " + streetSuffix;
        }
        return primaryNumber + " " + streetDirection + " " + streetName + " " + streetSuffix;
    }

}

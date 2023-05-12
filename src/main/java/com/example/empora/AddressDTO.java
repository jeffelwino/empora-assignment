package com.example.empora;

public class AddressDTO {

private String primaryNumber;
private String streetName;
private String streetSuffix;
private String cityName;
private String zipcode;
private String plus4Code;
private boolean isValidAddress;

    public AddressDTO(String primaryNumber, String streetName, String streetSuffix, String cityName, String zipcode, String plus4Code, boolean isValidAddress) {
        this.primaryNumber = primaryNumber;
        this.streetName = streetName;
        this.streetSuffix = streetSuffix;
        this.cityName = cityName;
        this.zipcode = zipcode;
        this.plus4Code = plus4Code;
        this.isValidAddress = isValidAddress;
    }


    public AddressDTO() {
    }

    public String getPrimaryNumber() {
        return primaryNumber;
    }

    public void setPrimaryNumber(String primaryNumber) {
        this.primaryNumber = primaryNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getStreetSuffix() {
        return streetSuffix;
    }

    public void setStreetSuffix(String streetSuffix) {
        this.streetSuffix = streetSuffix;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    //returns full zipcode
    public String getZipcode() {
        return zipcode + "-" + plus4Code;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getPlus4Code() {
        return plus4Code;
    }

    public void setPlus4Code(String plus4Code) {
        this.plus4Code = plus4Code;
    }

    public boolean getValidAddress() {
        return isValidAddress;
    }

    public void setValidAddress(boolean validAddress) {
        this.isValidAddress = validAddress;
    }
}

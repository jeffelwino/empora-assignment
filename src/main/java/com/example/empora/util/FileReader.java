package com.example.empora.util;

import com.example.empora.model.Address;

import java.io.File;
import java.util.*;

public class FileReader {

    private List<Address> addresses = new ArrayList<>();

    public void readFile(String inputPath) {

        try (Scanner input = new Scanner(new File(inputPath))) {
            while (input.hasNextLine()) {
                addresses.add(scanAddress(input.nextLine()));
            }
            // removes Header row
            addresses.remove(0);
        } catch (Exception e) {
            System.out.println(e + "IS A BIG PROBLEM");
        }
    }

    //Scans individual row from csv file
    private Address scanAddress(String line) {
        List<String> addressInfo = new ArrayList<>();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(",");
            while (rowScanner.hasNext()) {
                addressInfo.add(rowScanner.next());
            }
        } catch (Exception e) {
            System.out.println("Problem reading the information");
        }
        Address newAddress = new Address(addressInfo.get(0), addressInfo.get(1), addressInfo.get(2));
        return newAddress;

    }

    public List<Address> getAddresses(){
        return addresses;
    }

}

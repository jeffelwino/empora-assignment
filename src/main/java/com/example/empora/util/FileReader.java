package com.example.empora.util;

import com.example.empora.model.Address;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class FileReader {

    private List<Address> addresses = new ArrayList<>();

    public FileReader(List<Address> addresses) {
        this.addresses = addresses;
    }

    public FileReader() {
    }

    //help method to scan individual row from csv file. Used in readFile method.
    public Address scanAddress(String line) {
        List<String> addressInfo = new ArrayList<>();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(",");
            while (rowScanner.hasNext()) {
                addressInfo.add(rowScanner.next());
            }
        } catch (Exception e) {
            System.out.println("Problem scanning the address");
        }
        Address newAddress = new Address(addressInfo.get(0).trim(), addressInfo.get(1).trim(), Integer.parseInt(addressInfo.get(2).trim()));
        return newAddress;

    }
    // Scans file, updates Address list
    public void readFile(String inputPath) {

        try (Scanner input = new Scanner(new File(inputPath))) {

            input.nextLine();
            while (input.hasNext()) {
                    this.addresses.add(scanAddress(input.nextLine()));
            }
        } catch (FileNotFoundException e) {
            System.err.println("File Not Found. Please enter valid file path.");
        }
        catch (Exception e){
            System.err.println("Error at FileReader: " + e.getMessage());
        }

    }



    public List<Address> getAddresses(){
        return addresses;
    }

}

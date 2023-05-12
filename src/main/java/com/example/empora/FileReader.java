package com.example.empora;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.*;

public class FileReader {

    List<Address> addresses = new ArrayList<>();

    public void readFile(String inputPath) {

        try (Scanner input = new Scanner(new File(inputPath))) {
            while (input.hasNextLine()) {
                addresses.add(scanAddress(input.nextLine()));
            }
            // removes Header row
            addresses.remove(0);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

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

//    Map<Person,String> people = new HashMap<>();
//
//
//    public void readRecord(File inputFile) {
//
//        try (Scanner input = new Scanner(inputFile)) {
//            while (input.hasNextLine()) {
//                people.put(getLine(input.nextLine()), "XYZ");
//            }
//
//        } catch (Exception e) {
//            System.out.println("exception found");
//        }
//
//        for(Map.Entry<Person, String> person: people.entrySet()){
//            Person entry = person.getKey();
//            System.out.println(entry.getFirstName() + " , " + entry.getSurname() + "->" + person.getValue());
//        }
//
//    }
//
//    private Person getLine(String line) {
//        List<String> folks = new ArrayList<>();
//
//        try (Scanner rowScanner = new Scanner(line)) {
//            rowScanner.useDelimiter(",");
//            while (rowScanner.hasNext()) {
//                folks.add(rowScanner.next());
//            }
//        } catch (Exception e) {
//            System.out.println("Exception found");
//        }
//        Person newPerson = new Person(folks.get(0),folks.get(1),folks.get(2),folks.get(3));
//        return newPerson;
//
//    }


}

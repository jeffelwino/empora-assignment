# empora-assignment
## running the program
This program was made with in Java using the Spring framework with Maven. Make sure Maven is installed on your PC.

Before running the program the first time, type `mvn clean install` while in the top-level project directory. It will begin running after install automatically. 

Otherwise, to run the program in the console: enter `mvn spring-boot:run` while in the top-level project directory. 

The program will start and print
`Please enter the path of the file to be read:`

At this point, enter the path of the csv file you would like the program to read from. 
There is already a file with the path `empora_test_data.csv` that you can enter.

After entering the the csv file path, the program will run and output its responses to the console before closing out.

## running tests
### running all test
At the top-level directory type `mvn clean test` to run all tests together.

### running single test classes
At top-level directory type `mvn -Dtest=ExampleTestClassName test. For example, to run the "FileReaderTest" class
type `mvn -Dtest=FileReaderClass test'.

to run individual tests from the class, in this case the "scanAddressReturnsCorrect()" test, enter `mvn -Dtest=FileReaderClass#scanAddressReturnsCorrect test`

## Changing API key (AuthID and AuthToken)
There were issues using only the API key to call the smarty API. It kept returning an Authorization exception. Using the Auth ID and Auth Token worked. 

These are located in the SmartyConfig class file (path: src/main/java/com/example/empora/util/SmartyConfig.java). 

To change them, simply replace the values of the `authId` and `authToken` properties.

I recognize this is not very secure. Unfortunately, there were difficulties accessing the ID and token when storing them in the application.properties file and using @Value annotation in the Service class, as is standard in Spring. At the very least, placing this information in a speerate class allows its information to be easily accessed by any class that needs to access the Smarty API.

## Explanation of App Design

### FileReader class
The Filereader class handles the scanning of a csv document. It takes in a document and, line by line, splits the address up into an array at each comma. These strings then become the "street", "city", and "zipcode" properties of a new Address object. The new Address is then added to an ArrayList of Addresses.

### Address model class
The Address class is a model of a single row of the csv file. Its 3 properties are: "street"(String), "city"(String), and "zipcode"(int).The "street" property consists of the full street address, since calling an API query does not require it to be split up into smaller components.

### USAddressService(USAS) class
The USAS class handles the actual API request to the US Address validator. It contains an instance of the SmartyConfig class, for reasons explained in the "Changing API Key" section.

There are 2 methods in USAS. The getSmartyAddress method takes in an Address object, builds out a url query from its properties and the Auth-Id and Auth-token and then sends a get request with the Address information to the US Address Validator API. From the response body JSON, it then creates 7 new strings from various fields in the JSON and returns a new AddressDTO object, constructed from the 7 strings.

If the address entered was an invalid one, the AddressDTO will still be instantiated but all of it's string properties will be null and all of it's int properties will be 0.


The second method method - getAddressDTOs - takes in a List of Address objects, runs each object through the getSmartyAddress method, adds the resulting AddressDTO to a new List and returns the list.

### AddressDTO model class

The AddressDTO functions as a model for the necessary information contained in the API response. It has 7 properties , the first 4 combined equal the Address class's Street property (primaryNumber,streetDirection,streetName,streetSuffix), cityName, zipCode, and plus4Code (the extra 4 digits of the zipcode).

These are kept seperate since future fuunctionality may require manipulation of individual components of the returned address. However, 2 methods make for a more succinct construction of AddressDTO instances. Besides the standard getters, there is getFullStreetAddress() which returns the primaryNumber,streetDirection (if it exists),streetName, and streetSuffix properties into a single string.
And getFullZipCode() combines the zipcode and plus4Code with a hyphen into a single string.


### OutputBuilder class
A little service class with 3 methods to build out a single line of output. It implements the "Outputable" interface and overrides its methods:
1. buildAdressOutput concatenates the 3 properties of an Address object into the first half of a output response line.
2. buildDTOOutput builds the second half of the response output. It checks to see if an AddressDTO's cityName is null, and returns "Invalid Address" if so. Otherwise, it concatenates the properties of the AddressDTO to create a string from the API's response data.
3. buildFullOutput combines the results of the first 2 methods and adds extra formating to return a string.

The creation of the Outputable interface is inteded to help with future functionality.

### App class
Finally, The App class is where the other classes come together combine to handle a single run of the program. It contains an instance of a FileReader, USAS, and OutputBuilder.

The retreiveFileUser() method outputs to the console, scans in userInput, and returns a string of the User input file path.

Then the run() method: 
1. enters the filepath into the FileReader.readFile method to produce a list of Addresses from the file input. 
2. Creates a list of corresponding AddressDTOs is then made from running the addressService.getAddressDTOs method with the Address list as parameter.
3.Iterates through the address list in a for-loop. Running each address and corresponding addressDTO (based on index) through the OutBuilder.buildFullOutput function, and printing the result to the console.

After the program has printed all of the lines, it exits. The system actually runs in the EmporaAssignmentApplication class, using an instance of the App class.



package com.example.empora;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class EmporaAssignmentApplication {

	public static void main(String[] args) {
	App app = new App();
		SpringApplication.run(EmporaAssignmentApplication.class, args);
		Scanner userInput = new Scanner(System.in);
		System.out.println("Please enter the file to be read:");
		String filePath = userInput.next();
		System.out.println("Thank you");
		System.out.println(" ");

		app.run(filePath);

		System.out.println("");
		System.out.println("The End");

	}



}

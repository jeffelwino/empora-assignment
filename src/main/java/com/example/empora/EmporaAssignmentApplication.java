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
		app.run();

	}



}

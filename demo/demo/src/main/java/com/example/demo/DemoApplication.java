package com.example.demo;

import com.example.demo.model.Password;
import com.example.demo.model.PasswordCategory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		//System.out.println("Hellow from main");
		//SpringApplication.run(DemoApplication.class, args);
		Password password = new Password(PasswordCategory.MEDIUM, "XYZ");
		password.setPasswordCaesar(password);
		System.out.println(password.getPassword());
	}

}

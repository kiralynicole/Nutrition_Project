package com.example.demo;


import com.example.demo.model.Password;
import com.example.demo.model.PasswordCategory;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PasswordTest {

    @Test
    public void validateWeakPassword1(){
        Password password = new Password(PasswordCategory.WEAK, "abc");

        password.setPasswordCaesar(password);

        assertEquals("bcd", password.getPassword(), "The weak password encryption did not work as expected.");
    }
    @Test
    public void validateWeakPassword2(){
        Password password = new Password(PasswordCategory.WEAK, "ANA");

        password.setPasswordCaesar(password);

        assertEquals("BOB", password.getPassword(), "The weak password encryption did not work as expected.");
    }

    @Test
    public void validateMediumPassword1(){
        Password password = new Password(PasswordCategory.MEDIUM, "XYZ");
        password.setPasswordCaesar(password);
        assertEquals("BCD", password.getPassword(),"The medium password encryption did not work as expected." );
    }
    @Test
    public void validateMediumPassword2(){
        Password password = new Password(PasswordCategory.MEDIUM, "JAVA");
        password.setPasswordCaesar(password);
        assertEquals("NEZE", password.getPassword(),"The medium password encryption did not work as expected." );
    }

    @Test
    public void validateMediumPassword3(){
        Password password = new Password(PasswordCategory.MEDIUM, "ABC");
        password.setPasswordCaesar(password);
        assertEquals("EFG", password.getPassword(),"The medium password encryption did not work as expected." );
    }

}

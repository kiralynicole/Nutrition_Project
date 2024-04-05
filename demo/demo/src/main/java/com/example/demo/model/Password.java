package com.example.demo.model;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
public class Password {

    private PasswordCategory passwordCategory;
    private String password;

    public void setPasswordCaesar(Password password){
        StringBuilder encrypted = new StringBuilder();
        int shift = getShiftByCategory(this.getPasswordCategory());

        for(char character:password.getPassword().toCharArray()){
            if (Character.isLetter(character)) {
                char base = (Character.isLowerCase(character)) ? 'a' : 'A';
                int offset = (character - base + shift) % 26;
                encrypted.append((char) (base + offset));
            }else{
                encrypted.append(character);
            }
        }

        this.setPassword(encrypted.toString());

}

    private int getShiftByCategory(PasswordCategory category) {
        switch (category) {
            case WEAK:
                return 1;
            case MEDIUM:
                return 4;
            case COMPLEX:
                return LocalDate.now().getDayOfMonth();
            default:
                throw new IllegalArgumentException("Unknown PasswordCategory: " + category);
        }
    }
}

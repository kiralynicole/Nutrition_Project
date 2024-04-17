package com.example.demo.data;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

@AllArgsConstructor
@Builder
@Data

public class UserData  {
    private final UserRepository userRepository;
    //sau are o instanta a clasei cu fisiere

}

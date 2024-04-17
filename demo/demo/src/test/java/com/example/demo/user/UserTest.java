package com.example.demo.user;

import com.example.demo.interfaces.UserInterface;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for the {@link UserService} class.
 */
@SpringBootTest
public class UserTest {

    @Mock
    private UserRepository userRepository;

    //obiectul pe care il testam
    private UserInterface userInterface;

    /**
     * Sets up the testing environment before each test, initializes mocks and the user service interface.
     */
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        userInterface = new UserService(userRepository);

    }

    /**
     * Verifies that the save operation works correctly by ensuring that the repository's save method is called with the correct user.
     */
    @Test
    public void saveUserTest(){
        User expectedUser = new User("John Doe", "Baritiu nr.15", "john.doe@example.com", "johndoe123", "0723459876", false, false);

      //  when(userRepository.save(expectedUser)).thenReturn(expectedUser);
        userInterface.createUser(expectedUser);

        verify(userRepository).save(expectedUser);
        //assertEquals(expectedUser, savedUser);

    }

    /**
     * Tests the update functionality by verifying that the findById and save methods are invoked correctly with the proper user details.
     */
    @Test
    public void updateUserTest(){
        User existingUser = new User(1, "John Doe", "Baritiu nr.15", "john.doe@example.com", "johndoe123", "0723459876", false, false);
        User updatedUser = new User (1,"John Doew", "Baritiu nr.15", "john.doew@example.com", "johndoe123", "0723459876", false, false);


        when(userRepository.findById(existingUser.getId())).thenReturn(Optional.of(existingUser));
        when(userRepository.save(updatedUser)).thenReturn(updatedUser);

        userInterface.updateUser(updatedUser);

        verify(userRepository).findById(existingUser.getId());
        verify(userRepository).save(updatedUser);

        //assertEquals(existingUser.getId(), res.getId());
        //assertEquals(expectedUser, savedUser);
    }

    /**
     * Ensures that the delete operation is tested by verifying the deletion process through the repository's deleteById method.
     */
    @Test
    public void deletedUserTest(){
        User existingUser = new User(1, "John Doe", "Baritiu nr.15", "john.doe@example.com", "johndoe123", "0723459876", false, false);

        when(userRepository.findById(existingUser.getId())).thenReturn(Optional.of(existingUser));

        userInterface.deleteUser(existingUser.getId());

        verify(userRepository).deleteById(existingUser.getId());
    }
    /**
     * Validates the retrieval of a user by ID by ensuring that the repository's findById method is called.
     */
    @Test
    public void getIdUserTest(){
        User existingUser = new User(1, "John Doe", "Baritiu nr.15", "john.doe@example.com", "johndoe123", "0723459876", false, false);

        when(userRepository.findById(existingUser.getId())).thenReturn(Optional.of(existingUser));

        userInterface.getUserById(existingUser.getId());

        verify(userRepository).findById(existingUser.getId());
    }

    /**
     * Tests the retrieval of a user by name to verify the correct operation of the repository's findByName method.
     */
    @Test
    public void getNameUserTest(){
        User existingUser = new User(1, "John Doe", "Baritiu nr.15", "john.doe@example.com", "johndoe123", "0723459876", false, false);

        when(userRepository.findByName(existingUser.getName())).thenReturn(Optional.of(existingUser));

        userInterface.getUserByName(existingUser.getName());

        verify(userRepository).findByName(existingUser.getName());
    }

    /**
     * Confirms that all users are retrieved correctly by checking the findAll method of the repository.
     */
    @Test
    public void getAllUserTest(){
        User user1 = new User(1, "John Doe", "Baritiu nr.15", "john.doe@example.com", "johndoe123", "0723459876", false, false);
        User user2 = new User(2, "Johny Doe", "Baritiu nr.10", "johny.doe@example.com", "johnydoe123", "0729859876", false, false);

        List<User> users = Arrays.asList(user1,user2);

       when(userRepository.findAll()).thenReturn(users);

        userInterface.getAllUsers();

        verify(userRepository).findAll();
    }
}

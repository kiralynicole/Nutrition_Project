package com.example.demo.user;

import com.example.demo.interfaces.UserInterface;
import com.example.demo.listeners.EmailMsgListener;
import com.example.demo.model.Store;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.NotificationService;
import com.example.demo.service.UserService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

/**
 * Tests for the {@link UserService} class.
 */
@SpringBootTest
public class UserTest {

    @Mock
    private UserRepository userRepository;

    //obiectul pe care il testam
    private UserInterface userInterface;

    @Mock
    private NotificationService notificationService;

    @Mock
    private Store store;

    /**
     * Sets up the testing environment before each test, initializes mocks and the user service interface.
     */
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        when(store.getNotificationService()).thenReturn(notificationService); // Ensure this is correctly mocked
        userInterface = new UserService(userRepository, store);
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

    /**
     * Tests addSale method when an admin user attempts to initiate a sale.
     */
    @Test
    public void testAddSale_AdminInitiates() {
        User admin = new User(1, "Admin", "Observatorului nr. 10", "admin@example.com", "admin123", "0723678976", true, false);
        User user1= new User(2, "user1", "Observatorului nr. 1", "admin@example.com", "admin123", "0723678976", false, false);
        User user2 = new User(3, "user2", "Observatorului nr. 20", "admin@example.com", "admin123", "0723678976", false, false);
    List<User> users = Arrays.asList(admin, user1, user2);

        when(userRepository.findById(1)).thenReturn(Optional.of(admin));
        when(userRepository.findAll()).thenReturn(users);
        when(userRepository.save(admin)).thenReturn(admin);

        for(User user:users){
            when(userRepository.save(user)).thenReturn(user);
        }

        userInterface.addSale(1);

        verify(notificationService, times(users.size())).subscribe(any(EmailMsgListener.class));

        for(User user:users){
            assertTrue(user.isSale());
        }
    }

    /**
     * Tests addSale method failure when a non-admin user attempts to initiate a sale.
     */

    @Test
    public void testAddSale_NonAdminInitiates() {
        User admin = new User(1, "Admin", "Observatorului nr. 10", "admin@example.com", "admin123", "0723678976", true, false);
        User user1= new User(2, "user1", "Observatorului nr. 1", "admin@example.com", "admin123", "0723678976", false, false);
        User user2 = new User(3, "user2", "Observatorului nr. 20", "admin@example.com", "admin123", "0723678976", false, false);
        List<User> users = Arrays.asList(admin, user1, user2);

        when(userRepository.findById(user1.getId())).thenReturn(Optional.of(user1));
        when(userRepository.findAll()).thenReturn(users);

        userInterface.addSale(user1.getId());

        for(User user:users){
            assertFalse(user.isSale(), "Non admin initiates the sale");
        }

        verify(notificationService, never()).subscribe(any(EmailMsgListener.class));

    }

}

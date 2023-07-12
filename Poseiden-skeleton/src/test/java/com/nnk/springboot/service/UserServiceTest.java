package com.nnk.springboot.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class UserServiceTest {

  @Autowired
  IUserService iUserService;

  @MockBean
  UserRepository userRepository;

  User user = new User();

  List<User> listUsers = new ArrayList<>();

  @BeforeEach
  public void setup() {

    user.setId(1);
    user.setUsername("benoit");
    user.setFullname("benoit");
    user.setPassword("Benoit1!");
    user.setRole("USER");
  }

  @Test
  public void getUsersTest() {

    listUsers.add(user);

    // when
    when(userRepository.findAll()).thenReturn(listUsers);

    assertEquals(iUserService.getAllUsers().size(), 1);

    assertEquals((int) iUserService.getAllUsers().get(0).getId(), 1);
    assertEquals(iUserService.getAllUsers().get(0).getUsername(), "benoit");
    assertEquals(iUserService.getAllUsers().get(0).getFullname(), "benoit");
    assertEquals(iUserService.getAllUsers().get(0).getPassword(), "Benoit1!");
    assertEquals(iUserService.getAllUsers().get(0).getRole(), "USER");
  }

  @Test
  public void getUserByIdTest() throws Exception {

    // when
    when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
    
    assertEquals((int)iUserService.getUserById(user.getId()).getId(), 1);
    assertEquals(iUserService.getUserById(user.getId()).getPassword(),
        "Benoit1!");
    assertEquals(iUserService.getUserById(user.getId()).getUsername(),
        "benoit");

  }

  @Test
  public void saveUserTest() throws Exception {

    User user2 = new User();

    user2.setId(2);
    user2.setUsername("benoit");
    user2.setFullname("benoit");
    user2.setPassword("Benoit1!");
    user2.setRole("USER");

    when(userRepository.save(user2)).thenReturn(user2);

    // when

    assertEquals(iUserService.saveUser(user2), user2);
    verify(userRepository, times(1)).save(user2);
  }

  @Test
  public void updateUserTest() throws Exception {

    user.setId(5);
    user.setUsername("benoit");
    user.setFullname("benoit");
    user.setPassword("Benoit1!");
    user.setRole("USER");

    when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
    when(userRepository.save(user)).thenReturn(user);
    // when

    assertEquals(iUserService.updateUser(user), user);
    verify(userRepository, times(1)).save(user);

  }

  @Test
  public void updateUserFailTest() throws Exception {
    try {
      user.setId(5);
      user.setUsername("benoit");
      user.setFullname("benoit");
      user.setPassword("Benoit1!");
      user.setRole("USER");

      when(userRepository.findById(user.getId())).thenReturn(Optional.empty());

      // when
      iUserService.updateUser(user);

    } catch (Exception e) {
      assertEquals(e.getMessage(), "User to update not founded");
    }
  }

  @Test
    public void deleteUserTest() throws Exception {
    
    when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
      
      // when
    iUserService.deleteUser(user);
      
      verify(userRepository, times(1)).delete(user);

    }

  @Test
  public void deleteUserFailTest() throws Exception {

    try {
      when(userRepository.findById(user.getId())).thenReturn(Optional.empty());

      // when
      iUserService.deleteUser(user);

    } catch (Exception e) {
      assertEquals(e.getMessage(), "User to delete not founded");
      verify(userRepository, times(0)).delete(user);
    }

  }

}

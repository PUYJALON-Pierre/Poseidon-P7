package com.nnk.springboot.service;

import java.util.List;

import com.nnk.springboot.domain.User;


/**
 * Interface for services operations concerning User
 *
 * @author PUYJALON Pierre
 * @since 22/06/2023
 */
public interface IUserService {

  /**
   * Retrieve all Users
   *
   * @return List of AppAccount
   */
  public List<User> getAllUsers();

  
  /**
   * Retrieve a User by id
   *
   * @param id - int
   * @return User
   */
  public User getUserById(int id);

  public User saveUser(User user) throws Exception;

  public User updateUser(User user) throws Exception;

  public void deleteUser(User user) throws Exception;
}

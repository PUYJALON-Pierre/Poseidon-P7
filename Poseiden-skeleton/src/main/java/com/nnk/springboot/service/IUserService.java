package com.nnk.springboot.service;

import java.util.List;

import com.nnk.springboot.domain.User;

/**
 * Interface for services operations concerning User in Trading App UI (Poseidon inc)
 *
 * @author PUYJALON Pierre
 * @since 25/06/2023
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

  /**
   * Save a User
   * 
   * @param user - User
   * @return User
   * @throws Exception
   */
  public User saveUser(User user) throws Exception;

  /**
   * Update an existing User
   * 
   * @param user - User
   * @return User
   * @throws Exception
   */
  public User updateUser(User user) throws Exception;

  /**
   * Delete a User
   * 
   * @param user - User
   * @throws Exception
   */
  public void deleteUser(User user) throws Exception;
}

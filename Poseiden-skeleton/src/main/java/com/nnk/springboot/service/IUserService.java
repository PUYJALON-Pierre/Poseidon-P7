package com.nnk.springboot.service;

import java.util.List;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.domain.User;

public interface IUserService {

  public List<User> getAllUsers();

  public User getUserById(int id);

  public User saveUser(User user) throws Exception;

  public User updateUser(User user) throws Exception;

  public void deleteUser(User user) throws Exception;
}

package com.nnk.springboot.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

  final static Logger logger = LogManager.getLogger(UserServiceImpl.class);

  @Autowired
  UserRepository userRepository;

  @Override
  public List<User> getAllUsers() {
    logger.debug("Start finding all users");
    logger.info("Getting all users");

    if (userRepository.findAll().isEmpty()) {
      logger.error("No users founded");
    }
    return userRepository.findAll();
  }

  @Override
  public User getUserById(int id) {
    logger.debug("Finding user with id : {}", id);
    if (userRepository.findById(id).isPresent()) {
      logger.info("Founded user with id : {}", id);
      return userRepository.findById(id).get();
    } else {
      logger.error("No user founded with id : {}", id);
      return null;
    }
  }

  @Override
  public User saveUser(User user) throws Exception {
    logger.debug("Creating user with id : {}", user.getId());
    // Checking if user already exist
    if (userRepository.findById(user.getId()).isPresent()) {
      logger.error("This user cannot be created because already exist");
      throw new Exception("This user already exist");
    } else {
      logger.info("User with id : {} created", user.getId());
      userRepository.save(user);
    }
    return user;
  }

  @Override
  public User updateUser(User user) throws Exception {
    logger.debug("Updating user with id : {}", user.getId());
    // Checking if user already exist
    if (userRepository.findById(user.getId()).isPresent()) {
      logger.info("User with id : {} updated", user.getId());
      return userRepository.save(user);
    }

    else {
      logger.error("This user cannot be updated because not founded");
      throw new Exception("User to update not founded");
    }
  }

  @Override
  public void deleteUser(User user) throws Exception {
    logger.debug("Deleting user with id : {}", user.getId());

    if (getUserById(user.getId()) != null) {
      logger.info("User with id : {} deleted", user.getId());
      userRepository.delete(user);
    } else {
      logger.error("This user cannot be deleted because not founded");
      throw new Exception("User to delete not founded");
    }
  }

}

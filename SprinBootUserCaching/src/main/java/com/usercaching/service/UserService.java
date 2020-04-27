package com.usercaching.service;

import com.usercaching.model.User;

public interface UserService {
  
 public User findUserByEmail(String email);
 
 public void saveUser(User user);
 
 public void deleteUser(long id);
}
package com.usercaching.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.usercaching.model.Role;
import com.usercaching.model.User;
import com.usercaching.repository.RoleRespository;
import com.usercaching.repository.UserRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

@Service("userService")
public class UserServiceImpl implements UserService {
 
 @Autowired
 private UserRepository userRepository;
 
 @Autowired
 private RoleRespository roleRespository;
 
 @Autowired
 private BCryptPasswordEncoder bCryptPasswordEncoder;

 @Override
 @Cacheable(value="login")
 public User findUserByEmail(String email) {
	 try {
		System.out.println("Sleep for 10 sec");
		Thread.sleep(1000*10);
	} catch (InterruptedException e) {
		e.printStackTrace();	}
  return userRepository.findByEmail(email);
 }

 @Override
 @CacheEvict(value="signup",allEntries = true)
 public void saveUser(User user) {
  user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
  user.setActive(1);
  Role userRole = roleRespository.findByRole("ADMIN");
  user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
  userRepository.save(user);
 }

@Override
@Transactional
public void deleteUser(long id) {
	 Optional<User> user = userRepository.findById(id);
	 if(user.isPresent()) {
		 userRepository.deleteById(id);
	 }
	
}

 

}
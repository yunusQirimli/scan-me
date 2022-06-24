package com.yunus.qirimli.scanme.service;

import com.yunus.qirimli.scanme.entity.User;
import com.yunus.qirimli.scanme.service.dto.UserCredentialsDTO;
import com.yunus.qirimli.scanme.service.dto.UserDTO;

public interface UserService {

  User getUser(Long userId);

  User registerUser(UserCredentialsDTO userCredentialsDTO);

  void updateUser(Long userId, UserDTO userDTO);

  void updateUserCredentials(Long userId, UserCredentialsDTO userCredentialsDTO);

}

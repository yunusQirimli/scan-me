package com.yunus.qirimli.scanme.service.impl;

import com.yunus.qirimli.scanme.entity.User;
import com.yunus.qirimli.scanme.repository.UserRepository;
import com.yunus.qirimli.scanme.service.UserService;
import com.yunus.qirimli.scanme.service.dto.UserCredentialsDTO;
import com.yunus.qirimli.scanme.service.dto.UserDTO;
import com.yunus.qirimli.scanme.service.mapper.UserMapper;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

  private static final String USER_NOT_FOUND_ERR_MSG = "User not found for id=%s";

  private final UserMapper userMapper = UserMapper.INSTANCE;
  private final UserRepository userRepository;

  @Override
  public User registerUser(final UserCredentialsDTO userCredentialsDTO) {
    final User user = userMapper.UserCredentialsDTOToUser(userCredentialsDTO);
    user.setRegistrationDate(LocalDate.now());
    return userRepository.save(user);
  }

  @Override
  public User getUser(final Long userId) {
    return userRepository
        .findById(userId)
        .orElseThrow(
            () -> new IllegalArgumentException(String.format(USER_NOT_FOUND_ERR_MSG, userId)));
  }

  @Override
  public void updateUser(final Long userId, final UserDTO userDTO) {
    final User user = getUser(userId);
    userMapper.updateUserFromUserDTO(userDTO, user);
    userRepository.save(user);
  }

  @Override
  public void updateUserCredentials(
      final Long userId, final UserCredentialsDTO userCredentialsDTO) {
    final User user = getUser(userId);
    userMapper.updateUserFromUserCredentialsDTO(userCredentialsDTO, user);
    userRepository.save(user);
  }


}

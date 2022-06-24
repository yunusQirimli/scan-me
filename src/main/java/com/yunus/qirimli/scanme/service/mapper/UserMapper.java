package com.yunus.qirimli.scanme.service.mapper;

import com.yunus.qirimli.scanme.entity.User;
import com.yunus.qirimli.scanme.service.dto.UserCredentialsDTO;
import com.yunus.qirimli.scanme.service.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

  UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

  User UserCredentialsDTOToUser(UserCredentialsDTO userCredentialsDTO);

  @Mapping(target = "registrationDate", ignore = true)
  void updateUserFromUserDTO(UserDTO userDTO, @MappingTarget User user);

  void updateUserFromUserCredentialsDTO(UserCredentialsDTO userDTO, @MappingTarget User user);
}

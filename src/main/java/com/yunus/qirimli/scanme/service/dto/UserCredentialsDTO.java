package com.yunus.qirimli.scanme.service.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCredentialsDTO {

  static final int PASSWORD_SIZE_MIN = 4;
  static final int PASSWORD_SIZE_MAX = 16;

  @Email private String email;

  @NotBlank
  @Size(min = PASSWORD_SIZE_MIN, max = PASSWORD_SIZE_MAX)
  private String password;
}

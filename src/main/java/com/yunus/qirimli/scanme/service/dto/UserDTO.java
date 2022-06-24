package com.yunus.qirimli.scanme.service.dto;

import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserDTO {

  private String firstName;
  private String lastName;
  private String registrationDate;

  @Builder
  public UserDTO(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getRegistrationDate() {
    return registrationDate;
  }
}

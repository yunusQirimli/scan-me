package com.yunus.qirimli.scanme.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserService {

  @Id
  @SequenceGenerator(
      name = "user_service_sequence",
      sequenceName = "user_service_sequence",
      allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_service_sequence")
  private Long userServiceId;

  private String title;
  private String description;
  private Double price;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinTable(
      name = "user_service_application_user",
      joinColumns = @JoinColumn(name = "user_sevice_id", referencedColumnName = "userServiceId"),
      inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "userId"))
  @JsonIgnoreProperties(value = "userServices")
  private User serviceOwner;
}

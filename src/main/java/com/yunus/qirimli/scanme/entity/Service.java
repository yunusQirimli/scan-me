package com.yunus.qirimli.scanme.entity;

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
public class Service {

  @Id
  @SequenceGenerator(
      name = "service_sequence",
      sequenceName = "service_sequence",
      allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "service_sequence")
  private Long serviceId;

  private String title;
  private String description;
  private Double price;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinTable(
      name = "service_application_user",
      joinColumns = @JoinColumn(name = "sevice_id", referencedColumnName = "serviceId"),
      inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "userId"))
  private User serviceOwner;
}

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
public class Phone {

  @Id
  @SequenceGenerator(name = "phone_sequence", sequenceName = "phone_sequence", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "phone_sequence")
  private Long phoneId;

  private String number;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinTable(
      name = "phone_application_user",
      joinColumns = @JoinColumn(name = "phone_id", referencedColumnName = "phoneId"),
      inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "userId"))
  private User phoneOwner;
}

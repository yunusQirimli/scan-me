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
public class Contact {

  @Id
  @SequenceGenerator(
      name = "contact_sequence",
      sequenceName = "contact_sequence",
      allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contact_sequence")
  private Long contactId;

  private String title;
  private String url;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinTable(
      name = "contact_application_user",
      joinColumns = @JoinColumn(name = "contact_id", referencedColumnName = "contactId"),
      inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "userId"))
  private User contactOwner;
}

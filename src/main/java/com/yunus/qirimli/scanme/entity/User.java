package com.yunus.qirimli.scanme.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(
    name = "application_user",
    uniqueConstraints = @UniqueConstraint(name = "email_unique", columnNames = "email"))
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(
    exclude = {
      "ownedProfiles",
      "viewedProfiles",
      "bookmarkedProfiles",
      "phones",
      "services",
      "contacts"
    })
public class User {

  @Id
  @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
  private Long userId;

  private String firstName;
  private String lastName;
  private LocalDate registrationDate;
  private String email;
  private String password;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "application_user_role",
      joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "userId"),
      inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "roleId"))
  private Set<Role> roles;

  @OneToMany(mappedBy = "profileOwner", fetch = FetchType.LAZY)
  private List<Profile> ownedProfiles;

  @OneToMany(fetch = FetchType.LAZY)
  @JoinTable(
      name = "application_user_viewed_profile",
      joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "userId"),
      inverseJoinColumns = @JoinColumn(name = "profile_id", referencedColumnName = "profileId"))
  private List<Profile> viewedProfiles;

  @OneToMany(fetch = FetchType.LAZY)
  @JoinTable(
      name = "application_user_bookmarked_profile",
      joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "userId"),
      inverseJoinColumns = @JoinColumn(name = "profile_id", referencedColumnName = "profileId"))
  private List<Profile> bookmarkedProfiles;

  @OneToMany(mappedBy = "phoneOwner", fetch = FetchType.LAZY)
  private List<Phone> phones;

  @OneToMany(mappedBy = "serviceOwner", fetch = FetchType.LAZY)
  private List<Service> services;

  @OneToMany(mappedBy = "contactOwner", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<Contact> contacts;
}

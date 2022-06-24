package com.yunus.qirimli.scanme.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"profileOwner", "phones", "contacts", "userServices"})
public class Profile {

  @Id
  @SequenceGenerator(
      name = "profile_sequence",
      sequenceName = "profile_sequence",
      allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "profile_sequence")
  private Long profileId;

  private String title;
  private String description;
  private String profilePicture;
  private boolean active;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinTable(
      name = "profile_application_user",
      joinColumns = @JoinColumn(name = "profile_id", referencedColumnName = "profileId"),
      inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "userId"))
  @JsonIgnoreProperties(value = {"ownedProfiles", "viewedProfiles", "bookmarkedProfiles"})
  private User profileOwner;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
      name = "profile_phone",
      joinColumns = @JoinColumn(name = "profile_id", referencedColumnName = "profileId"),
      inverseJoinColumns = @JoinColumn(name = "phone_id", referencedColumnName = "phoneId"))
  private List<Phone> phones;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
      name = "profile_contact",
      joinColumns = @JoinColumn(name = "profile_id", referencedColumnName = "profileId"),
      inverseJoinColumns = @JoinColumn(name = "contact_id", referencedColumnName = "contactId"))
  private List<Contact> contacts;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
      name = "profile_user_service",
      joinColumns = @JoinColumn(name = "profile_id", referencedColumnName = "profileId"),
      inverseJoinColumns = @JoinColumn(name = "user_service_id", referencedColumnName = "userServiceId"))
  private List<UserService> userServices;
}

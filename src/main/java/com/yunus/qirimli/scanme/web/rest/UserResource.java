package com.yunus.qirimli.scanme.web.rest;

import com.yunus.qirimli.scanme.constants.ScanMeConstants;
import com.yunus.qirimli.scanme.entity.Contact;
import com.yunus.qirimli.scanme.entity.Phone;
import com.yunus.qirimli.scanme.entity.Profile;
import com.yunus.qirimli.scanme.entity.Role;
import com.yunus.qirimli.scanme.entity.User;
import com.yunus.qirimli.scanme.entity.UserService;
import com.yunus.qirimli.scanme.repository.ContactRepository;
import com.yunus.qirimli.scanme.repository.PhoneRepository;
import com.yunus.qirimli.scanme.repository.ProfileRepository;
import com.yunus.qirimli.scanme.repository.RoleRepository;
import com.yunus.qirimli.scanme.repository.UserRepository;
import com.yunus.qirimli.scanme.repository.UserServiceRepository;
import com.yunus.qirimli.scanme.service.dto.UserCredentialsDTO;
import com.yunus.qirimli.scanme.service.dto.UserDTO;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/v1/users")
@RestController
public class UserResource {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final ProfileRepository profileRepository;
  private final PhoneRepository phoneRepository;
  private final UserServiceRepository userServiceRepository;
  private final ContactRepository contactRepository;
  private final com.yunus.qirimli.scanme.service.UserService userService;

  @Autowired
  public UserResource(
      UserRepository userRepository,
      RoleRepository roleRepository,
      ProfileRepository profileRepository,
      PhoneRepository phoneRepository,
      UserServiceRepository userServiceRepository,
      ContactRepository contactRepository,
      com.yunus.qirimli.scanme.service.UserService userService) {
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
    this.profileRepository = profileRepository;
    this.phoneRepository = phoneRepository;
    this.userServiceRepository = userServiceRepository;
    this.contactRepository = contactRepository;
    this.userService = userService;
  }

  @GetMapping
  public ResponseEntity<List<User>> getUsers() {
    return ResponseEntity.ok().body(userRepository.findAll());
  }

  @PostMapping
  public ResponseEntity<User> registerUser(
      @Valid @RequestBody UserCredentialsDTO userCredentialsDTO) throws URISyntaxException {
    final User user = userService.registerUser(userCredentialsDTO);
    return ResponseEntity.created(
            new URI(ScanMeConstants.USER_RESOURCE_API_V1_URI + user.getUserId()))
        .body(user);
  }

  @GetMapping("/{userId}")
  public ResponseEntity<User> getUserById(@PathVariable("userId") Long userId) {
    return ResponseEntity.ok().body(userService.getUser(userId));
  }

  @PutMapping("/{userId}")
  public ResponseEntity<Void> updateUser(
      @PathVariable("userId") Long userId, @Valid @RequestBody UserDTO userDTO) {
    userService.updateUser(userId, userDTO);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{userId}")
  public ResponseEntity<Void> deleteUser(@PathVariable("userId") Long userId) {
    userRepository.delete(userService.getUser(userId));
    return ResponseEntity.ok().build();
  }

  @PutMapping("/{userId}/roles")
  public ResponseEntity<Void> addRole(@PathVariable("userId") Long userId, @RequestBody Role role) {
    final User user = userService.getUser(userId);
    user.getRoles().add(roleRepository.findById(role.getRoleId()).orElseThrow());
    userRepository.save(user);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{userId}/roles")
  public ResponseEntity<Void> deleteRole(
      @PathVariable("userId") Long userId, @RequestBody Role role) {
    final User user = userService.getUser(userId);
    user.getRoles().remove(roleRepository.findById(role.getRoleId()).orElseThrow());
    userRepository.save(user);
    return ResponseEntity.ok().build();
  }

  @PutMapping("/{userId}/owned-profiles")
  public ResponseEntity<Void> addOwnedProfile(
      @PathVariable("userId") Long userId, @RequestBody Profile profile) {
    final User user = userService.getUser(userId);
    user.getOwnedProfiles().add(profileRepository.findById(profile.getProfileId()).orElseThrow());
    userRepository.save(user);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{userId}/owned-profiles")
  public ResponseEntity<Void> deleteOwnedProfile(
      @PathVariable("userId") Long userId, @RequestBody Profile profile) {
    final User user = userService.getUser(userId);
    user.getOwnedProfiles()
        .remove(profileRepository.findById(profile.getProfileId()).orElseThrow());
    userRepository.save(user);
    return ResponseEntity.ok().build();
  }

  @PutMapping("/{userId}/viewed-profiles")
  public ResponseEntity<Void> addViewedProfile(
      @PathVariable("userId") Long userId, @RequestBody Profile profile) {
    final User user = userService.getUser(userId);
    user.getViewedProfiles().add(profileRepository.findById(profile.getProfileId()).orElseThrow());
    userRepository.save(user);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{userId}/viewed-profiles")
  public ResponseEntity<Void> deleteViewedProfile(
      @PathVariable("userId") Long userId, @RequestBody Profile profile) {
    final User user = userService.getUser(userId);
    user.getViewedProfiles()
        .remove(profileRepository.findById(profile.getProfileId()).orElseThrow());
    userRepository.save(user);
    return ResponseEntity.ok().build();
  }

  @PutMapping("/{userId}/bookmarked-profiles")
  public ResponseEntity<Void> addBookmarkedProfile(
      @PathVariable("userId") Long userId, @RequestBody Profile profile) {
    final User user = userService.getUser(userId);
    user.getBookmarkedProfiles()
        .add(profileRepository.findById(profile.getProfileId()).orElseThrow());
    userRepository.save(user);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{userId}/bookmarked-profiles")
  public ResponseEntity<Void> deleteBookmarkedProfile(
      @PathVariable("userId") Long userId, @RequestBody Profile profile) {
    final User user = userService.getUser(userId);
    user.getBookmarkedProfiles()
        .remove(profileRepository.findById(profile.getProfileId()).orElseThrow());
    userRepository.save(user);
    return ResponseEntity.ok().build();
  }

  @PutMapping("/{userId}/phones")
  public ResponseEntity<Void> addPhone(
      @PathVariable("userId") Long userId, @RequestBody Phone phone) {
    final User user = userService.getUser(userId);
    user.getPhones()
        .add(phoneRepository.findById(phone.getPhoneId()).orElseThrow());
    userRepository.save(user);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{userId}/phones")
  public ResponseEntity<Void> deletePhone(
      @PathVariable("userId") Long userId, @RequestBody Phone phone) {
    final User user = userService.getUser(userId);
    user.getPhones()
        .remove(phoneRepository.findById(phone.getPhoneId()).orElseThrow());
    userRepository.save(user);
    return ResponseEntity.ok().build();
  }

  @PutMapping("/{userId}/services")
  public ResponseEntity<Void> addService(
      @PathVariable("userId") Long userId, @RequestBody UserService userService) {
    final User user = this.userService.getUser(userId);
    user.getUserServices()
        .add(userServiceRepository.findById(userService.getUserServiceId()).orElseThrow());
    userRepository.save(user);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{userId}/services")
  public ResponseEntity<Void> deleteService(
      @PathVariable("userId") Long userId, @RequestBody UserService userService) {
    final User user = this.userService.getUser(userId);
    user.getUserServices()
        .remove(userServiceRepository.findById(userService.getUserServiceId()).orElseThrow());
    userRepository.save(user);
    return ResponseEntity.ok().build();
  }

  @PutMapping("/{userId}/contacts")
  public ResponseEntity<Void> addContact(
      @PathVariable("userId") Long userId, @RequestBody Contact contact) {
    final User user = userService.getUser(userId);
    user.getContacts()
        .add(contactRepository.findById(contact.getContactId()).orElseThrow());
    userRepository.save(user);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{userId}/contacts")
  public ResponseEntity<Void> deleteContact(
      @PathVariable("userId") Long userId, @RequestBody Contact contact) {
    final User user = userService.getUser(userId);
    user.getContacts()
        .remove(contactRepository.findById(contact.getContactId()).orElseThrow());
    userRepository.save(user);
    return ResponseEntity.ok().build();
  }
}

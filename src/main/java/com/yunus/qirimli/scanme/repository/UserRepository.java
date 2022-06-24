package com.yunus.qirimli.scanme.repository;

import com.yunus.qirimli.scanme.entity.User;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  @Query("select u from User u "
      + "join fetch u.ownedProfiles op")
  Set<User> findAllWithProfiles();

  User findUserByEmail(String email);

  @Query("select distinct u from User u "
      + "left join fetch u.ownedProfiles op "
      + "where u.email = :email")
  User findUserWithOwnedProfilesByEmail(@Param("email") String email);

  @Query("select distinct u from User u "
      + "left join fetch u.viewedProfiles vp "
      + "where u.email = :email")
  User findUserWithViewedProfilesByEmail(@Param("email") String email);

  @Query("select distinct u from User u "
      + "left join fetch u.bookmarkedProfiles bp "
      + "where u.email = :email")
  User findUserWithBookmarkedProfilesByEmail(@Param("email") String email);

  @Query("select distinct u from User u "
      + "left join fetch u.contacts bp "
      + "where u.email = :email")
  User findUserWithContactsByEmail(@Param("email") String email);
}

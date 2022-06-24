package com.yunus.qirimli.scanme.repository;

import com.yunus.qirimli.scanme.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

  Profile findProfileByTitle(String title);
}

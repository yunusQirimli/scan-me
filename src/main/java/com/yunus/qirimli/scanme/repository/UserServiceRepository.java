package com.yunus.qirimli.scanme.repository;

import com.yunus.qirimli.scanme.entity.UserService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserServiceRepository extends JpaRepository<UserService, Long> {

}

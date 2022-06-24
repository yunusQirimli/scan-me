package com.yunus.qirimli.scanme.repository;

import com.yunus.qirimli.scanme.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

  Role findRoleByName(String name);
}

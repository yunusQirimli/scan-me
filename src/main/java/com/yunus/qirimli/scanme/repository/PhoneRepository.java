package com.yunus.qirimli.scanme.repository;

import com.yunus.qirimli.scanme.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {

}

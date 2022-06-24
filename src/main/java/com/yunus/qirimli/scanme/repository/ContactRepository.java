package com.yunus.qirimli.scanme.repository;

import com.yunus.qirimli.scanme.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

  Contact findContactByTitle(String title);

}

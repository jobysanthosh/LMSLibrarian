package com.lms.LMSAdmin.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lms.LMSAdmin.pojo.Author;

@Repository
public interface AuthorDao extends JpaRepository<Author, Integer> {

}

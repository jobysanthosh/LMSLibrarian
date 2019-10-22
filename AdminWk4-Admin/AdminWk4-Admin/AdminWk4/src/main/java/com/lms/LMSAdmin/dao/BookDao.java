package com.lms.LMSAdmin.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lms.LMSAdmin.pojo.Book;

@Repository
public interface BookDao extends JpaRepository<Book, Integer> {

}

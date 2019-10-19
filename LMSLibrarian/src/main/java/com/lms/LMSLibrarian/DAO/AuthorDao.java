package com.lms.LMSLibrarian.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lms.LMSLibrarian.POJO.Author;

@Repository
public interface AuthorDao extends JpaRepository<Author, Integer>
{

}
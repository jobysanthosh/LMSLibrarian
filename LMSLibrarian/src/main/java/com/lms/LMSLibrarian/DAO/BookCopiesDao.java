package com.lms.LMSLibrarian.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.lms.LMSLibrarian.POJO.BookCopies;
import com.lms.LMSLibrarian.POJO.BookCopiesComposite;


@Repository
public interface BookCopiesDao extends JpaRepository<BookCopies, BookCopiesComposite> {	

}
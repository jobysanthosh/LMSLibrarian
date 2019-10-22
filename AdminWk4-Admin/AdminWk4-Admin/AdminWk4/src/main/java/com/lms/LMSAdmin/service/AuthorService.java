package com.lms.LMSAdmin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lms.LMSAdmin.dao.AuthorDao;
import com.lms.LMSAdmin.pojo.Author;

@Component
public class AuthorService {
	
	@Autowired
	AuthorDao authorDao;
	
	//Insert author record
	public Author insertAuthor(Author author) {
		return authorDao.save(author);
	}
	
	//Update author record
	public Author updateAuthor(Author author) {
		return authorDao.save(author);
	}
	
	//Delete author record
	public void deleteAuthor(Integer authorId) {
		authorDao.deleteById(authorId);;
	}
	
	//Get one author
	public Optional<Author> getAuthorById(Integer authorId) {
		return authorDao.findById(authorId);
	}
	
	//Get all author records
	public List<Author> getAllAuthors() {
		return authorDao.findAll();
	}
	
	//Validate author Id
	public boolean ifExists(Integer authorId) {
		List<Author> list = authorDao.findAll();
		
		boolean exists = list.stream()
				.anyMatch(id -> id.getAuthorId().equals(authorId));
	
		return exists;
	}
}

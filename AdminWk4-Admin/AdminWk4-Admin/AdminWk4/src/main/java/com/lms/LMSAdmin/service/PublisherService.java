package com.lms.LMSAdmin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lms.LMSAdmin.dao.PublisherDao;
import com.lms.LMSAdmin.pojo.Publisher;

@Component
public class PublisherService {
	
	@Autowired
	PublisherDao pubDao;
	
	//Insert publisher record
	public Publisher insertPub(Publisher publisher) {
		return pubDao.save(publisher);
	}
	
	//Update publisher record
	public Publisher updatePub(Publisher publisher) {
		return pubDao.save(publisher);
	}
	
	//Delete publisher record
	public void deletePub(Integer publisherId) {
		pubDao.deleteById(publisherId);
	}
	
	//Get one record
	public Optional<Publisher> getPubById(Integer publisherId) {
		return pubDao.findById(publisherId);
	}
	
	//Get all publisher records
	public List<Publisher> getAllPubs() {
		return pubDao.findAll();
	}
	
	//Validate publisher Id
	public boolean ifExists(Integer pubId) {
		List<Publisher> list = pubDao.findAll();
		
		boolean exists = list.stream()
				.anyMatch(id -> id.getPublisherId().equals(pubId));
	
		return exists;
	}
}

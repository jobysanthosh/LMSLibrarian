package com.lms.LMSAdmin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lms.LMSAdmin.dao.BorrowerDao;
import com.lms.LMSAdmin.pojo.Borrower;

@Component
public class BorrowerService {
	
	@Autowired
	BorrowerDao borrDao;
	
	//Insert record
	public Borrower insertBorr(Borrower borrower) {
		return borrDao.save(borrower);
	}
	
	//Update record
	public Borrower updateBorr(Borrower borrower) {
		return borrDao.save(borrower);
	}
	
	//Delete record
	public void deleteBorr(Integer cardNo) {
		borrDao.deleteById(cardNo);;
	}
	
	//Get one borrower
	public Optional<Borrower> getBorrById(Integer cardNo) {
		 return borrDao.findById(cardNo);
	}
	
	//Get all records
	public List<Borrower> getAllBorrs() {
		return borrDao.findAll();
	}
	
	//Validate Id
	public boolean ifExists(Integer cardNo) {
		List<Borrower> list = borrDao.findAll();
		
		boolean exists = list.stream()
				.anyMatch(id -> id.getCardNo().equals(cardNo));
	
		return exists;
	}
}

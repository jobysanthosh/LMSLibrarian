package com.lms.LMSAdmin.controller;

import java.util.List;
import java.util.Optional;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lms.LMSAdmin.pojo.Borrower;
import com.lms.LMSAdmin.service.BorrowerService;

@RestController
@RequestMapping("/admin/borrowers")
@Produces({"application/xml", "application/json"})
@Consumes({"application/xml", "application/json"})
public class BorrowerController {
	
	@ExceptionHandler({MethodArgumentTypeMismatchException.class, JsonProcessingException.class, NullPointerException.class, 
		ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handle(Exception e) {
        return "Invalid Data";
    }

	@Autowired
	BorrowerService borrService;
	
	//Create a record
	@PostMapping("")
	public ResponseEntity<?> insertBorr(@RequestBody Borrower borrower) {
		
		borrService.insertBorr(borrower);
		return new ResponseEntity<Borrower>(borrower, HttpStatus.CREATED);
	}
	
	//Update a record
	@PutMapping("/{cardNo}")
	public ResponseEntity<?> updateBorr(@PathVariable Integer cardNo, @RequestBody Borrower borrower) {
		
		boolean checkId = borrService.ifExists(cardNo);
		
		if(checkId == true) {
			borrower.setCardNo(cardNo);
			borrService.updateBorr(borrower);
			return new ResponseEntity<Borrower>(borrower, HttpStatus.OK);
		}
		
		return new ResponseEntity<String>("Invalid data.", HttpStatus.NOT_FOUND);
		
	}
	
	//Delete a record
	@DeleteMapping("/{cardNo}")
	public ResponseEntity<?> deleteBorr(@PathVariable Integer cardNo) {
				
		boolean checkId = borrService.ifExists(cardNo);
		
		if(checkId == true) {
			borrService.deleteBorr(cardNo);
			return new ResponseEntity<Borrower>(HttpStatus.NO_CONTENT);
		}
			
		return new ResponseEntity<String>("Invalid ID.", HttpStatus.NOT_FOUND);
	}
	
	//Get one record
	@GetMapping("/{cardNo}")
	@ResponseStatus(code = HttpStatus.OK)
	public Optional<Borrower> getBorrById(@PathVariable Integer cardNo) {
		return borrService.getBorrById(cardNo);
	}
	
	//Get all records
	@GetMapping("")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Borrower> getAllBorrs() {
		return borrService.getAllBorrs();
	}
}

package com.lms.LMSAdmin.controller;

import java.util.List;
import java.util.Optional;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.lms.LMSAdmin.service.OverrideService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.lms.LMSAdmin.pojo.BookLoans;

@RestController
@RequestMapping("/admin/bookloans")
@Produces({"application/xml", "application/json"})
@Consumes({"application/xml", "application/json"})
public class OverrideController {
	
	@ExceptionHandler({MethodArgumentTypeMismatchException.class, JsonProcessingException.class, NullPointerException.class, 
		ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handle(Exception e) {
        return "Invalid Data";
    }

	@Autowired
	OverrideService overService;
	
	//Override due date
	@PutMapping("/duedate")
	public ResponseEntity<?> overDueDate(@RequestBody BookLoans loans) {
		
		boolean checkIds = overService.ifExists(loans.getBlCompKey().getBorrower().getCardNo(), loans.getBlCompKey().getBranch().getBranchId(), 
				loans.getBlCompKey().getBook().getBookId());
		
		if(checkIds == true) {
			
			Optional<BookLoans> dateOut = overService.getLoanById(loans.getBlCompKey());
			loans.setDateOut(dateOut.get().getDateOut());
			
			loans.setDueDate(loans.getDueDate());
			
			overService.overDueDate(loans);
			
			overService.getEmbeddedDetails(loans);
			
			return new ResponseEntity<BookLoans>(loans, HttpStatus.OK);
		}
		
		return new ResponseEntity<String>("Invalid ID.", HttpStatus.NOT_FOUND);
	}
	
	//Get all book loans
	@GetMapping("")
	@ResponseStatus(code = HttpStatus.OK)
	public List<BookLoans> getBookLoans() {
		return overService.getBookLoans();
	}
}

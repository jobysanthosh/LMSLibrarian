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
import com.lms.LMSAdmin.pojo.Publisher;
import com.lms.LMSAdmin.service.PublisherService;

@RestController
@RequestMapping("/admin/publishers")
@Produces({"application/xml", "application/json"})
@Consumes({"application/xml", "application/json"})
public class PublisherController {
	
	@ExceptionHandler({MethodArgumentTypeMismatchException.class, JsonProcessingException.class, NullPointerException.class, 
		ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handle(Exception e) {
        return "Invalid Data";
    }
	
	@Autowired
	PublisherService pubService;
	
	//Create a record
	@PostMapping("")
	public ResponseEntity<?> insertPub(@RequestBody Publisher publisher) {
		
		pubService.insertPub(publisher);
		return new ResponseEntity<Publisher>(publisher, HttpStatus.CREATED);
	}
	
	//Update a record
	@PutMapping("/{publisherId}")
	public ResponseEntity<?> updatePub(@PathVariable Integer publisherId, @RequestBody Publisher publisher) {
		
		boolean checkId = pubService.ifExists(publisherId);
		
		if(checkId == true) {
			publisher.setPublisherId(publisherId);
			pubService.updatePub(publisher);
			return new ResponseEntity<Publisher>(publisher, HttpStatus.OK);
		}
			
		return new ResponseEntity<String>("Invalid ID.", HttpStatus.NOT_FOUND);
	}
	
	//Delete a record
	@DeleteMapping("/{publisherId}")
	public ResponseEntity<?> deletePub(@PathVariable Integer publisherId) {
		
		boolean checkId = pubService.ifExists(publisherId);
		
		if(checkId == true) {
			pubService.deletePub(publisherId);
			return new ResponseEntity<Publisher>(HttpStatus.NO_CONTENT);
		}
			
		return new ResponseEntity<String>("Invalid ID.", HttpStatus.NOT_FOUND);
	}
	
	//Get one record
	@GetMapping("/{publisherId}")
	@ResponseStatus(code = HttpStatus.OK)
	public Optional<Publisher> getPubById(@PathVariable Integer publisherId) {
		return pubService.getPubById(publisherId);
	}
	
	//Get all records
	@GetMapping("")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Publisher> getAllPubs() {
		return pubService.getAllPubs();
	}
}

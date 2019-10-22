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
import com.lms.LMSAdmin.pojo.LibraryBranch;
import com.lms.LMSAdmin.service.LibraryBranchService;

@RestController
@RequestMapping("/admin/branches")
@Produces({"application/xml", "application/json"})
@Consumes({"application/xml", "application/json"})
public class LibraryBranchController {
	
	@ExceptionHandler({MethodArgumentTypeMismatchException.class, JsonProcessingException.class, NullPointerException.class, 
		ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handle(Exception e) {
        return "Invalid Data";
    }
	
	@Autowired
	LibraryBranchService branService;
	
	//Create a record
	@PostMapping("")
	public ResponseEntity<LibraryBranch> insertBranch(@RequestBody LibraryBranch branch) {
		
		branService.insertBranch(branch);
		return new ResponseEntity<LibraryBranch>(branch, HttpStatus.CREATED);
	}
	
	//Update a record
	@PutMapping("/{branchId}")
	public ResponseEntity<?> updateBranch(@PathVariable Integer branchId, @RequestBody LibraryBranch branch) {
		
		boolean checkId = branService.ifExists(branchId);
		
		if(checkId == true) {
			branch.setBranchId(branchId);
			branService.updateBranch(branch);
			return new ResponseEntity<LibraryBranch>(branch, HttpStatus.OK);
		}
		
		return new ResponseEntity<String>("Invalid ID.", HttpStatus.NOT_FOUND);
	}
	
	//Delete branch
	@DeleteMapping("/{branchId}")
	public ResponseEntity<?> deleteBranch(@PathVariable Integer branchId) {
		
		boolean checkId = branService.ifExists(branchId);
		
		if(checkId == true) {
			branService.deleteBranch(branchId);
			return new ResponseEntity<LibraryBranch>(HttpStatus.NO_CONTENT);
		}
			
		return new ResponseEntity<String>("Invalid ID.", HttpStatus.NOT_FOUND);
	}
	
	//Get one record
	@GetMapping("/{branchId}")
	@ResponseStatus(code = HttpStatus.OK)
	public Optional<LibraryBranch> getPubById(@PathVariable Integer branchId) {
		return branService.getBranchById(branchId);
	}
	
	//Get all records
	@GetMapping("")
	@ResponseStatus(code = HttpStatus.OK)
	public List<LibraryBranch> getAllBranches() {
		return branService.getAllBranches();
	}
}

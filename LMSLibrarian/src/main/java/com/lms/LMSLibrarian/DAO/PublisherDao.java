package com.lms.LMSLibrarian.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lms.LMSLibrarian.POJO.Publisher;

@Repository
public interface PublisherDao extends JpaRepository<Publisher, Integer> {

}
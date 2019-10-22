package com.lms.LMSAdmin.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lms.LMSAdmin.pojo.Publisher;

@Repository
public interface PublisherDao extends JpaRepository<Publisher, Integer> {

}

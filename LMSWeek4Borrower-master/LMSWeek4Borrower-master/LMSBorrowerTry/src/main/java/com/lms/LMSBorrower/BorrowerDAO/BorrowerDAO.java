
package com.lms.LMSBorrower.BorrowerDAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.lms.LMSBorrower.POJO.Borrower;

@Repository

public interface BorrowerDAO extends JpaRepository<Borrower, Integer> {

}
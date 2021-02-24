package com.bankdemo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bankdemo.entity.TransactionDetails;

@Repository
public interface TransactionDetailsRepository extends JpaRepository<TransactionDetails, Long> {

	@Query(value = "select * from Transaction_Details where from_Acc_No = ? and status='S' and DATE_FORMAT(created_Date, '%b-%Y')= ?", nativeQuery = true)
	public List<TransactionDetails> getCreditStatement(Long fromAccNo, String date);
	
	@Query(value = "select * from Transaction_Details where to_Acc_No = ? and status='S' and DATE_FORMAT(created_Date, '%b-%Y')= ?", nativeQuery = true)
	public List<TransactionDetails> getDebitStatement(Long toAccNo, String date);
}

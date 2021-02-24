package com.bankdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bankdemo.entity.AccountDetails;

public interface AccountDetailsRepository extends JpaRepository<AccountDetails, Long> {

	@Query("from AccountDetails where accountNo= :accountNo and accountStatus='Y' ")
	AccountDetails getAccountDetails(Long accountNo);

}

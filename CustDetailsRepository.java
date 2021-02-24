package com.bankdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankdemo.entity.CustomerDetails;

@Repository
public interface CustDetailsRepository extends JpaRepository<CustomerDetails, Long> {

}

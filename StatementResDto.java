package com.bankdemo.dto;

import java.util.List;

public class StatementResDto {
	private String customerName;
	private Long balance;
	private Long accountNo;
	private List<TransDetailsDto> transDetails;
	private ResponseDto response;

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Long getBalance() {
		return balance;
	}

	public void setBalance(Long balance) {
		this.balance = balance;
	}

	public Long getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(Long accountNo) {
		this.accountNo = accountNo;
	}

	public List<TransDetailsDto> getTransDetails() {
		return transDetails;
	}

	public void setTransDetails(List<TransDetailsDto> transDetails) {
		this.transDetails = transDetails;
	}

	public ResponseDto getResponse() {
		return response;
	}

	public void setResponse(ResponseDto response) {
		this.response = response;
	}

}

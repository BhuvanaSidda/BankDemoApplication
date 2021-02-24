package com.bankdemo.dto;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;

public class StatementReqDto {

	@JsonFormat(pattern = "MMM-yyyy")
	private Date month;

	@NotNull
	@Min(value = 12032020, message = "Account number series should be in between '12032020 - 99999999'")
	@Max(value = 99999999, message = "Account number series should be in between '12032020 - 99999999'")
	private Long accountNo;

	public Date getMonth() {
		return month;
	}

	public void setMonth(Date month) {
		this.month = month;
	}

	public Long getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(Long accountNo) {
		this.accountNo = accountNo;
	}

}

package com.bankdemo.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.sun.istack.NotNull;

public class FundTransReqDto {

	@NotNull
	@Min(value = 12032020, message = "Account number series should be in between '12032020 - 99999999'")
	@Max(value = 99999999, message = "Account number series should be in between '12032020 - 99999999'")
	private Long fromAccount;

	@Min(value = 12032020, message = "Account number series should be in between '12032020 - 99999999'")
	@Max(value = 99999999, message = "Account number series should be in between '12032020 - 99999999'")
	private Long toAccount;

	@NotNull
	@Min(value= 100, message="amount should be greater than 100")
	@Max(value= 50000, message="amount should be less than 50000")
	private Long amount;

	private String remarks;

	public Long getFromAccount() {
		return fromAccount;
	}

	public void setFromAccount(Long fromAccount) {
		this.fromAccount = fromAccount;
	}

	public Long getToAccount() {
		return toAccount;
	}

	public void setToAccount(Long toAccount) {
		this.toAccount = toAccount;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}

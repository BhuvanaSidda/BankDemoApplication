package com.bankdemo.dto;

import java.time.LocalDateTime;

public class TransDetailsDto {
	private Long trans_id;
	private String trans_type;
	private Long amount;
	private String trans_date;

	public String getTrans_date() {
		return trans_date;
	}

	public void setTrans_date(String trans_date) {
		this.trans_date = trans_date;
	}

	public Long getTrans_id() {
		return trans_id;
	}

	public void setTrans_id(Long trans_id) {
		this.trans_id = trans_id;
	}

	public String getTrans_type() {
		return trans_type;
	}

	public void setTrans_type(String trans_type) {
		this.trans_type = trans_type;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

}

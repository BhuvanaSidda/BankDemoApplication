package com.bankdemo.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TransactionDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long transactionId;
	private Long fromAccNo;
	private Long toAccNo;
	private Long trans_amount;
	private String transType;
	private LocalDateTime createdDate;
	private String createdBy;
	private LocalDateTime modifiedDate;
	private String modifiedBy;
	private String status;
	private String remarks;

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public Long getTrans_amount() {
		return trans_amount;
	}

	public void setTrans_amount(Long trans_amount) {
		this.trans_amount = trans_amount;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public Long getFromAccNo() {
		return fromAccNo;
	}

	public void setFromAccNo(Long fromAccNo) {
		this.fromAccNo = fromAccNo;
	}

	public Long getToAccNo() {
		return toAccNo;
	}

	public void setToAccNo(Long toAccNo) {
		this.toAccNo = toAccNo;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

}

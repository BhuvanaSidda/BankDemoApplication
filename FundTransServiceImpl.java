package com.bankdemo.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankdemo.dto.FundTransReqDto;
import com.bankdemo.dto.ResponseDto;
import com.bankdemo.dto.StatementResDto;
import com.bankdemo.dto.TransDetailsDto;
import com.bankdemo.entity.AccountDetails;
import com.bankdemo.entity.CustomerDetails;
import com.bankdemo.entity.TransactionDetails;
import com.bankdemo.repository.AccountDetailsRepository;
import com.bankdemo.repository.CustDetailsRepository;
import com.bankdemo.repository.TransactionDetailsRepository;
import com.bankdemo.service.FundTransService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class FundTransServiceImpl implements FundTransService {

	private static final Logger logger = LoggerFactory.getLogger(FundTransServiceImpl.class);

	@Autowired
	TransactionDetailsRepository transactionDetailsRepository;

	@Autowired
	AccountDetailsRepository accountDetailsRepository;

	@Autowired
	CustDetailsRepository custDetailsRepository;

	@Override
	public ResponseDto doFundTrans(FundTransReqDto fundTransReq) throws JsonProcessingException {
		logger.info("FundTransfer Service Implimentation");
		logger.info("FundTransfer Request:::" + new ObjectMapper().writeValueAsString(fundTransReq));
		ResponseDto res = new ResponseDto();
		AccountDetails fromAccountDetails = accountDetailsRepository.getAccountDetails(fundTransReq.getFromAccount());
		AccountDetails toAccountDetails = accountDetailsRepository.getAccountDetails(fundTransReq.getToAccount());
		logger.info("AccountDetails::::" + new ObjectMapper().writeValueAsString(fromAccountDetails));
		if (fundTransReq.getFromAccount().equals(fundTransReq.getToAccount())) {
			res.setResCode("01");
			res.setResDesc("From account and To account should be different");
			return res;
		}
		if (fromAccountDetails != null && fromAccountDetails.getAmount() >= fundTransReq.getAmount()) {
			updateAccountDetails(fundTransReq, fromAccountDetails, toAccountDetails);
			saveTransactionDetails(fundTransReq);
			res.setResCode("00");
			res.setResDesc("Amount transferred successfully");
		} else if (fromAccountDetails == null) {
			res.setResCode("01");
			res.setResDesc("FromAccount number does not exist");
		} else if( toAccountDetails == null){
			res.setResCode("01");
			res.setResDesc("ToAccount number does not exist");
		}else {
			res.setResCode("01");
			res.setResDesc("Insuffiecient Amount");
		}

		return res;
	}

	@Override
	public StatementResDto getBankStatement(String monthYear, Long accountNo) throws JsonProcessingException {
		logger.info("Statement Service Implimentation");
		ResponseDto resDto = new ResponseDto();
		StatementResDto res = new StatementResDto();
		res.setAccountNo(accountNo);

		// Fetching Account details
		Optional<AccountDetails> accountDetails = accountDetailsRepository.findById(accountNo);
		if (accountDetails.isPresent() == false) {
			logger.info("accountDetails not found");
			resDto.setResCode("01");
			resDto.setResDesc("Account not found");
			res.setResponse(resDto);
			return res;
		}
		res.setBalance(accountDetails.get().getAmount());

		// Fetching Customer details
		CustomerDetails customerDetails = custDetailsRepository.findById(accountDetails.get().getCustomerId()).get();

		if (customerDetails != null)
			res.setCustomerName(customerDetails.getCustFirstName() + " " + customerDetails.getCustLastName());

		// Fetching debit and credit details
		List<TransactionDetails> debitDetailsList = transactionDetailsRepository.getCreditStatement(accountNo,
				monthYear);
		List<TransactionDetails> creditDetaisList = transactionDetailsRepository.getDebitStatement(accountNo,
				monthYear);

		if (debitDetailsList.isEmpty() && creditDetaisList.isEmpty()) {
			logger.info("No transactions found");
			resDto.setResCode("01");
			resDto.setResDesc("No transactions found");
			res.setResponse(resDto);
			return res;
		}

		List<TransDetailsDto> listObj = new ArrayList();
		for (TransactionDetails obj : debitDetailsList) {
			logger.info("Debit transactions adding");
			TransDetailsDto transRes = settingTransDetailsDto(obj);
			transRes.setTrans_type("Debit");
			listObj.add(transRes);
		}
		for (TransactionDetails obj : creditDetaisList) {
			logger.info("Credit transactions adding");
			TransDetailsDto transRes = settingTransDetailsDto(obj);
			transRes.setTrans_type("Credit");
			listObj.add(transRes);
		}

		resDto.setResCode("00");
		resDto.setResDesc("Success");
		res.setResponse(resDto);
		res.setTransDetails(listObj);
		return res;
	}

	private void updateAccountDetails(FundTransReqDto fundTransReq, AccountDetails fromAccountDetails,
			AccountDetails toAccountDetails) throws JsonProcessingException {
		logger.info("Account details updation");
		if (fromAccountDetails != null) {
			fromAccountDetails.setAmount(fromAccountDetails.getAmount() - fundTransReq.getAmount());
			fromAccountDetails.setModifiedDate(LocalDateTime.now());
			accountDetailsRepository.save(fromAccountDetails);
			logger.info("FromAccount details updated successfully");
		}
		if (toAccountDetails != null) {
			toAccountDetails.setAmount(toAccountDetails.getAmount() + fundTransReq.getAmount());
			toAccountDetails.setModifiedDate(LocalDateTime.now());
			accountDetailsRepository.save(fromAccountDetails);
			logger.info("ToAccount details updated successfully");
		}
	}

	private void saveTransactionDetails(FundTransReqDto fundTransReq) {
		logger.info("Transaction details saving");
		TransactionDetails transactionDetails = new TransactionDetails();
		transactionDetails.setFromAccNo(fundTransReq.getFromAccount());
		transactionDetails.setToAccNo(fundTransReq.getToAccount());
		transactionDetails.setTrans_amount(fundTransReq.getAmount());
		transactionDetails.setCreatedDate(LocalDateTime.now());
		transactionDetails.setTransType("FundTransfer");
		transactionDetails.setStatus("S");
		transactionDetails.setRemarks(fundTransReq.getRemarks());
		transactionDetailsRepository.save(transactionDetails);
		logger.info("Transaction details saved successfully");
	}

	private TransDetailsDto settingTransDetailsDto(TransactionDetails obj) {
		logger.info("Transaction details addition generic method");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String strDate = formatter.format(obj.getCreatedDate());
		TransDetailsDto transDetailsDto = new TransDetailsDto();
		transDetailsDto.setTrans_id(obj.getTransactionId());
		transDetailsDto.setAmount(obj.getTrans_amount());
		transDetailsDto.setTrans_date(strDate);

		return transDetailsDto;
	}
}

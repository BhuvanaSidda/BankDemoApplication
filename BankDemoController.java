package com.bankdemo.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bankdemo.dto.FundTransReqDto;
import com.bankdemo.dto.ResponseDto;
import com.bankdemo.dto.StatementResDto;
import com.bankdemo.service.FundTransService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping("/bankdemo")
public class BankDemoController {

	private static final Logger logger = LoggerFactory.getLogger(BankDemoController.class);

	@Autowired
	FundTransService fundTransService;

	@PostMapping("/transactions")
	public ResponseDto fundTransfer(@Valid @RequestBody FundTransReqDto fundTransReq) throws JsonProcessingException {
		logger.info("FundTransfer Controller");
		return fundTransService.doFundTrans(fundTransReq);
	}

	@GetMapping("/statement")
	public StatementResDto statement(@Valid @RequestParam String monthYear, @RequestParam Long accountNo)
			throws JsonProcessingException {
		logger.info("Statement generation Controller");
		return fundTransService.getBankStatement(monthYear, accountNo);
	}

}

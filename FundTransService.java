package com.bankdemo.service;

import java.util.Date;

import org.springframework.web.bind.annotation.RequestParam;

import com.bankdemo.dto.FundTransReqDto;
import com.bankdemo.dto.ResponseDto;
import com.bankdemo.dto.StatementReqDto;
import com.bankdemo.dto.StatementResDto;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface FundTransService {

	ResponseDto doFundTrans(FundTransReqDto fundTransReq) throws JsonProcessingException;

	StatementResDto getBankStatement(String monthYear, Long accountNo) throws JsonProcessingException;

}

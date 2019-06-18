package com.wschoi.wsblog.service;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wschoi.wsblog.dao.BbsDAO;
import com.wschoi.wsblog.dto.BbsDTO;

@Repository
public class BbsService
{
	private static final Logger logPrinter = LoggerFactory.getLogger(BbsService.class);

	@Autowired
	BbsDAO bbsDAO;
	
	public ArrayList<BbsDTO> getBbsDTOList(int pageNumber)
	{

		logPrinter.info("Searching articles...");
		BbsDAO bbsDAO = new BbsDAO();
		ArrayList<BbsDTO> list = bbsDAO.getList(pageNumber);
		return list;
	}
}

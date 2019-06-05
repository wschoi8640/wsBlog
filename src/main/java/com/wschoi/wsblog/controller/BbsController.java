package com.wschoi.wsblog.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wschoi.wsblog.service.BbsService;

@Controller
public class BbsController
{
	
	private static final Logger logPrinter = LoggerFactory.getLogger(BbsController.class);
	
	@Autowired
	BbsService bbsService;
	
	@RequestMapping(value = "/getBbsContent", method = RequestMethod.POST)
	public void getBbsContent(Model model, HttpServletResponse response) throws IOException
	{
		logPrinter.info("Fetching bbs Content...");
		
		int pageNumber = bbsService.setPageNumber(model);
		bbsService.setNextPage(model, pageNumber);
		String list = bbsService.setBbsDTOList(model, pageNumber);
		
		response.getWriter().write(list);
	}
}

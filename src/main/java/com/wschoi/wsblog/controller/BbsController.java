package com.wschoi.wsblog.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wschoi.wsblog.dto.BbsDTO;
import com.wschoi.wsblog.service.BbsService;

@Controller
@RequestMapping("/bbs")
public class BbsController
{
	
	private static final Logger logPrinter = LoggerFactory.getLogger(BbsController.class);
	
	@Autowired
	BbsService bbsService;
	
	@PostMapping("/getBbsContent/{pageNumber}")
	@ResponseBody
	public ArrayList<BbsDTO> getBbsContent(HttpSession session,
			@PathVariable int pageNumber) throws IOException
	{
		logPrinter.info("Fetching articles...");
		session.setAttribute("pageNumber", pageNumber);
		ArrayList<BbsDTO> list = bbsService.getBbsDTOList(pageNumber);
		
		return list;
	}
}

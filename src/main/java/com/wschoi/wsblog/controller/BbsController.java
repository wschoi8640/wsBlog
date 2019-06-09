package com.wschoi.wsblog.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
		String list = bbsService.getBbsDTOList(model, pageNumber);
		
		response.getWriter().write(list);
	}
	
	@RequestMapping(value ="/getArticleContent", method = RequestMethod.POST)
	public void getArticleContent(HttpServletResponse response,
				      HttpServletRequest request) throws IOException 
	{
		logPrinter.info("Fetching article Content...");
		HttpSession session = request.getSession();
		int bbsID = Integer.parseInt((String)session.getAttribute("bbsID"));
		String list = bbsService.getArticleList(bbsID, session);
		
		response.getWriter().write(list);
	}
	
	@RequestMapping(value = "/doWrite", method = RequestMethod.POST)
	public void doWrite(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("myTitle") String myTitle,
			@RequestParam("myContent") String myContent) throws IOException
	{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; chatset=UTF-8");
		
		HttpSession session = request.getSession();
		String userID = (String)session.getAttribute("userID");
		
		int result = bbsService.write(myTitle, userID, myContent);
		
		if(result == -1)
		{
			logPrinter.info("write Failed - DB Error");
			response.getWriter().write("-1");
		}
		else
		{
			logPrinter.info("write Successful");
			response.getWriter().write("1");
		}
	}
	
	@RequestMapping(value = "/doUpdate", method = RequestMethod.POST)
	public void doUpdate(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("myTitle") String myTitle,
			@RequestParam("myContent") String myContent) throws IOException
	{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; chatset=UTF-8");
		
		HttpSession session = request.getSession();
		int bbsID = Integer.parseInt((String)session.getAttribute("bbsID"));
		int result = bbsService.update(myTitle, bbsID, myContent);
		
		if(result == -1)
		{
			logPrinter.info("write Failed - DB Error");
			response.getWriter().write("-1");
		}
		else
		{
			logPrinter.info("update Successful");
			response.getWriter().write("1");
		}
	}
}

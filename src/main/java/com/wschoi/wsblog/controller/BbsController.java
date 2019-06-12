package com.wschoi.wsblog.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wschoi.wsblog.service.BbsService;

@Controller
public class BbsController
{
	
	private static final Logger logPrinter = LoggerFactory.getLogger(BbsController.class);
	
	@Autowired
	BbsService bbsService;
	
	@PostMapping("/getBbsContent")
	public void getBbsContent(Model model, HttpServletResponse response) throws IOException
	{
		logPrinter.info("Fetching bbs Content...");
		
		int pageNumber = bbsService.setPageNumber(model);
		bbsService.setNextPage(model, pageNumber);
		String list = bbsService.getBbsDTOList(model, pageNumber);
		
		response.getWriter().write(list);
	}
	
	@PostMapping("/getArticleContent")
	public void getArticleContent(HttpServletResponse response,
				      HttpSession session) throws IOException 
	{
		logPrinter.info("Fetching article Content...");
		int bbsID = Integer.parseInt((String)session.getAttribute("bbsID"));
		String list = bbsService.getArticleList(bbsID, session);
		
		response.getWriter().write(list);
	}
	
	@PostMapping("/doWrite")
	public void doWrite(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			@RequestParam("myTitle") String myTitle,
			@RequestParam("myContent") String myContent) throws IOException
	{
		logPrinter.info("Writing article Content...");

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; chatset=UTF-8");
		
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
	
	@PostMapping("/doUpdate")
	public void doUpdate(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			@RequestParam("myTitle") String myTitle,
			@RequestParam("myContent") String myContent) throws IOException
	{
		logPrinter.info("Updating article Content...");

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; chatset=UTF-8");
		
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
	
	@PostMapping("/doDelete")
	public void doDelete(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		int bbsID = 0;
		logPrinter.info("Deleting article Content...");
		
		if(request.getParameter("bbsID") != null){
			bbsID = Integer.parseInt(request.getParameter("bbsID"));
		}
		if(bbsID == 0){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('not valid!')");
			script.println("location.href = 'bbs'");
			script.println("</script>");
		}
		String userID = null;
		if(session.getAttribute("userID") != null)
		{
			userID = (String)session.getAttribute("userID");
		}
		if(userID == null){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('login first')");
			script.println("location.href = 'login'");
			script.println("</script>");
		}
		int result = bbsService.delete(bbsID, userID, response);
		
		if(result == -1)
		{
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('delete failed')");
			script.println("history.back()");
			script.println("</script>");
		}
		else
		{
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("location.href = 'bbs'");
			script.println("</script>");
		}
	}
}

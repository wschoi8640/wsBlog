package com.wschoi.wsblog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wschoi.wsblog.dao.BbsDAO;
import com.wschoi.wsblog.dto.BbsDTO;


/**
 * Handles requests for the application home page.
 */
@Controller
public class RedirectController 
{
	private static final Logger logPrinter = LoggerFactory.getLogger(RedirectController.class);
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String redirectToIndex() 
	{
		logPrinter.info("Redirecting to index.jsp");
		return "index";
	}
	
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String redirectToMain() 
	{
		logPrinter.info("Redirecting to main.jsp");
		return "main";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String redirectToLogin() 
	{
		logPrinter.info("Redirecting to login.jsp");
		return "login";
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String redirectToJoin()
	{
		
		logPrinter.info("Redirecting to join.jsp");
		return "join";
	}
	
	@RequestMapping(value = "/menu", method = RequestMethod.GET)
	public String redirectToFindMenu()
	{
		logPrinter.info("Redirecting to findMenu.jsp");
		return "findMenu";
	}
	
	@RequestMapping(value = "/guestBook", method = RequestMethod.GET)
	public String redirectToGuestBook()
	{
		logPrinter.info("Redirecting to guestBook.jsp");
		return "guestBook";
	}
	
	@RequestMapping(value = "/bbs", method = RequestMethod.GET)
	public String redirectToBbs()
	{
		logPrinter.info("Redirecting to bbs.jsp");
		return "bbs";
	}
	
	@RequestMapping(value = "/viewContent", method = RequestMethod.GET)
	public String redirectToViewContent(HttpServletRequest request, HttpServletResponse response)
	{
		logPrinter.info("Redirectiong to viewContent.jsp");
		HttpSession session = request.getSession();
		session.setAttribute("bbsID", request.getParameter("bbsID"));
		int bbsID = Integer.parseInt((String)session.getAttribute("bbsID"));
		BbsDTO bbs = new BbsDAO().getBbs(bbsID);
		session.setAttribute("bbsUserID", bbs.getUserID());
		
		return "viewContent";
	}
	
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String redirectToWrite()
	{
		logPrinter.info("Redirecting to write.jsp");
		return "write";
	}
}

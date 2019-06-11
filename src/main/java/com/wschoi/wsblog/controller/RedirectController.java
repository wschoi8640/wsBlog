package com.wschoi.wsblog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@GetMapping("/")
	public String redirectToIndex() 
	{
		logPrinter.info("Redirecting to index.jsp");
		return "index";
	}
	
	@GetMapping("/main")
	public String redirectToMain() 
	{
		logPrinter.info("Redirecting to main.jsp");
		return "main";
	}
	
	@GetMapping("/login")
	public String redirectToLogin() 
	{
		logPrinter.info("Redirecting to login.jsp");
		return "login";
	}
	
	@GetMapping("/join")
	public String redirectToJoin()
	{
		
		logPrinter.info("Redirecting to join.jsp");
		return "join";
	}
	
	@GetMapping("/menu")
	public String redirectToFindMenu()
	{
		logPrinter.info("Redirecting to findMenu.jsp");
		return "findMenu";
	}
	
	@GetMapping("/guestBook")
	public String redirectToGuestBook()
	{
		logPrinter.info("Redirecting to guestBook.jsp");
		return "guestBook";
	}
	
	@GetMapping("/bbs")
	public String redirectToBbs()
	{
		logPrinter.info("Redirecting to bbs.jsp");
		return "bbs";
	}
	
	@GetMapping("/viewContent")
	public String redirectToViewContent(HttpServletRequest request)
	{
		logPrinter.info("Redirectiong to viewContent.jsp");
		HttpSession session = request.getSession();
		session.setAttribute("bbsID", request.getParameter("bbsID"));
		int bbsID = Integer.parseInt((String)session.getAttribute("bbsID"));
		BbsDTO bbs = new BbsDAO().getBbs(bbsID);
		session.setAttribute("bbsUserID", bbs.getUserID());
		
		return "viewContent";
	}
	
	@GetMapping("/write")
	public String redirectToWrite()
	{
		logPrinter.info("Redirecting to write.jsp");
		return "write";
	}

	@GetMapping("/update")
	public String redirectToUpdate(HttpServletRequest request)
	{
		logPrinter.info("Redirectiong to update.jsp");
		HttpSession session = request.getSession();
		session.setAttribute("bbsID", request.getParameter("bbsID"));
		int bbsID = Integer.parseInt((String)session.getAttribute("bbsID"));
		BbsDTO bbs = new BbsDAO().getBbs(bbsID);
		
		session.setAttribute("bbsUserID", bbs.getUserID());
		session.setAttribute("bbsTitle", bbs.getBbsTitle());
		session.setAttribute("bbsContent", bbs.getBbsContent());
		
		return "update";
	}
}

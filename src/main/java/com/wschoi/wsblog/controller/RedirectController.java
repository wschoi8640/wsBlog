package com.wschoi.wsblog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

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
	
	@Autowired
	BbsDAO bbsDAO;
	
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

	@GetMapping("/bbs/{pageNumber}")
	public ModelAndView redirectToBbs(HttpSession session,
			@PathVariable int pageNumber)
	{
		logPrinter.info("Redirecting to bbs.jsp");

		if(pageNumber == 0) pageNumber = 1;
		boolean nextPage = bbsDAO.nextPage(pageNumber+1);
		
		session.setAttribute("pageNumber", pageNumber);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/bbs");
		mav.addObject("pageNumber", pageNumber);
		mav.addObject("nextPage", nextPage);

		return mav;
	}
	
	@GetMapping("/article/{bbsID}")
	public ModelAndView redirectToViewContent(@PathVariable int bbsID)
	{
		logPrinter.info("Redirectiong to viewContent.jsp");
			
		ModelAndView mav = new ModelAndView();
		if(bbsID == 0) 
		{
			mav.setViewName("/bbs");
		}
		else
		{
			BbsDTO bbs = new BbsDAO().getBbs(bbsID);
			mav.setViewName("/article");
			mav.addObject("bbsUserID", bbs.getUserID());
			mav.addObject("bbsID", bbsID);
		}
		return mav;
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
	
	@GetMapping("/write")
	public String redirectToWrite()
	{
		logPrinter.info("Redirecting to write.jsp");
		return "write";
	}

	@GetMapping("/update/{bbsID}")
	public ModelAndView redirectToUpdate(@PathVariable int bbsID)
	{
		logPrinter.info("Redirectiong to update.jsp");
		BbsDTO bbs = new BbsDAO().getBbs(bbsID);
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("/update");
		mav.addObject("bbsID", bbsID);
		mav.addObject("bbsUserID", bbs.getUserID());
		mav.addObject("bbsTitle", bbs.getBbsTitle());
		mav.addObject("bbsContent", bbs.getBbsContent());
		
		return mav;
	}
}

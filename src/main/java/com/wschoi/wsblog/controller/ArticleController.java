package com.wschoi.wsblog.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wschoi.wsblog.dto.BbsDTO;
import com.wschoi.wsblog.service.ArticleService;
import com.wschoi.wsblog.service.UtilService;

@Controller
@RequestMapping("/article")
public class ArticleController
{
	private static final Logger logPrinter = LoggerFactory.getLogger(ArticleController.class);
	
	@Autowired
	ArticleService articleService;

	@Autowired
	UtilService utilService;
	
	@PostMapping("/getArticleContent/{bbsID}")
	@ResponseBody
	public BbsDTO getArticleContent(HttpServletResponse response,
			@PathVariable int bbsID) throws IOException 
	{
		logPrinter.info("Fetching article Content...");
		BbsDTO article = articleService.getArticleList(bbsID);

		return article;
	}
	
	@PostMapping("/doWrite")
	public void doWrite(HttpServletResponse response,
			@RequestParam("myTitle") String myTitle,
			@RequestParam("userID") String userID,
			@RequestParam("myContent") String myContent) throws IOException
	{
		logPrinter.info("Writing article Content...");
		int result = articleService.write(myTitle, userID, myContent);
		
		if(result == -1)
		{
			logPrinter.warn("write Failed - DB Error");
			response.getWriter().write("-1");
		}
		else
		{
			logPrinter.info("write Successful");
			response.getWriter().write("1");
		}
	}
	
	@PostMapping("/doUpdate/{bbsID}")
	public void doUpdate(HttpServletResponse response,
			@PathVariable int bbsID,
			@RequestParam("myTitle") String myTitle,
			@RequestParam("myContent") String myContent) throws IOException
	{
		logPrinter.info("Updating article Content...");

		int result = articleService.update(myTitle, bbsID, myContent);
		
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
	
	@GetMapping("/doDelete/{bbsID}")
	public String doDelete(HttpSession session, HttpServletResponse response,
			@PathVariable int bbsID) throws IOException
	{
		logPrinter.info("Deleting article Content...");
		
		
		utilService.setResponse(response);
		int pageNumber = ((Integer)session.getAttribute("pageNumber")).intValue();
		
		if(bbsID == 0){
			utilService.alert("not Valid!");
			return "redirect:/bbs/" + pageNumber;
		}
		
		String userID = null;
		if(session.getAttribute("userID") != null)
		{
			userID = (String)session.getAttribute("userID");
		}
		
		if(userID == null){
			utilService.alert("login first!");
			return "redirect:/login";
		}
		int result = articleService.delete(bbsID, userID, response);
		
		if(result == -1)
		{
			utilService.alert("delete Failed!");
			return "redirect:/bbs/" + pageNumber;
		}
		return "redirect:/bbs/" + pageNumber;
	}

}

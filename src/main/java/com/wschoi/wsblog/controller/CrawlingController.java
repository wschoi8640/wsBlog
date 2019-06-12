package com.wschoi.wsblog.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wschoi.wsblog.service.CrawlingService;

@Controller
public class CrawlingController
{
	private static final Logger logPrinter = LoggerFactory.getLogger(CrawlingController.class);

	@Autowired
	CrawlingService crawlingService;
	
	@PostMapping("/myMenu")
	public String findMenu(Model model,
			     @RequestParam("cafe") String cafe,
			     @RequestParam("date") String date) throws UnsupportedEncodingException, ServletException
	{
		String menu = crawlingService.getMenu(cafe, date);
		model.addAttribute("menu", menu);
		
		logPrinter.info("Menu search Successful");

		return "getMenu";
	}
}

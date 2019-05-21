package com.wschoi.wsblog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


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
		public String redirectToHome() 
		{
				logPrinter.info("Redirecting to index.jsp");
			
				return "index";
		}
}

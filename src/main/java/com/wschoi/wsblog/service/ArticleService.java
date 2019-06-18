package com.wschoi.wsblog.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wschoi.wsblog.dao.BbsDAO;
import com.wschoi.wsblog.dto.BbsDTO;

@Repository
public class ArticleService
{
	private static final Logger logPrinter = LoggerFactory.getLogger(ArticleService.class);

	@Autowired
	BbsDAO bbsDAO;
	
	public BbsDTO getArticleList(int bbsID)
	{
		BbsDTO article = new BbsDAO().getBbs(bbsID);
		return article;
	}

	public int write(String encodedMyTitle, String encodedUserID, String encodedMyContent)
			throws UnsupportedEncodingException
	{
		logPrinter.info("Writing new article...");

		String myTitle = URLDecoder.decode(encodedMyTitle, "UTF-8");
		String userID = URLDecoder.decode(encodedUserID, "UTF-8");
		String myContent = URLDecoder.decode(encodedMyContent, "UTF-8");

		BbsDAO bbsDAO = new BbsDAO();
		int result = bbsDAO.write(myTitle, userID, myContent);

		return result;
	}

	public int update(String encodedMyTitle, int bbsID, String encodedMyContent) throws UnsupportedEncodingException
	{
		logPrinter.info("checking update Data");
		String myTitle = URLDecoder.decode(encodedMyTitle, "UTF-8");
		String myContent = URLDecoder.decode(encodedMyContent, "UTF-8");
		
		BbsDAO bbsDAO = new BbsDAO();
		int result = bbsDAO.update(bbsID, myTitle, myContent);

		return result;
	}

	public int delete(int bbsID, String userID, HttpServletResponse response)
	{
		int result = -1;
		BbsDTO bbs = new BbsDAO().getBbs(bbsID);
		if (!userID.equals(bbs.getUserID()))
		{
			try
			{
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('not allowed!')");
				script.println("location.href = 'bbs'");
				script.println("</script>");
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		} else
		{
			BbsDAO bbsDAO = new BbsDAO();
			result = bbsDAO.delete(bbsID);
		}
		return result;
	}

}

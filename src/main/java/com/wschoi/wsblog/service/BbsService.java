package com.wschoi.wsblog.service;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

import com.wschoi.wsblog.dao.BbsDAO;
import com.wschoi.wsblog.dto.BbsDTO;

@Repository
public class BbsService
{
	@Autowired
	BbsDAO bbsDAO;

	public int setPageNumber(Model model)
	{
		int pageNumber = 1;
		Map modelMap = model.asMap();
		if(modelMap.get("pageNumber") != null)
		{
			pageNumber = Integer.parseInt((String) modelMap.get("pageNumber"));
		}
		model.addAttribute("pageNumber", pageNumber);
		
		return pageNumber;
	}

	public void setNextPage(Model model, int pageNumber)
	{
		boolean nextPage = false;
		nextPage = bbsDAO.nextPage(pageNumber);
		model.addAttribute("nextPage", nextPage);
	}

	public String setBbsDTOList(Model model, int pageNumber)
	{
		StringBuffer result = new StringBuffer("");
		result.append("{\"result\":[");
		
		ArrayList<BbsDTO> list = bbsDAO.getList(pageNumber);
		for(int i = 0; i < list.size(); i++) {
			result.append("[{\"value\": \"" + list.get(i).getBbsID() + "\"},");
			result.append("{\"value\": \"" + list.get(i).getArrangedData() + "\"},");
			result.append("{\"value\": \"" + list.get(i).getUserID() + "\"},");
			result.append("{\"value\": \"" + list.get(i).getBbsTime() + "\"}]");
			if(i != list.size() - 1) result.append(",");
		}
		result.append("], \"last\":\"" + pageNumber + "\"}");
		String rs = result.toString();

		return rs;
	}
}

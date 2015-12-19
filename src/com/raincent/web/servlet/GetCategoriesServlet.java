package com.raincent.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.raincent.web.dao.CategoryDAO;
import com.raincent.web.model.Category;
import com.raincent.web.util.TextUtility;

/**
 * 获得新闻种类
 * @author DELL
 * http://localhost:8080/web/getCategories?startcid=0&count=10;
 */
public class GetCategoriesServlet extends HttpServlet
{
	
	private static final long serialVersionUID = -6534417657358062448L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		response.setContentType("text/html;charset=UTF-8");
		String startcidStr = request.getParameter("startcid");
		String countStr = request.getParameter("count");
		int startcid = 0;
		int count = 0;
		startcid = TextUtility.String2Int(startcidStr);
		count = TextUtility.String2Int(countStr);
		JSONObject jObject = new JSONObject();
		try
		{
			CategoryDAO typeDAO = new CategoryDAO();
			ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
			ArrayList<Category> retList = typeDAO.getTypes(startcid, count);
			HashMap<String, Object> map;
			for (Category category : retList)
			{
				map = new HashMap<String, Object>();
				map.put("cid", category.getCid());
				map.put("title", category.getNewstype());
				list.add(map);
			}		
			jObject.put("info", list);
			
		} catch (Exception e)
		{
			e.printStackTrace();
			try
			{
				jObject.put("emsg", e.getMessage());
			} catch (JSONException ex)
			{
				ex.printStackTrace();
			}
		}

		PrintWriter out = response.getWriter();
		out.println(jObject);
		out.flush();
		out.close();
	}
}

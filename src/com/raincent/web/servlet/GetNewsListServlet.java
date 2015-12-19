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

import com.raincent.web.dao.NewsDAO;
import com.raincent.web.model.News;
import com.raincent.web.util.TextUtility;

/**
 * 获得汛潮页面新闻列表
 * @author DELL
 * http://localhost:8080/web/getNewsList?startnewsid=0&count=10;
 */
public class GetNewsListServlet extends HttpServlet
{

	private static final long serialVersionUID = -8746952758069242838L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{

		response.setContentType("text/html;charset=UTF-8");
		String startnewsidStr = request.getParameter("startnewsid");
		String countStr = request.getParameter("count");
		int startnewsid = 0;
		int count = 0;
		startnewsid = TextUtility.String2Int(startnewsidStr);
		count = TextUtility.String2Int(countStr);
		JSONObject jObject = new JSONObject();
		try
		{
			NewsDAO newsDAO = new NewsDAO();
			ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
			ArrayList<News> retList = new ArrayList<News>();
			retList = newsDAO.getNewsList(startnewsid, count);
			for (News news : retList)
			{
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("newsId", news.getNewsid());
				map.put("newstype", news.getNewstype());
				map.put("newstitle", news.getNewstitle());			
				map.put("summary", news.getSummary());
				map.put("thumbnail_url", news.getThumbnail_url());
				map.put("newstime", TextUtility.formatDate(news.getNewstime()));
				list.add(map);
			}
			jObject.put("newslist", list);
		} catch (Exception e)
		{
			e.printStackTrace();
			try
			{
				jObject.put("error", e.getMessage());
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

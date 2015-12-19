package com.raincent.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
 * 请求新闻详情
 * @author DELL
 * http://localhost:8080/web/getNews?newsid=1;
 */
public class GetNewsServlet extends HttpServlet
{

	private static final long serialVersionUID = -3020482532264242318L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{

		response.setContentType("text/html;charset=UTF-8");
		String newsidStr= request.getParameter("newsid");
		int newsid = 0;
		newsid = TextUtility.String2Int(newsidStr);
		JSONObject jObject = new JSONObject();
		try
		{
			NewsDAO newsDAO = new NewsDAO();
			News news = newsDAO.getNews(newsid);
			HashMap<String, Object> map = new HashMap<String, Object>();
			
			map.put("newsId", news.getNewsid());
			map.put("newstitle", news.getNewstitle());
			map.put("author", news.getAuthor());
			map.put("summary", news.getSummary());
			map.put("content", news.getContent());
			map.put("thumbnail_url", news.getThumbnail_url());
			map.put("news_url", news.getNews_url());
			map.put("newstime", TextUtility.formatDate(news.getNewstime()));
			
			jObject.put("newsdetail", map);
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

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
 * 进行微博分享
 * @author DELL
 * http://localhost:8080/web/getShareWeibo?newsid=1;
 */
public class GetShareWeiBoServlet extends HttpServlet{

		private static final long serialVersionUID = -5192557065625973766L;

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
				map.put("summary", news.getSummary());
				map.put("news_url", news.getNews_url());
				map.put("thumbnail_url", news.getThumbnail_url());
				
				jObject.put("shareweibo", map);
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

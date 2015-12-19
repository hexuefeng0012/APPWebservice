package com.raincent.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.raincent.web.dao.FavDAO;
import com.raincent.web.util.TextUtility;

/**
 * 将新闻添加至用户收藏
 * @author DELL
 * http://localhost:8080/web/addfav;
 */
public class PostAddFavServlet extends HttpServlet{
	
	private static final long serialVersionUID = -7481047359101084173L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		response.setContentType("text/html;charset=UTF-8");
		
		int newsid = TextUtility.String2Int(request.getParameter("newsid"));
		int uid = TextUtility.String2Int(request.getParameter("uid"));
		
        
		JSONObject jObject = new JSONObject();
		try
		{
			FavDAO favDAO=new FavDAO();
			if(1 == favDAO.addfav(uid, newsid)){
				jObject.put("msg", "ok");
            }
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		PrintWriter out = response.getWriter();
		out.println(jObject);
		out.flush();
		out.close();
	}
}

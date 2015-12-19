package com.raincent.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.raincent.web.dao.QuestionDAO;
import com.raincent.web.util.TextUtility;

/**
 * 发送评论
 * @author Tong
 * http://localhost:8080/web/postQuestion;
 */
public class PostQuestionServlet extends HttpServlet
{
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		int uid = TextUtility.String2Int(request.getParameter("uid"));
		String title = TextUtility.toUTF8(request.getParameter("title"));
		String img = TextUtility.toUTF8(request.getParameter("img"));
	    
        QuestionDAO questionDAO;    
		JSONObject jObject = new JSONObject();
		try
		{
			questionDAO = new QuestionDAO();
			if(1 == questionDAO.addQuestion(uid, title, img)){
				jObject.put("msg", "ok");
            } else {
				jObject.put("msg", "error");
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

package com.raincent.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.raincent.web.dao.QuestionDAO;
import com.raincent.web.model.Questions;
import com.raincent.web.util.TextUtility;

/**
 * Get all the Question post by the user
 * @author Tong
 * http://ip:port/webroot/getQueList?uid=0&count=4;
 */
public class GetQueListServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		int uid = TextUtility.String2Int(req.getParameter("uid"));
		int count = TextUtility.String2Int(req.getParameter("count"));
		int start = TextUtility.String2Int(req.getParameter("start"));
        JSONObject jObject=new JSONObject();
        try {
			QuestionDAO questionDAO=new QuestionDAO();
			jObject.put("questionslist", questionDAO.getQuestionList(uid,start,count));
		} catch (Exception e) {
			e.printStackTrace();
		}

		PrintWriter out=resp.getWriter();
		out.println(jObject);
		out.flush();
		out.close();
	}
}

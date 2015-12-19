package com.raincent.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.raincent.web.dao.QuestionDAO;
import com.raincent.web.model.Questions;
import com.raincent.web.util.TextUtility;

/**
 * 用于banner展示
 * @author Tong
 * http://localhost:8080/web/getQueList?uid=0&count=4;
 */
public class GetAllQuestionServlet extends HttpServlet{


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		int count = TextUtility.String2Int(req.getParameter("count"));
		int type = TextUtility.String2Int(req.getParameter("type"));
		int start = TextUtility.String2Int(req.getParameter("start"));
		JSONObject jObject=new JSONObject();
		
        try {
			QuestionDAO questionDAO=new QuestionDAO();
			jObject.put("questionslist", questionDAO.getAllQuestions(type,start,count));
		} catch (Exception e) {
			e.printStackTrace();
		}
		PrintWriter out=resp.getWriter();
		out.println(jObject);
		out.flush();
		out.close();
	}
	
}

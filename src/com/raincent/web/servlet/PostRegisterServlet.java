package com.raincent.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.raincent.web.dao.UserDAO;
import com.raincent.web.model.User;

/**
 * Validate The User
 * @author DELL
 * http://localhost:8080/web/register;
 */
public class PostRegisterServlet extends HttpServlet
{

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		
        String uname = URLDecoder.decode(request.getParameter("uname"),"utf-8");
        String password = URLDecoder.decode(request.getParameter("password"),"utf-8");
        String email = URLDecoder.decode(request.getParameter("email"),"utf-8");
        JSONObject jObject = new JSONObject();

        try
        {
            UserDAO userDAO = new UserDAO();
            if(userDAO.checkExist(uname)){
            	jObject.put("msg","用户名已被注册，换一个试试吧!");
            }else{
            	 User user = userDAO.register(uname,password,email);
                 HashMap<String, Object> hashMap = new HashMap<String, Object>();
                 hashMap.put("uid", user.getUid());
                 hashMap.put("usename", user.getUsename());
                 hashMap.put("avator", user.getAvator());
                 hashMap.put("nick", user.getNick());
                 hashMap.put("email", user.getEmail());
                 
                 jObject.put("user", hashMap);
            }	
        } catch (Exception e){
            e.printStackTrace();
        }
        PrintWriter out = response.getWriter();
        out.println(jObject);
        out.flush();
        out.close();
    }

}

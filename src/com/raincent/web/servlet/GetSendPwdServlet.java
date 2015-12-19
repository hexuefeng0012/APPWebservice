package com.raincent.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.raincent.web.dao.UserDAO;
import com.raincent.web.model.User;
import com.raincent.web.util.TextUtility;
import com.raincent.web.util.Mail;

/**
 * 请求用户信息详情
 * 
 * @author DELL http://localhost:8080/web/getUser?uid=1;
 */
public class GetSendPwdServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String smtp = "smtp.126.com";
		String from = "raincent@126.com";
		String subject = "Your Raincent Password";
		String username = "raincent";
		String password = "ygline007";

		response.setContentType("text/html;charset=UTF-8");
		String uname = request.getParameter("uname");

		JSONObject jObject = new JSONObject();
		try {
			UserDAO userDAO = new UserDAO();
			String userName = userDAO.validateAccount(uname);
			if ("".equals(userName)) {
				jObject.put("msg", "not exist");
			} else {
				String email = userDAO.validateMail(uname);
				if ("".equals(email)) {
					jObject.put("msg", "Invalid Email");
				} else {
					String pwd = userDAO.getPwd(uname);
					if ("".equals(pwd)) {
						jObject.put("msg", "Invalid Email");
					} else {
						if (Mail.send(smtp, from, email, subject, "Password: "
								+ pwd, username, password)) {
							jObject.put("msg", "Password Sent!");
						} else {
							jObject.put("msg", "Fail to send password!");
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		PrintWriter out = response.getWriter();
		out.println(jObject);
		out.flush();
		out.close();
	}
}

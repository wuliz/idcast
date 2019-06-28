package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.itcast.servlet.BaseServlet;

public class LoginServlet extends BaseServlet {

/*
 * 
 */
	public String login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 获取表单验证码
		 * 获取图片的验证码
		 */
		String code = request.getParameter("code");
		HttpSession session = request.getSession();
		Object vCode = session.getAttribute("vCode");
		if(vCode.equals(code)){
			System.out.println(true);
		}
		return null;

	}

}

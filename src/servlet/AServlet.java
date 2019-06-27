package servlet;

import cn.itcast.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * 一般servlet和继承了BaseServlet的区别
 * BaseServlet:
 * 1\可以有多个请求处理方法
 * 2、简化了转发和重定向的代码
 *
 * 请求处理方法的格式
 * public String regist(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
 *
 * }
 *
 * 请求BaseServlet的路径
 * http://localhost:8080/tools/Aservlet?method=regist
 */

public class AServlet extends BaseServlet {
    public String regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("regist");
        return "/index.jsp";
        //return "r:/index.jsp";//请求转发
    }

    public String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("login");
        return "f:/index.jsp";//重定向
    }
}

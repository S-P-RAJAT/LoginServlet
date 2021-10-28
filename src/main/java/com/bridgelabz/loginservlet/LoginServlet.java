package com.bridgelabz.loginservlet;	

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

@WebServlet(
        description = "Login servlet",
        urlPatterns = {"/LoginServlet"},
        initParams = {
                @WebInitParam(name="user", value="Rahul"),
                @WebInitParam(name="password", value="abc@123"),
        }
)
public class LoginServlet  extends HttpServlet {

    private static final String NAME_PATTERN="^[A-Z][a-z]{2,}$";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String user = request.getParameter("user");
        String pwd = request.getParameter("pwd");
        String userID = getServletConfig().getInitParameter("user");
        String password = getServletConfig().getInitParameter("password");
        Pattern namePattern = Pattern.compile(NAME_PATTERN);


        if(namePattern.matcher(user).matches() && password.equals(pwd)){
            request.setAttribute("user", user);
            request.getRequestDispatcher("LoginSuccess.jsp").forward(request, response);

        }else{
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
            PrintWriter out = response.getWriter();
            out.println("<font color=red>Either username or password is wrong</font>");
            rd.include(request, response);
        }

    }
}
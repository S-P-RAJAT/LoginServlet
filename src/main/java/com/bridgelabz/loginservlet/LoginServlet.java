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
        urlPatterns = {"/LoginServlet"}
)
public class LoginServlet  extends HttpServlet {

    private static final String NAME_PATTERN="^[A-Z][a-z]{2,}$";
    private static final String PASSWORD_PATERN="^(?=.*[A-Z])(?=.*[0-9])(?=.{8,})[0-9a-zA-Z]*[^0-9a-zA-Z][0-9a-zA-Z]*$";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String user = request.getParameter("user");
        String pwd = request.getParameter("pwd");
        String userID = getServletConfig().getInitParameter("user");
        String password = getServletConfig().getInitParameter("password");
        Pattern namePattern = Pattern.compile(NAME_PATTERN);
        Pattern passwordPattern = Pattern.compile(PASSWORD_PATERN);


        if(namePattern.matcher(user).matches() && passwordPattern.matcher(pwd).matches()){
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
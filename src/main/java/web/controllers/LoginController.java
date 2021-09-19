package web.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;

//@WebServlet("/login")
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDao loginDao;

    public void init() {
        loginDao = new UserDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.sendRedirect("index.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
            authenticate(request, response);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void authenticate(HttpServletRequest request, HttpServletResponse response)
    throws Exception {
    	response.setContentType("text/html");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        PrintWriter out= response.getWriter();
        
        
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
    	digest.update(password.getBytes());
    	byte[] messageDigestSHA6 = digest.digest();
    	StringBuffer stringBuffer = new StringBuffer();
        for (byte bytes : messageDigestSHA6) {
            stringBuffer.append(String.format("%02x", bytes & 0xff));
        }
        password = stringBuffer.toString();
        if (loginDao.validate(username, password)) {
        	HttpSession session=request.getSession();
        	session.setAttribute("UserID",username);
        	session.setAttribute("Role",loginDao.getUser(username).getRole());
        	if(loginDao.getUser(username).getRole().equals("admin"))	{
        		RequestDispatcher dispatcher = request.getRequestDispatcher("profileController");
                dispatcher.include(request, response);
        	}
        	else if(loginDao.getUser(username).getRole().equals("user"))	{
	            RequestDispatcher dispatcher = request.getRequestDispatcher("profileController");
	            dispatcher.include(request, response);
        	} else {
	        	out.println("Wrong username or password.Try again");
	        	RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
	            dispatcher.include(request, response);
	        }
        }else {
        	out.println("Wrong username or password.Try again");
        	RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        	dispatcher.include(request, response);
        }
    }
}
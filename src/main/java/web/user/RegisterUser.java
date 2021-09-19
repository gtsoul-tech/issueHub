package web.user;


import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;

import model.User;

public class RegisterUser extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDao registerDao;

    public void init() {
        registerDao = new UserDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.sendRedirect("index.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
        	PrintWriter out= response.getWriter();
		 	response.setContentType("text/html");
        	String username = request.getParameter("username");
            String password = request.getParameter("password");
            String fname = request.getParameter("fname");
            String lname = request.getParameter("lname");
            String email = request.getParameter("email");
            String resumeSkills = request.getParameter("resume_skills");
            
            if (username == "" || password == "" || fname=="" || lname =="" || email == "" || resumeSkills == "" || resumeSkills.length() > 765) {
            	out.println("All fields are required,resume skills should be less that 765 characters");
            	RequestDispatcher dispatcher = request.getRequestDispatcher("registerUser.jsp");
                dispatcher.include(request, response);
    		}else {
	            User user = new User();
	            
	            User temp = new User();
	            temp = (User) registerDao.getUser(username);
	            if(temp != null) {
	            	out.println("Username already exists,try another username");
	            	RequestDispatcher dispatcher = request.getRequestDispatcher("registerUser.jsp");
	                dispatcher.include(request, response);
	            }else if((User) registerDao.getUserByEmail(email) != null){
	            	out.println("Email already exists,try another email");
	            	RequestDispatcher dispatcher = request.getRequestDispatcher("registerUser.jsp");
	                dispatcher.include(request, response);
	            }	else {
	            	user.setUsername(username);
	            	user.setEmail(email);
	            	user.setFirstName(fname);
	            	
	            	
	                
	                
	                
	            	
	            	
	            	MessageDigest digest = MessageDigest.getInstance("SHA-256");
	            	digest.update(password.getBytes());
	            	byte[] messageDigestSHA6 = digest.digest();
	            	StringBuffer stringBuffer = new StringBuffer();
	                for (byte bytes : messageDigestSHA6) {
	                    stringBuffer.append(String.format("%02x", bytes & 0xff));
	                }
	            	
	            	user.setPassword(stringBuffer.toString());
	            	user.setLastName(lname);
	            	user.setRole("user");
	                user.setResumeSkills(resumeSkills);
		                int result=registerDao.saveUser(user);
		                if (result ==1) {
		                	out.println("Register went wrong.Try again");
		                	RequestDispatcher dispatcher = request.getRequestDispatcher("registerUser.jsp");
		                    dispatcher.include(request, response);
		                }else if(result ==0) {
		                	out.println("Register Succeed");
		                	RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		                    dispatcher.include(request, response);
		                }
	                }
	           }
    		
        	
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    

}
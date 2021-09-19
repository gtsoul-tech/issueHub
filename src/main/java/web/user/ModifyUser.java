package web.user;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;

import model.User;

public class ModifyUser  extends HttpServlet {
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
        	String username = (String)request.getSession().getAttribute("UserID");
        	String oldpassword = request.getParameter("oldpassword");
        	String password = request.getParameter("password");
        	
        	MessageDigest oldDigest = MessageDigest.getInstance("SHA-256");
        	oldDigest.update(oldpassword.getBytes());
        	byte[] oldmessageDigestSHA6 = oldDigest.digest();
        	StringBuffer oldstringBuffer = new StringBuffer();
            for (byte bytes : oldmessageDigestSHA6) {
            	oldstringBuffer.append(String.format("%02x", bytes & 0xff));
            }
        	
        	
        	
        	
        	if(registerDao.validate(username,oldstringBuffer.toString())) {
	            User temp = new User();
	            temp = (User) registerDao.getUser(username);
	            if(temp != null) {
	            	
	            	MessageDigest digest = MessageDigest.getInstance("SHA-256");
	            	digest.update(password.getBytes());
	            	byte[] messageDigestSHA6 = digest.digest();
	            	StringBuffer stringBuffer = new StringBuffer();
	                for (byte bytes : messageDigestSHA6) {
	                    stringBuffer.append(String.format("%02x", bytes & 0xff));
	                }
	            	
	            	temp.setPassword(stringBuffer.toString());
		            int result=registerDao.saveOrUpdateUser(temp);
	                if (result ==1) {
	                	request.setAttribute("message","Modyfing went wrong.Try again");
	                	RequestDispatcher dispatcher = request.getRequestDispatcher("profileController");
	                    dispatcher.include(request, response);
	                }else if(result ==0) {
	                	request.setAttribute("message","Modified password successfully");
	                	RequestDispatcher dispatcher = request.getRequestDispatcher("profileController");
	                    dispatcher.include(request, response);
	                }
	            }else {
			    	out.println("You have to register first");
			    	RequestDispatcher dispatcher = request.getRequestDispatcher("registerUser.jsp");
			        dispatcher.include(request, response);
	            }
        	}else {
        		request.setAttribute("message","Wrong password.Try again");
            	RequestDispatcher dispatcher = request.getRequestDispatcher("profileController");
                dispatcher.include(request, response);
        	}
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}

package web.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import dao.UsersProjectsDao;
import dao.ProjectDao;


import model.*;


public class ProjectsController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProjectDao dataDao;

    public void init() {
        dataDao = new ProjectDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    	try {
        	PrintWriter out= response.getWriter();
		 	response.setContentType("text/html");
		 	
            UserDao userDao = new UserDao();

            request.setAttribute("myRequests", userDao.getMyRequests((String)request.getSession().getAttribute("UserID")));
            request.setAttribute("otherRequests", userDao.getOtherRequests((String)request.getSession().getAttribute("UserID")));
		 	
		 	request.setAttribute("ownedProjects", userDao.getOwnedProjects((String)request.getSession().getAttribute("UserID")));
		 	//System.out.println(userDao.getOwnedProjects((String)request.getSession().getAttribute("UserID")));
		 	UsersProjectsDao usersProjectsDao = new UsersProjectsDao();
		 	request.setAttribute("joinedProjects", usersProjectsDao.getProjectsByMember((String)request.getSession().getAttribute("UserID")));
			RequestDispatcher dispatcher = request.getRequestDispatcher("myProjects.jsp");
			if(request.getAttribute("message")!= null) {
				out.println(request.getAttribute("message"));
			}
			dispatcher.include(request, response);
        }catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    	try {
    		PrintWriter out= response.getWriter();
		 	response.setContentType("text/html");
		 	
            UserDao userDao = new UserDao();
            request.setAttribute("myRequests", userDao.getMyRequests((String)request.getSession().getAttribute("UserID")));
            request.setAttribute("otherRequests", userDao.getOtherRequests((String)request.getSession().getAttribute("UserID")));
		 	
            
            
		 	request.setAttribute("ownedProjects", userDao.getOwnedProjects((String)request.getSession().getAttribute("UserID")));
		 	//System.out.println(userDao.getOwnedProjects((String)request.getSession().getAttribute("UserID")));
		 	UsersProjectsDao usersProjectsDao = new UsersProjectsDao();
		 	request.setAttribute("joinedProjects", usersProjectsDao.getProjectsByMember((String)request.getSession().getAttribute("UserID")));
			RequestDispatcher dispatcher = request.getRequestDispatcher("myProjects.jsp");
			if(request.getAttribute("message")!= null) {
				out.println(request.getAttribute("message"));
			}
			dispatcher.include(request, response);
        }catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
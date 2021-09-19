
package web.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import dao.UsersProjectsDao;
import dao.AssignedToDao;
import dao.ProjectDao;


import model.*;


public class ProfileController extends HttpServlet {
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
		 	request.setAttribute("user", userDao.getUser((String)request.getSession().getAttribute("UserID")));
		 	
		 	request.setAttribute("otherInvites",userDao.getOtherInvites((String)request.getSession().getAttribute("UserID")));
		 	request.setAttribute("myInvites",userDao.getMyInvites((String)request.getSession().getAttribute("UserID")));
		 	
		 	AssignedToDao assignedDao = new AssignedToDao();
		 	int mystats=0;
		 	ArrayList<Issue> issues =assignedDao.getIssuesByUser((String)request.getSession().getAttribute("UserID"));
		 	Iterator<Issue> iterator = issues.iterator();
		 	
		 	while(iterator.hasNext()) {
        		Issue tempIssue=iterator.next();
        		if (tempIssue.getStatus().equals("Resolved")){
        			mystats++;
        		}
        		
        	}
		 	request.setAttribute("myStats",String.valueOf(mystats));
		 	
		 	
		 	UsersProjectsDao usersProjectsDao = new UsersProjectsDao();
		 	RequestDispatcher dispatcher = request.getRequestDispatcher("myProfile.jsp");
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
		 	request.setAttribute("user", userDao.getUser((String)request.getSession().getAttribute("UserID")));
		 	
		 	
		 	AssignedToDao assignedDao = new AssignedToDao();
		 	int mystats=0;
		 	ArrayList<Issue> issues =assignedDao.getIssuesByUser((String)request.getSession().getAttribute("UserID"));
		 	Iterator<Issue> iterator = issues.iterator();
		 	
		 	while(iterator.hasNext()) {
        		Issue tempIssue=iterator.next();
        		if (tempIssue.getStatus().equals("Resolved")){
        			mystats++;
        		}
        		
        	}
		 	request.setAttribute("myStats",String.valueOf(mystats));
		 	
		 	request.setAttribute("otherInvites",userDao.getOtherInvites((String)request.getSession().getAttribute("UserID")));
		 	request.setAttribute("myInvites",userDao.getMyInvites((String)request.getSession().getAttribute("UserID")));
		 	
		 	
		 	
		 	UsersProjectsDao usersProjectsDao = new UsersProjectsDao();
		 	RequestDispatcher dispatcher = request.getRequestDispatcher("myProfile.jsp");
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
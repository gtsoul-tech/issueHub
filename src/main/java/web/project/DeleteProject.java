
package web.project;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import dao.ProjectDao;


import model.*;


public class DeleteProject extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProjectDao registerDao;

    public void init() {
        registerDao = new ProjectDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.sendRedirect("index.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
        	String title = request.getParameter("title");
            if (title == "") {					//isws to kanw me dropdown
            	request.setAttribute("message","All fields are required,Try again to create a team");
    		}else {
	            Project tempProject = new Project();
	            tempProject = (Project) registerDao.getMyProject(title);
	            
	            if(tempProject != null) {
	            	UserDao userDao = new UserDao();
	            	if(userDao.getUser((String)request.getSession().getAttribute("UserID")).getId()!= tempProject.getLeader().getId()) {
	            		request.setAttribute("message","You must be the leader to delete it");
	            	}else {
	            		int result=registerDao.deleteProject(tempProject);
		                if (result ==1) {
		                	request.setAttribute("message","Deleting that project went wrong.Try again");
		                }else if(result ==0) {
		                	request.setAttribute("message","Deleted " +title + " successfully");
		                }
	            	}
	                    
	            }	else {
	            	request.setAttribute("message","Project doesnt exist,try another title");
	           }
    		
        	
        }

    	RequestDispatcher dispatcher = request.getRequestDispatcher("projectsController");
        dispatcher.forward(request, response);
        }catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
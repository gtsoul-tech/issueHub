
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
import dao.UsersProjectsDao;


import model.*;


public class JoinProject extends HttpServlet {
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
        	String title = (String)request.getAttribute("title");
        	System.out.println(title);
        	String username = (String)request.getAttribute("username");
        	if(username == null) {
        		username = (String)request.getSession().getAttribute("UserID");
        	}
            if (title == "") {					//isws to kanw me dropdown
            	request.setAttribute("message","All fields are required,Try again to create a project");
    		}else {
	            Project tempProject = new Project();
	            
	            tempProject = (Project) registerDao.getMyProject(title);
	            
	            if(tempProject != null) {
	            	UserDao userDao = new UserDao();
	            	UsersProjectsDao registerMember = new UsersProjectsDao();
	            	ProjectUserId key = new ProjectUserId(userDao.getUser(username).getId(),tempProject.getId());
	            	UsersProjects member = new UsersProjects(key,userDao.getUser(username),tempProject);
	            	
	            	if(userDao.amIMember(username ,tempProject)) {
	            		request.setAttribute("message","You already joined that team");
	            	}else if(userDao.amILeader(username ,tempProject)){
	            		request.setAttribute("message","You are the leader of that team");
	            	}
	            	else {
	                    int result=registerMember.saveMember(member);
		                if (result ==1) {
		                	request.setAttribute("message","Joining that team went wrong.Try again");
		                }else if(result ==0) {
		                	request.setAttribute("message","Joined successfully");
		                }
	            	}
	           }else {
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
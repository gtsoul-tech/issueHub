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
import dao.UsersProjectsDao;
import dao.ProjectDao;

import model.User;
import model.UsersProjects;
import model.Project;
import model.ProjectUserId;

public class RegisterProject extends HttpServlet {
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
            String description = request.getParameter("description");
            if (title == "" || description == "") {
            	request.setAttribute("message","All fields are required,Try again to create a project");
    		}else {
	            Project project = new Project();
	            
	            Project temp = new Project();
	            temp = (Project) registerDao.getMyProject(title);
	            if(temp != null) {
	            	request.setAttribute("message", "Project already exists,try another title");
	            }	else {
	            	
	            	project.setTitle(title);
	            	project.setDescription(description);
	            	UserDao userDao = new UserDao();
	            	project.setLeader(userDao.getUser((String)request.getSession().getAttribute("UserID")));
	                    int result=registerDao.saveProject(project);
		                if (result ==1) {
		                    request.setAttribute("message", "Register went wrong.Try again");
		                }else if(result ==0) {
		                    request.setAttribute("message", "Register Succeed");
		                }
	                }
	        }
            RequestDispatcher dispatcher = request.getRequestDispatcher("projectsController");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
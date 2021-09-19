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

public class ModifyProject extends HttpServlet {
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
            if (description == "") {
            	request.setAttribute("message","Description should not be empty");
    		}else {
	            
	            Project temp = new Project();
	            temp = (Project) registerDao.getMyProject(title);
            	temp.setDescription(description);
                int result=registerDao.mergeProject(temp);
                if (result ==1) {
                    request.setAttribute("message", "Modify went wrong.Try again");
                }else if(result ==0) {
                    request.setAttribute("message", "Modify Succeed");
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
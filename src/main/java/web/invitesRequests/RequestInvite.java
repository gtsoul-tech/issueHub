package web.invitesRequests;


import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import dao.UsersProjectsDao;
import dao.ProjectDao;
import dao.RequestDao;
import dao.AssignedToDao;
import dao.InvitedToDao;
import dao.IssueDao;

import model.User;
import model.UsersProjects;
import model.AssignedToKey;
import model.InvitedTo;
import model.InvitedToKey;
import model.AssignedTo;

import model.Issue;
import model.Project;
import model.ProjectUserId;
import model.Request;
import model.RequestKey;

public class RequestInvite extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void init() {
        
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
		 	
		 	
        	String admin = request.getParameter("username");
            String title = request.getParameter("project");
            String applicant = (String)request.getSession().getAttribute("UserID");
			ProjectDao registerDao = new ProjectDao();
	        UserDao userDao = new UserDao();
            Project temp = new Project();
            temp = (Project) registerDao.getMyProject(title);
            if(userDao.amIMember(applicant,temp)) {
            	request.setAttribute("message", applicant+ " is already a member");
            }else if(userDao.amILeader(applicant, temp)) {
            	request.setAttribute("message", "You are the admin");
            }else{
            	RequestDao registerRequestDao = new RequestDao();
            	RequestKey key = new RequestKey(userDao.getUser(admin).getId(),userDao.getUser(applicant).getId(),temp.getId());
            	Request application = new Request(key,userDao.getUser(admin),userDao.getUser(applicant),temp);
            	int result=registerRequestDao.saveOrUpdateRequest(application);
                if (result ==1) {
                	request.setAttribute("message","Request went wrong");
                }else if(result ==0) {
                	request.setAttribute("message","Request sent");
                }
            }
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("projectsController");//issuesController");
            dispatcher.include(request, response);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
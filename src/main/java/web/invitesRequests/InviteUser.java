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

public class InviteUser extends HttpServlet {
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
		 	
		 	
        	String username = request.getParameter("username");
            String title = request.getParameter("project");
            
			ProjectDao registerDao = new ProjectDao();
	        UserDao userDao = new UserDao();
            Project temp = new Project();
            temp = (Project) registerDao.getMyProject(title);
            if(temp == null) {
            	request.setAttribute("message", "Project doesn't exist");
            }else if(userDao.amIMember(username,temp)) {
            	request.setAttribute("message", username+ " is already a member");
            }else if(!userDao.amILeader((String)request.getSession().getAttribute("UserID"), temp)) {
            	request.setAttribute("message", "You have to be the admin to invite someone");
            }else{
            	InvitedToDao registerInvitedTo = new InvitedToDao();
            	InvitedToKey key = new InvitedToKey(userDao.getUser((String)request.getSession().getAttribute("UserID")).getId(),userDao.getUser(username).getId(),temp.getId());
            	InvitedTo invitedTo = new InvitedTo(key,userDao.getUser((String)request.getSession().getAttribute("UserID")),userDao.getUser(username),temp);
            	int result=registerInvitedTo.saveInvite(invitedTo);
                if (result ==1) {
                	request.setAttribute("message","Invite went wrong");
                }else if(result ==0) {
                	request.setAttribute("message","Invite sent");
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
package web.issue;


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
import dao.IssueDao;

import model.User;
import model.UsersProjects;
import model.AssignedToKey;
import model.AssignedTo;

import model.Issue;
import model.Project;
import model.ProjectUserId;

public class RegisterIssue extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IssueDao issueDao;

    public void init() {
        issueDao = new IssueDao();
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
		 	
        	String issueSummary = request.getParameter("issue_summary");
            String issueDescription = request.getParameter("issue_description");
            String title = request.getParameter("project");
            
            
            
            if (issueSummary  == "" || issueDescription == "" || title == "" ) {
            	out.println("All fields are required,Try again to submit an issue");
            	request.setAttribute("message","All fields are required,Try again to submit an issue");
    		}else {
    			ProjectDao registerDao = new ProjectDao();
    	        UserDao userDao = new UserDao();
	            Project temp = new Project();
	            temp = (Project) registerDao.getMyProject(title);
	            if(temp != null) {
	            	Issue issue = new Issue();
	            	issue.setIssueSummary(issueSummary);
	            	issue.setIssueDescription(issueDescription);	            	
	            	LocalDateTime localDateTime = LocalDateTime.now();
	            	issue.setCreatedDate(java.sql.Date.valueOf(localDateTime.toLocalDate()));
	            	issue.setCreatedBy((String)request.getSession().getAttribute("UserID"));
	            	issue.setStatus("Open");
	            	issue.setOwningProject(temp);
	            	if(userDao.amIMember((String)request.getSession().getAttribute("UserID"),temp) || userDao.amILeader((String)request.getSession().getAttribute("UserID"), temp)) {
	            		int result = issueDao.saveIssue(issue);
		            	if (result ==1) {
		                    request.setAttribute("message", "Register issue went wrong.Try again");
		                }else if(result ==0) {
		                    request.setAttribute("message", "Issue got submitted");
		                }
		            	
	            	}else {
	            		request.setAttribute("message", "You have to be a member or the leader to sumbit an issue");
	            	}
	            }	else {
	            	request.setAttribute("message", "Project doesnt exists,try another project");
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
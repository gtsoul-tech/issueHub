
package web.project;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import dao.UsersProjectsDao;
import dao.AssignedToDao;
import dao.IssueDao;
import dao.ProjectDao;


import model.*;


public class QuitProject extends HttpServlet {
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
        	String username = request.getParameter("username");
            if (title == "") {					//isws to kanw me dropdown
            	request.setAttribute("message","All fields are required,Try again to create a team");
            	
    		}else {
	            Project tempProject = new Project();
	            tempProject = (Project) registerDao.getMyProject(title);
	            
	            if(tempProject != null) {
	            	UsersProjects usersProject = new UsersProjects();
		            
		            UserDao userDao = new UserDao();
	            	UsersProjectsDao deleteMember = new UsersProjectsDao();
	            	ProjectUserId key;
	            	UsersProjects member;
	            	if(username == null) {
                		username= (String)request.getSession().getAttribute("UserID");
                	}
            	
	            	key = new ProjectUserId(userDao.getUser(username).getId(),tempProject.getId());
	            	member = new UsersProjects(key,userDao.getUser(username),tempProject);
	            	if(userDao.amIMember(username,tempProject)) {
	                    int result=deleteMember.deleteMember(member);
	            	
		                if (result ==1) {
		                	request.setAttribute("message","Quiting that project went wrong.Try again");
		                }else if(result ==0) {
		        		 	AssignedToDao assignedDao = new AssignedToDao();
		        		 	
		        			User user=userDao.getUser(username);
		        			ArrayList<Issue> projectIssues = assignedDao.getIssuesByProject(title);
		        			
		        			Iterator<Issue> iterator = projectIssues.iterator();
		        			
		        			Map<Issue,User> map=new HashMap<Issue,User>();
		        			IssueDao issueDao = new IssueDao();
		    	            
	                    	while(iterator.hasNext()) {
	                    		Issue tempIssue=iterator.next();
	                    		assignedDao.getAssignmentsByIssue(tempIssue);
	                    		if(assignedDao.getAssignmentsByIssue(tempIssue)!= null &&(assignedDao.getAssignmentsByIssue(tempIssue).getUser().getId()== user.getId())) {
	                    			map.put(tempIssue, user);
	                    		}
	                    	}
	                    	for (Map.Entry<Issue,User> entry : map.entrySet()) {
	                    		AssignedTo assignedTo = assignedDao.getAssignmentsByIssue(entry.getKey());

			    	            Issue issue = issueDao.getIssue(entry.getKey().getIssueId());
			    	            if( !issue.getStatus().equals("Resolved")) {
			            			issue.setStatus("Open");
			            			issueDao.mergeIssue(issue);
			            		}
	                    		assignedDao.deleteAssignement(assignedTo);
	                    	}
		                	request.setAttribute("message",username + " quit from " +title + " successfully");
		                }
		            	
	            	}else {
	            		request.setAttribute("message",username + " need to be a member of the project to quit");
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
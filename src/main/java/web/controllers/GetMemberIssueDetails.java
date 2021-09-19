package web.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
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


public class  GetMemberIssueDetails  extends HttpServlet {
    private static final long serialVersionUID = 1L;
    

    public void init() {
        //dataDao = new ProjectDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    	try {
    		response.sendRedirect("index.jsp");
        }catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    	try {
			String title = request.getParameter("Owned_Projects");
			String joined_title = request.getParameter("joined_projects");
			
            if (title == null && joined_title== null) {
            	request.setAttribute("message","You must select a project");
    		}else if(joined_title == null && title != null){

                request.setAttribute("tab", "v-pills-owned");
    			request.setAttribute("owned", "owned");
    			UserDao userDao = new UserDao();
    			UsersProjectsDao usersProjectsDao = new UsersProjectsDao();
    		 	ProjectDao dataDao = new ProjectDao();
    		 	AssignedToDao assignedDao = new AssignedToDao();
    		 	
    			request.setAttribute("project", dataDao.getMyProject(title));
    			ArrayList<User> members = usersProjectsDao.getMembersByProject(title);
    			ArrayList<Issue> projectIssues = assignedDao.getIssuesByProject(title);
    			
    			Iterator<Issue> iterator = projectIssues.iterator();
    			Iterator<User> iteratorUser = members.iterator();

    			request.setAttribute("members", members);
    			Map<Integer,User> map=new HashMap<Integer,User>();
    			while(iteratorUser.hasNext()){
                	User tempUser = iteratorUser.next();
                	
                	while(iterator.hasNext()) {
                		Issue tempIssue=iterator.next();
                		assignedDao.getAssignmentsByIssue(tempIssue);
                		if(assignedDao.getAssignmentsByIssue(tempIssue)!= null &&(assignedDao.getAssignmentsByIssue(tempIssue).getUser().getId()== tempUser.getId())) {
                			map.put(tempIssue.getIssueId(), tempUser);
                		}
                	}
                	iterator = projectIssues.iterator();
                	
                }
    			
    			User user= userDao.getUser((String)request.getSession().getAttribute("UserID"));
    			while(iterator.hasNext()) {
            		Issue tempIssue=iterator.next();
            		assignedDao.getAssignmentsByIssue(tempIssue);
            		if(assignedDao.getAssignmentsByIssue(tempIssue)!= null &&(assignedDao.getAssignmentsByIssue(tempIssue).getUser().getId()== user.getId())) {
            			map.put(tempIssue.getIssueId(), user);
            		}
            	}
    			request.setAttribute("map", map);
    			
    			request.setAttribute("projectIssues", projectIssues);
    			
    			
    			
    		}else if(title == null && joined_title != null){
    			request.setAttribute("tab", "v-pills-joined");
    			request.setAttribute("joined", "joined");
    			UserDao userDao = new UserDao();
    			UsersProjectsDao usersProjectsDao = new UsersProjectsDao();
    		 	ProjectDao dataDao = new ProjectDao();
    		 	AssignedToDao assignedDao = new AssignedToDao();
    		 	
    			
    			ArrayList<User> members = usersProjectsDao.getMembersByProject(joined_title);
    			ArrayList<Issue> projectIssues = assignedDao.getIssuesByProject(joined_title);
    			

    			request.setAttribute("joinedProjectMembers", usersProjectsDao.getMembersByProject(joined_title));
    			request.setAttribute("joinedProject", dataDao.getMyProject(joined_title));
    			request.setAttribute("joinedProjectIssues", assignedDao.getIssuesByProject(joined_title));
    			
    			Iterator<Issue> iterator = projectIssues.iterator();
    			Iterator<User> iteratorUser = members.iterator();

    			Map<Integer,User> joinedMap=new HashMap<Integer,User>();
    			while(iteratorUser.hasNext()){
                	User tempUser = iteratorUser.next();
                	
                	while(iterator.hasNext()) {
                		Issue tempIssue=iterator.next();
                		assignedDao.getAssignmentsByIssue(tempIssue);
                		if(assignedDao.getAssignmentsByIssue(tempIssue)!= null &&(assignedDao.getAssignmentsByIssue(tempIssue).getUser().getId()== tempUser.getId())) {
                			joinedMap.put(tempIssue.getIssueId(), tempUser);
                		}
                	}
                	iterator = projectIssues.iterator();
                	
                }
    			
    			User user= userDao.getUser((String)request.getSession().getAttribute("UserID"));
    			while(iterator.hasNext()) {
            		Issue tempIssue=iterator.next();
            		assignedDao.getAssignmentsByIssue(tempIssue);
            		if(assignedDao.getAssignmentsByIssue(tempIssue)!= null &&(assignedDao.getAssignmentsByIssue(tempIssue).getUser().getId()== user.getId())) {
            			joinedMap.put(tempIssue.getIssueId(), user);
            		}
            	}
    			request.setAttribute("joinedMap", joinedMap);
    			
    			
    			iterator = projectIssues.iterator();
    			
    			Map<Issue,User> myIssues=new HashMap<Issue,User>();
    			User tempUser =userDao.getUser((String)request.getSession().getAttribute("UserID"));
    			while(iterator.hasNext()) {
            		Issue tempIssue=iterator.next();
            		assignedDao.getAssignmentsByIssue(tempIssue);
            		if(assignedDao.getAssignmentsByIssue(tempIssue)!= null &&(assignedDao.getAssignmentsByIssue(tempIssue).getUser().getId()== tempUser.getId())) {
            			myIssues.put(tempIssue, tempUser);
            		}
            	}
    			request.setAttribute("myIssues", myIssues);
    			
    		}
			RequestDispatcher dispatcher = request.getRequestDispatcher("projectsController");
			dispatcher.forward(request, response);
        }catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
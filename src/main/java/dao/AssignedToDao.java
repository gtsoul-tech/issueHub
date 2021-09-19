package dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import model.UsersProjects;
import model.AssignedTo;
import model.Issue;
import model.Project;
import model.User;
import util.HibernateUtil;

public class AssignedToDao {
    public int saveAssignement(AssignedTo assingement) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            session.save(assingement);
            // commit transaction
            transaction.commit();
            session.close();
            return 0;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return 1;
        }
    }
    public int saveOrUpdateAssignement(AssignedTo assingement) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            session.saveOrUpdate(assingement);
            // commit transaction
            transaction.commit();
            session.close();
            return 0;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return 1;
        }
    }
    public int deleteAssignement(AssignedTo assingement) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            session.delete(assingement);
            // commit transaction
            transaction.commit();
            session.close();
            return 0;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return 1;
        }
    }
    
    public AssignedTo getAssignmentsByIssue(Issue isSue) {	//einai unique gt mono enas ginete na einai assigned
    	AssignedTo assignedTo = null;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        	
         transaction = session.beginTransaction();
         assignedTo = (AssignedTo) session.createQuery("FROM AssignedTo A WHERE A.issue =: isSue").setParameter("isSue",isSue)
                 .uniqueResult();
         transaction.commit();
         session.close();
         return assignedTo;
        } catch (Exception e) { 
	        if (transaction != null) {
	           transaction.rollback();
	        }
	        e.printStackTrace();
	        return null;
        }
    }
    
    public ArrayList<Issue> getIssuesByUser(String userName) {
    	Transaction transaction = null;
        User user = null;
        ArrayList<Issue> myIssues = new ArrayList<Issue>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            user = (User) session.createQuery("FROM User U WHERE U.username = :userName").setParameter("userName", userName)
                .uniqueResult();
            
            Iterator<AssignedTo> iterator = user.getAssignedTo().iterator();

            while(iterator.hasNext()){
            	AssignedTo temp = iterator.next();
            	if(temp.getUser().getId() == user.getId()) {
            		myIssues.add(temp.getIssue());
            	}
            }
            // commit transaction
            transaction.commit();
            session.close();
            return myIssues;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return myIssues;
    }
    public ArrayList<Issue> getIssuesByProject(String tiTle) {
    	Transaction transaction = null;
        Project project = null;
        ArrayList<Issue> projectIssues=null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            project = (Project) session.createQuery("FROM Project P WHERE P.title =: tiTle").setParameter("tiTle", tiTle)
                    .uniqueResult();
            
            if(project != null) {
            	projectIssues = new ArrayList<Issue>(project.getIssues());
        		//System.out.println(ownedProjects+ "   asd"  +user.getProjects());
        		session.close();
        		return projectIssues;
        	}
            // commit transaction
            transaction.commit();
            session.close();
            return projectIssues;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return projectIssues;
    }
    
    
    
}
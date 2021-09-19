package dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.hibernate.Session;
import org.hibernate.Transaction;

import model.AssignedTo;
import model.InvitedTo;
import model.Issue;
import model.Project;
import model.Request;
import model.User;
import model.UsersProjects;
import util.HibernateUtil;

public class UserDao {

    public int saveUser(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
        
            session.save(user);
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
    
    public int saveOrUpdateUser(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
        
            session.saveOrUpdate(user);
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
    
    public int deleteUser(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
           
            session.delete(user);
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
    
    public User getUser(String userName) {
    	User user = null;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        	
         transaction = session.beginTransaction();
         user = (User) session.createQuery("FROM User U WHERE U.username =: userName").setParameter("userName", userName)
                 .uniqueResult();
         transaction.commit();
         session.close();
         return user;
        } catch (Exception e) { 
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	        return null;
        }
    }
    public User getUserByEmail(String eMail) {
    	User user = null;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        	
         transaction = session.beginTransaction();
         user = (User) session.createQuery("FROM User U WHERE U.email =: eMail").setParameter("eMail", eMail)
                 .uniqueResult();
         transaction.commit();
         session.close();
         return user;
        } catch (Exception e) { 
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	        return null;
        }
    }
    
    public boolean validate(String userName, String password) {

        Transaction transaction = null;
        User user = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object
            user = (User) session.createQuery("FROM User U WHERE U.username = :userName").setParameter("userName", userName)
                .uniqueResult();

            if (user != null && user.getPassword().equals(password)) {
            	session.close();
                return true;
            }
            // commit transaction
            transaction.commit();
            session.close();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return false;
    }
    public boolean amIMember(String userName,Project project) {

        Transaction transaction = null;
        User user = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object
            user = (User) session.createQuery("FROM User U WHERE U.username = :userName").setParameter("userName", userName)
                .uniqueResult();
            
            Iterator<UsersProjects> iterator = user.getMembers().iterator();

            while(iterator.hasNext()){
            	UsersProjects temp = iterator.next();
            	if(temp.getProject().getId() == project.getId() && temp.getUser().getId() == user.getId()) {
            		session.close();
                    return true;
            	}
            }
            // commit transaction
            transaction.commit();
            session.close();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean amIassigned(String userName,Issue issue) {

        Transaction transaction = null;
        User user = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object
            user = (User) session.createQuery("FROM User U WHERE U.username = :userName").setParameter("userName", userName)
                .uniqueResult();
            
            Iterator<AssignedTo> iterator = user.getAssignedTo().iterator();

            while(iterator.hasNext()){
            	AssignedTo temp = iterator.next();
            	if(temp.getIssue().getIssueId() == issue.getIssueId() && temp.getUser().getId() == user.getId()) {
            		session.close();
                    return true;
            	}
            }
            // commit transaction
            transaction.commit();
            session.close();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean amILeader(String userName,Project project) {

        Transaction transaction = null;
        User user = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object
            user = (User) session.createQuery("FROM User U WHERE U.username = :userName").setParameter("userName", userName)
                .uniqueResult();
            
        	if(user.getId() == project.getLeader().getId()) {
        		session.close();
                return true;
        	}
            // commit transaction
            transaction.commit();
            session.close();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<Project> getOwnedProjects(String userName) {

        Transaction transaction = null;
        User user = null;
        ArrayList<Project> ownedProjects = new ArrayList<Project>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object
            user = (User) session.createQuery("FROM User U WHERE U.username = :userName").setParameter("userName", userName)
                .uniqueResult();
            
        	if(user != null) {
        		ownedProjects = new ArrayList<Project>(user.getProjects());
        		//System.out.println(ownedProjects+ "   asd"  +user.getProjects());
        		session.close();
        		return ownedProjects;
        	}
            // commit transaction
            transaction.commit();
            session.close();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return ownedProjects;
    }

    public ArrayList<InvitedTo> getOtherInvites(String userName) {

        Transaction transaction = null;
        User user = null;
        ArrayList<InvitedTo> invites = new ArrayList<InvitedTo>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object
            user = (User) session.createQuery("FROM User U WHERE U.username = :userName").setParameter("userName", userName)
                .uniqueResult();
            
        	if(user != null) {
        		invites  = new ArrayList<InvitedTo>(user.getGotInvitedTo());
        		session.close();
        		return invites;
        	}
            // commit transaction
            transaction.commit();
            session.close();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return invites;
    }
    public ArrayList<InvitedTo> getMyInvites(String userName) {

        Transaction transaction = null;
        User user = null;
        ArrayList<InvitedTo> invites = new ArrayList<InvitedTo>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object
            user = (User) session.createQuery("FROM User U WHERE U.username = :userName").setParameter("userName", userName)
                .uniqueResult();
            
        	if(user != null) {
        		invites  = new ArrayList<InvitedTo>(user.getInvitedTo());
        		session.close();
        		return invites;
        	}
            // commit transaction
            transaction.commit();
            session.close();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return invites;
    }
    
    public ArrayList<Request> getMyRequests(String userName) {

        Transaction transaction = null;
        User user = null;
        ArrayList<Request> invites = new ArrayList<Request>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object
            user = (User) session.createQuery("FROM User U WHERE U.username = :userName").setParameter("userName", userName)
                .uniqueResult();
            
        	if(user != null) {
        		invites  = new ArrayList<Request>(user.getMyRequest());
        		session.close();
        		return invites;
        	}
            // commit transaction
            transaction.commit();
            session.close();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return invites;
    }
    public ArrayList<Request> getOtherRequests(String userName) {

        Transaction transaction = null;
        User user = null;
        ArrayList<Request> invites = new ArrayList<Request>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object
            user = (User) session.createQuery("FROM User U WHERE U.username = :userName").setParameter("userName", userName)
                .uniqueResult();
            
        	if(user != null) {
        		invites  = new ArrayList<Request>(user.getOtherRequest());
        		session.close();
        		return invites;
        	}
            // commit transaction
            transaction.commit();
            session.close();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return invites;
    }
    public int getSearchSize(String queRy) {

        Transaction transaction = null;
        int pages=0;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            queRy = queRy.trim().replaceAll("\\s", "%");
            queRy = "%" + queRy + "%";
            int size = session.createQuery("FROM User U WHERE lower(U.resumeSkills) LIKE lower(:queRy)").setParameter("queRy", queRy).getResultList().size();
            // commit transaction
            transaction.commit();
            session.close();
            pages = (int) Math.ceil((double) size/5);
            return pages;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return pages;
    }
    
    
    public ArrayList<User> getUsersByQuery(String queRy,int page) {

        Transaction transaction = null;
        List<User> users = new ArrayList<User>();
        ArrayList<User> myResults = new ArrayList<User>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            queRy = queRy.trim().replaceAll("\\s", "%");
            queRy = "%" + queRy + "%";
            int pages = session.createQuery("FROM User U WHERE lower(U.resumeSkills) LIKE lower(:queRy)").setParameter("queRy", queRy).getResultList().size();
            users = session.createQuery("FROM User U WHERE lower(U.resumeSkills) LIKE lower(:queRy)").setParameter("queRy", queRy)
            		.setFirstResult(5*(page-1)) // equivalent to OFFSET
            		.setMaxResults(5) // equivalent to LIMIT
            		.getResultList();
            
            myResults = new ArrayList<User>(users);
            
            // commit transaction
            transaction.commit();
            session.close();
            return myResults;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return myResults;
    }
    
    
    
    
    
    
}
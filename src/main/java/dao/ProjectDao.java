
package dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import model.User;
import model.Issue;
import model.Project;

import util.HibernateUtil;

public class ProjectDao {

    public int saveProject(Project project) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            session.save(project);
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
    
    public int saveOrUpdateProject(Project project) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            session.saveOrUpdate(project);
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
    public int updateProject(Project project) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            session.update(project);
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

    public int mergeProject(Project project) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            session.merge(project);
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
    
    public int deleteProject(Project project) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.delete(project);
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
    
    public Project getMyProject(String tiTle) {
    	Project project = null;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        	
         transaction = session.beginTransaction();
         project = (Project) session.createQuery("FROM Project P WHERE P.title =: tiTle").setParameter("tiTle", tiTle)
                 .uniqueResult();
         transaction.commit();
         session.close();
         return project;
        } catch (Exception e) { 
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	        return null;
        }
    }
    public ArrayList<Project> getProjectsByQuery(String queRy,int page) {

        Transaction transaction = null;
        List<Project> projects = new ArrayList<Project>();
        ArrayList<Project> myResults = new ArrayList<Project>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            queRy = queRy.trim().replaceAll("\\s", "%");
            queRy = "%" + queRy + "%";
            
            projects = session.createQuery("FROM Project U WHERE lower(U.title) LIKE lower(:queRy) OR lower(U.description) LIKE lower(:queRy)").setParameter("queRy", queRy)
            		.setFirstResult(5*(page-1)) // equivalent to OFFSET
            		.setMaxResults(5) // equivalent to LIMIT
            		.getResultList();
            
            myResults = new ArrayList<Project>(projects);
            
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
    public int getSearchSize(String queRy) {

        Transaction transaction = null;
        int pages=0;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            queRy = queRy.trim().replaceAll("\\s", "%");
            queRy = "%" + queRy + "%";
            int size = session.createQuery("FROM Project U WHERE lower(U.title) LIKE lower(:queRy) OR lower(U.description) LIKE lower(:queRy)").setParameter("queRy", queRy).getResultList().size();
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
}
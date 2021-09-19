package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import model.*;

import util.HibernateUtil;

public class IssueDao  {

    public int saveIssue(Issue issue) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            session.save(issue);
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
    
    public int saveOrUpdateIssue(Issue issue) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            session.saveOrUpdate(issue);
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

    public int deleteIssue(Issue issue) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            session.delete(issue);
            // commit transaction
            transaction.commit();
            session.close();
            return 0;
        } catch (Exception e) {
        	
            if (transaction != null) {
                //transaction.rollback();
            }
            e.printStackTrace();
            return 1;
        }
    }
    public int mergeIssue(Issue issue) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            session.merge(issue);
            // commit transaction
            transaction.commit();
            session.close();
            return 0;
        } catch (Exception e) {
        	
            if (transaction != null) {
                //transaction.rollback();
            }
            e.printStackTrace();
            return 1;
        }
    }
    public Issue getIssue(Integer isSueId) {
    	Issue issue = null;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        	
         transaction = session.beginTransaction();
         issue = (Issue) session.createQuery("FROM Issue U WHERE U.issueId =: isSueId").setParameter("isSueId", isSueId)
                 .uniqueResult();
         transaction.commit();
         session.close();
         return issue;
        } catch (Exception e) { 
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	        return null;
        }
    }
}
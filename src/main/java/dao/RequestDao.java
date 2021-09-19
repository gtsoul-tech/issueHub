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
import model.InvitedTo;
import model.Issue;
import model.Project;
import model.Request;
import model.User;
import util.HibernateUtil;

public class RequestDao {
    public int saveRequest(Request request) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            session.save(request);
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
    public int saveOrUpdateRequest(Request request) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            session.saveOrUpdate(request);
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
    public int deleteRequest(Request request) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            session.delete(request);
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
    public Request getRequest(User uSer,User applicantUser,Project proJect) {
        Transaction transaction = null;
        Request request = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            
            
            request = (Request) session.createQuery("FROM Request U WHERE U.user =: uSer AND  U.applicant =: applicantUser AND U.project =: proJect").setParameter("uSer", uSer).setParameter("applicantUser",applicantUser).setParameter("proJect",proJect)
                    .uniqueResult();
            
            
            // commit transaction
            transaction.commit();
            session.close();
            return request;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return request;
        }
    }
    
}
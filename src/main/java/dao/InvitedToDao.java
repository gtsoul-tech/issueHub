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
import model.User;
import util.HibernateUtil;

public class InvitedToDao {
    public int saveInvite(InvitedTo invite) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            session.save(invite);
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
    public int saveOrUpdateInvite(InvitedTo invite) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            session.saveOrUpdate(invite);
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
    public int deleteInvite(InvitedTo invite) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            session.delete(invite);
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
    public InvitedTo getInvite(User uSer,User invitedUser,Project proJect) {
        Transaction transaction = null;
        InvitedTo invitedTo = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            
            
            invitedTo = (InvitedTo) session.createQuery("FROM InvitedTo U WHERE U.user =: uSer AND  U.invited =: invitedUser AND U.project =: proJect").setParameter("uSer", uSer).setParameter("invitedUser",invitedUser).setParameter("proJect",proJect)
                    .uniqueResult();
            
            
            // commit transaction
            transaction.commit();
            session.close();
            return invitedTo;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return invitedTo;
        }
    }
    
}
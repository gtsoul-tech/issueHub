package dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import model.UsersProjects;
import model.Project;
import model.User;
import util.HibernateUtil;

public class UsersProjectsDao {
    public int saveMember(UsersProjects member) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            session.save(member);
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
    public int saveOrUpdateMember(UsersProjects member) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            session.saveOrUpdate(member);
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
    public int deleteMember(UsersProjects member) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            session.delete(member);
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
    public int addMember(UsersProjects member) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            //UsersProjects member = new UsersProjects();
            //member.setProjectUserId(null);
            //member.setUser(user);
            session.save(member);
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
    public ArrayList<Project> getProjectsByMember(String userName) {
    	Transaction transaction = null;
        User user = null;
        ArrayList<Project> joinedProjects = new ArrayList<Project>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            user = (User) session.createQuery("FROM User U WHERE U.username = :userName").setParameter("userName", userName)
                .uniqueResult();
            
            Iterator<UsersProjects> iterator = user.getMembers().iterator();

            while(iterator.hasNext()){
            	UsersProjects temp = iterator.next();
            	if(temp.getUser().getId() == user.getId()) {
            		joinedProjects.add(temp.getProject());
            	}
            }
            // commit transaction
            transaction.commit();
            session.close();
            return joinedProjects;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return joinedProjects;
    }
    public ArrayList<User> getMembersByProject(String tiTle) {
    	Transaction transaction = null;
        Project project = null;
        ArrayList<User> members = new ArrayList<User>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            project = (Project) session.createQuery("FROM Project P WHERE P.title =: tiTle").setParameter("tiTle", tiTle)
                    .uniqueResult();
            
            Iterator<UsersProjects> iterator = project.getMembers().iterator();

            while(iterator.hasNext()){
            	UsersProjects temp = iterator.next();
            	if(temp.getProject().getId() == project.getId()) {
            		members.add(temp.getUser());
            	}
            }
            // commit transaction
            transaction.commit();
            session.close();
            return members;
        } catch (Exception e) {
            if (transaction != null) {
               transaction.rollback();
            }
            e.printStackTrace();
        }
        return members;
    }
    
    
    
    
}
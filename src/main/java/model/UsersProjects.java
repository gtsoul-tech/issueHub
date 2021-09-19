package model;


import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "users_projects")
public class UsersProjects implements Serializable{
private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	ProjectUserId projectUserId;
	
	@ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    User user;
	 
	@ManyToOne
    @MapsId("projectId")
    @JoinColumn(name = "project_id")
    Project project;
	
	public UsersProjects () {
    }
    public UsersProjects (ProjectUserId projectUserId,User user,Project project) {
    	this.projectUserId = projectUserId;
    	this.user = user;
    	this.project = project;
    }
	public ProjectUserId getProjectUserId() {
        return projectUserId;
    }
    public void setProjectUserId(ProjectUserId projectUserId) {
        this.projectUserId=projectUserId;
    }
    
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user=user;
    }
    public Project getProject() {
        return project;
    }
    
    public void setProject(Project project) {
        this.project = project;
    }
    /*
    public String getProperty() {
        return property;
    }
    
    public void setProperty(String property) {
        this.property = property;
    }*/
}
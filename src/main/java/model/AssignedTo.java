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
@Table(name = "users_issues")
public class AssignedTo implements Serializable{
private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private AssignedToKey assignedToKey;
	
	@ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
	private User user;
	 
	@ManyToOne
    @MapsId("issueId")
    @JoinColumn(name = "issue_id")
	private Issue issue;
	
	public AssignedTo () {
    }
    public AssignedTo (AssignedToKey assignedToKey,User user,Issue issue) {
    	this.assignedToKey = assignedToKey;
    	this.user = user;
    	this.issue = issue;
    }
	public AssignedToKey getAssingedToKey() {
        return assignedToKey;
    }
    public void setAssingedToKey(AssignedToKey assignedToKey) {
        this.assignedToKey=assignedToKey;
    }
    
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user=user;
    }
    public Issue getIssue() {
        return issue;
    }
    
    public void setIssue(Issue issue) {
        this.issue = issue;
    }
    /*
    public String getProperty() {
        return property;
    }
    
    public void setProperty(String property) {
        this.property = property;
    }*/
}
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
@Table(name = "requests")
public class Request implements Serializable{
private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private RequestKey requestKey;
	
	@ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
	private User user;
	 
	@ManyToOne
    @MapsId("applicantUserId")
    @JoinColumn(name = "applicant_user_id")
	private User applicant;
	
	@ManyToOne
	@MapsId("projectId")
    @JoinColumn(name = "project_id")
	private Project project;
	
	
	public Request () {
    }
    public Request (RequestKey requestKey,User user,User applicant,Project project) {
    	this.requestKey = requestKey;
    	this.user = user;
    	this.applicant = applicant;
    	this.project=project;
    }
	public RequestKey getRequestKey() {
        return requestKey;
    }
    public void setRequestKey(RequestKey requestKey) {
        this.requestKey=requestKey;
    }
    
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user=user;
    }
    public User getApplicant() {
        return applicant;
    }
    
    public void setApplicant(User applicant) {
        this.applicant = applicant;
    }
    
    public Project getProject() {
        return project;
    }
    public void setProject(Project project) {
        this.project = project;
    }
}
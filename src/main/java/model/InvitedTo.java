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
@Table(name = "invites")
public class InvitedTo implements Serializable{
private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private InvitedToKey invitedToKey;
	
	@ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
	private User user;
	 
	@ManyToOne
    @MapsId("invitedUserId")
    @JoinColumn(name = "invited_user_id")
	private User invited;
	
	@ManyToOne
	@MapsId("projectId")
    @JoinColumn(name = "project_id")
	private Project project;
	
	
	public InvitedTo () {
    }
    public InvitedTo (InvitedToKey invitedToKey,User user,User invited,Project project) {
    	this.invitedToKey = invitedToKey;
    	this.user = user;
    	this.invited = invited;
    	this.project=project;
    }
	public InvitedToKey getInvitedToKey() {
        return invitedToKey;
    }
    public void setInvitedToKey(InvitedToKey invitedToKey) {
        this.invitedToKey=invitedToKey;
    }
    
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user=user;
    }
    public User getInvited() {
        return invited;
    }
    
    public void setInvited(User user) {
        this.invited = user;
    }
    
    public Project getProject() {
        return project;
    }
    public void setProject(Project project) {
        this.project = project;
    }
}
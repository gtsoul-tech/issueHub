package model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "projects")
public class Project implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "title",unique = true)
    private String title;

    @Column(name = "description")
    private String description;
    
    @ManyToOne
    private User leader;
    
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private Set<UsersProjects> members;
    
    
    @OneToMany(mappedBy = "owningProject", cascade = CascadeType.ALL)
    private Set<Issue> issues;
    
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private Set<InvitedTo> invites;
    
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private Set<Request> requests;
    
    
    public int getId() {
    	return id;
    }
    public int setId(int id) {
    	return this.id=id;
    }
    
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    
    public User getLeader() {
    	return leader;
    }
    public void setLeader(User leader) {
    	this.leader= leader;
    }
    
    public Set<UsersProjects> getMembers() {
        return members;
    }
    public void setMembers(Set<UsersProjects>  members) {
        this.members = members;
    }
    
    public Set<Issue> getIssues() {
        return issues;
    }
    public void setIssues(Set<Issue>  issues) {
        this.issues = issues;
    }
    public Set<InvitedTo> getGotInvitedTo() {
        return invites;
    }
    public void setGotInvitedTo(Set<InvitedTo>  invites) {
        this.invites= invites;
    }
    public Set<Request> getRequests() {
        return requests;
    }
    public void setRequests(Set<Request>  requests) {
        this.requests= requests;
    }
    
    
    
}
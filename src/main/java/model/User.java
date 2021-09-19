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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "users")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "user_name",unique = true)
    private String username;

    @Column(name = "password")
    private String password;
    
    @Column(name = "email",unique = true)
    private String email;
    
    @Column(name = "role")
    private String role;
    @Column(name = "resume_skills")
    private String resumeSkills;
    
    
    @OneToMany(mappedBy = "leader" ,cascade=CascadeType.ALL)
    //@JoinColumn(name="user_id")
    private Set<Project> projects;
    
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<UsersProjects> members;
    
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<AssignedTo> assignedTo;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<InvitedTo> invitedTo;
    
    @OneToMany(mappedBy = "invited", cascade = CascadeType.ALL)
    private Set<InvitedTo> gotInvitedTo;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Request> myRequest;
    
    @OneToMany(mappedBy = "applicant", cascade = CascadeType.ALL)
    private Set<Request> otherRequest;
    
    
    
    
    public int getId() {
    	return id;
    }
    public int setId(int id) {
    	return this.id=id;
    }
    
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public String getResumeSkills() {
        return resumeSkills;
    }
    public void setResumeSkills(String resumeSkills) {
        this.resumeSkills = resumeSkills;
    }
    
    
    public Set<Project> getProjects() {
        return projects;
    }
 
    public void setProjects(Set<Project>  projects) {
        this.projects = projects;
    }
    public Set<UsersProjects> getMembers() {
        return members;
    }
    public void setMembers(Set<UsersProjects>  members) {
        this.members = members;
    }

    public Set<AssignedTo> getAssignedTo() {
        return assignedTo;
    }
    public void setAssignedTo(Set<AssignedTo>  assignedTo) {
        this.assignedTo = assignedTo;
    }
    public Set<InvitedTo> getInvitedTo() {
        return invitedTo;
    }
    public void setInvitedTo(Set<InvitedTo>  invitedTo) {
        this.invitedTo = invitedTo;
    }
    public Set<InvitedTo> getGotInvitedTo() {
        return gotInvitedTo;
    }
    public void setGotInvitedTo(Set<InvitedTo>  gotInvitedTo) {
        this.gotInvitedTo= gotInvitedTo;
    }
    public Set<Request> getMyRequest() {
        return myRequest;
    }
    public void setMyRequest(Set<Request>  myRequest) {
        this.myRequest= myRequest;
    }
    public Set<Request> getOtherRequest() {
        return otherRequest;
    }
    public void setOtherRequest(Set<Request>  otherRequest) {
        this.otherRequest= otherRequest;
    }
    
    
    
    
}
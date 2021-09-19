package model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
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
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import java.time.LocalDate;

@Entity
@Table(name = "issue")
public class Issue implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int issueId;

    @Column(name = "issue_summary")
    private String issueSummary;

    @Column(name = "issue_description")
    private String issueDescription;
    
    @Column(name = "created_date")
    private Date createdDate;
    
    @Column(name = "created_by")
    private String createdBy;
    
    @Column(name = "status")
    private String status;
    
    @Column(name = "priority")
    private String priority;
    
    @Column(name = "resolution")
    private String resolution;
    
    @Column(name = "target_date")
    private Date targetDate;
    @Column(name = "resolution_date")
    private Date resolutionDate;
    
    @OneToMany(mappedBy = "issue", cascade = CascadeType.ALL)		// gia na yparxei to null otan sbhnete
    private Set<AssignedTo> assignedTo;
    
    
	@ManyToOne
    @JoinColumn(name = "owning_project")
    Project owningProject;
	
	public int getIssueId() {
    	return issueId;
    }
    public void setIssueId(int issueId) {
    	this.issueId=issueId;
    }
    
    public String getIssueSummary() {
    	return issueSummary;
    }
    public void setIssueSummary(String issueSummary) {
    	this.issueSummary=issueSummary;
    }
    
    public String getIssueDescription() {
    	return issueDescription;
    }
    public void setIssueDescription(String issueDescription) {
    	this.issueDescription=issueDescription;
    }
    public Date getCreatedDate() {
    	return createdDate;
    }
    public void setCreatedDate(Date createdDate) {
    	this.createdDate=(Date)createdDate.clone();
    }
    public String getCreatedBy() {
    	return createdBy;
    }
    public void setCreatedBy(String createdBy) {
    	this.createdBy=createdBy;
    }
    public String getStatus() {
    	return status;
    }
    public void setStatus(String status) {
    	this.status=status;
    }
    public String getPriority() {
    	return priority;
    }
    public void setPriority(String priority) {
    	this.priority=priority;
    }
    public String getResolution() {
    	return resolution;
    }
    public void setResolution(String resolution) {
    	this.resolution=resolution;
    }
    public Date getTargetDate() {
    	return targetDate;
    }
    public void setTargetDate(Date targetDate) {
    	this.targetDate=(Date)targetDate.clone();
    }
    public Date getResolutionDate() {
    	return resolutionDate;
    }
    public void setResolutionDate(Date resolutionDate) {
    	this.resolutionDate=(Date)resolutionDate.clone();
    }
    
    public Set<AssignedTo> getAssignedTo() {
    	return assignedTo;
    }
    public void setAssignedTo(Set<AssignedTo> assignedTo) {
    	this.assignedTo=assignedTo;
    }
    public Project getOwningProject() {
    	return owningProject;
    }
    public void setOwningProject(Project owningProject) {
    	this.owningProject=owningProject;
    }
    
}
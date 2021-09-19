package model;


import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class AssignedToKey implements Serializable {
	private static final long serialVersionUID = 1L;
	
	 @Column(name = "user_id")
	 private int userId;
	 
	 @Column(name = "issue_id")
	 private int issueId;
	 
	 public AssignedToKey() {
	 }
	
	 public AssignedToKey(int user_id, int issue_id) {
	     this.userId = user_id;
	     this.issueId = issue_id;
	 }
	 
	 public int getUserID() {
	     return  userId;
	 }
	 
	 public void setUserId(int user_id) {
	     this.userId = user_id;
	 }
	 public int getIssueId() {
	     return issueId;
	 }
	 public void setIssueId(int issueId) {
	     this.issueId =issueId;
	 }
	
	 @Override
	 public boolean equals(Object o) {
	     if (this == o) return true;
	     if (!(o instanceof AssignedToKey)) return false;
	     AssignedToKey that = (AssignedToKey) o;
	     return Objects.equals(getUserID(), that.getUserID()) &&
	             Objects.equals(getIssueId(), that.getIssueId());
	 }
	
	 @Override
	 public int hashCode() {
	     return Objects.hash(getUserID(), getIssueId());
	 }
	
	 
}
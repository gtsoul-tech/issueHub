package model;


import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class ProjectUserId implements Serializable {
	private static final long serialVersionUID = 1L;
	
	 @Column(name = "user_id")
	 private int userId;
	 
	 @Column(name = "project_id")
	 private int projectId;
	 
	 public ProjectUserId() {
	 }
	
	 public ProjectUserId(int user_id, int project_id) {
	     this.userId = user_id;
	     this.projectId = project_id;
	 }
	 
	 public int getUserID() {
	     return  userId;
	 }
	 
	 public void setUserId(int user_id) {
	     this.userId = user_id;
	 }
	 public int getProjectId() {
	     return projectId;
	 }
	 public void setProjectId(int ProjectId) {
	     this.projectId =ProjectId;
	 }
	
	 @Override
	 public boolean equals(Object o) {
	     if (this == o) return true;
	     if (!(o instanceof ProjectUserId)) return false;
	     ProjectUserId that = (ProjectUserId) o;
	     return Objects.equals(getUserID(), that.getUserID()) &&
	             Objects.equals(getProjectId(), that.getProjectId());
	 }
	
	 @Override
	 public int hashCode() {
	     return Objects.hash(getUserID(), getProjectId());
	 }
	
	 
}
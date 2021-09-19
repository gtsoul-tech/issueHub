package model;


import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class RequestKey implements Serializable {
	private static final long serialVersionUID = 1L;
	
	 @Column(name = "user_id")
	 private int userId;
	 
	 @Column(name = "applicant_user_id")
	 private int applicantUserId;
	 
	 @Column(name = "project_id")
	 private int projectId;
	 
	 
	 public RequestKey() {
	 }
	
	 public RequestKey(int user_id, int applicant_user_id,int projectId) {
	     this.userId = user_id;
	     this.applicantUserId = applicant_user_id;
	     this.projectId = projectId;
	 }
	 
	 public int getUserId() {
	     return  userId;
	 }
	 
	 public void setUserId(int user_id) {
	     this.userId = user_id;
	 }
	 public int getApplicantUserId() {
	     return applicantUserId;
	 }
	 public void setApplicantUserId(int applicantUserId) {
	     this.applicantUserId =applicantUserId;
	 }
	 
	 public int getProjectId() {
	     return  projectId;
	 }
	 
	 public void setProjectId(int projectId) {
	     this.projectId = projectId;
	 }
	 @Override
	 public boolean equals(Object o) {
	     if (this == o) return true;
	     if (!(o instanceof RequestKey)) return false;
	     RequestKey that = (RequestKey) o;
	     return Objects.equals(getUserId(), that.getUserId()) &&
	             Objects.equals(getApplicantUserId(), that.getApplicantUserId()) &&
	             Objects.equals(getProjectId(), that.getProjectId());
	 }
	
	 @Override
	 public int hashCode() {
	     return Objects.hash(getUserId(), getApplicantUserId(),getProjectId());
	 }
	
	 
}
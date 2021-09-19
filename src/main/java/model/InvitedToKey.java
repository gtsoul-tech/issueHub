
package model;


import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class InvitedToKey implements Serializable {
	private static final long serialVersionUID = 1L;
	
	 @Column(name = "user_id")
	 private int userId;
	 
	 @Column(name = "invited_user_id")
	 private int invitedUserId;
	 
	 @Column(name = "project_id")
	 private int projectId;
	 
	 
	 public InvitedToKey() {
	 }
	
	 public InvitedToKey(int user_id, int invited_user_id,int projectId) {
	     this.userId = user_id;
	     this.invitedUserId = invited_user_id;
	     this.projectId = projectId;
	 }
	 
	 public int getUserId() {
	     return  userId;
	 }
	 
	 public void setUserId(int user_id) {
	     this.userId = user_id;
	 }
	 public int getInvitedUserId() {
	     return invitedUserId;
	 }
	 public void setInvitedUserId(int invitedUserId) {
	     this.invitedUserId =invitedUserId;
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
	     if (!(o instanceof InvitedToKey)) return false;
	     InvitedToKey that = (InvitedToKey) o;
	     return Objects.equals(getUserId(), that.getUserId()) &&
	             Objects.equals(getInvitedUserId(), that.getInvitedUserId()) &&
	             Objects.equals(getProjectId(), that.getProjectId());
	 }
	
	 @Override
	 public int hashCode() {
	     return Objects.hash(getUserId(), getInvitedUserId(),getProjectId());
	 }
	
	 
}
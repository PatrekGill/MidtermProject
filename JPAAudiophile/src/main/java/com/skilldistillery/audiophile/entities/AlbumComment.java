package com.skilldistillery.audiophile.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AlbumComment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "user_id")
	private int userId;
	
	@Column(name = "album id")
	private int albumId;
	
	private String comment;

	@Column(name = "comment_date")
	private LocalDateTime commentDate;

	@Column(name = "in_reply_to")
	private int inReplyTo;


	public AlbumComment() {
		super();
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public int getAlbumId() {
		return albumId;
	}


	public void setAlbumId(int albumId) {
		this.albumId = albumId;
	}


	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}


	public LocalDateTime getCommentDate() {
		return commentDate;
	}


	public void setCommentDate(LocalDateTime commentDate) {
		this.commentDate = commentDate;
	}


	public int getInReplyTo() {
		return inReplyTo;
	}


	public void setInReplyTo(int inReplyTo) {
		this.inReplyTo = inReplyTo;
	}


	@Override
	public int hashCode() {
		return Objects.hash(id);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AlbumComment other = (AlbumComment) obj;
		return id == other.id;
	}


	@Override
	public String toString() {
		return "AlbumComment [userId=" + userId + ", albumId=" + albumId + ", commentDate=" + commentDate
				+ ", inReplyTo=" + inReplyTo + "]";
	}
	
}
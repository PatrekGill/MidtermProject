package com.skilldistillery.audiophile.entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "album_comment")
public class AlbumComment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "album_id")
	private Album album;
	
	@Column(name="update_time")
	@UpdateTimestamp
	private LocalDateTime updateDateTime;
	
	private String comment;

	@Column(name = "comment_date")
	@CreationTimestamp
	private LocalDateTime commentDate;

	@Column(name = "in_reply_to")
	private Integer inReplyTo;
	
	@OneToMany
	@JoinColumn(name="in_reply_to")
	private List<AlbumComment> replies;


	public AlbumComment() {
		super();
	}


	public int getId() {
		return id;
	}
	

	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Album getAlbum() {
		return album;
	}


	public void setAlbum(Album album) {
		this.album = album;
	}


	public void setId(int id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	
	public List<AlbumComment> getReplies() {
		return replies;
	}
	public void setReplies(List<AlbumComment> replies) {
		this.replies = replies;
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


	public Integer getInReplyTo() {
		return inReplyTo;
	}


	public void setInReplyTo(Integer inReplyTo) {
		this.inReplyTo = inReplyTo;
	}

	
	public LocalDateTime getUpdateDateTime() {
		return updateDateTime;
	}
	public void setUpdateDateTime(LocalDateTime updateDateTime) {
		this.updateDateTime = updateDateTime;
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
		return "AlbumComment [userId=" + user + ", albumId=" + album + ", commentDate=" + commentDate
				+ ", inReplyTo=" + inReplyTo + "]";
	}
	
}
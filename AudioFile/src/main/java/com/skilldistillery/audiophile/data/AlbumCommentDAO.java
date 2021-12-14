package com.skilldistillery.audiophile.data;

import java.util.List;

import com.skilldistillery.audiophile.entities.AlbumComment;

public interface AlbumCommentDAO {

	List<AlbumComment> findByUsername (String username);
	List <AlbumComment> findByAlbumId(int id);
	public AlbumComment findAlbumCommentById(int id);
	public AlbumComment findAlbumCommentByUserId(int id);
	public List<AlbumComment> findByComment(String albumComment);
	public List<AlbumComment> sortAlbumCommentByCommentDate (boolean ascendingOrder, int numberOfRatingsToShow);
	public List<AlbumComment> sortAlbumCommentByCommentDate (boolean ascendingOrder);
	
	public boolean updateComment(int id, String newComment);
	public AlbumComment createAlbumComment(AlbumComment albumComment);
	public boolean deleteAlbumComment(int id);
}

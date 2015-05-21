package com.gcit.training.lms.entity;

public class Genre {

	@Override
	public String toString() {
		return  genreName; 
	}
	private int genreId;
	private String genreName;
	
	public int getGenreId() {
		return genreId;
	}
	public void setGenreId(int genreId) {
		this.genreId = genreId;
	}
	public String getGenreName() {
		return genreName;
	}
	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}
}

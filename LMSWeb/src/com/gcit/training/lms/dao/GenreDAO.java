package com.gcit.training.lms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.training.lms.entity.Genre;

public class GenreDAO extends BaseDAO<List<Genre>> {

	public GenreDAO() {
		 
	}
	public void create(Genre genre) throws Exception {
		save("insert into tbl_genre (genre_name) values (?)",
				new Object[] { genre.getGenreName() });
	}
	public int createLate(Genre genre) throws Exception {		
		int AuthorId = saveWithId("insert into tbl_genre (genre_name) values (?)",
				new Object[] { genre.getGenreName() });
		 return AuthorId;
	}

	public void update(Genre genre) throws Exception {
		save("update tbl_genre set genre_name = ? where genre_id = ?",
				new Object[] { genre.getGenreName(), genre.getGenreId() });
	}

	public void delete(Genre genre) throws Exception {
		save("delete from tbl_genre where genre_id = ?",
				new Object[] { genre.getGenreId() });
	}

	@SuppressWarnings("unchecked")
	public List<Genre> readAll() throws Exception {
		return (List<Genre>) read("select * from tbl_genre", null);
	}

	@SuppressWarnings("unchecked")
	public Genre readOne(int authorId) throws Exception {
		List<Genre> list = (List<Genre>) read(
				"select * from tbl_genre where genre_id = ?",
				new Object[] { authorId });

		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Genre> readbyName(String name) throws Exception {
		List<Genre> pubList = new ArrayList<>();
		pubList =  (List<Genre>) read("select * from tbl_genre p"
				+ " where upper(p.genre_name)= ?", new Object[] { name });
		return pubList;
	}

	@Override
	protected List<Genre> extractData(ResultSet rs) throws SQLException {
		List<Genre> list = new ArrayList<>();
		while (rs.next()) {
			Genre a = new Genre();
			a.setGenreId(rs.getInt("genre_id"));
			a.setGenreName(rs.getString("genre_name")); 
			list.add(a);
		}
		return list;
	}
	@Override
	protected List<?> veryExtractData(ResultSet rs) throws SQLException,
			Exception {
		// TODO Auto-generated method stub
		return null;
	}
	 

}

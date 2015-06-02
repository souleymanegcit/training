
package com.gcit.lms.dao;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import com.gcit.lms.domain.Genre;
import com.gcit .lms.domain.Book;

public class GenreDAO extends BaseDAO<List<Genre>> implements Serializable, ResultSetExtractor<List<Genre>> {
  
	/**
	 * 
	 */
	private static final long serialVersionUID = -6059201369554902973L;


	public void addGenre(Genre genre) throws SQLException {
 
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		template.update(
				"insert into tbl_genre (genre_name) values (?)", new Object[] {
						genre.getName()}, keyHolder);
		int genreId = keyHolder.getKey().intValue();
		
		for (Book bk : genre.getBooks()) {
			template.update("insert into tbl_book_genres (genre_id, bookId) values (?,?)",
					new Object[] {genreId, bk.getBookId()});
		}
	}
	
	public void update(Genre genre) throws Exception {
		template.update("update tbl_genre set genre_name = ? where genre_id = ?",
				new Object[] { genre.getName(), genre.getGenreId() });
	}

	public void delete(Genre genre) throws Exception {
		template.update("delete from tbl_genre where genre_id = ?",
				new Object[] { genre.getGenreId() });
	}

	 
	public List<Genre> readAll() throws Exception {
		return (List<Genre>) template.query("select * from tbl_genre", this);
	}
 
	public Genre readOne(int authorId) throws Exception {
		List<Genre> list = (List<Genre>) template.query(
				"select * from tbl_genre where genre_id = ?",
				new Object[] { authorId }, this);

		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	
 
//	public List<Genre> readbyName(String name) throws Exception {
//		List<Genre> pubList = new ArrayList<>();
//		pubList =  (List<Genre>) read("select * from tbl_genre p"
//				+ " where upper(p.genre_name)= ?", new Object[] { name });
//		return pubList;
//	}

	@Override
	public List<Genre> extractData(ResultSet rs) throws SQLException {
		List<Genre> list = new ArrayList<Genre>();
		while (rs.next()) {
			Genre a = new Genre();
			a.setGenreId(rs.getInt("genre_id"));
			a.setName(rs.getString("genre_name")); 
			list.add(a);
		}
		return list;
	}
	 
	 

}

package com.gcit.training.lms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.training.lms.entity.Author;
 

public class AuthorDAO extends BaseDAO<List<Author>> {

	public void create(Author author) throws Exception {
		   save ("insert into tbl_author (authorName) values (?)",
				new Object[] { author.getAuthorName() });
		  
	}
	public int createLate(Author author) throws Exception {		
		int AuthorId = saveWithId("insert into tbl_author (authorName) values (?)",
				new Object[] { author.getAuthorName() });
		 return AuthorId;
	}

	public void update(Author author) throws Exception {
		save("update tbl_author set authorName = ? where authorId = ?",
				new Object[] { author.getAuthorName(), author.getAuthorId() });
	}

	public void delete(Author author) throws Exception {
		save("delete from tbl_author where authorId = ?",
				new Object[] { author.getAuthorId() });
	}

	@SuppressWarnings("unchecked")
	public List<Author> readAll() throws Exception {
		return (List<Author>) read("select * from tbl_author", null);
	}

	@SuppressWarnings("unchecked")
	public Author readOne(int authorId) throws Exception {
		List<Author> list = (List<Author>) read(
				"select * from tbl_author where authorId = ?",
				new Object[] { authorId });

		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Author> readbyName(String authorname) throws Exception {
		List<Author> pubList = new ArrayList<>();
		pubList =  (List<Author>) read("select * from tbl_author p"
				+ " where upper(p.authorName)= ?", new Object[] { authorname });
		return pubList;
	}

	@Override
	protected List<Author> extractData(ResultSet rs) throws SQLException {
		List<Author> list = new ArrayList<Author>();
		while (rs.next()) {
			Author a = new Author();
			a.setAuthorId(rs.getInt("authorId"));
			a.setAuthorName(rs.getString("authorName"));

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

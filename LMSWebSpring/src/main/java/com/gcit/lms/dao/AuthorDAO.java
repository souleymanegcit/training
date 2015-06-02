package com.gcit.lms.dao;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;
import com.gcit.lms.domain.Author;

public class AuthorDAO extends BaseDAO<Author> implements Serializable, ResultSetExtractor<List<Author>> {

	private static final long serialVersionUID = 1619700647002508164L;

	public void addAuthor(Author author) throws SQLException {
		template.update("insert into tbl_author (authorName) values (?)",
				new Object[] { author.getAuthorName() });

	}

	public void updateAuthor(Author author) throws SQLException {
		template.update("update tbl_author set authorName = ? where authorId = ?",
				new Object[] { author.getAuthorName(), author.getAuthorId() });
	}

	public void removeAuthor(Author author) throws SQLException {
		template.update("delete from tbl_author where authorId=?",
				new Object[] { author.getAuthorId() });
	}

	public List<Author> readAll() throws SQLException {
		return (List<Author>) template.query("select * from tbl_author", this);
	}

	public Author readOne(int authorId) throws SQLException {
		List<Author> authors = (List<Author>) template.query(
				"select * from tbl_author where authorId = ?",
				new Object[] { authorId }, this);
		if (authors != null && authors.size() > 0) {
			return authors.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<Author> extractData(ResultSet rs) throws SQLException {
		List<Author> authors = new ArrayList<Author>();
		while (rs.next()) {
			Author a = new Author();
			a.setAuthorId(rs.getInt("authorId"));
			a.setAuthorName(rs.getString("authorName"));
			authors.add(a);
		}
		return authors;
	}
}

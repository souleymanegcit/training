package com.gcit.lms.dao;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.Book;

public class BookDAO extends BaseDAO<Book> implements Serializable, ResultSetExtractor<List<Book>> {

	private static final long serialVersionUID = 1619700647002508164L;
	
	@Autowired
	PublisherDAO pDAO;
	
	public void addBook(Book bk) throws SQLException {

		Integer pubId = null;
		if (bk.getPublisher() != null)
			pubId = bk.getPublisher().getId();
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		template.update(
				"insert into tbl_book (title, pubId) values (?,?)", new Object[] {
						bk.getTitle(), pubId }, keyHolder);
		int bookId = keyHolder.getKey().intValue();
		
		for (Author a : bk.getAuthors()) {
			template.update("insert into tbl_book_authors (bookId, authorId) values (?,?)",
					new Object[] { bookId, a.getAuthorId() });
		}
	}

   public void updateBook(Book book) throws Exception {
		
	   template.update("update tbl_book set title = ?  "
				+ "where bookId = ?", new Object[] {
				book.getTitle(),  book.getBookId() });
		
		for(Author a : book.getAuthors()) {
			template.update("update  tbl_book_authors set bookId=?  authorId=? where bookId=?   )",
					new Object[] { book.getBookId(), a.getAuthorId(), book.getBookId() });
		}
   }
		
	public void removeBook(Book book) throws SQLException {
		template.update("delete from tbl_Book where bookId = ?",
				new Object[] { book.getBookId() });
	}
	

	public List<Book> readAll() throws SQLException {
		return (List<Book>) template.query("select * from tbl_book", this);
	}

	public Author readOne(int authorId) throws SQLException {
		return null;
	}

	@Override
	public List<Book> extractData(ResultSet rs) throws SQLException {
		List<Book> books = new ArrayList<Book>();
		
		while (rs.next()) {
			Book b = new Book();
			b.setBookId(rs.getInt("bookId"));
			b.setTitle(rs.getString("title"));
			b.setPublisher(pDAO.readOne(rs.getInt("pubId")));
			books.add(b);
		}
		return books;
	}
	
	public List<Book> searchBookByTitle(String searchString) throws SQLException {
		searchString = "%" + searchString + "%";
		return (List<Book>) template.query("select * from tbl_book where title like ?", new Object[]{searchString}, this);
	}
}

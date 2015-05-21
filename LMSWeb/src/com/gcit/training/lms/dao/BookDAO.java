package com.gcit.training.lms.dao;

 
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List; 

import com.gcit.training.lms.entity.Author;
import com.gcit.training.lms.entity.Book;
import com.gcit.training.lms.entity.BookAuthors;
import com.gcit.training.lms.entity.Genre;
import com.gcit.training.lms.entity.Library;
import com.gcit.training.lms.entity.Publisher;


public class BookDAO extends BaseDAO<List<Book>> {

	public BookDAO( ) {
		
	}

	public void create(Book book) throws Exception {
		int bookId = saveWithId("insert into tbl_book (title, pubId) values (?,?)",
				new Object[] { book.getTitle(), book.getPublisher().getPublisherId() });
		
		for(Author a : book.getAuthors()) {
			save("insert into tbl_book_authors (bookId, authorId) values (?,?)",
					new Object[] { bookId, a.getAuthorId() });
		}

		for(Genre g : book.getGenres()) {
			save("insert into tbl_book_genres (bookId, genre_id) values (?,?)",
					new Object[] { bookId, g.getGenreId() });
		}
	} 

	public void updateAll(Book book) throws Exception {
		
		save("update tbl_book set title = ?  "
				+ "where bookId = ?", new Object[] {
				book.getTitle(),  book.getBookId() });
		
//		for(Author a : book.getAuthors()) {
//			save("update  tbl_book_authors set bookId=?  authorId=?)",
//					new Object[] { book.getBookId(), a.getAuthorId() });
//		}
//		
//		for(Genre g : book.getGenres()) {
//			save("update   tbl_book_genres set bookId=?, genre_id?)",
//					new Object[] { book.getBookId(), g.getGenreId() });
//		}
	  
		}
 
	public void delete(Book book) throws Exception {
		save("delete from tbl_Book where bookId = ?",
				new Object[] { book.getBookId() });
	}

	@SuppressWarnings("unchecked")
	public List<Book> readAll() throws Exception {
		return (List<Book>) read("select * from tbl_Book", null);
	}	
	@SuppressWarnings("unchecked")
	public List<Author> readBookAuthor(int bookId) throws Exception {
		return (List<Author>) read("select * from tbl_book_authors where bookId= ?", new Object[] { bookId });
		
	}	
	@SuppressWarnings("unchecked")
	public List<Genre> readBookGenre(int bookId) throws Exception {
		return (List<Genre>) read("select * from tbl_book_genres where bookId= ?", new Object[] { bookId });
		
	}	
		@SuppressWarnings("unchecked")
	public Publisher readPublisherBook(int publisherId) throws Exception {
			 
	return (Publisher) read(
	"select * from tbl_publisher where publisherId = ?",
	new Object[] { publisherId   });
	
	}

	@SuppressWarnings("unchecked")
	public Book readOne(int bookId) throws Exception {
		List<Book> pubList = (List<Book>) read(
				"select * from tbl_publisher where publisherId = ?",
				new Object[] { bookId });

		if (pubList != null && pubList.size() > 0) {
			return pubList.get(0);
		} else {
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	public Genre readOnegenre(int genreId) throws Exception {
		List<Genre> pubList = (List<Genre>) read(
				"select * from tbl_genre where genreId = ?",
				new Object[] { genreId });

		if (pubList != null && pubList.size() > 0) {
			return pubList.get(0);
		} else {
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	public List<Book> readbyName(String title) throws Exception {
		List<Book> pubList = new ArrayList<>();
		pubList =  (List<Book>) read("select * from tbl_Book p"
				+ " where upper(p.title)= ?", new Object[] { title });
		return pubList;
	}
 
	
	@SuppressWarnings("unchecked")
	@Override
	protected List<Book> extractData(ResultSet rs) throws Exception {
		List<Book> book = new ArrayList<Book>();
		PublisherDAO pub = new PublisherDAO();
		AuthorDAO author = new AuthorDAO();
		GenreDAO genre =  new GenreDAO();
		
		while (rs.next()) {
			Book p = new Book();
			p.setBookId(rs.getInt("bookId"));
			p.setTitle(rs.getString("title"));
			p.setPublisher(pub.readOne((rs.getInt("pubId"))));
			p.setAuthors(new ArrayList<Author>());
			p.setGenres(new ArrayList<Genre>());
			
			List<Author> authors = (List<Author>) author.read("select * from tbl_author where authorId in "
					+ "(select authorId from tbl_book_authors where bookId = ?)", new Object[]{p.getBookId()});
			p.setAuthors(authors);
			List<Genre> genres = (List<Genre>) genre.read("select * from tbl_genre where genre_Id in "
					+ "(select genre_Id from tbl_book_genres where bookId = ?)", new Object[]{ p.getBookId()});
			p.setGenres(genres);
			book.add(p);
		}
		return (List<Book>)book;
	}

	@Override
	protected List<?> veryExtractData(ResultSet rs) throws SQLException,
			Exception {
		// TODO Auto-generated method stub
		return null;
	}

	 	}

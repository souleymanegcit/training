package com.gcit.lms.service;

 
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.transaction.annotation.Transactional;

import com.gcit.lms.dao.AuthorDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.PublisherDAO;
import com.gcit.lms.dao.LibraryDAO;
import com.gcit.lms.dao.GenreDAO;
import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.Publisher;
import com.gcit.lms.domain.LibraryBranch;
import com.gcit.lms.domain.Genre;
//@Scope("prototype")
public class AdministratorService {

	@Autowired
	public BasicDataSource ds;

	@Autowired
	private AuthorDAO authorDAO;
	
	@Autowired
	private GenreDAO genreDAO;
	
	@Autowired
	private PublisherDAO pubDAO;
	
	@Autowired
	private LibraryDAO libraryDAO;
	
	@Autowired
	private BookDAO bookDAO;

	@Transactional
	public void addAuthor(Author author) throws Exception {
		authorDAO.addAuthor(author);
	}
	@Transactional
	public void addPublisher(Publisher p) throws Exception {
			pubDAO.addPublisher(p);
	}
	@Transactional
	public void addBook(Book b) throws Exception {
			bookDAO.addBook(b);
	}
	@Transactional
	public void addLibrary(LibraryBranch library) throws Exception {
		 libraryDAO.addLibrary(library);
	}
	@Transactional
	public void addGenre(Genre genre) throws Exception {
		 genreDAO.addGenre(genre);
	} 
	@Transactional
	public void deleteAuthor(Author author) throws Exception {
		authorDAO.removeAuthor(author);
	}
	@Transactional
	public void deletePublisher(Publisher publisher) throws Exception {
		pubDAO.removePublisher(publisher);
	}
	public void deleteBook(Book book) throws SQLException {
		 bookDAO.removeBook(book);
	}
	@Transactional
	public void deleteLibrary(LibraryBranch library) throws Exception {
		 libraryDAO.update(library);
	}  
	@Transactional
	public void deleteGenre(Genre genre) throws Exception {
		 genreDAO.delete(genre);
	}
	@Transactional
	public void editAuthor(Author author) throws Exception {
		authorDAO.updateAuthor(author);
	} 
	@Transactional
	public void editPublisher(Publisher publisher) throws Exception {
		pubDAO.updatePublisher(publisher);
	}
	public void editBook(Book book) throws Exception {
		bookDAO.updateBook(book);
	} 
	@Transactional
	public void editLibrary(LibraryBranch library) throws Exception {
		 libraryDAO.update(library);
	}
	@Transactional
	public void editGenre(Genre genre) throws Exception {
		 genreDAO.update(genre);
	} 
	public Author getAuthor(int authorId) throws Exception {
		return authorDAO.readOne(authorId);
	} 
	 
	public List<Book> getBooks() throws Exception {
		return bookDAO.readAll();
	}
	public List<Genre> getGenres() throws Exception {
		return genreDAO.readAll();
	} 
	public List<Publisher> getPublishers() throws Exception {
		return pubDAO.readAll();
	} 
	public List<Book> searchBooks(String searchString) throws Exception {
		return bookDAO.searchBookByTitle(searchString);
	}  
	public List<LibraryBranch> getLibray() throws Exception {
		return libraryDAO.readAll();
	}
	public LibraryBranch getLibrary(int branchId) throws Exception {
		return libraryDAO.readOne(branchId);
	} 
	public Genre getGenre(int genreId) throws Exception {
		return genreDAO.readOne(genreId);
	} 

}

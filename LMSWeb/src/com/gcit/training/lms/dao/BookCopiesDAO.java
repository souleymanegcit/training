package com.gcit.training.lms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List; 
import com.gcit.training.lms.entity.Book;
import com.gcit.training.lms.entity.BookCopies;
import com.gcit.training.lms.entity.Library;

public class BookCopiesDAO extends BaseDAO<List<BookCopies>> {

	public BookCopiesDAO() {

	}

	public void create(BookCopies bookCopies) throws Exception {
		save("insert into tbl_book_copies (bookId, branchId, noOfCopies) values (?,?,?)",
				new Object[] { ((Book) bookCopies.getBook()).getBookId(),
						((Library) bookCopies.getLibrary()).getBranchId(),
						bookCopies.getNoOfCopies() });
	}

	public void updateAll(BookCopies bookCopies) throws Exception {

		save("update tbl_book_copies set noOfCopies= ? "
				+ "where bookId = ? and branchId=?",
				new Object[] { bookCopies.getNoOfCopies(),
						((Book) bookCopies.getBook()).getBookId(),
						((Library) bookCopies.getLibrary()).getBranchId() });
	}

	@SuppressWarnings("unchecked")
	public List<BookCopies> readAll() throws Exception {
		return (List<BookCopies>) read("select * from tbl_book_copies", null);
	}  

	@SuppressWarnings("unchecked")
	public BookCopies readOne(int bookId, int branchId) throws Exception {
		List<BookCopies> pubList = (List<BookCopies>) read(
				"select * from tbl_book_copies where bookId = ? and branchId = ?",
				new Object[] { bookId, branchId });

		if (pubList != null && pubList.size() > 0) {
			return pubList.get(0);
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<BookCopies> readBookCopiesByBranch(int branchId) throws Exception {
		List<BookCopies> list = (List<BookCopies>) read(
				"Select * from tbl_book_copies where branchId=? and noOfCopies>0",
				new Object[] { branchId });
		return list; 
	}
	@SuppressWarnings("unchecked")
	public List<BookCopies> readBookCopiesByBranchId(int branchId) throws Exception {
		return (List<BookCopies>) read(
				"Select * from tbl_book_copies where branchId=? ",
				new Object[] { branchId });
		 
	}
	 
	public BookCopies readBookCopiesByBook(int bookId) throws Exception {
		return (BookCopies) read(
				"Select * from tbl_book_copies where bookId=? ",
				new Object[] { bookId });
		 
	} 
	@Override
	protected List<BookCopies> extractData(ResultSet rs) throws Exception {
		
		List<BookCopies> pubList = new ArrayList<BookCopies>();  
		BookDAO bookdao = new BookDAO();
		LibraryDAO  librarydao = new LibraryDAO(); 
		while (rs.next()) { 			
			BookCopies bc = new BookCopies(); 
			bc.setBook(bookdao.readBookOfCopies(rs.getInt("bookId")));
			bc.setLibrary(librarydao.readOne(rs.getInt("branchId")));
			bc.setNoOfCopies(rs.getInt("noOfCopies"));
			pubList.add(bc); 
 
		}
		return pubList;
	}

	@Override
	protected List<?> veryExtractData(ResultSet rs) throws SQLException,
			Exception {
		// TODO Auto-generated method stub
		return null;
	}
}

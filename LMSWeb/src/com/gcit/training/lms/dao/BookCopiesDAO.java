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
				new Object[] { bookCopies.getBook().getBookId(),
						bookCopies.getLibrary().getBranchId(),
						bookCopies.getNoOfCopies() });
	}

	public void updateAll(BookCopies bookCopies) throws Exception {

		save("update tbl_book_copies set noOfCopies= ? "
				+ "where bookId = ? and branchId=?",
				new Object[] { bookCopies.getNoOfCopies(),
						bookCopies.getBook().getBookId(),
						bookCopies.getLibrary().getBranchId() });
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
	public List<BookCopies> readBookCopiesByBook(int bookId) throws Exception {
		List<BookCopies> list = (List<BookCopies>) read(
				"Select * from tbl_book_copies where bookId=? ",
				new Object[] { bookId });
		return list; 
	}

	@Override
	protected List<BookCopies> extractData(ResultSet rs) throws SQLException {
		List<BookCopies> pubList = new ArrayList<BookCopies>();
		while (rs.next()) {
			BookCopies p = new BookCopies();
			Book b = new Book();
			Library l = new Library();
			b.setTitle(rs.getString(1));
			p.setBook(b);
			l.setBranchName(rs.getString(2));
			p.setLibrary(l);
			p.setNoOfCopies(rs.getInt(3));
			pubList.add(p);
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

package com.gcit.training.lms.service.admin;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.training.lms.dao.BookCopiesDAO;
import com.gcit.training.lms.dao.BookDAO;
import com.gcit.training.lms.dao.LibraryDAO;
import com.gcit.training.lms.entity.Book; 
import com.gcit.training.lms.entity.BookCopies;
import com.gcit.training.lms.entity.Library;

public class LibraryService {
 
	public LibraryService() {

	}

	public List<Library> displayLibrary() throws Exception {
		Connection con = ConnectionUtil.getConnection();
		try {
		LibraryDAO p = new LibraryDAO();
		return (List<Library>)p.readAll(); 
		} 
		finally{
			con.close();
		}
	}
	
//	public void displayLibraryByList() throws Exception {
//		try {
//			int count = 1;
//			List<Library> list = new ArrayList<Library>();
//			LibraryDAO p = new LibraryDAO();
//			list = p.readAll();
//			if (!list.isEmpty()) {
//				for (int i = 0; i < list.size(); i++) {
//					System.out.println(+i + 1 + ") "
//							+ list.get(i).getBranchName() + " , "
//							+ list.get(i).getBranchAdress());
//					count++;
//				}
//				System.out.println(count + ") Quit to previous");
//			}
//		} catch (Exception e) {
//			e.getStackTrace();
//			throw e;
//		}
//	}
//	public void displayBookByList() throws Exception {
//		try {
//			int count = 1;
//			List<Book> list = new ArrayList<Book>(); 
//			BookDAO p = new BookDAO();
//			list = p.readAll(); 
//			if (!list.isEmpty()) {
//				for (int i = 0; i < list.size(); i++) {
//					System.out.println(+i + 1 + ") " + list.get(i).getTitle()
//							+ " by " + list.get(i).getAuthors().get(i).getAuthorName());
//					count++;
//				}				
//				System.out.println(count + ") Quit to previous"); 
//			}
//		} catch (Exception e) {
//			e.getStackTrace(); 
//		}
//	}

	public BookCopies readOne(int bookid, int branchId) throws Exception {
		Connection con = ConnectionUtil.getConnection();
		try {
		BookCopiesDAO cpdao = new BookCopiesDAO();
		return cpdao.readOne(bookid, branchId);
		}
		finally {
			con.close();
		}
	}

	public int numOfBookCopies(Book book, Library library) throws Exception {
		Connection con = ConnectionUtil.getConnection();
		try {
		BookCopiesDAO bookCopiesDAO = new BookCopiesDAO();

		BookCopies bookCopies = bookCopiesDAO.readOne(book.getBookId(),
				library.getBranchId());
		if (bookCopies == null) {
			return 0;
		} else {
			return bookCopies.getNoOfCopies();
		}
		} finally {
			con.close();
		}
	}

	public Library displayLibraryList(int branchId) throws Exception { 
		Connection con = ConnectionUtil.getConnection();
		try {
		LibraryDAO p = new LibraryDAO();
			return p.readOne(branchId);
		}
		finally { 
			con.close();
		}
	}

	public List<Library> displayLibraryOne() throws Exception {
		Connection con = ConnectionUtil.getConnection();
		try {			 
			LibraryDAO p = new LibraryDAO(); 
			return (List<Library>)p.readAll();
		} catch (Exception e) {
			e.getStackTrace();
			throw e;
		}
		finally {
			con.close();
		}
	}

	public void insertLibrary(Library library) throws Exception {
		Connection con = ConnectionUtil.getConnection();
		try {
			LibraryDAO p = new LibraryDAO();
			// calling the method create to insert into table
			p.create(library);
			System.out.println("Inserted records into the table...Library");
			con.commit();
		} catch (Exception se) {
			con.rollback();
			// Handle errors for JDBC
			se.printStackTrace();
		} finally {
			 con.close();
		}
	}

//	public int insertLibraryLate(Library library) throws ClassNotFoundException {
//		int k = 0;
//		try {
//			LibraryDAO p = new LibraryDAO();
//			// calling the method create to insert into table
//			k = p.createLate(library);
//			System.out.println("Inserted records into the table...Library");
//		} catch (Exception se) {
//			// Handle errors for JDBC
//			se.printStackTrace();
//		} finally {
//			try {
//				displayLibrary();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		return k;
//	}

//	public boolean checkLibraryInList(List<Library> list, int branchId)
//			throws Exception {
//		boolean flag = false;
//		List<Library> pubList = new ArrayList<Library>(list);
//		for (Library ele : pubList) {
//			if (ele.getBranchId() == branchId) {
//				flag = true;
//				break;
//			}
//		}
//		return flag;
//	}

	public void updateLibrary(Library library) throws Exception {
		Connection con = ConnectionUtil.getConnection();
		try {
			LibraryDAO pa = new LibraryDAO();
			pa.update(library);
			con.commit();
		} catch (Exception se) {
			con.rollback();
			// Handle errors for JDBC
			se.printStackTrace();
		} finally {
			con.close();
		}
	}

//	public List<Library> checkLibraryByName(String authorname) throws Exception {
//		 
//		try {
//			if (authorname == null || authorname == null
//					|| authorname.length() == 0 || authorname.length() > 45) {
//				throw new Exception(
//						"Library name cannot be null and Name should be 1-45 characters");
//			}
//			LibraryDAO pa = new LibraryDAO();
//			return  (List<Library>) pa.readbyName(authorname);
//
//		} catch (Exception e) {
//			throw e;
//		} finally {
//
//		}
//
//	}

	public Library checkLibraryById(int libraryId) throws Exception {
		Connection con = ConnectionUtil.getConnection();
		try { 
			LibraryDAO pa = new LibraryDAO();
			return  pa.readOne(libraryId);

		} catch (Exception e) {
			throw e;
		} finally {
			con.close();
		}
	}

	public void deleteLibrary(Library library) throws Exception {
		Connection con = ConnectionUtil.getConnection();
		try {
			LibraryDAO pa = new LibraryDAO();
			pa.delete(library);
			con.commit();
		} catch (SQLException se) {
			
			con.rollback();// Handle errors for JDBC
			se.printStackTrace();
		}
		finally {
			con.close();
		}
	}
}

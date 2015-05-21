package com.gcit.training.lms.service.admin;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.training.lms.dao.BookCopiesDAO;
import com.gcit.training.lms.dao.BookDAO;
import com.gcit.training.lms.dao.LibraryDAO;
import com.gcit.training.lms.entity.Book;
import com.gcit.training.lms.entity.BookAuthors;
import com.gcit.training.lms.entity.BookCopies;
import com.gcit.training.lms.entity.Library;

public class LibraryService {
	private List<Library> pubList;

	public LibraryService() {

	}

	public void displayLibrary() throws Exception {

		LibraryDAO p = new LibraryDAO();
		List<Library> list = new ArrayList<>();
		list = p.readAll();
		System.out.println("The list of  Librarys");
		for (Library ele : list) {
			System.out.println(" ");
			System.out.println("Library Id: " + ele.getBranchId());
			System.out.println("Library name: " + ele.getBranchName());
			System.out.println("Branch address: " + ele.getBranchAdress());
		}
	}

	public void displayLibraryByList() throws Exception {
		try {
			int count = 1;
			List<Library> list = new ArrayList<Library>();
			LibraryDAO p = new LibraryDAO();
			list = p.readAll();
			if (!list.isEmpty()) {
				for (int i = 0; i < list.size(); i++) {
					System.out.println(+i + 1 + ") "
							+ list.get(i).getBranchName() + " , "
							+ list.get(i).getBranchAdress());
					count++;
				}
				System.out.println(count + ") Quit to previous");
			}
		} catch (Exception e) {
			e.getStackTrace();
			throw e;
		}
	}
	public void displayBookByList() throws Exception {
		try {
			int count = 1;
			List<Book> list = new ArrayList<Book>(); 
			BookDAO p = new BookDAO();
			list = p.readAll(); 
			if (!list.isEmpty()) {
				for (int i = 0; i < list.size(); i++) {
					System.out.println(+i + 1 + ") " + list.get(i).getTitle()
							+ " by " + list.get(i).getAuthors().get(i).getAuthorName());
					count++;
				}				
				System.out.println(count + ") Quit to previous"); 
			}
		} catch (Exception e) {
			e.getStackTrace(); 
		}
	}

	public BookCopies readOne(int bookid, int branchId) throws Exception {

		BookCopiesDAO cpdao = new BookCopiesDAO();
		return cpdao.readOne(bookid, branchId);
	}

	public int numOfBookCopies(Book book, Library library) throws Exception {

		BookCopiesDAO bookCopiesDAO = new BookCopiesDAO();

		BookCopies bookCopies = bookCopiesDAO.readOne(book.getBookId(),
				library.getBranchId());
		if (bookCopies == null) {
			return 0;
		} else
			return bookCopies.getNoOfCopies();

	}

	public List<Library> displayLibraryList(int branchId) throws Exception {
		try {
			List<Library> list = new ArrayList<Library>();
			LibraryDAO p = new LibraryDAO();
			return list = p.readOne(branchId);
		} catch (Exception e) {
			e.getStackTrace();
			throw e;
		}
	}

	public List<Library> displayLibraryOne() throws Exception {
		try {
			List<Library> list = new ArrayList<Library>();
			LibraryDAO p = new LibraryDAO();
			return list = p.readAll();
		} catch (Exception e) {
			e.getStackTrace();
			throw e;
		}
	}

	public void insertLibrary(Library library) throws ClassNotFoundException {
		try {
			LibraryDAO p = new LibraryDAO();
			// calling the method create to insert into table
			p.create(library);
			System.out.println("Inserted records into the table...Library");
		} catch (Exception se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} finally {
			try {
				displayLibrary();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public int insertLibraryLate(Library library) throws ClassNotFoundException {
		int k = 0;
		try {
			LibraryDAO p = new LibraryDAO();
			// calling the method create to insert into table
			k = p.createLate(library);
			System.out.println("Inserted records into the table...Library");
		} catch (Exception se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} finally {
			try {
				displayLibrary();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return k;
	}

	public boolean checkLibraryInList(List<Library> list, int branchId)
			throws Exception {
		boolean flag = false;
		List<Library> pubList = new ArrayList<Library>(list);
		for (Library ele : pubList) {
			if (ele.getBranchId() == branchId) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	public void updateLibrary(Library library) throws Exception {
		try {
			LibraryDAO pa = new LibraryDAO();
			pa.update(library);
		} catch (Exception se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} finally {
		}
	}

	public List<Library> checkLibraryByName(String authorname) throws Exception {
		pubList = new ArrayList<>();
		try {
			if (authorname == null || authorname == null
					|| authorname.length() == 0 || authorname.length() > 45) {
				throw new Exception(
						"Library name cannot be null and Name should be 1-45 characters");
			}
			LibraryDAO pa = new LibraryDAO();
			return pubList = (List<Library>) pa.readbyName(authorname);

		} catch (Exception e) {
			throw e;
		} finally {

		}

	}

	public List<Library> checkLibraryById(int libraryId) throws Exception {
		pubList = new ArrayList<>();
		try {

			LibraryDAO pa = new LibraryDAO();
			return pubList = (List<Library>) pa.readOne(libraryId);

		} catch (Exception e) {
			throw e;
		} finally {

		}
	}

	public void deleteLibrary(Library library) throws Exception {
		try {
			LibraryDAO pa = new LibraryDAO();
			pa.delete(library);
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		}
	}
}

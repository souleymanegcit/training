package com.gcit.training.lms.service.admin;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.training.lms.dao.AuthorDAO;
import com.gcit.training.lms.dao.BookDAO;
import com.gcit.training.lms.dao.PublisherDAO;
import com.gcit.training.lms.entity.Author;
import com.gcit.training.lms.entity.Book;
import com.gcit.training.lms.entity.BookAuthors;
import com.gcit.training.lms.entity.Genre;
import com.gcit.training.lms.entity.Publisher;
 

public class BookService {

	public BookService() {		 
	}
	
//	public void displayBook() throws Exception {		
//		BookDAO p = new BookDAO();
//		List<Book> list = new ArrayList<>(); 		 
//		list= p.readAll();
//		System.out.println("The list of  Books");
//		for(Book ele: list) {
//		System.out.println(" ");
//		System.out.println("#######################");
//     	System.out.println("Book Id: " + ele.getBookId());
//     	System.out.println("Title: " + ele.getTitle());    
//     	System.out.println("Authors: " + ele.getAuthors().get(0));    
//     	System.out.println("Publisher: " + ele.getPublisher().getPublisherName());    
//    	System.out.println("#######################");
//		}
//		}
//	public void displayBookByList() throws Exception {
//		try {
//			//int count = 1;
//			List<Book> list = new ArrayList<Book>(); 
//			BookDAO p = new BookDAO();
//			list = p.readAll(); 
//			//if (!list.isEmpty()) {
//				for (int i = 0; i < list.size(); i++) {
//					System.out.println(+i + 1 + ") " + list.get(i).getTitle()
//							+ " by " + list.get(i).getAuthors().get(i).getAuthorName());
//					//count++;
//				}				
//				System.out.println(count + ") Quit to previous"); 
//			}
//		} catch (Exception e) {
//			e.getStackTrace(); 
//		}
//	}

	public List<Book> displayBookList() throws Exception {		
		BookDAO p = new BookDAO();
		List<Book> list = new ArrayList<Book>(); 		 
		return list=(List<Book>) p.readAll(); 
		} 
	
 public void displayBookByList(List<Book> plist) throws Exception {		
		 
		List<Book> list = new ArrayList<>(); 
		list=plist;	
		if(!list.isEmpty()) {
		System.out.println("The list of  Books");
		for(Book ele: list) {
		System.out.println(" ");
		System.out.println("#######################");
		System.out.println("Book Id: " + ele.getBookId());
     	System.out.println("Title: " + ele.getTitle()); 
    	System.out.println("#######################");
		}
		}
		else {
			System.out.println("This Book name doesn't exist!!! please check it out and try again");
		}
		}
 
 public List<Author> authorInBook (int bookId) throws Exception {	 
	 try {
		List<Author> list = new ArrayList<Author>(); 
		BookDAO bookdao = new BookDAO();		
		return list=bookdao.readBookAuthor(bookId);
		}  	catch (Exception e) {
			throw e;
		}   finally {
			 
				} 
 }
 public List<Genre> genreInBook (int bookId) throws Exception {	 
	 try {
		List<Genre> list = new ArrayList<Genre>(); 
		BookDAO bookdao = new BookDAO();		
		return list=bookdao.readBookGenre(bookId);
		}  	catch (Exception e) {
			throw e;
		}   finally {
			 
				} 
 }
 public Book publisherInBook (int book) throws Exception {	 
	 try {
		 
		BookDAO bookdao = new BookDAO();	
		return bookdao.readOne(book);
		}  	catch (Exception e) {
			throw e;
		}   finally {
			 
				} 
 }
 public List<Book> checkBookByName(String name) throws Exception {
	  List<Book>pubList = new ArrayList<>();		
	try{
		if (name == null ||name == null
				||  name.length() == 0
				|| name.length() > 45) {
			throw new Exception(
					"Bookname cannot be null and Name should be 1-45 characters");
		}
		BookDAO pa = new BookDAO();
		return pubList= (List <Book>)pa.readbyName(name);
	 
		}  	catch (Exception e) {
			throw e;
		}   finally {
			 
				} 
	} 
 public boolean checkBookInList(List<Book> list, int bookId) throws Exception {
		boolean flag=false;
		List<Book> pubList = new ArrayList<Book>(list);		
		for(Book ele: pubList) {			 
	       if(ele.getBookId()==bookId) {
	     	 flag=true;
	     	 break;
			}
		}
		return flag;
	}
	
 public  void insertBook(Book book) throws ClassNotFoundException {
		try {
			BookDAO b = new BookDAO();
	    //calling the method create to insert into table 
	      b.create(book);
	  	  // displayBook();
	      System.out.println("The Book" +book.getTitle()+ " is inserting in the table...Book");
	       }
		catch(Exception se){
	 	   //Handle errors for JDBC
	 	   se.printStackTrace();
	 	   }	  
	       finally { 
	       }
	 	}
 public void updateBook(Book book) throws Exception {	 
		try {
			BookDAO ba = new BookDAO();
			ba.updateAll(book); 
			}
			catch(Exception se){
		     //Handle errors for JDBC
		    se.printStackTrace();		    
			   }
			finally {				 
				}
		}
 public  void deleteBook(Book book) throws Exception {
		try{
			BookDAO pa = new BookDAO();
			pa.delete(book);
			}
			catch(SQLException se){
			      //Handle errors for JDBC
				// conn.rollback();
			      se.printStackTrace();
			   }
		}
 
 public Book readOneById(int bookId) throws Exception {
	BookDAO b = new BookDAO();	
	 return b.readOne(bookId);
		
	}
 
}
 

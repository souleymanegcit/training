package com.gcit.training.lms.service.admin;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;  
import com.gcit.training.lms.dao.BookDAO; 
import com.gcit.training.lms.entity.Author;
import com.gcit.training.lms.entity.Book; 
import com.gcit.training.lms.entity.Genre; 
 

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
		Connection con = ConnectionUtil.getConnection();
		try{
		BookDAO p = new BookDAO();	  		 
		return (List<Book>) p.readAll();  
		} 
		finally {
			con.close();
		} 
		} 
	
// public void displayBookByList(List<Book> plist) throws Exception {		
//		 
//		List<Book> list = new ArrayList<>(); 
//		list=plist;	
//		if(!list.isEmpty()) {
//		System.out.println("The list of  Books");
//		for(Book ele: list) {
//		System.out.println(" ");
//		System.out.println("#######################");
//		System.out.println("Book Id: " + ele.getBookId());
//     	System.out.println("Title: " + ele.getTitle()); 
//    	System.out.println("#######################");
//		}
//		}
//		else {
//			System.out.println("This Book name doesn't exist!!! please check it out and try again");
//		}
//		}
 
 public List<Author> authorInBook (int bookId) throws Exception {	
	 Connection con = ConnectionUtil.getConnection();
	 try { 
		BookDAO bookdao = new BookDAO();		
		return (List<Author>)bookdao.readBookAuthor(bookId);
		}  	 
	 finally {
			con.close();
		} 
 }
 public List<Genre> genreInBook (int bookId) throws Exception {	 
	 Connection con = ConnectionUtil.getConnection();
	 try { 
		BookDAO bookdao = new BookDAO();		
		return (List<Genre>)bookdao.readBookGenre(bookId);
		}  	 
	 finally {
		 con.close(); 
				} 
 }
 public Book publisherInBook (int book) throws Exception {	 
	 Connection con = ConnectionUtil.getConnection();
	 try {
		 
		BookDAO bookdao = new BookDAO();	
		return bookdao.readOne(book);
		}  	 
	 finally {
		 con.close(); 
				} 
 }
 public List<Book> checkBookByName(String name) throws Exception {
	 Connection con = ConnectionUtil.getConnection();	
	try{
		if (name == null ||name == null
				||  name.length() == 0
				|| name.length() > 45) {
			throw new Exception(
					"Bookname cannot be null and Name should be 1-45 characters");
		}
		BookDAO pa = new BookDAO();
		return (List <Book>)pa.readbyName(name);
	 
		}  	   finally {
			con.close();
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
	
 public  void insertBook(Book book) throws  Exception {
	 Connection con = ConnectionUtil.getConnection();	
	 try {
			BookDAO b = new BookDAO();
	    //calling the method create to insert into table 
	      b.create(book);
	  	  // displayBook();
	      System.out.println("The Book" +book.getTitle()+ " is inserting in the table...Book");
	      con.commit();
	       }
		catch(Exception se){
	 	   //Handle errors for JDBC
			con.rollback();
	 	   se.printStackTrace();
	 	   }	  
	 	finally {
		con.close();
	 	}
	 }
 public void updateBook(Book book) throws Exception {	 
	 Connection con = ConnectionUtil.getConnection();		
	 try {
			BookDAO ba = new BookDAO();
			ba.updateAll(book); 
			con.commit();
			}
			catch(Exception se){
				con.rollback();
				//Handle errors for JDBC
		    se.printStackTrace();		    
			   }
			finally {
				con.close();
				}
		}
 public  void deleteBook(Book book) throws Exception {
	 Connection con = ConnectionUtil.getConnection();			
	 try{
			BookDAO pa = new BookDAO();
			pa.delete(book);
			con.commit();
			}
			catch(SQLException se){
				con.rollback();  
				//Handle errors for JDBC
				// conn.rollback();
			      se.printStackTrace();
			   }
	 		finally {
	 			con.close();
	 		}
		}
 
 public List<Book> readOneById(int bookId) throws Exception {
	 Connection con = ConnectionUtil.getConnection();			
	 try {
		 BookDAO b = new BookDAO();		 
	 return (List<Book>)b.readBook(bookId);	
	 }
	 finally {
		 con.close();
	 }
		
	}
 
}
 

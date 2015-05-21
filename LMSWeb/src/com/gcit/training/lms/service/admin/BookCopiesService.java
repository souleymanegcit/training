package com.gcit.training.lms.service.admin; 
 
import java.util.ArrayList;
import java.util.List; 

import com.gcit.training.lms.dao.BookCopiesDAO; 
import com.gcit.training.lms.dao.LibraryDAO;
import com.gcit.training.lms.entity.BookCopies;
import com.gcit.training.lms.entity.Library;

public class BookCopiesService { 
	 
	public BookCopiesService() {
		
	}
	public void displayBookCopies() throws Exception {		
		BookCopiesDAO p = new BookCopiesDAO();
		List<BookCopies> list = new ArrayList<>(); 		 
		list= p.readAll();
		System.out.println("The list of  Book copies");
		for(BookCopies ele: list) {
		System.out.println(" ");
		System.out.println("#######################");
		System.out.println("Book ID: " + ele.getBook().getBookId());
     	System.out.println("Book : " + ele.getBook().getTitle());
     	System.out.println("Author: " + ele.getBook().getAuthors());
     	System.out.println("Branch ID: " + ele.getLibrary().getBranchId());
    	System.out.println("Number of Copies: " + ele.getNoOfCopies());
    	System.out.println("Library Branch: " + ele.getLibrary().getBranchName());    
    	System.out.println("#######################");
		}
		}
	 public  List<BookCopies>  displayBookCopiesOne() throws Exception {		
		 try { 
		List<BookCopies> list = new ArrayList<BookCopies>();
		BookCopiesDAO p = new BookCopiesDAO();
		return list=p.readAll();	 
		 } catch(Exception e){
			 e.getStackTrace();
			 throw e;
		 }
		}
	 public void updateBookCopies(BookCopies bookcopies) throws Exception {	 
			try {
				BookCopiesDAO ba = new BookCopiesDAO();
				ba.updateAll(bookcopies); 
				}
				catch(Exception se){
			     //Handle errors for JDBC
			    se.printStackTrace();		    
				   }
				finally {				 
					}
			}
 
}

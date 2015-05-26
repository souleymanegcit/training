package com.gcit.training.lms.service.admin; 
 
 
import java.sql.Connection;
import java.util.List;   

import com.gcit.training.lms.dao.BookCopiesDAO;  
import com.gcit.training.lms.entity.BookCopies;
 

public class BookCopiesService { 
	 
	public BookCopiesService() {
		
	}
	public List<BookCopies> displayBookCopies() throws Exception {		
		Connection con = ConnectionUtil.getConnection();
		try{
		
		BookCopiesDAO p = new BookCopiesDAO(); 
		return (List<BookCopies>)p.readAll(); 
		}
		finally {
			con.close();
		}
		}
	
	 public  List<BookCopies>  displayBookCopiesByBranch(int branchId) throws Exception {		
		 Connection con = ConnectionUtil.getConnection();
			try{
			
		 
		BookCopiesDAO p = new BookCopiesDAO();
		return (List<BookCopies>)p.readBookCopiesByBranchId(branchId);	 
		 } 
			finally {
				con.close();
			}
		}
	 
	 public void updateBookCopies(BookCopies bookcopies) throws Exception {	 
		 Connection con = ConnectionUtil.getConnection();	
		 try {
				BookCopiesDAO ba = new BookCopiesDAO();
				ba.updateAll(bookcopies); 
				con.commit();
				}
				catch(Exception se){
			     con.rollback();
					//Handle errors for JDBC
			    se.printStackTrace();		    
				   }
				finally {	con.close();			 
					}
			}
 
}

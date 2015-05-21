package com.gcit.training.lms.service.admin;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.gcit.training.lms.dao.AuthorDAO;
import com.gcit.training.lms.entity.Author;


public class AuthorService {

	private List <Author> pubList;

	public AuthorService() {
		
	}
	public List<Author> displayAuthor() throws Exception {
		
		AuthorDAO p = new AuthorDAO();
		List<Author> list = new ArrayList<>(); 		 
		return list= p.readAll(); 
		}
 public void displayAuthorByList(List<Author> plist) throws Exception {		
		 
		List<Author> list = new ArrayList<>(); 
		list=plist;	
		if(!list.isEmpty()) {
		System.out.println("The list of  authors");
		for(Author ele: list) {
		System.out.println(" "); 
		System.out.println("#######################");
     	System.out.println("Author Id: " + ele.getAuthorId());
     	System.out.println("Author name: " + ele.getAuthorName());     		
    	System.out.println("#######################");
		}
		}
		else {
			System.out.println("This Author name doesn't exist!!! please check it out and try again");
		}
		}
	
	public  void insertAuthor(Author author) throws ClassNotFoundException {
		try {
			AuthorDAO p = new AuthorDAO();
	    //calling the method create to insert into table 
	      p.create(author);
	      System.out.println("Inserted records into the table...Author");
	      displayAuthor();
	       }
		catch(Exception se){
 	 	   //Handle errors for JDBC
 	 	   se.printStackTrace();
	 	   }	  
	       finally { 
			} 
	 	}
	
	public  int insertAuthorLate(Author author) throws ClassNotFoundException {
		int k=0;
		try {
			AuthorDAO p = new AuthorDAO();
	    //calling the method create to insert into table 
	     k= p.createLate(author);
	      System.out.println("Inserted records into the table...Author");
	      displayAuthor();
	       }
		catch(Exception se){
 	 	   //Handle errors for JDBC
 	 	   se.printStackTrace();
	 	   }	  
	       finally {
	    	   
			}
		return k;
	 	} 
	
	public boolean checkAuthorInList(List<Author> list, int authorId) throws Exception {
		boolean flag=false;
		List<Author> pubList = new ArrayList<Author>(list);		
		for(Author ele: pubList) {			 
	       if(ele.getAuthorId()==authorId) {
	     	 flag=true;
	     	 break;
			}
		}
		return flag;
	}
	
	public void updateAuthor(Author author) throws Exception {	 
		try {
			AuthorDAO pa = new AuthorDAO();
			 pa.update(author);
			}
			catch(Exception se){
		     //Handle errors for JDBC
		    se.printStackTrace();		    
			   }
			finally {				 
				}
		}
	
	public List<Author> checkAuthorByName(String authorname) throws Exception {
		  pubList = new ArrayList<>();		
		try{
			if (authorname == null ||authorname == null
					||  authorname.length() == 0
					|| authorname.length() > 45) {
				throw new Exception(
						"Author name cannot be null and Name should be 1-45 characters");
			}
			AuthorDAO pa = new AuthorDAO();
			return pubList= (List <Author>)pa.readbyName(authorname);
 		 
			}  	catch (Exception e) {
				throw e;
			}   finally {
				 
					}
		 
		}
	public Author returnAuthor(int authorId) throws Exception {
		  	
		try{
			if (authorId <=0) {
				throw new Exception(
						"Author ID cannot be 0 or under 0 ");
			}
			AuthorDAO pa = new AuthorDAO();
			return  pa.readOne(authorId);
		 
			}  	catch (Exception e) {
				throw e;
			}   finally {
				 
					}
		 
		}
	
	public  void deleteAuthor(Author author) throws Exception {
		try{
			AuthorDAO pa = new AuthorDAO();
			pa.delete(author);
			}
			catch(SQLException se){
			       
			      se.printStackTrace();
			   }
		}
	}

package com.gcit.training.lms.service.admin;

import java.util.ArrayList;
import java.util.List; 

import com.gcit.training.lms.dao.AuthorDAO;
import com.gcit.training.lms.dao.GenreDAO; 
import com.gcit.training.lms.entity.Author;
import com.gcit.training.lms.entity.Genre;

public class GenreService {

	public GenreService() {
		 
	}
	public  int insertGenreLate(Genre genre) throws ClassNotFoundException {
		int k=0;
		try {
			GenreDAO p = new GenreDAO();
	    //calling the method create to insert into table 
	     k= p.createLate(genre);
	      System.out.println("Inserted records into the table...Genre");
	       }
		catch(Exception se){
 	 	   //Handle errors for JDBC
 	 	   se.printStackTrace();
	 	   }	  
	       finally {
	    	   try {
				 
			} catch (Exception e) {				 
				e.printStackTrace();
			}
	       }
		return k;
	 	}
	public void displayGenreByList(List<Genre> plist) throws Exception {		
		 
		List<Genre> list = new ArrayList<>(); 
		list=plist;	
		if(!list.isEmpty()) {
		System.out.println("The list of  authors");
		for(Genre ele: list) {
		System.out.println(" "); 
		System.out.println("#######################");
     	System.out.println("Genre Id: " + ele.getGenreId());
     	System.out.println("Genre name: " + ele.getGenreName());     		
    	System.out.println("#######################");
		}
		}
		else {
			System.out.println("This Genre name doesn't exist!!! please check it out and try again");
		}
		}
	public boolean checkGenreInList(List<Genre> list, int genreId) throws Exception {
		boolean flag=false;
		List<Genre> pubList = new ArrayList<Genre>(list);		
		for(Genre ele: pubList) {			 
	       if(ele.getGenreId()==genreId) {
	     	 flag=true;
	     	 break;
			}
		}
		return flag;
	}
	public void updateGenre(Genre genre) throws Exception {	 
		try {
			GenreDAO pa = new GenreDAO();
			 pa.update(genre);
			}
			catch(Exception se){
		     //Handle errors for JDBC
		    se.printStackTrace();		    
			   }
			finally {				 
				}
		}
	@SuppressWarnings("unchecked")
	public List<Genre> checkAuthorByName(String name) throws Exception {
		List<Genre>  pubList = new ArrayList<>();		
		try{
			if ( name == null || name == null
					||  name.length() == 0
					||  name.length() > 45) {
				throw new Exception(
						"Author name cannot be null and Name should be 1-45 characters");
			}
			GenreDAO pa = new GenreDAO();
			return pubList= (List <Genre>)pa.readbyName( name);
		 
			}  	catch (Exception e) {
				throw e;
			}   finally {
				 
					}
		 
		}
	
	public List<Genre> listGenre() throws Exception {
		List<Genre>  pubList = new ArrayList<>();		
		 try{
			GenreDAO pa = new GenreDAO();
			return pubList= (List <Genre>)pa.readAll();
		 
			}  	catch (Exception e) {
				throw e;
			}   finally {
				 
					}
		 
		}
}

package com.gcit.training.lms.service.admin;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.training.lms.dao.PublisherDAO;
import com.gcit.training.lms.entity.Publisher;

public class PublisherService {
 
	private List <Publisher> pubList;

	public PublisherService() {
		 
	}
	
	public List<Publisher> displayPublisher() throws Exception {
		
		PublisherDAO p = new PublisherDAO();
		List<Publisher> list = new ArrayList<>(); 		 
		return list= p.readAll();
		 
		}
public Publisher returnPublisher(int publisherId) throws Exception { 
		PublisherDAO p = new PublisherDAO(); 
		return  p.readOne(publisherId);
		 
		}


	public void displayPublisherByList(List<Publisher> plist) throws Exception {		
		 
		List<Publisher> list = new ArrayList<>(); 
		list=plist;	
		if(!list.isEmpty()) {
		System.out.println("The list of  publishers");
		for(Publisher ele: list) {
		System.out.println(" ");
		System.out.println("#######################");
     	System.out.println("Publisher Id: " + ele.getPublisherId());
     	System.out.println("Publisher: " + ele.getPublisherName());
     	System.out.println("Address: " + ele.getPublisherAddress());	
     	System.out.println("Phone: " + ele.getPublisherPhone());	
    	System.out.println("#######################");
		}
		}
		else {
			System.out.println("This Plublisher name doesn't exist!!! please check it out and try again");
		}
		}
	
	public  void insertPublisher(Publisher publisher) throws ClassNotFoundException {
		try {
	      PublisherDAO p = new PublisherDAO();
	    //calling the method create to insert into table 
	      p.create(publisher);
	      System.out.println("Inserted records into the table...Publisher");
	       }
		catch(Exception se){
 	 	   //Handle errors for JDBC
 	 	   se.printStackTrace();
	 	   }	  
	       finally {
	    	   try {
				displayPublisher();
			} catch (Exception e) {				 
				e.printStackTrace();
			}
	       }
	 	} 
	public boolean checkPublisherInList	(List<Publisher> list, int publisherId) throws Exception {
		boolean flag=false;
		List<Publisher> pubList = new ArrayList<Publisher>(list);		
		for(Publisher ele: pubList) {			 
	       if(ele.getPublisherId()==publisherId) {
	     	 flag=true;
	     	 break;
			}
		}
		return flag;
	}
	
	public void updatePublisher(Publisher publisher) throws Exception {	 
		try {
			 PublisherDAO pa = new PublisherDAO();
			 pa.updateAll(publisher);
			}
			catch(Exception se){
		     //Handle errors for JDBC
		    se.printStackTrace();		    
			   }
			finally {				 
				}
		}
	
	public List<Publisher> checkPublisherByName(String publishername) throws Exception {
		pubList = new ArrayList<>();
		
		try{
			if (publishername == null || publishername== null
					|| publishername.length() == 0
					|| publishername.length() > 45) {
				throw new Exception(
						"Publisher cannot be null and Name should be 1-45 characters");
			}
			PublisherDAO pa = new PublisherDAO();
			return pubList= (List <Publisher>)pa.readbyName(publishername);
 		 
			}  	catch (Exception e) {
				throw e;
			} finally {
				 
	}
		//return pubList;
		}
	
	public  void deletePublisher(Publisher publisher) throws Exception {
		try{
			PublisherDAO pa = new PublisherDAO();
			pa.delete(publisher);
			}
			catch(SQLException se){ 
			      se.printStackTrace();
			   }
		}
	}

package com.gcit.training.lms.service.admin;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.training.lms.dao.PublisherDAO;
import com.gcit.training.lms.entity.Publisher;

public class PublisherService { 

	public PublisherService() {
		 
	}
	
	public List<Publisher> displayPublisher() throws Exception {
		Connection con = ConnectionUtil.getConnection();
		try {
		PublisherDAO p = new PublisherDAO(); 
		return (List<Publisher>) p.readAll();
		}
		finally {
			con.close();
		}
		}
public Publisher returnPublisher(int publisherId) throws Exception { 
	Connection con = ConnectionUtil.getConnection();
	try {
		PublisherDAO p = new PublisherDAO(); 
		return  p.readOne(publisherId);
		}
	finally {
		con.close();
	}
		}
//
//
//	public void displayPublisherByList(List<Publisher> plist) throws Exception {		
//		 
//		List<Publisher> list = new ArrayList<>(); 
//		list=plist;	
//		if(!list.isEmpty()) {
//		System.out.println("The list of  publishers");
//		for(Publisher ele: list) {
//		System.out.println(" ");
//		System.out.println("#######################");
//     	System.out.println("Publisher Id: " + ele.getPublisherId());
//     	System.out.println("Publisher: " + ele.getPublisherName());
//     	System.out.println("Address: " + ele.getPublisherAddress());	
//     	System.out.println("Phone: " + ele.getPublisherPhone());	
//    	System.out.println("#######################");
//		}
//		}
//		else {
//			System.out.println("This Plublisher name doesn't exist!!! please check it out and try again");
//		}
//		}
//	
	public  void insertPublisher(Publisher publisher) throws  Exception {
		Connection con = ConnectionUtil.getConnection();
		try {
	      PublisherDAO p = new PublisherDAO();
	    //calling the method create to insert into table 
	      p.create(publisher);
	      System.out.println("Inserted records into the table...Publisher");
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
//	public boolean checkPublisherInList	(List<Publisher> list, int publisherId) throws Exception {
//		boolean flag=false;
//		List<Publisher> pubList = new ArrayList<Publisher>(list);		
//		for(Publisher ele: pubList) {			 
//	       if(ele.getPublisherId()==publisherId) {
//	     	 flag=true;
//	     	 break;
//			}
//		}
//		return flag;
//	}
//	
	public void updatePublisher(Publisher publisher) throws Exception {	 
		Connection con = ConnectionUtil.getConnection();
		try {
			 PublisherDAO pa = new PublisherDAO();
			 pa.updateAll(publisher);
			 con.commit();
			}
			catch(Exception se){
		     
				con.rollback();//Handle errors for JDBC
		    se.printStackTrace();		    
			   }
			finally {
				con.close();
				}
		}
	
	public List<Publisher> checkPublisherByName(String publishername) throws Exception { 
		Connection con = ConnectionUtil.getConnection();
		try{
			if (publishername == null || publishername== null
					|| publishername.length() == 0
					|| publishername.length() > 45) {
				throw new Exception(
						"Publisher cannot be null and Name should be 1-45 characters");
			}
			PublisherDAO pa = new PublisherDAO();
			return  (List <Publisher>)pa.readbyName(publishername);
		 
 		 
			}  	catch (Exception e) {
				throw e;
			} finally {
				 con.close();
				}
		//return pubList;
		}
	
	public  void deletePublisher(Publisher publisher) throws Exception {
		Connection con = ConnectionUtil.getConnection();
		try{
			PublisherDAO pa = new PublisherDAO();
			pa.delete(publisher);
			con.commit();
			}
			catch(SQLException se){ 
			      se.printStackTrace();
			   }
		finally {
			con.close();
		}
		}
	}

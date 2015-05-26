package com.gcit.training.lms.service.admin;

import java.sql.Connection;
import java.util.List;

import com.gcit.training.lms.dao.BorrowerDAO;  
import com.gcit.training.lms.entity.Borrower;
 
 

public class BorrowerService {

	public BorrowerService() {
		 
	}
	
	public List<Borrower> displayLibrary() throws Exception {
		Connection con = ConnectionUtil.getConnection();
		try {
			BorrowerDAO p = new BorrowerDAO();
		return (List<Borrower>)p.readAll(); 
		} 
		finally{
			con.close();
		}
	}
	public Borrower checkBorrower(int cardNo) throws Exception { 
		Connection con = ConnectionUtil.getConnection();
		try {
			BorrowerDAO p = new BorrowerDAO();
			return p.readOne(cardNo);
		}
		finally { 
			con.close();
		}
	}

}

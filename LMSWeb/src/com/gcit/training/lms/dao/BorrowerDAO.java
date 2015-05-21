package com.gcit.training.lms.dao;

import java.sql.ResultSet;
import java.sql.SQLException; 
import java.util.List;

import com.gcit.training.lms.entity.Borrower;
 
	public class BorrowerDAO extends BaseDAO<List<Borrower>> {	
		
		public BorrowerDAO() {
			 
		} 
		public void create(Borrower borrower) throws Exception {
			save("insert into tbl_borrower (name, address, phone) values (?,?,?)",
					new Object[] { borrower.getName(),
							borrower.getAddress(),
							borrower.getPhone() });
		} 
		 
		@SuppressWarnings("unchecked")
		public List<Borrower> readAll() throws Exception {
			return (List<Borrower>) read("select * from tbl_borrower", null);
		}
		
		 
		@SuppressWarnings("unchecked")
		public boolean readOne(int cardNo) throws Exception {
			List<Borrower> pubList = (List<Borrower>) read(
					"select * from tbl_borrower where carNo = ?",
					new Object[] { cardNo });

			if (pubList != null && pubList.size() > 0) {
				return true;
			} else {
				return false;
			}
		}
		@Override
		protected List<?> extractData(ResultSet rs) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		protected List<?> veryExtractData(ResultSet rs) throws SQLException,
				Exception {
			// TODO Auto-generated method stub
			return null;
		} 
		 
		
		}

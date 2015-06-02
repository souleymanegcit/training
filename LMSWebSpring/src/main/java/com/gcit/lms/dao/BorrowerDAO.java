package com.gcit.lms.dao; 

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException; 
import java.util.ArrayList;
import java.util.List; 

import org.springframework.jdbc.core.ResultSetExtractor; 
import com.gcit.lms.domain.Borrower;;
 
	public class BorrowerDAO extends BaseDAO<List<Borrower>> implements Serializable, ResultSetExtractor<List<Borrower>> {	
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 6490591905190907454L;

		public BorrowerDAO() {
			 
		} 
		public void create(Borrower borrower) throws Exception {
			template.update("insert into tbl_borrower (name, address, phone) values (?,?,?)",
					new Object[] { borrower.getBorrowerName(),
							borrower.getBorrowerAddress(),
							borrower.getBorrowerPhone() });
		} 
		public void updateBorrower(Borrower borrower) throws SQLException {
			template.update("update tbl_borrower set name =?, address =?, phone =? where cardNo = ?",
					new Object[] { borrower.getBorrowerName(), borrower.getBorrowerAddress(),
					borrower.getBorrowerPhone(), borrower.getCardNo()  });
		}

		public void removeBorrower(Borrower borrower) throws SQLException {
			template.update("delete from tbl_borrower where CardNo=?",
					new Object[] { borrower.getCardNo() });
		}
 
		public List<Borrower> readAll() throws Exception {
			return (List<Borrower>) template.query("select * from tbl_borrower", this);
		}
 
		public Borrower readOne(int cardNo) throws Exception {
			List<Borrower> pubList = (List<Borrower>)  template.query("select * from tbl_borrower where carNo = ?",
					new Object[] { cardNo }, this);

			if (pubList != null && pubList.size() > 0) {
				return pubList.get(0);
			} else {
				return null;
			}
		}
		 
		@Override
		public List<Borrower> extractData(ResultSet rs) throws SQLException {
			List<Borrower> list = new ArrayList<Borrower>();
			while (rs.next()) {
				Borrower a = new Borrower();
				a.setCardNo(rs.getInt("cardNo")); 
				a.setBorrowerName(rs.getString("name"));
				a.setBorrowerAddress(rs.getString("address"));
				a.setBorrowerPhone(rs.getString("phone"));

				list.add(a);
			}
			return list;
		}
		 
		
		}

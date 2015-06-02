package com.gcit.lms.dao;
 

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List; 

import org.springframework.jdbc.core.ResultSetExtractor; 

import com.gcit.lms.domain.*;


public class LibraryDAO extends BaseDAO<List<LibraryBranch>> implements Serializable, ResultSetExtractor<List<LibraryBranch>>  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2339718288004823089L;

	public LibraryDAO() {
		 
	} 
	public void addLibrary(LibraryBranch library) throws SQLException {
		template.update("insert into tbl_library_branch (branchName) values (?)",
				new Object[] { library.getBranchName() });
	}
 
	public void update(LibraryBranch library) throws Exception {
		template.update("update tbl_library_branch set branchName = ?, branchAddress = ? where branchId = ?",
				new Object[] { library.getBranchName(), library.getBranchAddress(), library.getBranchId() });
	}

	public void delete(LibraryBranch library) throws Exception {
		template.update("delete from tbl_library_branch where branchId = ?",
				new Object[] { library.getBranchId() });
	} 
 
	public List<LibraryBranch> readAll() throws Exception {
		return (List<LibraryBranch>) template.query("select * from tbl_library_branch", this);
	} 
  
	public LibraryBranch readOne(int branchId) throws Exception {
		List<LibraryBranch> list = (List<LibraryBranch>) template.query(
				"select * from tbl_library_branch where branchId = ?",
				new Object[] { branchId }, this);
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		} 
	} 
	 
	public List<LibraryBranch> readbyName(String branchname) throws Exception {
		List<LibraryBranch> pubList = new ArrayList<>();
		pubList =  (List<LibraryBranch>) template.query("select * from tbl_library_branch p"
				+ " where upper(p.authorName)= ?", new Object[] { "%" + branchname + "%" }, this);
		return pubList;
	}
	
	@Override
	public List<LibraryBranch> extractData(ResultSet rs) throws SQLException {
		List<LibraryBranch> list = new ArrayList<LibraryBranch>();		
		while (rs.next()) {
			LibraryBranch a = new LibraryBranch();
			a.setBranchId(rs.getInt("branchId"));
			a.setBranchName(rs.getString("branchName"));
			a.setBranchName(rs.getString("branchAddress")); 
			list.add(a);
		}
		return list;
	} 
 
	 
}

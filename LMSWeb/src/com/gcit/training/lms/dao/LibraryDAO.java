package com.gcit.training.lms.dao; 
 
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List; 

import com.gcit.training.lms.entity.Library;

public class LibraryDAO extends BaseDAO<List<Library>> {

	public LibraryDAO() {
		 
	} 
	public void create(Library library) throws Exception {
		save("insert into tbl_library_branch (branchName) values (?)",
				new Object[] { library.getBranchName() });
	}
	public int createLate(Library library) throws Exception {		
		int AuthorId = saveWithId("insert into tbl_library_branch (branchName) values (?)",
				new Object[] { library.getBranchName() });
		 return AuthorId;
	}

	public void update(Library library) throws Exception {
		save("update tbl_library_branch set branchName = ?, branchAddress = ? where branchId = ?",
				new Object[] { library.getBranchName(), library.getBranchAdress(), library.getBranchId() });
	}

	public void delete(Library library) throws Exception {
		save("delete from tbl_library_branch where branchId = ?",
				new Object[] { library.getBranchId() });
	} 
	@SuppressWarnings("unchecked")
	public List<Library> readAll() throws Exception {
		return (List<Library>) read("select * from tbl_library_branch", null);
	} 
	@SuppressWarnings("unchecked")
	public List<Object> readLibraryBooki() throws Exception {
		return (List<Object>) read(
				"select b.title, a.authorName"
			+"from tbl_book b join tbl_book_copies bc on b.bookId=bc.bookId"
			+"join tbl_book_authors ba on b.bookId=ba.bookId"
			+"join tbl_author a on a.authorId=ba.authorId"
			+"join tbl_library_branch lb on lb.branchId=bc.branchId", null); 
	}
	
	@SuppressWarnings("unchecked")
	public List<Library> readOne(int branchId) throws Exception {
		List<Library> list = (List<Library>) read(
				"select * from tbl_library_branch where branchId = ?",
				new Object[] { branchId });
		return  list; 
	} 
	@SuppressWarnings("unchecked")
	public List<Library> readbyName(String branchname) throws Exception {
		List<Library> pubList = new ArrayList<>();
		pubList =  (List<Library>) read("select * from tbl_library_branch p"
				+ " where upper(p.authorName)= ?", new Object[] { "%" + branchname + "%" });
		return pubList;
	} 
	@Override
	protected List<Library> extractData(ResultSet rs) throws SQLException {
		List<Library> list = new ArrayList<Library>();
		while (rs.next()) {
			Library a = new Library();
			a.setBranchId(rs.getInt("branchId"));
			a.setBranchName(rs.getString("branchName"));
			a.setBranchAdress(rs.getString("branchAddress")); 
			list.add(a);
		}
		return list;
	} 
//	@Override
//	protected List<Object> extractDataBooki(ResultSet rs) throws SQLException {
//		List<Object> list = new ArrayList<Object>(); int i=0; int j=1;
//		Array a ;		 
//		while (rs.next()) {
//			a=(Array) rs.getObject(j);			 
//			list.add(i, a);
//			i++;j++;
//		}
//		return list;
//	}
	@Override
	protected List<?> veryExtractData(ResultSet rs) throws SQLException,
			Exception {
		// TODO Auto-generated method stub
		return null;
	}

}

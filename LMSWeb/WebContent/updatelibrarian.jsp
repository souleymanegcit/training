<%@page import="com.gcit.training.lms.entity.*"%>
<%@page import="com.gcit.training.lms.service.admin.*"%>

<% 
String libraryIdupdate = request.getParameter("libraryIdupdate");
Library b = new LibraryService().displayLibraryList(Integer.parseInt(libraryIdupdate)); 
%> 
<div>
	<div class="modal-header"> 
			<button type="button" class="close" data-dismiss="modul">&times;</button>
			<h3 class="modul-title">Library Updating</h3>
	</div>
<div class="modal-body">  
	<form action="updatelibrarian" method="post">
		Enter Library Branch:      <input type="text" name="branchName" value="<%=b.getBranchName()%>"/><br/>	
		Enter branch Address:      <input type="text" name="branchAddress" value="<%=b.getBranchAdress()%>"/><br/>
		<input type="hidden" value="<%=b.getBranchId()%>" name="branchId" />
		<input type="submit" value= "Update" class="btn btn-sucess"/> 
		<a href=""  class="btn btn-danger" data-dismiss="modul">Cancel</a>
	</form>
</div>
</div>
 
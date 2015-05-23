<%@page import="com.gcit.training.lms.entity.Author"%>
<%@page import="com.gcit.training.lms.service.admin.AuthorService"%> 
<%
String authorIdupdate = request.getParameter("authorIdupdate");
Author a = new AuthorService().returnAuthor(Integer.parseInt(authorIdupdate)); 
%> 
<div>
	<div class="modal-header"> 
			<button type="button" class="close" data-dismiss="modul">&times;</button>
			<h3 class="modul-title">Author Updating</h3>
	</div>
<div class="modal-body"> 

	<form action="updateAuthor" method="post">
		Enter Author Name: <input type="text" name="authorName" value="<%=a.getAuthorName()%>"/>
		<input type="hidden" value="<%=a.getAuthorId()%>" name="authorId" />
		<input type="submit" value= "Update" class="btn btn-sucess"/> 
		<a href=""  class="btn btn-danger" data-dismiss="modul">Cancel</a>
	</form>
</div>
</div>
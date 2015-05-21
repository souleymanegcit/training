<%@include file="index.jsp"%>
<%@page import="com.gcit.training.lms.entity.Author"%>
<%@page import="com.gcit.training.lms.service.admin.AuthorService"%>
<div style="margin-top:80px;margin-left:10px;">
		<center> <h4>Update Author</h4> </center>
<% 
String authorIdupdate = request.getParameter("authorIdupdate");
Author a = new AuthorService().returnAuthor(Integer.parseInt(authorIdupdate)); 
%> 
<div class="modal-body"> 

	<form action="updateAuthor" method="post">
		Enter Author Name: <input type="text" name="authorName" value="<%=a.getAuthorName()%>"/>
		<input type="hidden" value="<%=a.getAuthorId()%>" name="authorId" />
		<input type="submit" value= "Update"/>
	</form>
</div>
</div>
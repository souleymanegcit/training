<%@page import="com.gcit.training.lms.entity.Author"%>
<%@page import="com.gcit.training.lms.service.admin.AuthorService"%>
<%@page import="java.util.List"%> 
<%
List<Author> authors = new AuthorService().displayAuthor(); 
%>	 
<%@include file="index.jsp"%>
<script>
	function deleteAuthor(id) {
		//document.location.href = "deleteAuthor?authorId="+id;		
		document.getElementById("authorId").value = id;
		document.deleteFrm.submit(); 
	} 
</script>  
<script>
	function updateAuthor(id) {
		//document.location.href = "deleteAuthor?authorId="+id;
		document.getElementById("authorId").value = id;
		document.deleteFrm.submit();
	} 
</script> 
<script src="./scripts/tableJS.js" type="text/javascript"></script> 
<script src="./scripts/tableCSS.js" type="text/javascript"></script>
 
<div style="margin-top:80px;margin-left:10px;">
<section>
<table  class="table table-hover table-bordered" >
	<tr>
		<td>Author Id</td>
		<td>Author Name</td>
		<td>Edit</td>			 
		<td>Delete</td>
	</tr>
	<%for(Author a : authors) { %>	
	<tr>
		<td><%=a.getAuthorId()%></td>
		<td><%=a.getAuthorName()%></td>  
		<td><button class=" open_add btn btn-success" href="updateAuthor.jsp?authorIdupdate=<%=a.getAuthorId()%>"
				data-target="#basicModal" data-toggle="modal">Edit</button></td>
		<td><button class="btn btn-danger" onclick="javascript:deleteAuthor(<%=a.getAuthorId()%>);">Delete</button></td>
	</tr>
	<% } %>
	<tr>
		<td>Author Id</td>
		<td>Author Name</td>
		<td>Edit</td>
		<td>Delete</td>
	</tr>
</table> 
<section>
<div class="modal fade" id="basicModal">
    <div class="modal-dialog">
        <div class="modal-content">   
    </div>
  </div>
</div>
</section>
<form action="deleteAuthor" method="post" name="deleteFrm">
	<input type="hidden" name="authorId" id="authorId"/>
</form>
 
</section>
</div>
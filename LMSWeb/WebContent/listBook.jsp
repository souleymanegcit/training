<%@page import="com.gcit.training.lms.entity.Author"%>
<%@page import="com.gcit.training.lms.entity.*"%>
<%@page import="com.gcit.training.lms.service.admin.BookService"%>
<%@page import="com.gcit.training.lms.service.admin.*"%>
<%@page import="java.util.List"%>
 
<% 
List<Book> book = new BookService().displayBookList();  
%>
<%@include file="index.jsp"%>
<script>
	function deleteBook(id) { 
		
		document.getElementById("bookId").value = id;
		document.deleteFrm.submit();
	} 
</script>
 
<div style="margin-top:80px;margin-left:10px;">
<section>
<table id="mybook" class="table table-hover table-bordered">
	<tr>
		<td>Book Id</td>
		<td>Book Tile</td>
		<td>Publisher </td>
		<td>Book Authors</td>
		<td>Genre</td>
		<td>Edit</td>
		<td>Delete</td>
	</tr>
	<%for(Book elem : book ) { %> 
		<tr>  
		<td><%=elem.getBookId()%></td>			
		<td><%=elem.getTitle()%></td>
		<td><% if(elem.getPublisher() != null) { %>
		<%=elem.getPublisher().getPublisherName()%>
		<% } %>
		</td>
		<td><% if(elem.getAuthors() != null && elem.getAuthors().size() > 0) {
		for(Author auth : elem.getAuthors()) { %>
			<%=auth.getAuthorName() %>,
		<% }
		}%> 
		</td>		
		 
		<td><%=elem.getGenres()%></td>
	   
		<td><button class=" open_add btn btn-success" href="updateBook.jsp?bookIdupdate=<%=elem.getBookId()%>"
				data-target="#basicModal" data-toggle="modal">Edit</button></td>
		<td><button class="btn btn-danger" onclick="javascript:deleteBook
		(<%=elem.getBookId()%>);"><span class="glyphicon glyphicon-trash"></span> Delete</button></td>
	 </tr>
	
	<% } %>
	<tr>
		<td>Book Id</td>
		<td>Book Tile</td>
		<td>Publisher </td>
		<td>Book Authors</td>
		<td>Genre</td>
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
<form action="deleteBook" method="post" name="deleteFrm">
	<input type="hidden" name="bookId" id="bookId"/>
</form>

</section>
</div>
<!-- ------------------------------------------------Scripting jquery code right here------------------------------------------------------ -->
<script src="./scripts/jquery.js"></script>
<script src="./scripts/jquery.dataTables.js"></script>
<script src="./scripts/jquery.dataTables.css"></script>
<script>
$(document).ready(function() {
    var table = $('#mybook').DataTable();
 
    $('button').click( function() {
        var data = table.$('input, select').serialize();
        alert(
            "The following data would have been submitted to the server: \n\n"+
            data.substr( 0, 120 )+'...'
        );
        return false;
    } );
} );
</script> 
<!-- ------------------------------------------------Scripting code right here------------------------------------------------------ -->

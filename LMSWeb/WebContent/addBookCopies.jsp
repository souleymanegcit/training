<%@page import="com.gcit.training.lms.service.admin.*"%>
<%@page import="com.gcit.training.lms.entity.*"%>
 <%@page import="java.util.List"%>
<%@include file="index.jsp"%>
<div style="margin-top:80px;margin-left:10px;">
<%  
List<BookCopies> list = new BookCopiesService().displayBookCopies(); 
%> 

<div style="margin-top:80px;margin-left:10px;"> 
<label> <p>
		 <h3> Updating quantity of books </h3> 
		</p>
</label>
<div widht="80%"> 
<section>
<table id="mytab" class="table table-hover table-bordered"> 
	<thead>
		<tr>
	    
		<th data-field="bookid>Book Id" data-align="right" data-sortable="true">
		Book ID<span class="glyphicon glyphicon-sort"></span></th>
		<th data-field="branchid" data-align="right" data-sortable="true">
		Branch ID <span class="glyphicon glyphicon-sort"></span></th>
		<th data-field="title" data-align="center" data-sortable="true">
		Book Title <span class="glyphicon glyphicon-sort"></span></th>
		<th data-field="authorid" data-align="center" data-sortable="true">
		Authors <span class="glyphicon glyphicon-sort"></span></th> 
		<th data-field="qnt" data-sortable="true">
		Quantity of book Copies </th> 
		<th data-field="edit">Add Copy</th> 
	  </tr> 
  </thead>  
	<%for(BookCopies elem : list) {%>	
	<tr>  
		<td>
		<%if(elem.getBook().getBookId() != 0) {%>
		<%=elem.getBook().getBookId()%>
		<%} %>
		</td>
		<td><%=elem.getLibrary().getBranchId()%></td>
		<td><%=elem.getBook().getTitle()%></td>
		<td><% if(elem.getBook().getAuthors() != null 
		&& elem.getBook().getAuthors().size() > 0) {
		for(Author auth : elem.getBook().getAuthors()) { %>
			<%=auth.getAuthorName() %>,
		<% }
		}%> 
		<script>
	function updateBookCopies(bookid,branchid,noOfCopies) { 
		var x = document.getElementById("nocp").value;
		document.getElementById("bookId").value =bookid;
		document.getElementById("branchId").value =branchid;
		document.getElementById("noOfCopies").value =x; 
		document.updateFrm.submit();
	} 
</script>  
		</td>
		<td><input id="nocp" name="num" type="text" value="<%=elem.getNoOfCopies()%>"></td> 
		<td><button class=" open_add btn btn-success" onclick="javascript:updateBookCopies
		(<%=elem.getBook().getBookId()%>,<%=elem.getLibrary().getBranchId()%>,<%=elem.getNoOfCopies()%>);"> 
		<span class="glyphicon glyphicon-edit"></span>Update</button>
		</td>  
	</tr> 
	<%}%>
	<tfoot>
		  <tr> 
			<th data-field="bookid>Book Id" data-align="right" data-sortable="true">Book ID</th>
			<th data-field="branchid" data-align="right" data-sortable="true">Branch ID</th>
			<th data-field="title" data-align="center" data-sortable="true">Book Title</th>
			<th data-field="authorid" data-align="center" data-sortable="true">Authors</th> 
			<th data-field="qnt" data-sortable="true">Quantity of book Copies</th>		 
		   <th data-field="edit">Add Copy</th> 
	     </tr> 
	</tfoot>
</table>
</section>
<form action="addBookCopies" method="post" name="updateFrm">
	<input type="hidden" name="bookId" id="bookId"/>
	<input type="hidden" name="branchId" id="branchId" />
	<input type="hidden" name="noOfCopies" id="noOfCopies"/>
</form>
</div> 
</div> 
</div>
<!-- this is a comment -->
<script>
$(document).ready(function() {
    $('#mytab').dataTable();
} );
</script>
<script>
$(document).ready(function() {
    var table = $('#mybook').DataTable();
 
    $('button').click( function() {
        var data = table.$('input, select').serialize();
        
    } );
} );
</script> 
<!-- ------------------------------------------------Scripting jquery code right here------------------------------------------------------ -->
<script src="./scripts/jquery.js"></script>
<script src="./scripts/jquery.dataTables.js"></script>
<script src="./scripts/jquery.dataTables.css"></script>

<!-- ------------------------------------------------Scripting code right here------------------------------------------------------ -->


<%@page import="com.gcit.training.lms.service.admin.*"%>
<%@page import="com.gcit.training.lms.entity.*"%>
 <%@page import="java.util.List"%>
<%@include file="index.jsp"%>
<div style="margin-top:80px;margin-left:10px;">
<% Library library = new Library();
List<Library> lib = new LibraryService().displayLibrary(); 
%> 
<script>
	function deleteLibrarian(id) { 
		document.getElementById("branchId").value = id;
		document.deleteFrm.submit();
	} 
</script> 
<div style="margin-top:80px;margin-left:10px;">
<section>
<table class="table table-hover table-bordered">
	<tr>
		<td>Branch Id</td>
		<td>Branch Name</td>
		<td>Branch Address</td> 
		<td>Edit</td>
		<td>Delete</td>
	</tr>
	<% 
	for(Library elem : lib) { 
	%>	
	<tr>  
		<td><%=elem.getBranchId()%></td>
		<td><%=elem.getBranchName()%></td>
		<td><%=elem.getBranchAdress()%></td> 
			
		<td><button class=" open_add btn btn-success" href="updatelibrarian.jsp?libraryIdupdate=<%=elem.getBranchId()%>"
				data-target="#basicModal" data-toggle="modal"><span class="glyphicon glyphicon-check"></span>Select</button></td>
		 
	</tr> 
	<% } %>
	<tr>
		<td>Branch Id</td>
		<td>Branch Name</td>
		<td>Branch Address</td> 
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
<form action="deletePublisher" method="post" name="deleteFrm">
	<input type="hidden" name="branchId" id="branchId"/>
</form> 
</section>
</div> 
</div>
<!-- this is a comment -->

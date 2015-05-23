<%@page import="com.gcit.training.lms.entity.Author"%>
<%@page import="com.gcit.training.lms.entity.Publisher"%>
<%@page import="com.gcit.training.lms.service.admin.PublisherService"%>
<%@page import="java.util.List"%>
 
<% 
List<Publisher> publishers = new PublisherService().displayPublisher(); 
%>
<%@include file="index.jsp"%>
<script>
	function deletePublisher(id) {
		 
		document.getElementById("publisherId").value = id;
		document.deleteFrm.submit();
	} 
</script> 
<div style="margin-top:80px;margin-left:10px;">
<section>
<table class="table table-hover table-bordered">
	<tr>
		<td>Publisher Id</td>
		<td>Publisher Name</td>
		<td>Publisher Address</td>
		<td>Publisher Phone</td>
		<td>Edit</td>
		<td>Delete</td>
	</tr>
	<%for(Publisher elem : publishers ) { %>	
	<tr>  
		<td><%=elem.getPublisherId()%></td>
		<td><%=elem.getPublisherName()%></td>
		<td><%=elem.getPublisherAddress()%></td>
		<td><%=elem.getPublisherPhone()%></td>
			
		<td><button class=" open_add btn btn-success" href="updatePublisher.jsp?publisherIdupdate=<%=elem.getPublisherId()%>"
				data-target="#basicModal" data-toggle="modal">Edit</button></td>
		<td><button class="btn btn-danger" onclick="javascript:deletePublisher(<%=elem.getPublisherId()%>);">Delete</button></td>
	</tr> 
	<% } %>
	<tr>
		<td>Publisher Id</td>
		<td>Publisher Name</td>
		<td>Publisher Address</td>
		<td>Publisher Phone</td>
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
	<input type="hidden" name="publisherId" id="publisherId"/>
</form> 
</section>
</div>
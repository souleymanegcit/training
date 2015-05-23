<%@page import="com.gcit.training.lms.entity.Publisher"%>
<%@page import="com.gcit.training.lms.service.admin.PublisherService"%>

<% 
String publisherIdupdate = request.getParameter("publisherIdupdate");
Publisher a = new PublisherService().returnPublisher(Integer.parseInt(publisherIdupdate)); 
%> 
<div>
	<div class="modal-header"> 
			<button type="button" class="close" data-dismiss="modul">&times;</button>
			<h3 class="modul-title">Publisher Updating</h3>
	</div>
<div class="modal-body">  
	<form action="updatePublisher" method="post">
		Enter Publisher's Name:      <input type="text" name="publisherName" value="<%=a.getPublisherName()%>"/><br/>	
		Enter Publisher's Addrs:  <input type="text" name="publisherAddress" value="<%=a.getPublisherAddress()%>"/><br/>		
		Enter Publisher's Phone:     <input type="text" name="publisherPhone" value="<%=a.getPublisherPhone()%>"/><br/>	 
		<input type="hidden" value="<%=a.getPublisherId()%>" name="publisherId" />
		<input type="submit" value= "Update" class="btn btn-sucess"/> 
		<a href=""  class="btn btn-danger" data-dismiss="modul">Cancel</a>
	</form>
</div>
</div>
</div> 
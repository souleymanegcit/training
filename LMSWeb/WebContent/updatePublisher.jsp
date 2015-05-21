<%@include file="index.jsp"%>
<%@page import="com.gcit.training.lms.entity.Publisher"%>
<%@page import="com.gcit.training.lms.service.admin.PublisherService"%>
<div style="margin-top:80px;margin-left:10px;">
		<center> <h4>Update Publisher</h4> </center>
<% 
String publisherIdupdate = request.getParameter("publisherIdupdate");
Publisher a = new PublisherService().returnPublisher(Integer.parseInt(publisherIdupdate)); 
%> 
<div class="modal-body"> 

	<form action="updatePublisher" method="post">
		Enter Publisher's Name:      <input type="text" name="publisherName" value="<%=a.getPublisherName()%>"/><br/>	
		Enter Publisher's Addrs:  <input type="text" name="publisherAddress" value="<%=a.getPublisherAddress()%>"/><br/>		
		Enter Publisher's Phone:     <input type="text" name="publisherPhone" value="<%=a.getPublisherPhone()%>"/><br/>	 
		<input type="hidden" value="<%=a.getPublisherId()%>" name="publisherId" />
		<input type="submit" value= "Update"/>
	</form>
</div>
</div> 
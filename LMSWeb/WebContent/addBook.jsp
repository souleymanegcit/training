<%@page import="com.gcit.training.lms.entity.Author"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.training.lms.service.admin.AuthorService"%>
<%@page import="com.gcit.training.lms.service.admin.GenreService"%>
<%@page import="com.gcit.training.lms.service.admin.PublisherService"%>
<%@page import="com.gcit.training.lms.entity.Publisher"%>
<%@page import="com.gcit.training.lms.entity.Genre"%>

<% 
List<Author> author = new AuthorService().displayAuthor();
List<Genre> genre = new GenreService().listGenre();
List<Publisher> publisher = new PublisherService().displayPublisher();
%> 
<%@include file="index.jsp"%> 
<div style="margin-top:80px;margin-left:10px;">
<br/><br/>
<br/><br/>
 
<section>
<form method="post" action="addBook" role="form">  
<div class="form-group">
<section>
	<input type="text" placeholder="Enter Title" name="title"/><br/><br/>
</section>
<section>
	<div class="col-xs-2"> 
	<select multiple name="authorId" class="form-control">
<%for(Author a : author) { %>	
			<option value="<%=a.getAuthorId()%>"><%=a.getAuthorName()%></option>
		<% } %>
	</select><br/>
	  </div>
</section>
<section>
	  <div class="col-xs-2"> 
	<select multiple name="genreId" class="form-control">
<%for(Genre g : genre) { %>	
			<option value="<%=g.getGenreId()%>"><%=g.getGenreName()%></option>
		<% } %>
	</select><br/>
	  </div>
</section>	
	  <div class="col-xs-2"> 
	<select name= "publisherid" class="form-control">
<%for(Publisher p : publisher) { %>	
			<option value="<%=p.getPublisherId()%>"><%=p.getPublisherName()%>
			<%=p.getPublisherAddress()%><%=p.getPublisherPhone()%></option>			
		<% } %> 
		</select><br/>
		 </div>
	</div>
	<input type="submit"/> 
</form>
</section>
</div>
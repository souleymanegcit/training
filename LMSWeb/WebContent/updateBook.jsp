<%@page import="com.gcit.training.lms.entity.*"%>
<%@page import="com.gcit.training.lms.service.admin.*"%>
<%@page import="java.util.List"%>
<% 
String bookIdupdate = request.getParameter("bookIdupdate");
List <Book> b =  new BookService().readOneById(Integer.parseInt(bookIdupdate)); 
List<Author> author = new AuthorService().displayAuthor();
List<Genre> genre = new GenreService().listGenre();
List<Publisher> publisher = new PublisherService().displayPublisher();

%> 
<div>
	<div class="modal-header"> 
			<button type="button" class="close" data-dismiss="modul">&times;</button>
			<h3 class="modul-title">Book Updating</h3>
	</div>
<div class="modal-body">  
	<form action="updateBook" method="post">
		Enter Title:      <input type="text" name="title" value="<%=b.get(0).getTitle()%>"/><br/>	
		Select Publisher:  
		<div class="col-xs-2"> 
			<select name= "publisherid" class="form-control">
		<%for(Publisher p : publisher) { %>	
					<option value="<%=p.getPublisherId()%>"><%=p.getPublisherName()%>
					<%=p.getPublisherAddress()%><%=p.getPublisherPhone()%></option>			
				<% } %> 
				</select><br/>
		</div>	
	<section>
	Select Authors:
	<div class="col-xs-2"> 
	<select multiple name="authorid" class="form-control">
		<%for(Author a : author) { %>	
			<option value="<%=a.getAuthorId()%>"><%=a.getAuthorName()%></option>
		<% } %>
	</select><br/>
	  </div>
  </section>
  <section>
  Select Genre:
	  <div class="col-xs-2"> 
	<select multiple name="genreid" class="form-control">
		<%for(Genre g : genre) { %>	
			<option value="<%=g.getGenreId()%>"><%=g.getGenreName()%></option>
		<% } %>
	</select><br/>
	  </div>
</section>	 
		<input type="hidden" value="<%=b.get(0).getBookId()%>" name="bookid" />
		<input type="submit" value= "Update" class="btn btn-sucess"/> 
		<a href=""  class="btn btn-danger" data-dismiss="modul">Cancel</a>
	</form>
</div>
</div>
  
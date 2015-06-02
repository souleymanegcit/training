package com.gcit.training.lms.servlet;

import java.io.IOException;
import java.util.ArrayList; 
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse; 

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gcit.training.lms.entity.Author; 
import com.gcit.training.lms.entity.Book;
import com.gcit.training.lms.entity.BookCopies;
import com.gcit.training.lms.entity.Genre;
import com.gcit.training.lms.entity.Library;
import com.gcit.training.lms.entity.Publisher; 
import com.gcit.training.lms.service.admin.AuthorService;
import com.gcit.training.lms.service.admin.BookCopiesService;
import com.gcit.training.lms.service.admin.BookService;
import com.gcit.training.lms.service.admin.LibraryService;
import com.gcit.training.lms.service.admin.PublisherService;

/**
 * Servlet implementation class AdministratorServlet
 */
@WebServlet({ "/addAuthor", "/deleteAuthor" , "/updateAuthor",
	"/addPublisher" , "/deletePublisher" , "/updatePublisher",
	"/addBook", "/updateBook", "/deleteBook","/updatelibrarian" , "/addBookCopies" })
public class AdministratorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdministratorServlet() {
        super();
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String function = request.getRequestURI().substring(
				request.getContextPath().length(),
				request.getRequestURI().length());

		//System.out.println(function);

		switch (function) {
		
		case "/addAuthor": {
			addAuthor(request, response);
			break;
		}
		case "/deleteAuthor": {
			deleteAuthor(request, response);
			break;
		}
		case "/addPublisher": {
			addPublisher(request, response);
			break;
		}
		case "/deletePublisher": {
			deletePublisher(request, response);
			break;
		}
		case "/updateAuthor": {
			updateAuthor(request, response);
			break;
		}
		case "/updatePublisher": {
			updatePublisher(request, response);
			break;
		}
		case "/updateBook": {
			updateBook(request, response);
			break;
		}
		case "/addBook": {
			addBook(request, response);
			break;
		}
		case "/deleteBook": {
			deleteBook(request, response);
			break;
		}
		case "/updatelibrarian": {
			updateLibrary(request, response);
			break;
		}
		case "/addBookCopies": {
			updateBookCopies(request, response);
			break;
		}
		default:
			break;
		}
	}
	
	
		private void deleteAuthor(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			
			String authorId = request.getParameter("authorId");
			Author author = new Author();
			author.setAuthorId(Integer.parseInt(authorId));

			RequestDispatcher rd = getServletContext().getRequestDispatcher(
					"/listAuthors.jsp");
			try {
				new AuthorService().deleteAuthor(author);

				request.setAttribute("result", "Author Deleted Succesfully!");
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("result",
						"Author Delete Failed because: " + e.getMessage());
			}
			
			rd.forward(request, response);
		}
		private void updateAuthor(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			
			String authorId = request.getParameter("authorId");
			String authorName = request.getParameter("authorName");
			Author author = new Author();
			author.setAuthorId(Integer.parseInt(authorId));
			author.setAuthorName(authorName);

			RequestDispatcher rd = getServletContext().getRequestDispatcher(
					"/listAuthors.jsp");
			try {
				new AuthorService().updateAuthor(author); 

				request.setAttribute("result", "Author updated Succesfully!");
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("result",
						"Author update Failed because: " + e.getMessage());
			}
			
			rd.forward(request, response);
		}
		private void updateLibrary(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			
			String branchId = request.getParameter("branchId");
			String branchName = request.getParameter("branchName");
			String branchAddress = request.getParameter("branchAddress");
			Library library = new Library();
			library.setBranchId(Integer.parseInt(branchId));
			library.setBranchName(branchName);
			library.setBranchAdress(branchAddress);

			RequestDispatcher rd = getServletContext().getRequestDispatcher(
					"/librarian.jsp");
			try {
				new LibraryService().updateLibrary(library); 
				request.setAttribute("result", "Branch updated Succesfully!");
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("result",
						"Branch update Failed because: " + e.getMessage());
			}
			
			rd.forward(request, response);
		}
		
		private void updateBookCopies(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException { 
			
			String branchId = request.getParameter("branchId");
			String bookId = request.getParameter("bookId"); 
			String noOfCopies = request.getParameter("noOfCopies");
			System.out.println(noOfCopies);
			BookCopies bookcopies = new BookCopies();
			Book  book  = new Book();
			Library  library  = new Library();  
			
			book.setBookId(Integer.parseInt(bookId));
			bookcopies.setBook(book);
			library.setBranchId(Integer.parseInt(branchId));
			bookcopies.setLibrary(library);
			bookcopies.setNoOfCopies(Integer.parseInt(noOfCopies));

			RequestDispatcher rd = getServletContext().getRequestDispatcher(
					"/addBookCopies.jsp");
			try {
				new BookCopiesService().updateBookCopies(bookcopies); 
				request.setAttribute("result", "BookCopies updated Succesfully!");
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("result",
						"BookCopies update Failed because: " + e.getMessage());
			}			
			rd.forward(request, response);
		}
		
		private void deletePublisher(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			
			String publisherId = request.getParameter("publisherId");
			Publisher publisher = new Publisher();
			publisher.setPublisherId(Integer.parseInt(publisherId)); 
			RequestDispatcher rd = getServletContext().getRequestDispatcher(
					"/listPublisher.jsp");
			try {
				new PublisherService().deletePublisher(publisher);

				request.setAttribute("result", "Publisher Deleted Succesfully!");
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("result",
						"Publisher Delete Failed because: " + e.getMessage());
			}
			
			rd.forward(request, response);
		}
		private void deleteBook(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			
			String bookId = request.getParameter("bookId");
			Book book = new Book();
			book.setBookId(Integer.parseInt(bookId)); 
			RequestDispatcher rd = getServletContext().getRequestDispatcher(
					"/listBook.jsp");
			try {
				new BookService().deleteBook(book);

				request.setAttribute("result", "Book Deleted Succesfully!");
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("result",
						"Book Delete Failed because: " + e.getMessage());
			}
			
			rd.forward(request, response);
		}
		private void addAuthor(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			String authorName = request.getParameter("authorName");
			Author author = new Author();
			author.setAuthorName(authorName);

			RequestDispatcher rd = getServletContext().getRequestDispatcher(
					"/authors.jsp");
			try {
				new AuthorService().insertAuthor(author); 

				request.setAttribute("result", "Author Added Succesfully!");
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("result",
						"Author Add Failed because: " + e.getMessage());
			}
			
			rd.forward(request, response);
		}
		private void addPublisher(HttpServletRequest request, 
				HttpServletResponse response) 
				throws ServletException, IOException {
			String publisherName = request.getParameter("publisherName");
			String publisherAddress = request.getParameter("publisherAddress");
			String publisherPhone = request.getParameter("publisherPhone");
			
			Publisher publisher = new Publisher();
			publisher.setPublisherName(publisherName);  
			publisher.setPublisherAddress(publisherAddress);
			publisher.setPublisherPhone(publisherPhone);
			
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/publisher.jsp");
			try {
				PublisherService publisherService = new PublisherService();
				publisherService.insertPublisher(publisher);
				request.setAttribute("result", "Publisher Added Succesfully!");
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("result", "Publisher Add Failed because: " + e.getMessage());
			}

			rd.forward(request, response);
		}
		
		private void updatePublisher(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			
			String publisherId = request.getParameter("publisherId");
			String publisherName = request.getParameter("publisherName");
			String publisherAddress = request.getParameter("publisherAddress");
			String publisherPhone = request.getParameter("publisherPhone");
			
			Publisher publisher = new Publisher();
			publisher.setPublisherId(Integer.parseInt(publisherId));			 
			publisher.setPublisherName(publisherName);  
			publisher.setPublisherAddress(publisherAddress);
			publisher.setPublisherPhone(publisherPhone);

			RequestDispatcher rd = getServletContext().getRequestDispatcher(
					"/listPublisher.jsp");
			try {
				new PublisherService().updatePublisher(publisher); 

				request.setAttribute("result", "Publisher updated Succesfully!");
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("result",
						"Publisher update Failed because: " + e.getMessage());
			} 
			rd.forward(request, response);
		}
		private void addBook(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			
			String[] authorIds = request.getParameterValues("authorId");
			String title = request.getParameter("title");
			String[] genres = request.getParameterValues("genreId");
			Publisher p = new Publisher();
			String publisherid = request.getParameter("publisherid");
			//System.out.println(publisherid);
			Book book = new Book();
			book.setTitle(title); 
			p.setPublisherId(Integer.parseInt(publisherid)); 
			book.setPublisher(p);
			
			if(authorIds != null && authorIds.length > 0) {
				book.setAuthors(new ArrayList<Author>());
				for(String s : authorIds) {
					Author author = new Author();
					author.setAuthorId(Integer.parseInt(s));
					book.getAuthors().add(author);
				}
			} 
			if(genres != null && genres.length > 0) {
				book.setGenres(new ArrayList<Genre>());
				for(String g : genres) {
					Genre genre = new Genre();
					genre.setGenreId(Integer.parseInt(g));
					book.getGenres().add(genre); 
				}
			} 
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/book.jsp");
			try {
				new BookService().insertBook(book);

				request.setAttribute("result", "Book Added Succesfully!");
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("result",
						"Book Add Failed because: " + e.getMessage());
			}

			rd.forward(request, response);
		}
		private void updateBook(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			String[] authorIds = request.getParameterValues("authorid");
			String bookId = request.getParameter("bookid");
			String title = request.getParameter("title");
			String[] genres = request.getParameterValues("genreid");
			Publisher p = new Publisher();
			String publisherId = request.getParameter("publisherid"); 
			Book book = new Book();
			book.setBookId(Integer.parseInt(bookId));
			book.setTitle(title);
			p.setPublisherId(Integer.parseInt(publisherId)); 
			book.setPublisher(p);
			if(authorIds != null && authorIds.length > 0) {
				book.setAuthors(new ArrayList<Author>());
				for(String s : authorIds) {
					Author author = new Author();
					author.setAuthorId(Integer.parseInt(s));
					book.getAuthors().add(author);
				}
			} 
			if(genres != null && genres.length > 0) {
				book.setGenres(new ArrayList<Genre>());
				for(String g : genres) {
					Genre genre = new Genre();
					genre.setGenreId(Integer.parseInt(g));
					book.getGenres().add(genre); 
				}
			} 
			
			RequestDispatcher rd = getServletContext().getRequestDispatcher(
					"/listBook.jsp");
			try {
				new BookService().updateBook(book); 

				request.setAttribute("result", "Book updated Succesfully!");
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("result",
						"Book update Failed because: " + e.getMessage());
			}  

			rd.forward(request, response);
		}
	}


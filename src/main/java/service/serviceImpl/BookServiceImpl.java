package service.serviceImpl;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dao.bookDao.BookDaoImpl;
import model.BookModel;
import service.BookService;

public class BookServiceImpl implements BookService {
	List<BookModel> books;
	BookDaoImpl bookDao;
	BookModel book;

	public BookServiceImpl() {
		book = new BookModel();
		bookDao = new BookDaoImpl();
	}

	private BookModel initBookModel(String author, String nameOfBook) {
		BookModel bookP = new BookModel();
		bookP.setAuthor(author);
		bookP.setNameOfBook(nameOfBook);
		return bookP;
	}

	@Override
	public void showAllBooks() {
		try {
			books = bookDao.getAllBooks();
		} catch (InstantiationException | IllegalAccessException | SQLException e) {
			System.out.println("Error. Please try again...");
			e.printStackTrace();
		}
		for (BookModel book : books) {
			System.out.println(book.toString());
		}
	}

	public String findNameOfBook(String inputedString) {
		String result = "";
		Pattern p = Pattern.compile("-n=(.*?) ?(-a|$)");
		Matcher m = p.matcher(inputedString);

		if (m.find()) {
			result = m.group(1);
			return result;
		}

		return result;
	}

	public String findCommand(String inputedString) {
		String result = "";
		Pattern p = Pattern.compile(" ?(.*?)( |-a|-n|$)");
		Matcher m = p.matcher(inputedString);

		if (m.find()) {
			result = m.group(1);
			return result;
		}

		return result;
	}

	@Override
	public void addBook(String author, String nameOfBook) {
		if (author.isEmpty() || nameOfBook.isEmpty()) {
			System.out.println("Incorrect input. Current request is: add -a=your author -n=name of book");
		} else {
			book = initBookModel(author, nameOfBook);
			try {
				if (bookDao.addBook(book)) {
					System.out.println("Book " + author + " " + nameOfBook + " was added");
				} else {
					System.out.println("Error. Please try again...");
				}
			} catch (SQLException e) {
				System.out.println("Error. Please try again later...");
				e.printStackTrace();
			}
		}
	}

	@Override
	public void removeBook(String nameOfBook, String author) {
		if (nameOfBook.isEmpty()) {
			System.out.println("Incorrect input.");
		} else {
			book.setNameOfBook(nameOfBook);
		}

		if (bookDao.removeBook(book)) {
			System.out.println("Book " + author + " " + nameOfBook + " was removed");
		} else {
			System.out.println("Error. Please try again...");
		}

	}

	@Override
	public void editBook(String idStr, String newName, String newAuthor) {
		book.setIdBooks(idStr);
		book.setNameOfBook(newName);
		book.setAuthor(newAuthor);

		try {
			if (bookDao.editBook(book)) {
				System.out.println("Book changed successfully.");
			} else {
				System.out.println("Error. Please try again...");
			}
		} catch (SQLException e) {
			System.out.println("Error. Please try again later...");
			e.printStackTrace();
		}

	}

	public String findAuthor(String input) {
		String result = "";
		Pattern p = Pattern.compile("-a=(.*?) ?(-n|$)");
		Matcher m = p.matcher(input);

		if (m.find()) {
			result = m.group(1);
			return result;
		}

		return result;
	}
}

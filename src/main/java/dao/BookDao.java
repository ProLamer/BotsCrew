package dao;

import java.sql.SQLException;
import java.util.List;

import model.BookModel;

public interface BookDao {
	boolean addBook(BookModel book) throws SQLException;

	boolean editBook(BookModel book) throws SQLException;

	boolean removeBook(BookModel book);

	List<BookModel> getAllBooks() throws InstantiationException, IllegalAccessException, SQLException;
}

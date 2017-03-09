package service;

public interface Service {
	void showAllBooks();

	void addBook(String author, String nameOfBook);

	void removeBook(String nameOfBook, String author);

	void editBook(String idStr, String newName, String newAuthor);
}

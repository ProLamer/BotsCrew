package controller;

import java.util.Scanner;

import model.Menu;
import service.serviceImpl.BookServiceImpl;

public class BookController {
	BookServiceImpl service = new BookServiceImpl();
	Menu menu;
	Scanner scan;

	public BookController() {
		menu = new Menu();
		scan = new Scanner(System.in);
	}

	public void run() {
		String inputedString = "";
		String command = "";
		String author = "";
		String nameOfBook = "";
		while (true) {
			menu.choiceOfClient();
			inputedString = menu.getChoice();

			if (inputedString.equals("all books") || inputedString.equals("help")) {
				command = inputedString;
			} else {
				command = service.findCommand(inputedString);
				author = service.findAuthor(inputedString);
				nameOfBook = service.findNameOfBook(inputedString);
			}

			switch (command) {
			case "add":
				service.addBook(author, nameOfBook);
				break;

			case "remove":
				if (nameOfBook.isEmpty()) {
					System.out.println("Input name of book. Our books:");
					service.showAllBooks();
					System.out.print("Name of book = ");
					nameOfBook = scan.nextLine();
					service.removeBook(nameOfBook, author);
				} else {
					service.removeBook(nameOfBook, author);
				}
				break;

			case "edit":
				System.out.println("Choose id of book:");
				service.showAllBooks();
				String idStr = scan.nextLine();

				System.out.print("Input new author: ");
				String newAuthor = scan.nextLine();

				System.out.print("Input new name: ");
				String newName = scan.nextLine();
				service.editBook(idStr, newName, newAuthor);
				break;

			case "all books":
				service.showAllBooks();
				break;

			case "help":
				menu.showHelpMenu();
				break;

			case "exit":
				scan.close();
				System.exit(0);
				break;

			default:
				System.out.println("Incorrect input, please input help for documentation.");
				break;
			}
		}
	}
}

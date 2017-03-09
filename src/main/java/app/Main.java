package app;

import controller.controllerImpl.BookController;

public class Main {

	public static void main(String[] args) {
		BookController controller = new BookController();
		controller.run();
	}

}

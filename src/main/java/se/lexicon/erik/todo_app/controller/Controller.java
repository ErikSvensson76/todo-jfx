package se.lexicon.erik.todo_app.controller;

import se.lexicon.erik.todo_app.data_access.TodoItemDao;

public class Controller {
	
	private TodoItemDao todoItemDao = TodoItemDao.get();
	
}

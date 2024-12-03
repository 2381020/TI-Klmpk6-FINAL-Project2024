package todoapp.services;

import todoapp.entities.TodoList;

import java.util.ArrayList;
import java.util.List;

public class TodoListServiceImpl implements TodoListService {

    private final List<TodoList> todoList = new ArrayList<>();

    @Override
    public TodoList[] getTodoList() {
        return todoList.toArray(new TodoList[0]);
    }

    @Override
    public void addTodoList(String todo) {
        todoList.add(new TodoList(todo));
        System.out.println("Todo ditambahkan: " + todo);
    }

    @Override
    public boolean removeTodoList(int number) {
        if (number - 1 >= 0 && number - 1 < todoList.size()) {
            TodoList removed = todoList.remove(number - 1);
            System.out.println("Todo dihapus: " + removed.getTask());
            return true;
        } else {
            System.out.println("Nomor tidak valid!");
            return false;
        }
    }

    @Override
    public boolean editTodoList(int number, String newTodo) {
        if (number - 1 >= 0 && number - 1 < todoList.size()) {
            todoList.get(number - 1).setTask(newTodo);
            System.out.println("Todo diperbarui: " + newTodo);
            return true;
        } else {
            System.out.println("Nomor tidak valid!");
            return false;
        }
    }
}

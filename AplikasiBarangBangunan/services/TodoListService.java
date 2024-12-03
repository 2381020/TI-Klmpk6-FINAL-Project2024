package todoapp.services;

import todoapp.entities.TodoList;

public interface
TodoListService {
    TodoList[] getTodoList(); // Mengembalikan daftar TodoList
    void addTodoList(String todo); // Menambahkan item ke TodoList
    boolean removeTodoList(int number); // Menghapus item berdasarkan nomor
    boolean editTodoList(int number, String newTodo); // Mengedit item TodoList
}

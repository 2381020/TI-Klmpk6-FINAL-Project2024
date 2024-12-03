package todoapp.views;

import todoapp.entities.TodoList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import todoapp.services.TodoListService;
import java.util.Scanner;

@Component
public class TodoListTerminalViewImpl implements TodoListView {

    private static final Scanner scanner = new Scanner(System.in); // 1 usage
    private final TodoListService todoListService; // 5 usages

    @Autowired
    public TodoListTerminalViewImpl(TodoListService todoListService) {
        this.todoListService = todoListService;
    }

    @Override
    public void showMainMenu() {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("\nTi-Kimpk6-FINAL-Project2024 === Aplikasi Barang Bangunan");
            System.out.println("1. Tambah Barang");
            System.out.println("2. Hapus Barang");
            System.out.println("3. Edit Barang");
            System.out.println("\nKELUAR");

            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    createTodo();
                    break;
                case "2":
                    deleteTodo();
                    break;
                case "3":
                    updateTodo();
                    break;
                default:
                    isRunning = false;
                    System.out.println("\nProgram selesai.");
                    break;
            }
        }
    }

    private void createTodo() {
        System.out.print("Masukkan barang: ");
        String barang = scanner.nextLine();
        TodoList newTodo = new TodoList(barang);
        todoListService.saveTodo(newTodo);
        System.out.println("Barang berhasil ditambahkan.");
    }

    private void deleteTodo() {
        System.out.print("Masukkan ID barang yang akan dihapus: ");
        int id = Integer.parseInt(scanner.nextLine());
        todoListService.deleteTodo(id);
        System.out.println("Barang berhasil dihapus.");
    }

    private void updateTodo() {
        System.out.print("Masukkan ID barang yang akan diedit: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Masukkan nama barang baru: ");
        String newName = scanner.nextLine();
        TodoList updatedTodo = new TodoList(id, newName);
        todoListService.updateTodo(updatedTodo);
        System.out.println("Barang berhasil diubah.");
    }
}
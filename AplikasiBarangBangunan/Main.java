package todoapp.views;

import todoapp.entities.TodoList;
import todoapp.services.TodoListService;
import todoapp.services.TodoListServiceImpl;

import java.util.Scanner;

public class Main {

    private static final TodoListService todoService = new TodoListServiceImpl();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("\n=== Aplikasi Barang Bangunan ===");
            System.out.println("1. Lihat TodoList");
            System.out.println("2. Tambah Barang");
            System.out.println("3. Hapus Barang");
            System.out.println("4. Edit Barang");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    showTodoList();
                    break;
                case "2":
                    addTodoList();
                    break;
                case "3":
                    removeTodoList();
                    break;
                case "4":
                    editTodoList();
                    break;
                case "5":
                    running = false;
                    System.out.println("Terima kasih telah menggunakan aplikasi!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }

    private static void showTodoList() {
        TodoList[] todos = todoService.getTodoList();
        System.out.println("\nDaftar Barang:");
        if (todos.length == 0) {
            System.out.println("Tidak ada barang.");
        } else {
            for (int i = 0; i < todos.length; i++) {
                System.out.println((i + 1) + ". " + todos[i].getTask());
            }
        }
    }

    private static void addTodoList() {
        System.out.print("Masukkan nama barang: ");
        String todo = scanner.nextLine();
        todoService.addTodoList(todo);
    }

    private static void removeTodoList() {
        showTodoList();
        System.out.print("Masukkan nomor barang yang ingin dihapus: ");
        int number = Integer.parseInt(scanner.nextLine());
        todoService.removeTodoList(number);
    }

    private static void editTodoList() {
        showTodoList();
        System.out.print("Masukkan nomor barang yang ingin diedit: ");
        int number = Integer.parseInt(scanner.nextLine());
        System.out.print("Masukkan nama baru: ");
        String newTodo = scanner.nextLine();
        todoService.editTodoList(number, newTodo);
    }
}

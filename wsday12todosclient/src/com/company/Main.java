package com.company;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.text.ParseException;
import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws ParseException {
        // write your code here
            Controller controller = new Controller(API.getAPI("http://localhost:3000/"));

            loop:
            while (true) {
                try {
                    System.out.println("""
                            Menu:
                            1. List out all todos
                            2. Add todo
                            3. Update todo by id
                            4. Delete a todo by id
                            5. Delete all todos
                            0. Exit
                            """);
                    int option = input.nextInt();
                    input.nextLine();
                    switch (option) {
                        case 1:
                            controller.getAllTodos();
                            break;
                        case 2:
                            System.out.println(controller.addTodo(
                                    createTodo()
                            ));
                            break;
                        case 3:
                            System.out.println("Enter an id to update todo");
                            int updateId = input.nextInt();
                            input.nextLine();
                            controller.updateTodo(updateId, createTodo());
                            break;
                        case 4:
                            System.out.println("Enter an id to delete a todo");
                            int id = input.nextInt();
                            input.nextLine();
                            controller.deleteTodo(id);
                            break;
                        case 5:
                            controller.deleteAllTodos();
                            break;
                        case 0:
                            break loop;
                        default:
                            break;
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
    }
    private static Todo createTodo() throws ParseException {
        System.out.print("task: ");
        String task = input.nextLine();
        System.out.print("due date (YYYY-MM-DD): ");
        String dueDate = input.nextLine();
        System.out.print("Status (true for done, false for pending): ");
        Status status = input.nextBoolean() ? Status.done : Status.pending;
        input.nextLine();
        return new Todo(task,dueDate,status);
    }
}

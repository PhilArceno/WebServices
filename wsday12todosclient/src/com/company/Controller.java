package com.company;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

public class Controller {
    public TodosAPI api;

    public Controller(TodosAPI api) {
        this.api = api;
    }

    public void getAllTodos() {
        try {
            Call<List<Todo>> getAllTodos = api.getAllTodos();
            Response<List<Todo>> res = getAllTodos.execute();
            if (res.isSuccessful()) {// 2xx code
                List<Todo> todoList = res.body();
                for (Todo todo : todoList) {
                    System.out.println(todo);
                }
                if (todoList.size() < 1) {
                    System.out.println("There are no todos");
                }
            } else {  // error - not 2xx code
                System.out.println("error: " + res.code());
                ResponseBody errorBody = res.errorBody();
                System.out.println("body:" + errorBody.string());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void getTodoById(int id) {
        try {
            Call<Todo> getTodoById = api.getTodoById(id);
            Response<Todo> res = getTodoById.execute();
            if (res.isSuccessful()) {
                Todo todo = res.body();
                System.out.println(todo);
            } else {  // error - not 2xx code
                System.out.println("error: " + res.code());
                ResponseBody errorBody = res.errorBody();
                System.out.println("body:" + errorBody.string());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int addTodo(Todo todo) {
        try {
            Call<Integer> addTodoCall = api.addTodo(todo);
            Response<Integer> res = addTodoCall.execute();

            if (res.isSuccessful()) {
                return res.body();
            } else {  // error - not 2xx code
                System.out.println("error: " + res.code());
                ResponseBody errorBody = res.errorBody();
                System.out.println("body:" + errorBody.string());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void updateTodo(Integer id, Todo todo) {
        try {
            Call<Boolean> addTodoCall = api.updateTodo(id, todo);
            Response<Boolean> res = addTodoCall.execute();

            if (res.isSuccessful()) {
                System.out.println(res.body());
            } else {  // error - not 2xx code
                System.out.println("error: " + res.code());
                ResponseBody errorBody = res.errorBody();
                System.out.println("body:" + errorBody.string());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteTodo(int id) {
        try {
            Call<ApiResult> deleteTodoCall = api.deleteTodo(id);
            Response<ApiResult> res = deleteTodoCall.execute();
            if (res.isSuccessful()) {
                System.out.println(res.body().message);
            } else {  // error - not 2xx code
                System.out.println("error: " + res.code());
                ResponseBody errorBody = res.errorBody();
                System.out.println("body:" + errorBody.string());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void deleteAllTodos() {
        try {
            Call<ApiResult> deleteTodoCall = api.deleteAllTodos();
            Response<ApiResult> res = deleteTodoCall.execute();
            if (res.isSuccessful()) {
                System.out.println(res.body().message);
            } else {  // error - not 2xx code
                System.out.println("error: " + res.code());
                ResponseBody errorBody = res.errorBody();
                System.out.println("body:" + errorBody.string());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

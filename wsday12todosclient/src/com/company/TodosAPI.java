package com.company;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.*;

public interface TodosAPI {
    @GET("todos")
    Call<List<Todo>> getAllTodos();

    @GET("todos/{id}")
    Call<Todo> getTodoById(@Path("id") Integer id);

    @POST("todos")
    Call<Integer> addTodo(@Body Todo todo);

    @PUT("todos/{id}")
    Call<Boolean> updateTodo(@Path("id") Integer id, @Body Todo todo);

    @DELETE("todos/{id}")
    Call<ApiResult> deleteTodo(@Path("id") Integer id);

    @DELETE("todos")
    Call<ApiResult> deleteAllTodos();
}

package com.example.todo.domain.repository.todo;

import java.util.Collection;

import com.example.todo.domain.model.Todo;

public interface TodoRepository {

	// 1件取得
    Todo findOne(String todoId);

    // 全件取得
    Collection<Todo> findAll();

    void create(Todo todo);

    boolean update(Todo todo);

    void delete(Todo todo);

    long countByFinished(boolean finished);
}
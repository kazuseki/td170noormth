package com.example.todo.domain.repository.todo;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import com.example.todo.domain.model.Todo;

@Repository // (1)
public class TodoRepositoryImpl implements TodoRepository {

	// ConcurrentHashMap を使って同期をとる
    private static final Map<String, Todo> TODO_MAP = new ConcurrentHashMap<String, Todo>();

    @Override
    public Todo findOne(String todoId) {
        return TODO_MAP.get(todoId);
    }

    public Todo findOneByTodoTitle(String todoTitle) {

    	Todo todoHit = null;

    	// 最初に見つかった同一のtodotitleを取り出す
    	for (Todo todo : TODO_MAP.values()) {
    		if( todoTitle.equals( todo.getTodoTitle() )) {
    			todoHit = todo;
    			break;
    		}
    	}

    	return todoHit;
    }

    @Override
    public Collection<Todo> findAll() {
        return TODO_MAP.values();
    }

    @Override
    public void create(Todo todo) {
        TODO_MAP.put(todo.getTodoId(), todo);
    }

    @Override
    public boolean update(Todo todo) {
        TODO_MAP.put(todo.getTodoId(), todo);
        return true;
    }

    @Override
    public void delete(Todo todo) {
        TODO_MAP.remove(todo.getTodoId());
    }

    @Override
    public long countByFinished(boolean finished) {
        long count = 0;
        for (Todo todo : TODO_MAP.values()) {
            if (finished == todo.isFinished()) {
                count++;
            }
        }
        return count;
    }
}
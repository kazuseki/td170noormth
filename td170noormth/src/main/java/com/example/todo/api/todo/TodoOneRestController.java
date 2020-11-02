package com.example.todo.api.todo;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.todo.domain.model.Todo;
import com.example.todo.domain.service.todo.TodoService;
import com.github.dozermapper.core.Mapper;

@RestController
@RequestMapping("todo")
public class TodoOneRestController {

    @Inject
    TodoService todoService;

    @Inject
    Mapper beanMapper;

    @GetMapping("{todoTitle}") // (1)
    @ResponseStatus(HttpStatus.OK) // (2)
    public TodoResource getTodoByTitle( @PathVariable("todoTitle") String todoTitle) {

        Todo todo = todoService.findByTodoTitle(todoTitle);

        TodoResource todoResource = beanMapper.map(todo, TodoResource.class);

        return todoResource; // (4)
    }
}

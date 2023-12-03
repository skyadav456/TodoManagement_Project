package com.sharad.service;

import com.sharad.dto.TodoDto;
import com.sharad.entity.Todo;
import org.springframework.stereotype.Service;

import java.util.List;


public interface TodoService {

    TodoDto addTodo(TodoDto todoDto);
    TodoDto getTodo(Long id);

    List<TodoDto> getAllTodo();

    TodoDto updateTodo(TodoDto todoDto,Long id);

    void deleteTodo(Long id);

    TodoDto completeTodo(Long id);

    TodoDto incompleteTodo(Long id);
    //void deleteAllTodo();

}

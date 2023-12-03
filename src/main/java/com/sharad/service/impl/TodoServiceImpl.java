package com.sharad.service.impl;

import com.sharad.dto.TodoDto;
import com.sharad.entity.Todo;
import com.sharad.exception.ResourceNotFound;
import com.sharad.repository.TodoRepository;
import com.sharad.service.TodoService;
import lombok.AllArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class TodoServiceImpl implements TodoService {
    @Autowired
    private TodoRepository todoRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public TodoDto addTodo(TodoDto todoDto) {
        // convert TodoDto  into Todo Jpa Entity
        // using model mapper to convert for Dto to JPA and vice versa
//        Todo todo=new Todo();
//        todo.setTitle(todoDto.getTitle());
//        todo.setDescription(todoDto.getDescription());
//        todo.setCompleted(todoDto.isCompleted());

         Todo todo= modelMapper.map(todoDto,Todo.class);


        // Todo Jpa Entity
        Todo savedTodo = todoRepository.save(todo);

        // Convert saved Todo JPA entity object into TodoDto object

//        TodoDto savedTodoDto=new TodoDto();
//        savedTodoDto.setId(savedtodo.getId());
//        savedTodoDto.setTitle(savedtodo.getTitle());
//        savedTodoDto.setDescription(savedtodo.getDescription());
//        savedTodoDto.setCompleted(savedtodo.isCompleted());

         TodoDto saveTodoDto=modelMapper.map(savedTodo,TodoDto.class);
         return saveTodoDto;


    }

    @Override
    public TodoDto getTodo(Long id) {
//        Todo todo = todoRepository.findById(id).get();
        Todo todo = todoRepository.findById(id).orElseThrow(()->new ResourceNotFound("Todo not found with id" +id));
        return modelMapper.map(todo,TodoDto.class);
    }

    @Override
    public List<TodoDto> getAllTodo() {
        List<Todo> todos = todoRepository.findAll();
        return todos.stream().map((todo)->modelMapper.map(todo,TodoDto.class)).collect(Collectors.toList());
    }

    @Override
    public TodoDto updateTodo(TodoDto todoDto, Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Todo Not found with id:" + id));
        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setCompleted(todoDto.isCompleted());
        Todo updatedTodo = todoRepository.save(todo);
        return modelMapper.map(todo,TodoDto.class);
    }

    @Override
    public void deleteTodo(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Todos not find with id :" + id));
        todoRepository.deleteById(id);
    }

    @Override
    public TodoDto completeTodo(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Todo not found with the id: " + id));
        todo.setCompleted(Boolean.TRUE);
        Todo updatedTodo = todoRepository.save(todo);

        return modelMapper.map(updatedTodo,TodoDto.class);
    }

    @Override
    public TodoDto incompleteTodo(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Todo not found with the id" + id));
        todo.setCompleted(Boolean.FALSE);
        Todo updatedTodo = todoRepository.save(todo);

        return modelMapper.map(updatedTodo,TodoDto.class);
    }

//    @Override
//    public void deleteAllTodo() {
//        todoRepository.deleteAll();
//    }


}

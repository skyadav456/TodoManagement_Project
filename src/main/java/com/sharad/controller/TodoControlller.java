package com.sharad.controller;

import com.sharad.dto.TodoDto;
import com.sharad.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/todos")
public class TodoControlller {

    @Autowired
    private TodoService todoService;


    // built add Todo Rest Api
    @PostMapping()
    public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto todoDto){
        TodoDto savedTodo = todoService.addTodo(todoDto);
        return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);
    }
    // Build the GET Rest Api
    @GetMapping("{id}")
    public ResponseEntity<TodoDto> getTodo(@PathVariable("id") Long todoId){
        TodoDto todoDto = todoService.getTodo(todoId);
        return new ResponseEntity<>(todoDto,HttpStatus.OK);
    }

    //Build GET All Todos Rest Api

    @GetMapping
    public ResponseEntity<List<TodoDto>> getAllTodo(){
        List<TodoDto> todo = todoService.getAllTodo();
       return new ResponseEntity<>(todo,HttpStatus.OK);
       // return ResponseEntity.ok(todo);                 // both are same statement
    }

    @PutMapping("{id}")
    // Build Update Todo Rest Api
    public  ResponseEntity<TodoDto> updateTodo(@RequestBody TodoDto todoDto, @PathVariable Long id){
        TodoDto updatedTodo = todoService.updateTodo(todoDto, id);
        return ResponseEntity.ok(updatedTodo);
    }

    //Build DELETE Todo Rest API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable Long id){
        todoService.deleteTodo(id);
        return  ResponseEntity.ok("Todo delete Successfully");
    }

    //Build Complete Todo Rest Api
    //@PatchMapping --update exsiting  resource partially
    @PatchMapping  ("{id}/complete")
    public  ResponseEntity<TodoDto> completeTodo(@PathVariable Long id){
        TodoDto updatedTodo = todoService.completeTodo(id);
        return ResponseEntity.ok(updatedTodo);
    }

    // Built inComplete Todo Rest Api
    @PatchMapping("{id}/incomplete")
    public ResponseEntity<TodoDto> incompleteTodo(@PathVariable Long id){
        TodoDto updatedTodo = todoService.incompleteTodo(id);
        return ResponseEntity.ok(updatedTodo);
    }

}

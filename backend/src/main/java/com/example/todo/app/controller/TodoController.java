package com.example.todo.app.controller;

import com.example.todo.app.entity.Todo;
import com.example.todo.app.request.AddTodoRequest;
import com.example.todo.app.request.UpdateStatusRequest;
import com.example.todo.app.request.UpdateTodoRequest;
import com.example.todo.app.response.TodoResponse;
import com.example.todo.app.response.TodoVO;
import com.example.todo.app.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class TodoController {
    @Autowired
    private TodoService todoService;

    //add todos in project
    @PostMapping("/todo/addTask")
    public ResponseEntity<TodoVO> addTodo(@RequestBody AddTodoRequest todo)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                todoService.addTodo(todo)

        );
    }

    //get all todos of all projects
    @GetMapping("/todo/getAllTodos")
    public  ResponseEntity<TodoResponse> getAllTodos()
    {

        TodoResponse response=todoService.getAllTodos();
        return new ResponseEntity<>(response,HttpStatus.OK);

    }

    @DeleteMapping("/todo/deleteTodo/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable int id)
    {

        boolean deleted=todoService.deleteTodo(id);
        if(deleted)
        return ResponseEntity.ok("Succesfully deleted");
        else
        {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("todo/updateTodo/{id}")
    public ResponseEntity<String>updateTodo(@PathVariable int id, @RequestBody UpdateTodoRequest request)
    {
        boolean updated=todoService.updateTodo(id,request);
        if(updated)
        {
            return ResponseEntity.ok("Succesfully updated");
        }
        else
        {
            return ResponseEntity.notFound().build();
        }


    }

    @PutMapping("todo/MarkTodo/{id}")
    public ResponseEntity<String>MarkTodo(@PathVariable int id, @RequestBody UpdateStatusRequest request)
    {
        boolean marked=todoService.MarkTodo(id,request);
        if(marked)
        {
            return ResponseEntity.ok("Status Succesfully updated");
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("todo/getAllTodoOfProject/{project_id}")
    public ResponseEntity<TodoResponse>getAllTodoOfProject(@PathVariable int project_id)
    {
        TodoResponse response=todoService.getAllTodoOfProject(project_id);
        return new ResponseEntity<>(response,HttpStatus.OK);

    }

    @GetMapping("todo/getTodoProject/{id}")
    public ResponseEntity<TodoVO>getTodoProject(@PathVariable int id)
    {
        TodoVO response=todoService.getTodoProject(id);
        return new ResponseEntity<>(response,HttpStatus.OK);

    }

}

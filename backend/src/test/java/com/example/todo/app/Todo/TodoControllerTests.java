package com.example.todo.app.Todo;

import com.example.todo.app.controller.TodoController;
import com.example.todo.app.model.Status;
import com.example.todo.app.request.AddTodoRequest;
import com.example.todo.app.request.UpdateStatusRequest;
import com.example.todo.app.request.UpdateTodoRequest;
import com.example.todo.app.response.TodoResponse;
import com.example.todo.app.response.TodoVO;
import com.example.todo.app.services.TodoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class TodoControllerTests {

    @Mock
    private TodoService todoService;

    @InjectMocks
    private TodoController todoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddTodo() {
        // Mocking todoService.addTodo()
        AddTodoRequest addTodoRequest = new AddTodoRequest();
        addTodoRequest.setDescription("New Todo");
        TodoVO todoVO = new TodoVO();
        todoVO.setId(1);
        todoVO.setDescription("New Todo");
        todoVO.setCreatedDate(new Date());
        when(todoService.addTodo(addTodoRequest)).thenReturn(todoVO);

        // Testing addTodo() method
        ResponseEntity<TodoVO> responseEntity = todoController.addTodo(addTodoRequest);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(todoVO, responseEntity.getBody());
    }

    @Test
    void testGetAllTodos() {
        // Mocking todoService.getAllTodos()
        List<TodoVO> todoVOList = new ArrayList<>();
        TodoVO todoVO = new TodoVO();
        todoVO.setId(1);
        todoVO.setDescription("Todo 1");
        todoVO.setCreatedDate(new Date());
        todoVOList.add(todoVO);
        TodoResponse todoResponse = new TodoResponse(todoVOList);
        when(todoService.getAllTodos()).thenReturn(todoResponse);

        // Testing getAllTodos() method
        ResponseEntity<TodoResponse> responseEntity = todoController.getAllTodos();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(todoResponse, responseEntity.getBody());
    }

    @Test
    void testDeleteTodo() {
        // Mocking todoService.deleteTodo()
        int todoId = 1;
        when(todoService.deleteTodo(todoId)).thenReturn(true);

        // Testing deleteTodo() method
        ResponseEntity<String> responseEntity = todoController.deleteTodo(todoId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Succesfully deleted", responseEntity.getBody());
    }

    @Test
    void testUpdateTodo() {
        // Mocking todoService.updateTodo()
        int todoId = 1;
        UpdateTodoRequest updateTodoRequest = new UpdateTodoRequest();
        updateTodoRequest.setDescription("Updated Description");
        updateTodoRequest.setStatus(Status.COMPLETED);
        when(todoService.updateTodo(todoId, updateTodoRequest)).thenReturn(true);
        ResponseEntity<String> responseEntity = todoController.updateTodo(todoId, updateTodoRequest);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Succesfully updated", responseEntity.getBody());
    }

    @Test
    void testMarkTodo() {
        // Mocking todoService.MarkTodo()
        int todoId = 1;
        UpdateStatusRequest updateStatusRequest = new UpdateStatusRequest(Status.COMPLETED);
        when(todoService.MarkTodo(todoId, updateStatusRequest)).thenReturn(true);

        // Testing MarkTodo() method
        ResponseEntity<String> responseEntity = todoController.MarkTodo(todoId, updateStatusRequest);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Status Succesfully updated", responseEntity.getBody());
    }

    @Test
    void testGetAllTodoOfProject() {
        // Mocking todoService.getAllTodoOfProject()
        int projectId = 1;
        List<TodoVO> todoVOList = new ArrayList<>();
        TodoVO todoVO = new TodoVO();
        todoVO.setId(1);
        todoVO.setDescription("Todo 1");
        todoVO.setCreatedDate(new Date());
        todoVOList.add(todoVO);
        TodoResponse todoResponse = new TodoResponse(todoVOList);
        when(todoService.getAllTodoOfProject(projectId)).thenReturn(todoResponse);

        // Testing getAllTodoOfProject() method
        ResponseEntity<TodoResponse> responseEntity = todoController.getAllTodoOfProject(projectId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(todoResponse, responseEntity.getBody());
    }
    @Test
    void testGetTodoProject() {
        // Mocking todoService.getTodoProject()
        int todoId = 1;
        TodoVO todoVO = new TodoVO();
        todoVO.setId(todoId);
        todoVO.setDescription("Todo 1");
        todoVO.setCreatedDate(new Date());
        when(todoService.getTodoProject(todoId)).thenReturn(todoVO);

        // Testing getTodoProject() method
        ResponseEntity<TodoVO> responseEntity = todoController.getTodoProject(todoId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(todoVO, responseEntity.getBody());
    }




    // Other test cases for TodoController can be added similarly
}

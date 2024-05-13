package com.example.todo.app.services;


import com.example.todo.app.entity.Project;
import com.example.todo.app.entity.Todo;
import com.example.todo.app.repository.ProjectRepo;
import com.example.todo.app.repository.TodoRepo;
import com.example.todo.app.request.AddTodoRequest;
import com.example.todo.app.request.UpdateStatusRequest;
import com.example.todo.app.request.UpdateTodoRequest;
import com.example.todo.app.response.ProjectVO;
import com.example.todo.app.response.TodoResponse;
import com.example.todo.app.response.TodoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService {


    @Autowired
    private TodoRepo todorepo;

    @Autowired
    private ProjectRepo projectRepo;

    public boolean deleteTodo(int id) {
        try {
            todorepo.deleteById(id);
            return true;
        }
        catch(EmptyResultDataAccessException e)
        {
            return false;
        }

    }

    public  TodoVO addTodo(AddTodoRequest todo) {

        return createTodo(todo);


    }

    private TodoVO createTodo(AddTodoRequest todo) {
        Todo returnedtodo=new Todo();
        returnedtodo.setDescription(todo.getDescription());
        returnedtodo.setStatus(todo.getStatus());
        Optional<Project> p=projectRepo.findById(todo.getProject_id());

        returnedtodo.setProject(p.get());
        Todo returned =todorepo.save(returnedtodo);
        TodoVO todovo=new TodoVO();
        todovo.setId(returned.getId());
        todovo.setDescription(returned.getDescription());
        todovo.setStatus(returned.getStatus());
        todovo.setCreatedDate(returned.getCreatedDate());
        todovo.setUpdatedDate(returned.getUpdatedDate());
        Project project=returned.getProject();
        todovo.setProject(project);
        return todovo;

    }

    public TodoResponse getAllTodos() {

       List<Todo> list= (List<Todo>) todorepo.findAll();
       List<TodoVO> todos=buildtodos(list);
       return new TodoResponse(todos);


    }

    private List<TodoVO> buildtodos(List<Todo> list) {

        List<TodoVO> response=new ArrayList<>();
        for(Todo t:list)
        {
            TodoVO todo=new TodoVO();
            todo.setId(t.getId());
            todo.setUpdatedDate(t.getUpdatedDate());
            todo.setCreatedDate(t.getCreatedDate());
            todo.setDescription(t.getDescription());
            todo.setStatus(t.getStatus());
            Project project=t.getProject();
            todo.setProject(project);
            response.add(todo);

        }
        return response;
    }

    public boolean updateTodo(int id, UpdateTodoRequest request) {
        Optional<Todo> t=todorepo.findById(id);
        if(t.isPresent())
        {
            Date d=new Date();
            Todo todo=t.get();
            todo.setUpdatedDate(d);
            todo.setStatus(request.getStatus());
            todo.setDescription(request.getDescription());
            todorepo.save(todo);
            return true;
        }
        else {
            return false;
        }
    }

    public boolean MarkTodo(int id, UpdateStatusRequest request)
    {
      Optional<Todo> t=todorepo.findById(id);
      if(t.isPresent())
      {
          Date d=new Date();
          Todo todo=t.get();
          todo.setUpdatedDate(d);
          todo.setStatus(request.getStatus());
          todorepo.save(todo);
          return true;
      }
      else {
          return false;
      }
    }

    public TodoResponse getAllTodoOfProject(int project_id) {

        List<Todo> list= (List<Todo>) todorepo.findAll();
        List<TodoVO> todos=buildtodosOfProjects(list,project_id);
        return new TodoResponse(todos);

    }

    private List<TodoVO> buildtodosOfProjects(List<Todo> list,int id) {
        List<TodoVO> response=new ArrayList<>();
        for(Todo t:list)
        {
            TodoVO todo=new TodoVO();
           // System.out.println(t.getProject().getId());
            if(t.getProject()!=null&&t.getProject().getId()==id) {
                //System.out.println();
                todo.setId(t.getId());
                todo.setUpdatedDate(t.getUpdatedDate());
                todo.setCreatedDate(t.getCreatedDate());
                todo.setDescription(t.getDescription());
                todo.setStatus(t.getStatus());
                Project project=t.getProject();
                todo.setProject(project);
                response.add(todo);
            }

        }
        return response;
    }

    public TodoVO getTodoProject(int id) {
        Optional<Todo> todo=todorepo.findById(id);
        return buildTodoProject(todo);


    }

    private TodoVO buildTodoProject(Optional<Todo> todo) {
        TodoVO todovo=new TodoVO();
        todovo.setId(todo.get().getId());
        todovo.setStatus(todo.get().getStatus());
        todovo.setProject(todo.get().getProject());
        todovo.setCreatedDate(todo.get().getCreatedDate());
        todovo.setUpdatedDate(todo.get().getUpdatedDate());
        todovo.setDescription(todo.get().getDescription());
        return todovo;
    }



    private ProjectVO buildProjectsVO(Optional<Project> p) {
        ProjectVO projectVO=new ProjectVO();
        projectVO.setId(p.get().getId());
        projectVO.setTitle(p.get().getTitle());
        projectVO.setCreatedDate(p.get().getCreatedDate());
        projectVO.setTodos(p.get().getTodos());
        return projectVO;

    }
}

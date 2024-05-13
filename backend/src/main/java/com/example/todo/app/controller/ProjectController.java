package com.example.todo.app.controller;


import com.example.todo.app.entity.Project;
import com.example.todo.app.repository.ProjectRepo;
import com.example.todo.app.request.AddProjectRequest;
import com.example.todo.app.request.AddTodoRequest;
import com.example.todo.app.response.ProjectResponse;
import com.example.todo.app.response.ProjectVO;
import com.example.todo.app.response.TodoVO;
import com.example.todo.app.services.ProjectService;
import com.example.todo.app.services.TodoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ProjectController {

    @Autowired
    private ProjectService projectService;


    //get all projects
    @GetMapping("/project/getAllProject")
    public ResponseEntity<ProjectResponse> getAllProject()
    {
        ProjectResponse response = projectService.getProjects();
       // System.out.println(response+"---response");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    //add Project

    @PostMapping("/project/addProject")
    public ResponseEntity<ProjectVO>addProject(@RequestBody AddProjectRequest project)
    {

        return ResponseEntity.status(HttpStatus.CREATED).body(
                projectService.addProject(project)

        );

    }

    //get Project by Id
    @GetMapping("/project/getProjectById/{id}")
    public ResponseEntity<ProjectVO>getProjectById(@PathVariable int id)
    {
        ProjectVO returnedVO=projectService.getProjectById(id);
        return new ResponseEntity<>(returnedVO,HttpStatus.OK);

    }
  /*  @Autowired
    private ProjectRepo projectRepo;
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id)
    {
        projectRepo.deleteById(id);
    }*/
}

package com.example.todo.app.services;

import com.example.todo.app.entity.Project;
import com.example.todo.app.repository.ProjectRepo;
import com.example.todo.app.request.AddProjectRequest;
import com.example.todo.app.response.ProjectResponse;
import com.example.todo.app.response.ProjectVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepo projectRepo;
    public ProjectResponse getProjects() {
       List<Project>projectsList= (List<Project>) projectRepo.findAll();
       List<ProjectVO> projects= buildProjects(projectsList);
       System.out.println(projects+"-----");
        return new ProjectResponse(projects);

    }

    private List<ProjectVO> buildProjects(List<Project> productList) {
        List<ProjectVO> response = new ArrayList<>();
        for (Project p : productList) {
            ProjectVO prod = new ProjectVO();
            prod.setId(p.getId());
            prod.setCreatedDate(p.getCreatedDate());
            prod.setTitle(p.getTitle());
            response.add(prod);
        }
        return response;
    }

    public ProjectVO addProject(AddProjectRequest project) {
        return createProject(project);
    }

    public ProjectVO createProject(AddProjectRequest project)
    {
        System.out.println(project.getTitle());
        Project p=new Project();
        p.setTitle(project.getTitle());
        Project t=projectRepo.save(p);
        ProjectVO pvo=new ProjectVO();
        pvo.setId(t.getId());
        System.out.println(t.getId());
        pvo.setCreatedDate(t.getCreatedDate());
        System.out.println(t.getCreatedDate());
        pvo.setTitle(t.getTitle());
        System.out.println(t.getTitle());
        pvo.setTodos(Collections.EMPTY_LIST);
        System.out.println(pvo);
        return pvo;
    }

    public ProjectVO getProjectById(int id) {
        Optional<Project> p=projectRepo.findById(id);
        return buildProjectsVO(p);
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

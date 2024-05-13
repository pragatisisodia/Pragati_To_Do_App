package com.example.todo.app.Project;

import com.example.todo.app.controller.ProjectController;
import com.example.todo.app.request.AddProjectRequest;
import com.example.todo.app.response.ProjectResponse;
import com.example.todo.app.response.ProjectVO;
import com.example.todo.app.services.ProjectService;
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

class ProjectControllerTests {

    @Mock
    private ProjectService projectService;

    @InjectMocks
    private ProjectController projectController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }




    @Test
    void testGetAllProject() {
        // Mocking projectService.getProjects()
        List<ProjectVO> projectVOList = new ArrayList<>();
        ProjectVO projectVO = new ProjectVO();
        projectVO.setId(1);
        projectVO.setTitle("Project 1");
        projectVO.setCreatedDate(new Date());
        projectVOList.add(projectVO);
        ProjectResponse projectResponse = new ProjectResponse(projectVOList);
        when(projectService.getProjects()).thenReturn(projectResponse);

        // Testing getAllProject() method
        ResponseEntity<ProjectResponse> responseEntity = projectController.getAllProject();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(projectResponse, responseEntity.getBody());
    }

    @Test
    void testAddProject() {
        // Mocking projectService.addProject()
        AddProjectRequest addProjectRequest = new AddProjectRequest("New Project");
        ProjectVO projectVO = new ProjectVO();
        projectVO.setId(1);
        projectVO.setTitle("New Project");
        projectVO.setCreatedDate(new Date());
        when(projectService.addProject(addProjectRequest)).thenReturn(projectVO);

        // Testing addProject() method
        ResponseEntity<ProjectVO> responseEntity = projectController.addProject(addProjectRequest);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(projectVO, responseEntity.getBody());
    }

    @Test
    void testGetProjectById() {
        // Mocking projectService.getProjectById()
        int projectId = 1;
        ProjectVO projectVO = new ProjectVO();
        projectVO.setId(projectId);
        projectVO.setTitle("Project 1");
        projectVO.setCreatedDate(new Date());
        when(projectService.getProjectById(projectId)).thenReturn(projectVO);

        // Testing getProjectById() method
        ResponseEntity<ProjectVO> responseEntity = projectController.getProjectById(projectId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(projectVO, responseEntity.getBody());
    }

    // Other test cases for ProjectController can be added similarly
}


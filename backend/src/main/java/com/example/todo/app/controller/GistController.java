package com.example.todo.app.controller;

import com.example.todo.app.response.ProjectVO;
import com.example.todo.app.services.GistService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/gist")
public class GistController {

    @Autowired
    private GistService gistService;

    @PostMapping()
    public ResponseEntity<List<String>> createGistMarkdown() {
        // fetch all projects from Database
        List<String> gistLink = new ArrayList<>();
        List<ProjectVO> listProject = gistService.getAllProjects();
        listProject.forEach((project) -> {
            String title = project.getTitle();
            int id = project.getId();
            System.out.println(title + id);
            List<String> pendingTask = gistService.getAllTask(id, "pending");
            List<String> completedTask = gistService.getAllTask(id, "completed");
            System.out.println(pendingTask);
            System.out.println(completedTask);
            String markdownContent = gistService.generateMarkdownContent(title, pendingTask, completedTask);

            String gistId = null;
            try {
                gistId = gistService.newcreateGist(title + ".md", markdownContent);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
            if (gistId != null) {
                // Return the URL of the created gist
                gistLink.add("https://gist.github.com/" + gistId);
                System.out.println("Gist created successfully! URL: https://gist.github.com/" + gistId);
            } else {
                System.out.println("Failed to create gist.");
            }
        });
        return ResponseEntity.status(200).body(gistLink);
    }
}

package com.example.todo.app.services;

import com.example.todo.app.entity.Project;
import com.example.todo.app.entity.Todo;
import com.example.todo.app.repository.ProjectRepo;
import com.example.todo.app.repository.TodoRepo;
import com.example.todo.app.response.ProjectVO;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONObject;

@Service
public class GistService {

    @Autowired
    private ProjectRepo projectRepo;

    @Autowired
    private TodoRepo todoRepo;

    private String YOUR_GITHUB_TOKEN = "please-let-me-know-if-u-need-github-token-to-run-gist-api";

    public List<ProjectVO> getAllProjects() {
        List<Project> projects = (List<Project>) projectRepo.findAll();
        List<ProjectVO> returnProjects = new ArrayList<>();
        projects.forEach((project) -> {
            ProjectVO projectVO = new ProjectVO();
            projectVO.setTitle(project.getTitle());
            projectVO.setId(project.getId());
            returnProjects.add(projectVO);
        });
        return returnProjects;
    }

    public List<String> getAllTask(int projectId, String status) {
        return todoRepo.findAllTodosBasedOnStatus(status.toUpperCase(), projectId);
    }

    public String generateMarkdownContent(String projectTitle, List<String> pendingTodos, List<String> completedTodos) {

        StringBuilder markdownContent = new StringBuilder();
        markdownContent.append("# ").append(projectTitle).append("\n\n");

        // Append summary
        int totalTodos = pendingTodos.size() + completedTodos.size();
        String summary = String.format("**Summary:** %d / %d completed.\n\n", completedTodos.size(), totalTodos);
        markdownContent.append(summary);

        // Append section for pending todos
        markdownContent.append("## Section 1: Pending Todos\n\n");
        for (String name : pendingTodos) {
            markdownContent.append("- [ ] ").append(name).append("\n");
        }
        markdownContent.append("\n");

        // Append section for completed todos
        markdownContent.append("## Section 2: Completed Todos\n\n");
        for (String name : completedTodos) {
            markdownContent.append("- [x] ").append(name).append("\n");
        }
        markdownContent.append("\n");

        // Return the generated markdown content
        return markdownContent.toString();
    }

    public String newcreateGist(String filename, String content) throws JSONException {
        // GitHub API endpoint for creating a gist
        String apiUrl = "https://api.github.com/gists";

        // Create JSON object for the files
        JSONObject filesObject = new JSONObject();
        filesObject.put(filename, new JSONObject().put("content", content));

        // Create JSON object for the request body
        JSONObject requestBody = new JSONObject();
        requestBody.put("files", filesObject);
        requestBody.put("public", false);

        // Set up headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setBearerAuth(YOUR_GITHUB_TOKEN); // Replace with your GitHub token

        // Create an HTTP entity with the request body and headers
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody.toString(), headers);

        // Send the request to create the gist
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.POST, requestEntity, String.class);

        // Check if the request was successful
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            // Parse the response to get the gist ID
            String responseBody = responseEntity.getBody();
            String gistId = new JSONObject(responseBody).getString("id");
            return gistId;
        } else {
            // Request failed, return null
            return null;
        }
    }

    public String createGist(String filename, String content) {
        // GitHub API endpoint for creating a gist
        String apiUrl = "https://api.github.com/gists";

        // Construct the request body
        String requestBody = String.format("{\"files\": {\"%s\": {\"content\": \"%s\"}}, \"public\": false}", filename, escapeJson(content));

        // Set up headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setBearerAuth(YOUR_GITHUB_TOKEN); // Replace with your GitHub token

        // Create an HTTP entity with the request body and headers
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        // Send the request to create the gist
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.POST, requestEntity, String.class);

        // Check if the request was successful
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            // Parse the response to get the gist ID
            String responseBody = responseEntity.getBody();
            String gistId = responseBody.split("\"")[3]; // Extract gist ID from the response JSON
            return gistId;
        } else {
            // Request failed, return null
            return null;
        }
    }
    private String escapeJson(String content) {
        return content.replace("\\", "\\\\").replace("\"", "\\\"");
    }

}

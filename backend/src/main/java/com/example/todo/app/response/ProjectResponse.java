package com.example.todo.app.response;

import lombok.*;

import java.util.List;



@AllArgsConstructor
@Data
@NoArgsConstructor
public class ProjectResponse {

    private List<ProjectVO> projects;


}

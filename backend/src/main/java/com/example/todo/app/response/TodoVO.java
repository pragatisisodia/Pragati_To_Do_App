package com.example.todo.app.response;


import com.example.todo.app.entity.Project;
import com.example.todo.app.model.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoVO {
    private int id;
    private String description;
    private Date createdDate;
    private Date updatedDate;
    private Status status;
    private Project project;


}

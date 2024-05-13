package com.example.todo.app.request;


import com.example.todo.app.entity.Project;
import com.example.todo.app.model.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddTodoRequest {

    private String description;
    private Status status;
    private int project_id;


}

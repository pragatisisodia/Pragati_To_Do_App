package com.example.todo.app.request;

import com.example.todo.app.model.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTodoRequest {
    private String description;
    private Status status;
}

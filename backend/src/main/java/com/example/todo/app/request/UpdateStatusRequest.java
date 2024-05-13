package com.example.todo.app.request;

import com.example.todo.app.model.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateStatusRequest {

    private Status status;
}

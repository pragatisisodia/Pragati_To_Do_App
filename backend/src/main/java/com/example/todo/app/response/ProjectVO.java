package com.example.todo.app.response;

import com.example.todo.app.entity.Todo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectVO {

    private int id;
    private Date createdDate;
    private String title;




    private List<Todo> todos=new ArrayList<>();


    public void setId(int id) {
        this.id = id;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTodos(List<Todo> todos) {
        this.todos = todos;
    }
}

package com.example.todo.app.entity;


import com.example.todo.app.model.Status;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Setter
@Getter
@AllArgsConstructor
@ToString
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String description;
    private Date createdDate;
    private Date updatedDate;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne
    @JoinColumn(name = "project_id")
    @JsonIgnoreProperties("todos") // Ignore the todos field during serialization
    private Project project;



    public Todo()
    {
        this.createdDate=new Date();
        this.updatedDate=new Date();
    }



}

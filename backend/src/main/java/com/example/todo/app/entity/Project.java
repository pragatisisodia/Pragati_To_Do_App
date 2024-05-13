package com.example.todo.app.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@ToString
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date createdDate;
    private String title;

   // private List<Todo> todos;


   // @OneToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "address_id", referencedColumnName = "id")


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "id")
    private List<Todo> todos=new ArrayList<>();

    public Project()
    {
        this.createdDate=new Date();
    }

}

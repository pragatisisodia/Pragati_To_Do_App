package com.example.todo.app.repository;

import com.example.todo.app.entity.Project;
import org.springframework.data.repository.CrudRepository;

public interface ProjectRepo extends CrudRepository<Project,Integer> {
}

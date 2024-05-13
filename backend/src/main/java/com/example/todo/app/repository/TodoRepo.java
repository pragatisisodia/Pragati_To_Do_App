package com.example.todo.app.repository;

import com.example.todo.app.entity.Todo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepo extends CrudRepository<Todo,Integer> {

    @Query(nativeQuery = true, value = "SELECT description from todo where status = :status AND project_id = :projectId")
    List<String> findAllTodosBasedOnStatus(@Param("status") String status, @Param("projectId") int projectId);


}

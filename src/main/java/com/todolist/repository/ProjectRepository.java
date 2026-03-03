package com.todolist.repository;

import com.todolist.model.Project;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
	// O Spring Data JPA cria a query automaticamente baseada no nome do método!
    List<Project> findByUserId(Long userId);
}

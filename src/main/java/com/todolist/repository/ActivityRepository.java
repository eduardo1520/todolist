package com.todolist.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.todolist.model.Activity;
import com.todolist.model.ActivityStatus;

@Repository
public interface ActivityRepository extends JpaRepository<Activity,Long> {
	List<Activity> findByProjectId(Long projectId);
	List<Activity> findByProjectIdAndStatus(Long projectId, ActivityStatus status);
	Optional<Activity> findByIdAndProjectId(Long id, Long projectId);
}

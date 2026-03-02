package com.todolist.service;

import com.todolist.dto.ActivityDTO;
import com.todolist.exception.ResourceNotFoundException;
import com.todolist.model.Activity;
import com.todolist.model.ActivityStatus;
import com.todolist.model.Project;
import com.todolist.repository.ActivityRepository;
import com.todolist.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActivityService {

	@Autowired
    private  ActivityRepository activityRepository;
    @Autowired
	private  ProjectRepository projectRepository;

    public List<ActivityDTO.Response> findByProject(Long projectId, ActivityStatus status) {
        getProjectOrThrow(projectId);
        List<Activity> activities = (status != null)
                ? activityRepository.findByProjectIdAndStatus(projectId, status)
                : activityRepository.findByProjectId(projectId);
        return activities.stream().map(this::toResponse).toList();
    }

    public ActivityDTO.Response findById(Long projectId, Long activityId) {
        Activity activity = getActivityOrThrow(projectId, activityId);
        return toResponse(activity);
    }

    @Transactional
    public ActivityDTO.Response create(Long projectId, ActivityDTO.Request request) {
        Project project = getProjectOrThrow(projectId);
        Activity activity = new Activity();
        activity.setTitle(request.getTitle());
        activity.setDescription(request.getDescription());
        activity.setStatus(request.getStatus() != null ? request.getStatus() : ActivityStatus.TODO);
        activity.setDueDate(request.getDueDate());
        activity.setProject(project);
        return toResponse(activityRepository.save(activity));
    }

    @Transactional
    public ActivityDTO.Response update(Long projectId, Long activityId, ActivityDTO.Request request) {
        Activity activity = getActivityOrThrow(projectId, activityId);
        activity.setTitle(request.getTitle());
        activity.setDescription(request.getDescription());
        activity.setStatus(request.getStatus() != null ? request.getStatus() : activity.getStatus());
        activity.setDueDate(request.getDueDate());
        return toResponse(activityRepository.save(activity));
    }

    @Transactional
    public ActivityDTO.Response updateStatus(Long projectId, Long activityId, ActivityDTO.StatusRequest request) {
        Activity activity = getActivityOrThrow(projectId, activityId);
        activity.setStatus(request.getStatus());
        return toResponse(activityRepository.save(activity));
    }

    @Transactional
    public void delete(Long projectId, Long activityId) {
        Activity activity = getActivityOrThrow(projectId, activityId);
        activityRepository.delete(activity);
    }

    private Project getProjectOrThrow(Long projectId) {
        return projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Projeto não encontrado com id: " + projectId));
    }

    private Activity getActivityOrThrow(Long projectId, Long activityId) {
        return activityRepository.findByIdAndProjectId(activityId, projectId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Atividade " + activityId + " não encontrada no projeto " + projectId));
    }

    private ActivityDTO.Response toResponse(Activity activity) {
        ActivityDTO.Response response = new ActivityDTO.Response();
        response.setId(activity.getId());
        response.setTitle(activity.getTitle());
        response.setDescription(activity.getDescription());
        response.setStatus(activity.getStatus());
        response.setDueDate(activity.getDueDate());
        response.setProjectId(activity.getProject().getId());
        response.setCreatedAt(activity.getCreatedAt());
        response.setUpdatedAt(activity.getUpdatedAt());
        return response;
    }
}

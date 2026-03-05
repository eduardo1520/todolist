package com.todolist.service;

import com.todolist.dto.ActivityDTO;
import com.todolist.dto.ProjectDTO;
import com.todolist.exception.ResourceNotFoundException;
import com.todolist.model.Activity;
import com.todolist.model.ActivityStatus;
import com.todolist.model.Project;
import com.todolist.model.User;
import com.todolist.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {
	@Autowired
    private  ProjectRepository projectRepository;

    public List<ProjectDTO.Response> findAll() {
        return projectRepository.findAll().stream().map(this::toResponse).toList();
    }
    
    public List<ProjectDTO.Response> findAllMyProjects(User user) {
        // Buscamos apenas o que pertence ao ID do usuário logado
        List<Project> projects = projectRepository.findByUserId(user.getId());
        // Converte a lista de entidades para uma lista de DTOs de resposta
        return projects.stream().map(this::toResponse).collect(Collectors.toList());
    }
    
    public ProjectDTO.Response findById(Long id) {
        Project project = getOrThrow(id);
        ProjectDTO.Response response = new ProjectDTO.Response();
        response.setId(project.getId());
        response.setName(project.getName());
        response.setDescription(project.getDescription());
        
        List<ActivityDTO.Response> activityResponses = project.getActivities().stream()
                .map(activity -> {
                    ActivityDTO.Response actDto = new ActivityDTO.Response();
                    actDto.setId(activity.getId());
                    actDto.setTitle(activity.getTitle());
                    actDto.setStatus(activity.getStatus()); 
                    actDto.setDescription(activity.getDescription());
                    return actDto;
                }).collect(Collectors.toList());
            response.setActivities(activityResponses);
        return toResponse(project);
    }

    @Transactional
    public ProjectDTO.Response create(ProjectDTO.Request request, User user) {
        Project project = new Project();
        String name = (String) request.getName();
        project.setName(name);
        String description = (String ) request.getDescription();
        project.setDescription(description);
        project.setUser(user);
        return toResponse(projectRepository.save(project));
    }

    @Transactional
    public ProjectDTO.Response update(Long id, ProjectDTO.Request request) {
        Project project = getOrThrow(id);
        String name = (String) request.getName();
        project.setName(name);
        String description = (String ) request.getDescription();
        project.setDescription(description);
        
        project.setImageUrl(request.getImageUrl());
        
        return toResponse(projectRepository.save(project));
    }

    @Transactional
    public void delete(Long id) {
        Project project = getOrThrow(id);
        projectRepository.delete(project);
    }

    private Project getOrThrow(Long id) {
        return projectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Projeto não encontrado com id: " + id));
    }

    private ProjectDTO.Response toResponse(Project project) {
        ProjectDTO.Response response = new ProjectDTO.Response();
        response.setId(project.getId());
        response.setName(project.getName());
        response.setDescription(project.getDescription());
        response.setCreatedAt(project.getCreatedAt());
        response.setUpdatedAt(project.getUpdatedAt());
        List<Activity> activities = project.getActivities();
        response.setTotalActivities(activities.size());
        response.setTodoCount(activities.stream().filter(a -> a.getStatus() == ActivityStatus.TODO).count());
        response.setDoingCount(activities.stream().filter(a -> a.getStatus() == ActivityStatus.DOING).count());
        response.setDoneCount(activities.stream().filter(a -> a.getStatus() == ActivityStatus.DONE).count());
        boolean finished = !activities.isEmpty() && activities.stream().allMatch(a -> a.getStatus() == ActivityStatus.DONE);
        response.setFinished(finished);
        response.setImageUrl(project.getImageUrl());
        if (activities != null) {
            List<ActivityDTO.Response> activityResponses = activities.stream()
                .map(activity -> {
                    ActivityDTO.Response actDto = new ActivityDTO.Response();
                    actDto.setId(activity.getId());
                    actDto.setTitle(activity.getTitle());
                    actDto.setDescription(activity.getDescription());
                    actDto.setStatus(activity.getStatus()); 
                    
                    actDto.setCreatedAt(activity.getCreatedAt());
                    actDto.setStartedAt(activity.getStartedAt());
                    actDto.setFinishedAt(activity.getFinishedAt());
                    return actDto;
                })
                .collect(Collectors.toList());
                
            response.setActivities(activityResponses);
        }
        return response;
    }
}

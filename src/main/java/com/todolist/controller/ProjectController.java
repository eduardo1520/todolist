package com.todolist.controller;

import com.todolist.dto.ProjectDTO;
import com.todolist.model.User;
import com.todolist.repository.UserRepository;
import com.todolist.security.services.UserDetailsImpl;
import com.todolist.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "https://project-todolist-frontend-mwo98bb8d.vercel.app", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.PATCH,RequestMethod.DELETE, RequestMethod.OPTIONS})
@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping
	public ResponseEntity<List<ProjectDTO.Response>> findAll() {
		return ResponseEntity.ok(projectService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProjectDTO.Response> findById(@PathVariable Long id) {
		return ResponseEntity.ok(projectService.findById(id));
	}

	@PostMapping
	public ResponseEntity<ProjectDTO.Response> create(@Valid @RequestBody ProjectDTO.Request request) {
	    UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    User currentUser = userRepository.findById(userDetails.getId()).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
	    ProjectDTO.Response response = projectService.create(request, currentUser);
	    return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProjectDTO.Response> update(@PathVariable Long id,
			@Valid @RequestBody ProjectDTO.Request request) {
		return ResponseEntity.ok(projectService.update(id, request));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		projectService.delete(id);
		return ResponseEntity.noContent().build();
	}
}

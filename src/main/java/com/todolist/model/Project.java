package com.todolist.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference 
    private List<Activity> activities = new ArrayList<>();
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @Lob
    @Column(name = "image_url", columnDefinition = "TEXT")
    private String imageUrl;

    public Long getId() { 
    	return id;
    }
    
    public void setId(Long id) {
    	this.id = id; 
    }

    public String getName() { 
    	return name; 
    }
    
    public void setName(String name) { 
    	this.name = name; 
    }

    public String getDescription() { 
    	return description; 
    }
    
    public void setDescription(String description) { 
    	this.description = description; 
    }

    public LocalDateTime getCreatedAt() { 
    	return createdAt; 
    }
    
    public void setCreatedAt(LocalDateTime createdAt) { 
    	this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() { 
    	return updatedAt; 
    }
    
    public void setUpdatedAt(LocalDateTime updatedAt) { 
    	this.updatedAt = updatedAt; 
    }

    public List<Activity> getActivities() { 
    	return activities; 
    }
    
    public void setActivities(List<Activity> activities) { 
    	this.activities = activities; 
    }
	
    public void setUser(User newUser) {
		user = newUser;
	}
    
    public User getUser(){
    	return user;
    }
    
    public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
}
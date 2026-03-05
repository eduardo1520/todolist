package com.todolist.dto;

import com.todolist.model.ActivityStatus;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ActivityDTO {

    public static class Request {
        private String title;
        private String description;
        private ActivityStatus status = ActivityStatus.TODO;
        private LocalDate dueDate;
        private LocalDateTime startedAt;
        private LocalDateTime finishedAt;

        public String getTitle() { 
        	return title; 
        }
        
        public void setTitle(String title) { 
        	this.title = title; 
        }

        public String getDescription() { 
        	return description;
        }
        
        public void setDescription(String description) { 
        	this.description = description;
        }

        public ActivityStatus getStatus() { 
        	return status; 
        }
        
        public void setStatus(ActivityStatus status) { 
        	this.status = status; 
        }

        public LocalDate getDueDate() { 
        	return dueDate;
        }
        
        public void setDueDate(LocalDate dueDate) { 
        	this.dueDate = dueDate; 
        }

		public LocalDateTime getStartedAt() {
			return startedAt;
		}

		public void setStartedAt(LocalDateTime startedAt) {
			this.startedAt = startedAt;
		}

		public LocalDateTime getFinishedAt() {
			return finishedAt;
		}

		public void setFinishedAt(LocalDateTime finishedAt) {
			this.finishedAt = finishedAt;
		}
        
    }

    public static class StatusRequest {
        private ActivityStatus status;

        public ActivityStatus getStatus() { 
        	return status; 
    	}
        
        public void setStatus(ActivityStatus status) { 
        	this.status = status;
        }
    }

    public static class Response {
        private Long id;
        private String title;
        private String description;
        private ActivityStatus status;
        private LocalDate dueDate;
        private Long projectId;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
		private LocalDateTime startedAt;
		private LocalDateTime finishedAt;

        public Long getId() { 
        	return id;
    	}
        
        public void setId(Long id) { 
        	this.id = id;
    	}

        public String getTitle() {
        	return title; 
    	}
        
        public void setTitle(String title) { 
        	this.title = title; 
    	}

        public String getDescription() { 
        	return description; 
    	}
        
        public void setDescription(String description) { 
        	this.description = description; 
    	}

        public ActivityStatus getStatus() { 
        	return status; 
    	}
        
        public void setStatus(ActivityStatus status) { 
        	this.status = status; 
    	}

        public LocalDate getDueDate() { 
        	return dueDate;
    	}
        
        public void setDueDate(LocalDate dueDate) { 
        	this.dueDate = dueDate; 
    	}

        public Long getProjectId() { 
        	return projectId; 
    	}
        
        public void setProjectId(Long projectId) { 
        	this.projectId = projectId; 
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
        
        public LocalDateTime getStartedAt() {
			return startedAt;
		}
        
		public void setStartedAt(LocalDateTime started_at) {
			this.startedAt = started_at;			
		}
		
		public LocalDateTime getFinishedAt() {
			return finishedAt;
		}
		
		public void setFinishedAt(LocalDateTime finished_at) {
			this.finishedAt = finished_at;
		}
    }
}
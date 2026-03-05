package com.todolist.dto;

import java.time.LocalDateTime;
import java.util.List;


public class ProjectDTO {

	public static class Request {
        private String name;
        private String description;
        private String imageUrl;

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
		
        public String getImageUrl() {
			return imageUrl;
		}
        
        public void setImageUrl(String imageUrl) {
			this.imageUrl = imageUrl;
		}
		
    }

    public static class Response {
        private Long id;
        private String name;
        private String description;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private long totalActivities;
        private long todoCount;
        private long doingCount;
        private long doneCount;
        private boolean finished;
        private String imageUrl;
        
        private List<ActivityDTO.Response> activities;

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

        public long getTotalActivities() { 
        	return totalActivities;
        }
        
        public void setTotalActivities(long totalActivities) { 
        	this.totalActivities = totalActivities; 
        }

        public long getTodoCount() { 
        	return todoCount; 
        }
        
        public void setTodoCount(long todoCount) { 
        	this.todoCount = todoCount; 
        }

        public long getDoingCount() { 
        	return doingCount;
        }
        
        public void setDoingCount(long doingCount) { 
        	this.doingCount = doingCount; 
        }

        public long getDoneCount() { 
        	return doneCount;
        }
        
        public void setDoneCount(long doneCount) { 
        	this.doneCount = doneCount;
        }
        
        public boolean isFinished() { 
        	return finished; 
    	}
		
        public void setFinished(boolean statusFinished) {
			finished = statusFinished;			
		}
        
        public List<ActivityDTO.Response> getActivities() {
			return activities;
		}
        
        public void setActivities(List<ActivityDTO.Response> activities) {
			this.activities = activities;
		}
        
        public String getImageUrl() {
			return imageUrl;
		}
        
        public void setImageUrl(String imageUrl) {
			this.imageUrl = imageUrl;
		}
        
    }
}
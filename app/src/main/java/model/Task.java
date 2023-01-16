package model;

import java.util.Date;

public class Task {
    
    private Integer id;
    private Integer idProject;
    private String name;
    private String description;
    private String notes;
    private Boolean completed;
    private Date deadline; 
    private Date createdAt;
    private Date updatedAt;

    public Task(Integer id, Integer idProject, String name, String description, String notes, Boolean completed, Date deadline, Date createdAt, Date updatedAt) {
        this.id = id;
        this.idProject = idProject;
        this.name = name;
        this.description = description;
        this.notes = notes;
        this.completed = completed;
        this.deadline = deadline;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    
     public Task() {
         //this.deadline = new Date();
        this.completed = false;
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getIdProject() {
        return idProject;
    }
    public void setIdProject(Integer idProject) {
        this.idProject = idProject;
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
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
    public Boolean getCompleted() {
        return completed;
    }
    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
    public Date getDeadline() {
        return deadline;
    }
    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }
    public Date getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    public Date getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Task{" + "id=" + id + ", idProject=" + idProject + ", name=" + name + ", notes=" + notes + ", isCompleted=" + completed + ", deadline=" + deadline + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + '}';
    }
    
    
}

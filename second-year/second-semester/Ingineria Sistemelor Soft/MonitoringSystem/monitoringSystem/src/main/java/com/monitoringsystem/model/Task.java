package com.monitoringsystem.model;


import jakarta.persistence.Id;
import jakarta.persistence.Table;

@jakarta.persistence.Entity
@Table( name = "task")
public class Task extends Entity<Long> {
    private String description;
    private Status status;
    private Long employeeId;

    public Task() {

    }

    public Task(Long id, String description, Status status, Long employeeId) {
        this.description = description;
        this.status = status;
        this.employeeId = employeeId;
        setId(id);
    }

    @Override
    @Id
    public Long getId() {
        return super.getId();
    }

    @Override
    public void setId(Long aLong) {
        super.setId(aLong);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }
}

package com.monitoringsystem.repository.api;

import com.monitoringsystem.model.Task;

import java.util.List;


public interface TaskRepository extends Repository<Long, Task> {
    List<Task> getSpecificTasks(Long userId);
    void finishTask(Long id);
}

package com.monitoringsystem.service.api;

import com.monitoringsystem.model.Task;
import com.monitoringsystem.model.User;
import com.monitoringsystem.utils.Observable;

import java.util.List;

public interface TaskService extends Observable {
    void add(String description, Long userId);
    List<Task> getAll();
    List<Task> getSpecificTasks(Long userId);
    void finishTask(Long id);
}

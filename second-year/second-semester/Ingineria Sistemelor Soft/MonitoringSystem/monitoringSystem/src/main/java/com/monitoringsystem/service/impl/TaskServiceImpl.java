package com.monitoringsystem.service.impl;

import com.monitoringsystem.model.Status;
import com.monitoringsystem.model.Task;
import com.monitoringsystem.repository.api.TaskRepository;
import com.monitoringsystem.service.api.TaskService;
import com.monitoringsystem.utils.Observable;
import com.monitoringsystem.utils.Observer;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class TaskServiceImpl implements Observable, TaskService {
    private final TaskRepository taskRepository;
    private final Random random;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
        random = new Random();
    }

    @Override
    public void add(String description, Long employeeId) {
        Task task = new Task(random.nextLong(), description, Status.PENDING, employeeId);
        taskRepository.add(task);
        notifyUpdate();
    }

    @Override
    public List<Task> getAll() {
        return taskRepository.getAll();
    }

    @Override
    public List<Task> getSpecificTasks(Long userId) {
        return taskRepository.getSpecificTasks(userId);
    }

    @Override
    public void finishTask(Long id) {
        taskRepository.finishTask(id);
        notifyUpdate();
    }
}
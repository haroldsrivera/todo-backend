package com.bacobogota.todo.service;

import com.bacobogota.todo.model.Task;
import com.bacobogota.todo.repository.ITaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TaskService {

    private final ITaskRepository taskRepository;

    public TaskService(ITaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new IllegalArgumentException("Tarea no encontrada con el id: " + id);
        }
        taskRepository.deleteById(id);
    }
}

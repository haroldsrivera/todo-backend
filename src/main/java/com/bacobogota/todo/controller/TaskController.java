package com.bacobogota.todo.controller;

import com.bacobogota.todo.DTO.TaskDTO;
import com.bacobogota.todo.model.Task;
import com.bacobogota.todo.service.TaskService;
import com.bacobogota.todo.mapper.TaskMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    @PostMapping
    public ResponseEntity<Map<String, String>> createTask(@Valid @RequestBody TaskDTO taskDTO) {
        Task task = TaskMapper.toEntity(taskDTO);
        taskService.saveTask(task);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Tarea creada exitosamente");

        return ResponseEntity.ok(response);
    }


    // Endpoint para retornar todas las tareas
    @GetMapping
    public ResponseEntity<List<TaskDTO>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        List<TaskDTO> taskDTOs = tasks.stream()
                .map(TaskMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(taskDTOs);
    }

    // Endpoint para eliminar una tarea por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteTask(@PathVariable Long id) {
        try {
            taskService.deleteTask(id);

            Map<String, String> response = new HashMap<>();
            response.put("message", "Tarea eliminada exitosamente");

            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Tarea no encontrada");
            return ResponseEntity.status(404).body(error);
        }
    }
}

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

    // Endpoint para crear una nueva tarea
    @PostMapping
    public ResponseEntity<TaskDTO> createTask(@Valid @RequestBody TaskDTO taskDTO) {
        Task task = TaskMapper.toEntity(taskDTO);
        Task savedTask = taskService.saveTask(task);
        return ResponseEntity.ok(TaskMapper.toDTO(savedTask));
    }
    // Endpoint para obtener una tarea por ID
    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable Long id) {
        Task task = taskService.getTaskById(id);
        return ResponseEntity.ok(TaskMapper.toDTO(task));
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

    // Endpoint para actualizar una tarea por ID
    @PutMapping("/{id}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable Long id, @Valid @RequestBody TaskDTO taskDTO) {
        Task task = TaskMapper.toEntity(taskDTO);
        Task updatedTask = taskService.updateTask(id, task);
        return ResponseEntity.ok(TaskMapper.toDTO(updatedTask));
    }

}

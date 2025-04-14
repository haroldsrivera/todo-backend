package com.bacobogota.todo.controller;

import com.bacobogota.todo.DTO.TaskDTO;
import com.bacobogota.todo.mapper.TaskMapper;
import com.bacobogota.todo.model.Task;
import com.bacobogota.todo.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class TaskControllerTest {

    private MockMvc mockMvc;

    @Mock
    private TaskService taskService;

    @InjectMocks
    private TaskController taskController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(taskController).build();
    }

    @Test
    void testGetAllTasks() throws Exception {
        Task task1 = new Task(1L, "Task 1", "Description 1", LocalDate.parse("2025-04-12"));
        Task task2 = new Task(2L, "Task 2", "Description 2", LocalDate.parse("2025-04-13"));

        when(taskService.getAllTasks()).thenReturn(Arrays.asList(task1, task2));

        mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].title").value("Task 1"))
                .andExpect(jsonPath("$[1].title").value("Task 2"));
    }

    @Test
    void testCreateTask() throws Exception {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTitle("Task 1");
        taskDTO.setDescription("Description 1");
        taskDTO.setDate(LocalDate.parse("2025-04-12"));

        Task task = TaskMapper.toEntity(taskDTO);
        task.setId(1L);
        when(taskService.saveTask(any(Task.class))).thenReturn(task);

        String jsonRequest = """
        {
            "title": "Task 1",
            "description": "Description 1",
            "date": "2025-04-12"
        }""";

        mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Task 1"))
                .andExpect(jsonPath("$.description").value("Description 1"))
                .andExpect(jsonPath("$.date").value("2025-04-12"));
    }


    @Test
    void testDeleteTask_Success() throws Exception {
        doNothing().when(taskService).deleteTask(1L);

        mockMvc.perform(delete("/tasks/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Tarea eliminada exitosamente"));
    }

    @Test
    void testDeleteTask_NotFound() throws Exception {
        doThrow(new IllegalArgumentException("Task not found")).when(taskService).deleteTask(1L);

        mockMvc.perform(delete("/tasks/1"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Tarea no encontrada"));
    }

    @Test
    void testUpdateTask_Success() throws Exception {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTitle("Updated Task");
        taskDTO.setDescription("Updated Description");
        taskDTO.setDate(LocalDate.parse("2025-04-12"));

        Task updatedTask = new Task(1L, "Updated Task", "Updated Description", LocalDate.parse("2025-04-12"));
        when(taskService.updateTask(any(Long.class), any(Task.class))).thenReturn(updatedTask);

        String jsonRequest = """
            {
                "title": "Updated Task",
                "description": "Updated Description",
                "date": "2025-04-12"
            }""";

        mockMvc.perform(put("/tasks/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Task"));
    }

    @Test
    void findById() throws Exception {
        Task task = new Task(1L, "Task 1", "Description 1", LocalDate.parse("2025-04-12"));
        when(taskService.getTaskById(1L)).thenReturn(task);

        mockMvc.perform(get("/tasks/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Task 1"));
    }
}
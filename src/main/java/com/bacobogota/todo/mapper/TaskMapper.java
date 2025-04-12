package com.bacobogota.todo.mapper;

import com.bacobogota.todo.DTO.TaskDTO;
import com.bacobogota.todo.model.Task;

public class TaskMapper {

    public static Task toEntity(TaskDTO dto) {
        Task task = new Task();
        task.setId(dto.getId());
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setDate(dto.getDate());
        return task;
    }

    public static TaskDTO toDTO(Task task) {
        return new TaskDTO(task.getTitle(), task.getDescription(), task.getDate(), task.getId());
    }
}

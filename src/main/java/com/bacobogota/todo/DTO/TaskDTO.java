package com.bacobogota.todo.DTO;

import java.time.LocalDate;

public class TaskDTO {

    private Long id;
    private String title;
    private String description;

    private LocalDate date;

    public TaskDTO() {
    }

    public TaskDTO(String title, String description, LocalDate date, Long id) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }


}

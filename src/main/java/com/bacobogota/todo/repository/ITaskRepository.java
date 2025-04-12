package com.bacobogota.todo.repository;

import com.bacobogota.todo.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

//JPAREPOSITORY es una interfaz que permite realizar operaciones CRUD sobre la entidad Task
public interface ITaskRepository extends JpaRepository<Task, Long> {

}

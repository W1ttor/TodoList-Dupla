package com.doido.todolistback.repository;


import com.doido.todolistback.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}

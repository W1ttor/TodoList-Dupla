package com.doido.todolistback.infra.repositories;


import com.doido.todolistback.domain.task.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, Long> {
}

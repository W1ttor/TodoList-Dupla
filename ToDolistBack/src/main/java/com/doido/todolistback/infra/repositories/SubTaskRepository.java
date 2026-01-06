package com.doido.todolistback.infra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.doido.todolistback.domain.subtask.SubTask;

public interface SubTaskRepository extends JpaRepository<SubTask, Long> {
    
}

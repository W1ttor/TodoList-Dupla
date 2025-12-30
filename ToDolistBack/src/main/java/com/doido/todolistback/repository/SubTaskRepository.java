package com.doido.todolistback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.doido.todolistback.entity.SubTask;

public interface SubTaskRepository extends JpaRepository<SubTask, Long> {
    
}

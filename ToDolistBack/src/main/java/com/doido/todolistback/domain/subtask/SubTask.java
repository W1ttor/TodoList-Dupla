package com.doido.todolistback.domain.subtask;

import com.doido.todolistback.domain.task.entity.Task;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "TABLE_SUBTASK")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubTask {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nameSubTask;

    private Boolean completed;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

}

package com.doido.todolistback.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TABLE_SUBTASK")
@Getter
@Setter
@AllArgsConstructor
public class SubTask {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nameSubTask;

    private Boolean completed;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

}

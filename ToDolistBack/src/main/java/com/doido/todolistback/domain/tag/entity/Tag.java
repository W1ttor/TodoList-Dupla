package com.doido.todolistback.domain.tag.entity;

import com.doido.todolistback.domain.task.entity.Task;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "TABLE_TAG")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nameTag;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

}

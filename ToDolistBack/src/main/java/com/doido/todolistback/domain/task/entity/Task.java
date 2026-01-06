package com.doido.todolistback.domain.task.entity;

import com.doido.todolistback.domain.subtask.SubTask;
import com.doido.todolistback.domain.listClass.entity.ListClass;
import com.doido.todolistback.domain.tag.entity.Tag;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "TABLE_TASK")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    private String title;
    private String description;

    private Boolean completed;

    @OneToMany(mappedBy = "task")
    private List<Tag> tags;

    @ManyToOne
    @JoinColumn(name = "list_id")
    private ListClass listClass;

    @OneToMany(mappedBy = "task")
    private List<SubTask> subTasks;
}

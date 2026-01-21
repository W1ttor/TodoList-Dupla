package com.doido.todolistback.domain.task.entity;

import com.doido.todolistback.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "TABLE_TASK",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"user_id", "list"})
        })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID Id;

    private String title;
    private String description;

    private Boolean completed;

    @OneToMany(mappedBy = "task",  cascade = CascadeType.ALL,  orphanRemoval = true)
    private List<SubTask> subTasks;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    private String List;

    public void addSubTask(SubTask subTask){
        this.subTasks.add(subTask);
        subTask.setTask(this);
    }

}

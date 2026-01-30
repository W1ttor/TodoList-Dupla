package com.doido.todolistback.domain.task.entity;

import com.doido.todolistback.domain.subtask.SubTask;
import com.doido.todolistback.domain.listClass.entity.ListClass;
import com.doido.todolistback.domain.tag.entity.Tag;
import com.doido.todolistback.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "TABLE_TASK")
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

    @OneToMany(mappedBy = "task")
    private List<Tag> tags;

    @ManyToOne
    @JoinColumn(name = "list_id")
    private ListClass listClass;

    @OneToMany(mappedBy = "task")
    private List<SubTask> subTasks;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}

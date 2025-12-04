package com.doido.todolistback.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Task")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long UUID;

    private String title;
    private String desc;

    private Boolean completed;
}

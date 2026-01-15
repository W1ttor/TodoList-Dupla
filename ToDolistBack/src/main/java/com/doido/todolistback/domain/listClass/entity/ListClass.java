package com.doido.todolistback.domain.listClass.entity;

import java.util.List;
import java.util.UUID;

import com.doido.todolistback.domain.task.entity.Task;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TABLE_LIST")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ListClass {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nameList;

    @OneToMany(mappedBy = "listClass")
    private List<Task> tasks;


}

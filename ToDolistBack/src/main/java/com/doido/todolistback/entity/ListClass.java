package com.doido.todolistback.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TABLE_LIST")
@Getter
@Setter
@AllArgsConstructor
public class ListClass {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nameList;

    @OneToMany(mappedBy = "listClass")
    private List<Task> tasks;
    

}

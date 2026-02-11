package com.doido.todolistback.domain.task.shared.utils;

import com.doido.todolistback.domain.task.entity.Task;
import com.doido.todolistback.domain.task.repositories.TaskRepository;
import com.doido.todolistback.domain.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskSecurityUtils {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    public Task userIsOwner(Task task){
        return null;
    }

}

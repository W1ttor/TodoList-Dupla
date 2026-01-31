package com.doido.todolistback.domain.task.specification;

import com.doido.todolistback.domain.task.dtos.request.TaskRequest;
import com.doido.todolistback.domain.task.entity.Task;
import org.springframework.data.jpa.domain.Specification;

public class TaskSpecification {


    public static Specification<Task> completed(Boolean completed) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (completed == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("completed"), completed);
        };

    }

}

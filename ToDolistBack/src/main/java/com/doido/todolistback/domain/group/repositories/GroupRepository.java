package com.doido.todolistback.domain.group.repositories;

import com.doido.todolistback.domain.group.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;
import java.util.UUID;

public interface GroupRepository extends JpaRepository<Group, UUID> {


    @Query("""
            select distinct g
            from Group g
            join g.members m
            where m.user.id = :userId
            """)
    Set<Group> findAllByUserId(@Param("userId") UUID userId);
}

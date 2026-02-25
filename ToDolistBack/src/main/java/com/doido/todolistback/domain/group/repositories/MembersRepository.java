package com.doido.todolistback.domain.group.repositories;

import com.doido.todolistback.domain.group.entity.Members;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface MembersRepository extends JpaRepository<Members, UUID> {

    Optional<Members> findByUser_IdAndGroup_Id(UUID userId, UUID groupId);
    Set<Members> findAllByUser_IdAndGroup_Id(UUID userId,UUID groupId);
}

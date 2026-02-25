package com.doido.todolistback.web.controller;

import com.doido.todolistback.domain.group.dto.post.GroupPost;
import com.doido.todolistback.domain.group.dto.post.MembersPost;
import com.doido.todolistback.domain.group.entity.Group;
import com.doido.todolistback.domain.group.service.GroupService;
import com.doido.todolistback.domain.group.shared.enuns.RolesGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/group")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;


    @PostMapping("/create")
    public ResponseEntity<?> createGroup(String title) {
        return new ResponseEntity<>(groupService.createGroup(title),HttpStatus.CREATED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteGroup(UUID groupId) {
        groupService.deleteGroup(groupId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<GroupPost> findGroupById(@PathVariable UUID id) {
        return new ResponseEntity<>(groupService.findGroupById(id),HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<Set<GroupPost>> findAllByUserId() {
        return new ResponseEntity<>(groupService.findAllByUser(), HttpStatus.OK);
    }

    @PutMapping("/edit")
    public ResponseEntity<GroupPost> editGroup(UUID groupId, Group group, RolesGroup rolesGroup) {
        return new ResponseEntity<>(groupService.editGroup(groupId, group, rolesGroup), HttpStatus.OK);
    }

    @PostMapping("/addmembers")
    public ResponseEntity<MembersPost> addMemberToGroup(UUID groupId, UUID memberId, RolesGroup rolesGroup) {
        return new ResponseEntity<>(groupService.addMembersInGroup(memberId, groupId, rolesGroup), HttpStatus.OK);
    }

    @DeleteMapping("/removermembers")
    public ResponseEntity<?>  removeMemberFromGroup(UUID userID, UUID groupId) {
        groupService.removeMembersInGroup(userID, groupId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/alterroles")
    public ResponseEntity<MembersPost> alterRolesInGroup(UUID userID, UUID groupId, RolesGroup rolesGroup) {
        return new ResponseEntity<>(groupService.alterRolesInGroup(userID, groupId, rolesGroup), HttpStatus.OK);
    }

    @GetMapping("{idGroup}/members")
    public ResponseEntity<Set<MembersPost>> getMembersInGroup(@PathVariable UUID idGroup) {
        return new ResponseEntity<>(groupService.showMembersInGroup(idGroup), HttpStatus.OK);
    }

}

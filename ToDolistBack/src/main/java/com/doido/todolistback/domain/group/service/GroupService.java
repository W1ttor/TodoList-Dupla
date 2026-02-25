package com.doido.todolistback.domain.group.service;

import com.doido.todolistback.domain.group.dto.post.GroupPost;
import com.doido.todolistback.domain.group.dto.post.MembersPost;
import com.doido.todolistback.domain.group.entity.Group;
import com.doido.todolistback.domain.group.entity.Members;
import com.doido.todolistback.domain.group.repositories.GroupRepository;
import com.doido.todolistback.domain.group.repositories.MembersRepository;
import com.doido.todolistback.domain.group.shared.enuns.RolesGroup;
import com.doido.todolistback.domain.group.shared.mapper.GroupMapper;
import com.doido.todolistback.domain.group.shared.mapper.MembersMapper;
import com.doido.todolistback.domain.user.entity.User;
import com.doido.todolistback.domain.user.repositories.UserRepository;
import com.doido.todolistback.shared.exception.CustomsExceptions.ForbiddenException;
import com.doido.todolistback.shared.exception.CustomsExceptions.ResourceNotFound;
import com.doido.todolistback.shared.utils.SecurityUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service @Transactional
public class GroupService {

    private final GroupRepository groupRepository;
    private final MembersRepository membersRepository;
    private final UserRepository userRepository;
    private final GroupMapper groupMapper;
    private final MembersMapper membersMapper;

    public GroupPost createGroup(String title) {
        User user = (User) SecurityUtils.getUser();
        Group group = Group.create(title);
        group.addMember(user, RolesGroup.OWNER);
        return groupMapper.groupPost(groupRepository.save(group));
    }

    public void deleteGroup(UUID groupID) {
        User userAdmin = (User) SecurityUtils.getUser();
        assertRoleAssignmentAllowed(userAdmin, groupID, RolesGroup.OWNER);
        groupRepository.deleteById(groupID);
    }

    public GroupPost findGroupById(UUID groupID) {
        Group group = groupRepository.findById(groupID)
                .orElseThrow(ResourceNotFound::new);

        return groupMapper.groupPost(group);
    };

    public Set<GroupPost> findAllByUser() {
        User user = (User) SecurityUtils.getUser();

        Set<Group> groups = groupRepository.findAllByUserId(user.getId());

        if (groups.isEmpty()) {
            throw new ResourceNotFound();
        }

        return groups.stream()
                .map(groupMapper::groupPost)
                .collect(Collectors.toSet());
    }

    public GroupPost editGroup(UUID groupId, Group group, RolesGroup rolesGroup) {

        User user = (User) SecurityUtils.getUser();
        Group editGroup = findGroup(groupId);

        assertRoleAssignmentAllowed(user, editGroup.getId(), rolesGroup);

        if (group.getTitle() != null) {
            editGroup.setTitle(group.getTitle());
        }

        return groupMapper.groupPost(groupRepository.save(editGroup));
    }

    public MembersPost addMembersInGroup(UUID userID, UUID groupId, RolesGroup rolesGroup) {
        User userAdmin = (User) SecurityUtils.getUser();
        Group group = findGroup(groupId);
        User user = findUser(userID);

        assertRoleAssignmentAllowed(userAdmin, groupId, rolesGroup);

        group.addMember(user, rolesGroup);

        groupRepository.save(group);

        Members member = membersRepository.findByUser_IdAndGroup_Id(user.getId(), groupId)
                .orElseThrow(ResourceNotFound::new);

        if (member != null) {
            return MembersPost.Create(member);
        }

        throw new ForbiddenException();
    }

    public void removeMembersInGroup(UUID userID, UUID groupId) {

        User userAdmin = (User) SecurityUtils.getUser();
        Group group = findGroup(groupId);
        User user = findUser(userID);

        Members memberUser = membersRepository.findByUser_IdAndGroup_Id(user.getId(), groupId)
                .orElseThrow(ResourceNotFound::new);

        assertRoleAssignmentAllowed(userAdmin, groupId, memberUser.getRolesGroup());

        membersRepository.deleteById(memberUser.getId());

        if (group.getMembers().isEmpty()){
            groupRepository.deleteById(group.getId());
        }
    }

    public MembersPost alterRolesInGroup(UUID userID, UUID groupId, RolesGroup rolesGroup) {

        User userAdmin =  (User) SecurityUtils.getUser();

        assertRoleAssignmentAllowed(userAdmin, groupId, rolesGroup);

        Members members = membersRepository.findByUser_IdAndGroup_Id(userID, groupId)
                .orElseThrow(ResourceNotFound::new);

        members.setRolesGroup(rolesGroup);

        membersRepository.save(members);

        return membersMapper.membersPost(members);
    }

    public Set<MembersPost> showMembersInGroup(UUID groupId) {
        User user = (User) SecurityUtils.getUser();

        Set<Members> members = membersRepository.findAllByUser_IdAndGroup_Id(user.getId(),groupId);

        return  members
                .stream()
                .map(membersMapper::membersPost)
                .collect(Collectors.toSet());
    }

    //private methods
    private void assertRoleAssignmentAllowed(User user, UUID groupId, RolesGroup rolesGroup) {

        Group group = findGroup(groupId);

        Members member = membersRepository.findByUser_IdAndGroup_Id(user.getId(), group.getId())
                .orElseThrow(ResourceNotFound::new);


       if (member.getRolesGroup().getLevel() > rolesGroup.getLevel() || member.getRolesGroup().getLevel() == RolesGroup.OWNER.getLevel()) {
               return;
       }

       throw new ForbiddenException();
    }

    private User findUser(UUID userID) {
        return userRepository.findById(userID)
                .orElseThrow(() -> new ResourceNotFound("User not found"));
    }

    private Group findGroup(UUID grouId) {
        return groupRepository.findById(grouId)
                .orElseThrow(()->new ResourceNotFound("Group not found"));
    }

}

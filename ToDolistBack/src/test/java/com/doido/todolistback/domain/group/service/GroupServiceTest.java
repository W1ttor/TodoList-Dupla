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
import com.doido.todolistback.shared.utils.SecurityUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GroupServiceTest {

    @Mock
    private  GroupRepository groupRepository;

    @Mock
    private  MembersRepository membersRepository;

    @Mock
    private  UserRepository userRepository;

    @Mock
    private  GroupMapper groupMapper;

    @Mock
    private MembersMapper membersMapper;

    @InjectMocks
    private GroupService groupService;


    @Test
    void createGroup() {
        User fakeUser = new User();

        try(MockedStatic<SecurityUtils> mockedStatic = mockStatic(SecurityUtils.class)) {
            mockedStatic.when(SecurityUtils::getUser).thenReturn(fakeUser);
            groupService.createGroup("title");
            verify(groupRepository).save(any(Group.class));

        }

    }

    @Test
    void deleteGroup() {

        UUID groupID = UUID.randomUUID();
        User userFake = new User();

        Group groupFake = new Group();
        groupFake.setId(groupID);

        Members memberFake = new Members();
        memberFake.setRolesGroup(RolesGroup.OWNER);
        memberFake.setUser(userFake);
        memberFake.setGroup(groupFake);

        try(MockedStatic<SecurityUtils> mockedStatic = mockStatic(SecurityUtils.class)) {

            mockedStatic.when(SecurityUtils::getUser).thenReturn(userFake);

            when(groupRepository.findById(groupID))
                    .thenReturn(Optional.of(groupFake));

            when(membersRepository.findByUser_IdAndGroup_Id(userFake.getId(), groupID))
                    .thenReturn(Optional.of(memberFake));

            groupService.deleteGroup(groupID);

            verify(groupRepository).deleteById(groupID);
        }

    }

    @Test
    void findGroupById() {
    }

    @Test
    void findAllByUser() {
        User fakeUser = new User();
        fakeUser.setId(UUID.randomUUID());

        Group groupFake = new Group();
        groupFake.setId(UUID.randomUUID());

        Group groupFake2 = new Group();
        groupFake2.setId(UUID.randomUUID());

        GroupPost groupPost = new GroupPost();
        GroupPost groupPost2 = new GroupPost();


        try(MockedStatic<SecurityUtils> mockedStatic = mockStatic(SecurityUtils.class)) {
            mockedStatic.when(SecurityUtils::getUser).thenReturn(fakeUser);

            when(groupRepository.findAllByUserId(fakeUser.getId()))
                    .thenReturn(Set.of(groupFake, groupFake2));

            when(groupMapper.groupPost(groupFake)).thenReturn(groupPost);

            when(groupMapper.groupPost(groupFake2)).thenReturn(groupPost2);


            Set<GroupPost> result = groupService.findAllByUser();

            assertEquals(2, result.size());
            assertTrue(result.contains(groupPost));
            assertTrue(result.contains(groupPost2));

            verify(groupRepository, times(1)).findAllByUserId(fakeUser.getId());

            verify(groupMapper).groupPost(groupFake);

            verify(groupMapper).groupPost(groupFake2);

            verifyNoMoreInteractions(groupRepository, groupMapper);
        }
    }

    @Test
    void editGroup() {
    }

    @Test
    void addMembersInGroup() {
        User adminFake = new User();
        adminFake.setId(UUID.randomUUID());

        User userFake = new User();
        userFake.setId(UUID.randomUUID());

        Group groupFake = new Group();
        groupFake.setId(UUID.randomUUID());
        groupFake.addMember(adminFake, RolesGroup.OWNER);

        Members adminMemberFake = new Members();
        adminMemberFake.setRolesGroup(RolesGroup.OWNER);
        adminMemberFake.setUser(adminFake);

        Members memberFake = new Members();
        memberFake.setUser(userFake);

        try(MockedStatic<SecurityUtils> mockedStatic = mockStatic(SecurityUtils.class) ) {

            mockedStatic.when(SecurityUtils::getUser).thenReturn(adminFake);

            when(groupRepository.findById(groupFake.getId())).thenReturn(Optional.of(groupFake));

            when(userRepository.findById(userFake.getId())).thenReturn(Optional.of(userFake));

            when(membersRepository.findByUser_IdAndGroup_Id(adminFake.getId(), groupFake.getId()))
                    .thenReturn(Optional.of(adminMemberFake));

            when(membersRepository.findByUser_IdAndGroup_Id(userFake.getId(), groupFake.getId()))
                    .thenReturn(Optional.of(memberFake));

            groupService.addMembersInGroup(userFake.getId(), groupFake.getId(), RolesGroup.MEMBER);

            verify(groupRepository).save(any(Group.class));

        }

    }

    @Test
    void removeMembersInGroup() {

        User adminFake = new User();
        adminFake.setId(UUID.randomUUID());

        User userFake = new User();
        userFake.setId(UUID.randomUUID());

        Group groupFake = new Group();
        groupFake.setId(UUID.randomUUID());

        Members adminMemberFake = new Members();
        adminMemberFake.setRolesGroup(RolesGroup.OWNER);
        adminMemberFake.setUser(adminFake);

        Members memberFake = new Members();
        memberFake.setUser(userFake);
        memberFake.setRolesGroup(RolesGroup.MEMBER);

        groupFake.addMember(adminFake, RolesGroup.OWNER);
        groupFake.addMember(userFake, RolesGroup.MEMBER);

        try(MockedStatic<SecurityUtils> mockedStatic = mockStatic(SecurityUtils.class) ) {
            mockedStatic.when(SecurityUtils::getUser).thenReturn(adminFake);

            when(userRepository.findById(userFake.getId())).thenReturn(Optional.of(userFake));

            when(groupRepository.findById(groupFake.getId())).thenReturn(Optional.of(groupFake));

            when(membersRepository.findByUser_IdAndGroup_Id(userFake.getId(), groupFake.getId()))
                    .thenReturn(Optional.of(memberFake));

            when(membersRepository.findByUser_IdAndGroup_Id(adminFake.getId(), groupFake.getId()))
                    .thenReturn(Optional.of(adminMemberFake));

            groupService.removeMembersInGroup(userFake.getId(), groupFake.getId());

            verify(membersRepository).deleteById(memberFake.getId());

            verify(groupRepository, never()).deleteById(groupFake.getId());


        }


    }

    @Test
    void alterRolesInGroup() {
        User adminFake = new User();
        adminFake.setId(UUID.randomUUID());

        User userFake = new User();
        userFake.setId(UUID.randomUUID());

        Members adminMemberFake = new Members();
        adminMemberFake.setRolesGroup(RolesGroup.OWNER);
        adminMemberFake.setUser(userFake);

        Members memberFake = new Members();
        memberFake.setUser(userFake);
        memberFake.setRolesGroup(RolesGroup.MEMBER);

        Group groupFake = new Group();
        groupFake.setId(UUID.randomUUID());

        MembersPost membersPost = new MembersPost();

        try(MockedStatic<SecurityUtils> mockedStatic = mockStatic(SecurityUtils.class)) {

            mockedStatic.when(SecurityUtils::getUser).thenReturn(adminFake);

            when(groupRepository.findById(groupFake.getId()))
                    .thenReturn(Optional.of(groupFake));

            when(membersRepository.findByUser_IdAndGroup_Id(adminFake.getId(), groupFake.getId()))
                    .thenReturn(Optional.of(adminMemberFake));

            when(membersRepository.findByUser_IdAndGroup_Id(userFake.getId(), groupFake.getId()))
                    .thenReturn(Optional.of(memberFake));

            when(membersMapper.membersPost(memberFake)).thenReturn(membersPost);

            groupService.alterRolesInGroup(userFake.getId(), groupFake.getId(), RolesGroup.ADMIN);

            verify(membersRepository).save(any(Members.class));
        }
    }

    @Test
    void showMembersInGroup() {

        User adminFake = new User();
        adminFake.setId(UUID.randomUUID());

        Group groupFake = new Group();
        groupFake.setId(UUID.randomUUID());

        Members member1 = new Members();

        Members member2 = new Members();

        groupFake.setMembers(Set.of(member1, member2));

        MembersPost membersPost = new MembersPost();

        MembersPost membersPost2 = new MembersPost();

        try(MockedStatic<SecurityUtils> mockedStatic = mockStatic(SecurityUtils.class)) {
            mockedStatic.when(SecurityUtils::getUser).thenReturn(adminFake);

            when(membersRepository.findAllByUser_IdAndGroup_Id(adminFake.getId(), groupFake.getId()))
                    .thenReturn(Set.of(member1, member2));

            when(membersMapper.membersPost(member1)).thenReturn(membersPost);

            when(membersMapper.membersPost(member2)).thenReturn(membersPost2);


            Set<MembersPost> members = groupService.showMembersInGroup(groupFake.getId());

            assertEquals(2, members.size());
            assertTrue(members.contains(membersPost));
            assertTrue(members.contains(membersPost2));
        }


    }
}
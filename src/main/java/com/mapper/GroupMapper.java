//package com.mapper;
//
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class GroupMapper {
//    public Group mapToGroup(final GroupDto groupDto) {
//        return new Group(
//                groupDto.getId(),
//                groupDto.getName()
//                );
//    }
//
//    public GroupDto mapToGroupDto(final Group group) {
//        return new GroupDto(
//                group.getId(),
//                group.getGroupName()
//        );
//    }
//
//    public List<GroupDto> mapToGroupDtoList(final List<Group> groupList) {
//        return groupList.stream()
//                .map(this::mapToGroupDto)
//                .collect(Collectors.toList());
//    }
//}

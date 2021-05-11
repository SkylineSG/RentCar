//package com.controller;
//
//import com.domain.Group;
//import com.controller.exceptions.GroupNotFoundException;
//import com.domain.dto.GroupDto;
//import com.mapper.GroupMapper;
//import com.service.GroupDbService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController("GroupController")
//@RequestMapping("/v1/group")
//public class GroupController {
//    private final GroupMapper mapper;
//    private final GroupDbService service;
//
//    @Autowired
//    public GroupController(GroupMapper mapper, GroupDbService service) {
//        this.mapper = mapper;
//        this.service = service;
//    }
//
//    @GetMapping(value = "getAllGroups")
//    public List<GroupDto> getGroups() {
//        List<Group> groups = service.getAllGroups();
//        return mapper.mapToGroupDtoList(groups);
//    }
//
//    @PostMapping(value = "createGroup", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public void createGroup(@RequestBody GroupDto groupDto) {
//        Group group = mapper.mapToGroup(groupDto);
//        service.saveGroup(group);
//    }
//
//    @GetMapping("getGroup")
//    public GroupDto getGroup(@RequestParam Long groupId) throws GroupNotFoundException {
//        return mapper.mapToGroupDto(service.getGroup(groupId).orElseThrow(GroupNotFoundException::new));
//    }
//
//    @PutMapping(value = "updateGroup")
//    public GroupDto updateGroup(GroupDto groupDto) {
//        Group group = mapper.mapToGroup(groupDto);
//        Group savedGroup = service.saveGroup(group);
//        return mapper.mapToGroupDto(savedGroup);
//    }
//}
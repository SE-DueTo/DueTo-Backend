package de.dueto.backend.controller.v1;

import de.dueto.backend.service.GroupService;
import org.springframework.stereotype.Controller;

@Controller("/v1/group")
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

}

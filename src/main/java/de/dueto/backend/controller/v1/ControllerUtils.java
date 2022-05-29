package de.dueto.backend.controller.v1;

import de.dueto.backend.model.group.Group;
import de.dueto.backend.model.user.User;
import de.dueto.backend.service.GroupService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;


@Component
public class ControllerUtils {

    private final AuthorizationMapper authorizationMapper;
    private final GroupService groupService;

    public ControllerUtils(
            AuthorizationMapper authorizationMapper,
            GroupService groupService) {
        this.authorizationMapper = authorizationMapper;
        this.groupService = groupService;
    }

    public User checkUser(String token) throws ResponseStatusException {
        User user = authorizationMapper.getUser(token);
        if(user == null) throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        return user;
    }

    public Group checkGroup(long groupId) throws ResponseStatusException {
        Group group = groupService.getGroupById(groupId);
        if(group == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return group;
    }

}

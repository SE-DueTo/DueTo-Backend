package de.dueto.backend.service;

import de.dueto.backend.mysqlData.GroupRepository;
import org.springframework.stereotype.Service;

@Service
public class GroupService {

    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }
}

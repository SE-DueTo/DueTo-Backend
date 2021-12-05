package de.dueto.backend.model.group;

import de.dueto.backend.model.user.User;
import de.dueto.backend.mysql_data.UserRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class GroupMapper {

    private final UserRepository userRepository;

    public GroupMapper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Group fromGroupAddNormalDTO(GroupAddNormalDTO groupAddNormalDTO) {
        return Group.builder()
                .groupName(groupAddNormalDTO.getGroupname())
                .groupType(GroupType.NORMAL)
                .password(groupAddNormalDTO.getPassword())
                .users(groupAddNormalDTO.getUsers()
                        .stream()
                        .map(uid -> userRepository.findById(uid).orElse(null))
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList()))
                .build();
    }

    public Group fromGroupAddSpontaneous(User user, long userId) {
        Optional<User> user2 = userRepository.findById(userId);
        if(user2.isEmpty()) return null;
        return Group.builder()
                .groupName("n/a")
                .users(List.of(user, user2.get()))
                .password(null)
                .groupType(GroupType.SPONTANEOUS)
                .build();
    }
}

package de.dueto.backend.model.dashboard;

import de.dueto.backend.model.group.Group;
import de.dueto.backend.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DashboardDataDTO {

    private User user;
    private List<Group> groups;
    private long balance;

}

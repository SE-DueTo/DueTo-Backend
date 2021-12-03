package de.dueto.backend.model.group;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GroupAddNormalDTO {

    private String groupname;
    private List<Long> users;
    private String password;

}

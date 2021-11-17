package de.dueto.backend.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "groups")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Group {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long groupId;
    private String groupName;
    private String password;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<User> users;

}

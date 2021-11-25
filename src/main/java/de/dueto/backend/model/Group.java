package de.dueto.backend.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "dueto_groups")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Group {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(nullable = false, unique = true)
    private long groupId;

    @Column(nullable = false)
    private String groupName;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private GroupType groupType;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<User> users;

}

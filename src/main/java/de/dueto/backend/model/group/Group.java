package de.dueto.backend.model.group;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.dueto.backend.model.user.User;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "dueto_groups")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Group implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(nullable = false, unique = true)
    private long groupId;

    @Column(nullable = false)
    private String groupName;

    @JsonIgnore
    @Column
    private String password;

    @Enumerated(EnumType.STRING)
    private GroupType groupType;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "dueto_group_user",
            joinColumns = { @JoinColumn(name = "group_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") }
    )
    private List<User> users;

}

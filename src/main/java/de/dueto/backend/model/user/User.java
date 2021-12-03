package de.dueto.backend.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.dueto.backend.model.group.Group;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Table(name = "dueto_users")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Embeddable
@Builder
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(nullable = false, unique = true, updatable = false, name = "user_id")
    private long userId;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String email;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @Column(name="avatar_url")
    private String avatarUrl;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Group> groups;

}
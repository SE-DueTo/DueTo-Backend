package de.dueto.backend.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Table(name = "users")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(nullable = false, unique = true, updatable = false)
    private long id;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private String avatarUrl;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Group> groups;

}
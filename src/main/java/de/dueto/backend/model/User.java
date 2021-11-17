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
    private long id;
    private String userName;
    private String email;
    private String password;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Group> groups;

}
package de.dueto.backend.model;

import de.dueto.backend.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;

import javax.persistence.*;

@Table(name = "dueto_sessions")
@Entity
@Scope("session")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Session {

    public static final String USER_TOKEN = "user_token";

    @Id
    @Column(unique = true)
    private String sessionId;

    @ManyToOne
    private User user;

}

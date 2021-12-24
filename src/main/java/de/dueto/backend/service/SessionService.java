package de.dueto.backend.service;

import com.fasterxml.uuid.Generators;
import de.dueto.backend.model.Session;
import de.dueto.backend.model.user.User;
import de.dueto.backend.mysql_data.DuetoSessionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SessionService {

    private final DuetoSessionRepository sessionRepository;

    public SessionService(DuetoSessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public User getUser(String sessionId) {
        Optional<Session> user = sessionRepository.findById(sessionId);
        return user.map(Session::getUser).orElse(null);
    }

    public void save(Session session) {
        if(session.getSessionId() == null) {
            String uuid;
            do {
                uuid = getUUID();
            } while (sessionRepository.findById(uuid).isPresent());

            session.setSessionId(uuid);
        }
        sessionRepository.save(session);
    }

    private synchronized String getUUID() {
        return Generators.timeBasedGenerator().generate().toString();
    }

    public Session getSession(String sessionId) {
        return sessionRepository.findById(sessionId).orElse(null);
    }

    public void destroySession(String sessionId) {
        sessionRepository.deleteById(sessionId);
    }
}

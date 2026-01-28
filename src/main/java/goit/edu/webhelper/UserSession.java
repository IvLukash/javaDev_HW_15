package goit.edu.webhelper;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserSession {
    private static final String USER_ID = "user_id";

    public UUID getOrCreateUserId(HttpSession session) {
        UUID userId = (UUID) session.getAttribute(USER_ID);
        if (userId == null) {
            userId = UUID.randomUUID();
            session.setAttribute(USER_ID, userId);
        }
        return userId;
    }
}

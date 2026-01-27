package goit.edu.web.webhelper;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserSession {

    public UUID getOrCreateUserId(HttpSession session) {
        UUID userId = (UUID) session.getAttribute("user_id");
        if (userId == null) {
            userId = UUID.randomUUID();
            session.setAttribute("user_id", userId);
        }
        return userId;
    }
}

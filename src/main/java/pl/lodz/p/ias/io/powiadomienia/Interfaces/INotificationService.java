package pl.lodz.p.ias.io.powiadomienia.Interfaces;

import org.aspectj.weaver.ast.Not;
import pl.lodz.p.ias.io.powiadomienia.notification.Notification;
import pl.lodz.p.ias.io.powiadomienia.notification.NotificationType;
import pl.lodz.p.ias.io.uwierzytelnianie.model.Account;
import pl.lodz.p.ias.io.uwierzytelnianie.model.Role;

import java.util.List;

/**
 * Interfejs do zarządzania powiadomieniami w systemie.
 */
public interface INotificationService {
    /**
     * Wysyła powiadomienie z określoną wiadomością, typem i odbiorcą.
     *
     * @param message treść powiadomienia
     * @param type typ powiadomienia ({@link NotificationType})
     * @param user użytkownik ({@link Account}), do którego powiadomienie ma zostać wysłane
     * @return utworzony obiekt {@link Notification}
     */
    public Notification notify (String message, NotificationType type, Account user);

    public Notification notify (String message, NotificationType type);

    public List<Notification> notify (String message,NotificationType type, Role role);
}

package be.teachngo.mail;


import be.teachngo.data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class MailConstructor {
    @Autowired
    private Environment env;

    public SimpleMailMessage constructResetTokenEmail(String contextPath, Locale locale, String token, User user) {
        String url = contextPath + "/api/newUser?token=" + token + "&login=" + user.getLogin();
        String message = "\n Valider votre inscription avec ce lien :\n";
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(user.getEmail());
        email.setSubject("Teachngo - Nouvelle inscription");
        email.setText(message + url);
        email.setFrom(env.getProperty("support.email"));
        return email;
    }


}

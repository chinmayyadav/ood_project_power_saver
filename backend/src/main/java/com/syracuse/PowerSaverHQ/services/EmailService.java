package com.syracuse.PowerSaverHQ.services;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.syracuse.PowerSaverHQ.models.UserDetails;
import com.syracuse.PowerSaverHQ.utils.EmailConnections;
import org.springframework.stereotype.Service;

import java.io.IOException;
@Service
public class EmailService extends EmailConnections {
    public void sendRegistrationEmail(UserDetails user){
        try{
            Email from = new Email("powersaverhq@gmail.com");
            String subject = "Welcome to PowerSaverHQ";
            Email to = new Email(user.getEmail());
            Content content = new Content("text/plain", "Hello "+user.getFirstName()+", \n\n" +
                    "Thank you for Registering. Welcome to Power Saver HQ");
            Mail mail = new Mail(from, subject, to, content);
            Request request = new Request();
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = EmailConnections.sendGrid.api(request);
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }
}

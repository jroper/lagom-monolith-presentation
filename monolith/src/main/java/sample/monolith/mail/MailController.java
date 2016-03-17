package sample.monolith.mail;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailController {

    @RequestMapping("/mail/{userId}")
    public void sendMail(@PathVariable("userId") String userId, @RequestBody MailEvent mailEvent) {
        System.out.println("Sending " + mailEvent + " to " + userId);
    }
}

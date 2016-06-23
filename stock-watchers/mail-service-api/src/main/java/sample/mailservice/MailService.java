package sample.mailservice;

import akka.NotUsed;
import com.lightbend.lagom.javadsl.api.Descriptor;
import com.lightbend.lagom.javadsl.api.Service;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.lightbend.lagom.javadsl.api.transport.Method;

import static com.lightbend.lagom.javadsl.api.Service.*;

public interface MailService extends Service {

    ServiceCall<MailEvent, NotUsed> sendMail(String userId);

    @Override
    default Descriptor descriptor() {
        return named("mail").withCalls(
                restCall(Method.POST, "/mail/:userId", this::sendMail)
        );
    }
}

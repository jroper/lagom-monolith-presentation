package sample.stockwatching;

import akka.NotUsed;
import com.lightbend.lagom.javadsl.api.Descriptor;
import com.lightbend.lagom.javadsl.api.Service;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.lightbend.lagom.javadsl.api.deser.IdSerializers;
import com.lightbend.lagom.javadsl.api.transport.Method;

import java.util.Arrays;

import static com.lightbend.lagom.javadsl.api.Service.*;

public interface StockWatchingService extends Service {


}

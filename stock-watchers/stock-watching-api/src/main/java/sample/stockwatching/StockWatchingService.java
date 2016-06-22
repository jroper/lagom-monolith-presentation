package sample.stockwatching;

import akka.NotUsed;
import com.lightbend.lagom.javadsl.api.Descriptor;
import com.lightbend.lagom.javadsl.api.Service;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.lightbend.lagom.javadsl.api.transport.Method;

import static com.lightbend.lagom.javadsl.api.Service.*;

public interface StockWatchingService extends Service {

    ServiceCall<NotUsed, NotUsed> watchStock(String stockId, String userId);
    ServiceCall<NotUsed, NotUsed> unwatchStock(String stockId, String userId);
    ServiceCall<NotUsed, Boolean> isWatchingStock(String stockId, String userId);
    ServiceCall<NotUsed, NotUsed> stockAvailabilityChanged(String stockId);

    @Override
    default Descriptor descriptor() {
        return named("stockWatching").with(
                restCall(Method.PUT, "/stock/:stockId/watch/:userId", this::watchStock),
                restCall(Method.DELETE, "/stock/:stockId/watch/:userId", this::unwatchStock),
                restCall(Method.GET, "/stock/:stockId/watch/:userId", this::isWatchingStock),
                restCall(Method.POST, "/stock/:stockId/availability", this::stockAvailabilityChanged)
        );
    }


}

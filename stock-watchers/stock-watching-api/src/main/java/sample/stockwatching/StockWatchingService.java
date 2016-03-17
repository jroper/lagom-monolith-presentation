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

    ServiceCall<StockWatch, NotUsed, NotUsed> watchStock();
    ServiceCall<StockWatch, NotUsed, NotUsed> unwatchStock();
    ServiceCall<StockWatch, NotUsed, Boolean> isWatchingStock();
    ServiceCall<String, NotUsed, NotUsed> stockAvailabilityChanged();

    @Override
    default Descriptor descriptor() {
        return named("stockWatching").with(
                restCall(Method.PUT, "/stock/:stockId/watch/:userId", watchStock()),
                restCall(Method.DELETE, "/stock/:stockId/watch/:userId", unwatchStock()),
                restCall(Method.GET, "/stock/:stockId/watch/:userId", isWatchingStock()),
                restCall(Method.POST, "/stock/:stockId/availability", stockAvailabilityChanged())
        ).with(StockWatch.class, IdSerializers.create("StockWatch", StockWatch::new,
                sw -> Arrays.asList(sw.stockId(), sw.userId()))
        );
    }


}

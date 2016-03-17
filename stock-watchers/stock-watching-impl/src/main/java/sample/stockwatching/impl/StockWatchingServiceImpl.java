package sample.stockwatching.impl;

import akka.NotUsed;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.lightbend.lagom.javadsl.persistence.PersistentEntityRef;
import com.lightbend.lagom.javadsl.persistence.PersistentEntityRegistry;
import org.pcollections.PSet;
import sample.mailservice.MailEvent;
import sample.mailservice.MailService;
import sample.stockwatching.StockWatch;
import sample.stockwatching.StockWatchingService;
import sample.stockwatching.impl.WatchedStockCommand.*;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

public class StockWatchingServiceImpl implements StockWatchingService {

    private final PersistentEntityRegistry entities;

    @Inject
    public StockWatchingServiceImpl(PersistentEntityRegistry persistentEntityRegistry) {
        this.entities = persistentEntityRegistry;

        entities.register(WatchedStockEntity.class);
    }

    public ServiceCall<StockWatch, NotUsed, NotUsed> watchStock() {
        return (stockWatch, req) ->
                entity(stockWatch.stockId())
                        .ask(new AddWatcher(stockWatch.userId()))
                        .thenApply(done -> NotUsed.getInstance());
    }

    public ServiceCall<StockWatch, NotUsed, NotUsed> unwatchStock() {
        return (stockWatch, req) ->
                entity(stockWatch.stockId())
                        .ask(new RemoveWatcher(stockWatch.userId()))
                        .thenApply(done -> NotUsed.getInstance());
    }

    public ServiceCall<StockWatch, NotUsed, Boolean> isWatchingStock() {
        return (stockWatch, req) ->
                entity(stockWatch.stockId())
                        .ask(new IsWatching(stockWatch.userId()));
    }

    public ServiceCall<String, NotUsed, NotUsed> stockAvailabilityChanged() {
        return (stockId, req) -> {
            CompletionStage<PSet<String>> result = entity(stockId)
                    .ask(GetWatchers.INSTANCE);
            return result.thenApply(watchers -> {
                        watchers.forEach(watcher -> {
                            // send email here

                        });
                        return NotUsed.getInstance();
                    });
        };
    }

    private PersistentEntityRef<WatchedStockCommand> entity(String stockId) {
        return entities.refFor(WatchedStockEntity.class, stockId);
    }
}

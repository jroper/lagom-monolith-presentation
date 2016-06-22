package sample.stockwatching.impl;

import akka.NotUsed;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.lightbend.lagom.javadsl.persistence.PersistentEntityRef;
import com.lightbend.lagom.javadsl.persistence.PersistentEntityRegistry;
import org.pcollections.PSet;
import sample.mailservice.MailEvent;
import sample.mailservice.MailService;
import sample.stockwatching.StockWatchingService;
import sample.stockwatching.impl.WatchedStockCommand.*;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

public class StockWatchingServiceImpl implements StockWatchingService {

    private final PersistentEntityRegistry entities;
    private final MailService mailService;

    @Inject
    public StockWatchingServiceImpl(PersistentEntityRegistry persistentEntityRegistry, MailService mailService) {
        this.entities = persistentEntityRegistry;
        this.mailService = mailService;

        entities.register(WatchedStockEntity.class);
    }

    @Override
    public ServiceCall<NotUsed, NotUsed> watchStock(String stockId, String userId) {
        return (req) ->
                entity(stockId)
                        .ask(new AddWatcher(userId))
                        .thenApply(done -> NotUsed.getInstance());
    }

    @Override
    public ServiceCall<NotUsed, NotUsed> unwatchStock(String stockId, String userId) {
        return (req) ->
                entity(stockId)
                        .ask(new RemoveWatcher(userId))
                        .thenApply(done -> NotUsed.getInstance());
    }

    @Override
    public ServiceCall<NotUsed, Boolean> isWatchingStock(String stockId, String userId) {
        return (req) ->
                entity(stockId)
                        .ask(new IsWatching(userId));
    }

    @Override
    public ServiceCall<NotUsed, NotUsed> stockAvailabilityChanged(String stockId) {
        return (req) -> {
            CompletionStage<PSet<String>> result = entity(stockId)
                    .ask(GetWatchers.INSTANCE);
            return result.thenApply(watchers -> {
                        watchers.forEach(watcher ->
                                // Send email here
                                mailService.sendMail(watcher)
                                        .invoke(new MailEvent.StockAvailabilityChanged(stockId))
                        );
                        return NotUsed.getInstance();
                    });
        };
    }

    private PersistentEntityRef<WatchedStockCommand> entity(String stockId) {
        return entities.refFor(WatchedStockEntity.class, stockId);
    }
}

package sample.stockwatching.impl;

import akka.Done;
import com.lightbend.lagom.javadsl.persistence.PersistentEntity;
import sample.stockwatching.impl.WatchedStockCommand.*;
import sample.stockwatching.impl.WatchedStockEvent.*;

import java.util.Optional;

public class WatchedStockEntity extends PersistentEntity<WatchedStockCommand, WatchedStockEvent, WatchedStockState> {
    @Override
    public Behavior initialBehavior(Optional<WatchedStockState> snapshotState) {
        BehaviorBuilder b = newBehaviorBuilder(snapshotState.orElse(WatchedStockState.EMPTY));

        b.setCommandHandler(AddWatcher.class,
                (cmd, ctx) -> ctx.thenPersist(new WatcherAdded(cmd.userId()),
                        e -> ctx.reply(Done.getInstance())));

        b.setCommandHandler(RemoveWatcher.class,
                (cmd, ctx) -> ctx.thenPersist(new WatcherRemoved(cmd.userId()),
                        e -> ctx.reply(Done.getInstance())));

        b.setReadOnlyCommandHandler(IsWatching.class,
                (cmd, ctx) -> ctx.reply(state().userIds().contains(cmd.userId()))
        );

        b.setReadOnlyCommandHandler(GetWatchers.class,
                (cmd, ctx) -> ctx.reply(state().userIds()));

        b.setEventHandler(WatcherAdded.class, event -> state().plusWatcher(event.userId()));
        b.setEventHandler(WatcherRemoved.class, event -> state().minusWatcher(event.userId()));

        return b.build();
    }
}

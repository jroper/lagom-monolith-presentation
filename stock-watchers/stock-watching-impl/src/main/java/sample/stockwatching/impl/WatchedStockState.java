package sample.stockwatching.impl;

import org.pcollections.HashTreePSet;
import org.pcollections.PSet;

public final class WatchedStockState {
    private final PSet<String> userIds;

    public static WatchedStockState EMPTY = new WatchedStockState(HashTreePSet.empty());

    public WatchedStockState(PSet<String> userIds) {
        this.userIds = userIds;
    }

    public PSet<String> userIds() {
        return userIds;
    }

    public WatchedStockState plusWatcher(String userId) {
        return new WatchedStockState(userIds.plus(userId));
    }

    public WatchedStockState minusWatcher(String userId) {
        return new WatchedStockState(userIds.minus(userId));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WatchedStockState)) return false;

        WatchedStockState that = (WatchedStockState) o;

        return userIds.equals(that.userIds);

    }

    @Override
    public int hashCode() {
        return userIds.hashCode();
    }

    @Override
    public String toString() {
        return "WatchedStockState{" +
                "userIds=" + userIds +
                '}';
    }
}

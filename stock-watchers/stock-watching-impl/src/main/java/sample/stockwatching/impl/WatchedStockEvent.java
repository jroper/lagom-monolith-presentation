package sample.stockwatching.impl;

import com.lightbend.lagom.serialization.Jsonable;

public interface WatchedStockEvent extends Jsonable {

    final class WatcherAdded implements WatchedStockEvent {
        private final String userId;

        public WatcherAdded(String userId) {
            this.userId = userId;
        }

        public String userId() {
            return userId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof WatcherAdded)) return false;

            WatcherAdded that = (WatcherAdded) o;

            return userId.equals(that.userId);

        }

        @Override
        public int hashCode() {
            return userId.hashCode();
        }

        @Override
        public String toString() {
            return "WatcherAdded{" +
                    "userId='" + userId + '\'' +
                    '}';
        }
    }

    final class WatcherRemoved implements WatchedStockEvent {
        private final String userId;

        public WatcherRemoved(String userId) {
            this.userId = userId;
        }

        public String userId() {
            return userId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof WatcherRemoved)) return false;

            WatcherRemoved that = (WatcherRemoved) o;

            return userId.equals(that.userId);

        }

        @Override
        public int hashCode() {
            return userId.hashCode();
        }

        @Override
        public String toString() {
            return "WatcherRemoved{" +
                    "userId='" + userId + '\'' +
                    '}';
        }
    }
}

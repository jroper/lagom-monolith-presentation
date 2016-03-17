package sample.stockwatching.impl;

import akka.Done;
import com.lightbend.lagom.javadsl.persistence.PersistentEntity;
import com.lightbend.lagom.serialization.Jsonable;
import org.pcollections.PSet;

public interface WatchedStockCommand extends Jsonable {

    final class AddWatcher implements WatchedStockCommand, PersistentEntity.ReplyType<Done> {
        private final String userId;

        public AddWatcher(String userId) {
            this.userId = userId;
        }

        public String userId() {
            return userId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof AddWatcher)) return false;

            AddWatcher that = (AddWatcher) o;

            return userId.equals(that.userId);

        }

        @Override
        public int hashCode() {
            return userId.hashCode();
        }

        @Override
        public String toString() {
            return "AddWatcher{" +
                    "userId='" + userId + '\'' +
                    '}';
        }
    }

    final class RemoveWatcher implements WatchedStockCommand, PersistentEntity.ReplyType<Done> {
        private final String userId;

        public RemoveWatcher(String userId) {
            this.userId = userId;
        }

        public String userId() {
            return userId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof RemoveWatcher)) return false;

            RemoveWatcher that = (RemoveWatcher) o;

            return userId.equals(that.userId);

        }

        @Override
        public int hashCode() {
            return userId.hashCode();
        }

        @Override
        public String toString() {
            return "RemoveWatcher{" +
                    "userId='" + userId + '\'' +
                    '}';
        }
    }

    final class IsWatching implements WatchedStockCommand, PersistentEntity.ReplyType<Boolean> {
        private final String userId;

        public IsWatching(String userId) {
            this.userId = userId;
        }

        public String userId() {
            return userId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof IsWatching)) return false;

            IsWatching that = (IsWatching) o;

            return userId.equals(that.userId);

        }

        @Override
        public int hashCode() {
            return userId.hashCode();
        }

        @Override
        public String toString() {
            return "IsWatching{" +
                    "userId='" + userId + '\'' +
                    '}';
        }
    }

    final class GetWatchers implements WatchedStockCommand, PersistentEntity.ReplyType<PSet<String>> {

        public static final GetWatchers INSTANCE = new GetWatchers();

        public int hashCode() {
            return getClass().hashCode();
        }

        public boolean equals(Object other) {
            return other instanceof GetWatchers;
        }

        @Override
        public String toString() {
            return "GetWatchers{}";
        }
    }
}

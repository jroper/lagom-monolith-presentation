package sample.mailservice;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
@JsonSubTypes({
        @JsonSubTypes.Type(value = MailEvent.StockAvailabilityChanged.class, name = "stockAvailabilityChanged")
})
public interface MailEvent {

    final class StockAvailabilityChanged implements MailEvent {
        private final String stockId;

        public StockAvailabilityChanged(String stockId) {
            this.stockId = stockId;
        }

        public String stockId() {
            return stockId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof StockAvailabilityChanged)) return false;

            StockAvailabilityChanged that = (StockAvailabilityChanged) o;

            return stockId.equals(that.stockId);

        }

        @Override
        public int hashCode() {
            return stockId.hashCode();
        }

        @Override
        public String toString() {
            return "StockAvailabilityChanged{" +
                    "stockId='" + stockId + '\'' +
                    '}';
        }
    }

}

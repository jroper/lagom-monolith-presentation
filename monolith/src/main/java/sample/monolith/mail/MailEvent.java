package sample.monolith.mail;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
@JsonSubTypes({
        @JsonSubTypes.Type(value = MailEvent.StockAvailabilityChanged.class, name = "stockAvailabilityChanged")
})
public interface MailEvent {

    final class StockAvailabilityChanged implements MailEvent {
        private String stockId;

        public String getStockId() {
            return stockId;
        }

        public void setStockId(String stockId) {
            this.stockId = stockId;
        }

        @Override
        public String toString() {
            return "StockAvailabilityChanged{" +
                    "stockId='" + stockId + '\'' +
                    '}';
        }
    }

}

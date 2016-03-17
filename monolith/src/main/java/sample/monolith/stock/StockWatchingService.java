package sample.monolith.stock;

public interface StockWatchingService {
    void watchStock(String stockId, String userId);

    void unwatchStock(String stockId, String userId);

    boolean isWatchingStock(String stockId, String userId);

    void stockAvailabilityChanged(String stockId);
}

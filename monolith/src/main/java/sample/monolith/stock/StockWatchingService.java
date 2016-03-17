package sample.monolith.stock;

/**
 * Manage stock watchers
 */
public interface StockWatchingService {

    /**
     * Add a watch
     *
     * @param stockId The stock to watch
     * @param userId The user to watch the stock
     */
    void watchStock(String stockId, String userId);

    /**
     * Remove a watch
     *
     * @param stockId The stock to watch
     * @param userId The user to watch the stock
     */
    void unwatchStock(String stockId, String userId);

    /**
     * Whether the user is watching the stock
     *
     * @param stockId The stock to watch
     * @param userId The user to watch the stock
     */
    boolean isWatchingStock(String stockId, String userId);

    /**
     * Signaled when the availability of the stock has changed.
     *
     * @param stockId The stock that has changed its availability.
     */
    void stockAvailabilityChanged(String stockId);
}

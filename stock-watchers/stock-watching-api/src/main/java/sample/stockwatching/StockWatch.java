package sample.stockwatching;

public final class StockWatch {
    private final String stockId;
    private final String userId;

    public StockWatch(String stockId, String userId) {
        this.stockId = stockId;
        this.userId = userId;
    }

    public String stockId() {
        return stockId;
    }

    public String userId() {
        return userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StockWatch)) return false;

        StockWatch that = (StockWatch) o;

        if (!stockId.equals(that.stockId)) return false;
        return userId.equals(that.userId);

    }

    @Override
    public int hashCode() {
        int result = stockId.hashCode();
        result = 31 * result + userId.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "StockWatch{" +
                "stockId='" + stockId + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}

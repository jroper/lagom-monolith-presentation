package sample.monolith.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class LagomStockWatchingService implements StockWatchingService {

    private RestTemplate restTemplate = new RestTemplate();

    @Value("${services.stockWatching.uri}")
    private String stockWatchingServiceUri;

    @Override
    public void watchStock(String stockId, String userId) {
        restTemplate.put(stockWatchingServiceUri + "/stock/{stockId}/watch/{userId}", null, stockId, userId);
    }

    @Override
    public void unwatchStock(String stockId, String userId) {
        restTemplate.delete(stockWatchingServiceUri + "/stock/{stockId}/watch/{userId}", stockId, userId);
    }

    @Override
    public boolean isWatchingStock(String stockId, String userId) {
        return restTemplate.getForObject(stockWatchingServiceUri + "/stock/{stockId}/watch/{userId}", Boolean.class,
                stockId, userId);
    }

    @Override
    public void stockAvailabilityChanged(String stockId) {
        restTemplate.execute(stockWatchingServiceUri + "/stock/{stockId}/availability", HttpMethod.POST, null, null, stockId);
    }

}

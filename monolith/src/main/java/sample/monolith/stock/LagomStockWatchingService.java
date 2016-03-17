package sample.monolith.stock;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class LagomStockWatchingService implements StockWatchingService {

    private RestTemplate restTemplate = new RestTemplate();

    
}

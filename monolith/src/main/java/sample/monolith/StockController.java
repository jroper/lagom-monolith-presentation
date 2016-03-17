package sample.monolith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sample.monolith.stock.StockWatchingService;

@RestController
public class StockController {

    @Autowired
    private StockWatchingService stockWatchingService;

    @RequestMapping("/addWatch")
    public void addWatch(@RequestParam("stockId") String stockId, @RequestParam("userId") String userId) {
        stockWatchingService.watchStock(stockId, userId);
    }

    @RequestMapping("/removeWatch")
    public void removeWatch(@RequestParam("stockId") String stockId, @RequestParam("userId") String userId) {
        stockWatchingService.unwatchStock(stockId, userId);
    }

    @RequestMapping("/isWatching")
    public boolean isWatching(@RequestParam("stockId") String stockId, @RequestParam("userId") String userId) {
        return stockWatchingService.isWatchingStock(stockId, userId);
    }

    @RequestMapping("/stockAvailable")
    public void stockAvailable(@RequestParam("stockId") String stockId) {
        stockWatchingService.stockAvailabilityChanged(stockId);
    }
}
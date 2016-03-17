package sample.stockwatching.impl;

import com.google.inject.AbstractModule;
import com.lightbend.lagom.javadsl.server.ServiceGuiceSupport;
import sample.mailservice.MailService;
import sample.stockwatching.StockWatchingService;

public class StockWatchingModule extends AbstractModule implements ServiceGuiceSupport {

    @Override
    protected void configure() {
        bindServices(serviceBinding(StockWatchingService.class, StockWatchingServiceImpl.class));
    }
}

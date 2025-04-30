package io.stein.infrastructure;

import io.quarkus.arc.log.LoggerName;
import io.stein.domain.CustomerCreatedEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import org.jboss.logging.Logger;

@ApplicationScoped
public class CustomerEventLogger {
    @LoggerName("customer-events")
    Logger logger;

    public void logCustomerEvent(@Observes CustomerCreatedEvent event) {
        logger.infov("Customer created: {0}", event.customer().getUuid());
    }
}

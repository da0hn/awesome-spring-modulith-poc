package dev.da0hn.library.management.system.catalog;

import lombok.extern.slf4j.Slf4j;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BookListener {

    @ApplicationModuleListener
    public void handleBookCreatedEvent(final BookCreatedEvent event) {
        log.info("Book created event received: {}", event.id());
    }

}

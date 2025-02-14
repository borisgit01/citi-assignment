package com.borisgd.citi_assignment;

import com.borisgd.citi_assignment.domain.CAUser;
import com.borisgd.citi_assignment.repos.CAUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataInitializer.class);

    private final CAUserRepository caUserRepository;

    public DataInitializer(CAUserRepository caUserRepository) {
        this.caUserRepository = caUserRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("DataInitializer.run - entering");

        CAUser u1 = new CAUser(1, "James T. Kirk", "james@zzz.com");
        CAUser u2 = new CAUser(2, "Spock", "spock@zzz.com");
        CAUser u3 = new CAUser(3, "Leonard McCoy", "leonard@zzz.com");
        CAUser u4 = new CAUser(4, "Scotty", "scotty@zzz.com");
        CAUser u5 = new CAUser(5, "Nyota Uhura", "nyota@zzz.com");

        caUserRepository.save(u1);
        caUserRepository.save(u2);
        caUserRepository.save(u3);
        caUserRepository.save(u4);
        caUserRepository.save(u5);

        LOGGER.info("DataInitializer.run - all done");
    }
}

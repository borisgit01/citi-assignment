package com.borisgd.citi_assignment;

import com.borisgd.citi_assignment.domain.CAUser;
import com.borisgd.citi_assignment.repos.CAUserRepository;
import com.borisgd.citi_assignment.services.CAUserService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataInitializer.class);

    private final CAUserRepository caUserRepository;
    private final CAUserService caUserService;

    public DataInitializer(CAUserRepository caUserRepository, CAUserService caUserService) {
        this.caUserRepository = caUserRepository;
        this.caUserService = caUserService;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        CAUser u1 = new CAUser(1, "James T. Kirk", "james@zzz.com");
        CAUser u2 = new CAUser(2, "Spock", "spock@zzz.com");
        CAUser u3 = new CAUser(3, "Leonard McCoy", "leonard@zzz.com");
        CAUser u4 = new CAUser(4, "Scotty", "scotty@zzz.com");
        CAUser u5 = new CAUser(5, "Nyota Uhura", "nyota@zzz.com");
        CAUser u6 = new CAUser(6, "Jean-Luc Picard", "jean@zzz.com");
        CAUser u7 = new CAUser(7, "William Riker", "william@zzz.com");
        CAUser u8 = new CAUser(8, "Geordi La Forge", "georgi@zzz.com");
        CAUser u9 = new CAUser(9, "Tasha Yar", "tasha@zzz.com");
        CAUser u10 = new CAUser(10, "Worf", "worf@zzz.com");
        CAUser u11 = new CAUser(11, "Beverly Crusher", "beverly@zzz.com");
        CAUser u12 = new CAUser(12, "Deanna Troi", "deanna@zzz.com");

        caUserService.addNewUser(u1);
        caUserService.addNewUser(u2);
        caUserService.addNewUser(u3);
        caUserService.addNewUser(u4);
        caUserService.addNewUser(u5);
        caUserService.addNewUser(u6);
        caUserService.addNewUser(u7);
        caUserService.addNewUser(u8);
        caUserService.addNewUser(u9);
        caUserService.addNewUser(u10);
        caUserService.addNewUser(u11);
        caUserService.addNewUser(u12);

        caUserService.addFriend(1, 2);
        caUserService.addFriend(1, 3);
        caUserService.addFriend(1, 4);
        caUserService.addFriend(1, 5);
        caUserService.addFriend(1, 6);
        caUserService.addFriend(1, 7);

        caUserService.addFriend(7, 8);

        caUserService.addFriend(9, 10);
        caUserService.addFriend(9, 11);
        caUserService.addFriend(9, 12);
    }
}

package com.borisgd.citi_assignment.services;

import com.borisgd.citi_assignment.DataInitializer;
import com.borisgd.citi_assignment.domain.CAUser;
import com.borisgd.citi_assignment.repos.CAUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CAUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CAUserService.class);

    private final CAUserRepository caUserRepository;

    public CAUserService(CAUserRepository caUserRepository) {
        this.caUserRepository = caUserRepository;
    }

    public List<CAUser> getAllUsers() {
        return caUserRepository.findAll();
    }

    public CAUser addNewUser(CAUser caUser) {
        return caUserRepository.save(caUser);
    }

    public void deleteById(Integer id) {
        caUserRepository.deleteById(id);
    }

    public void addFriend(Integer userId, Integer friendId) {
        Optional<CAUser> oUser = caUserRepository.findById(userId);
        if(oUser.isEmpty()) {
            LOGGER.error("Cannot find user with id = {} in the database.", userId);
            return;
        }
        CAUser user = oUser.get();
        oUser = caUserRepository.findById(friendId);
        if(oUser.isEmpty()) {
            LOGGER.error("Cannot find user with id = {} in the database.", userId);
            return;
        }
        CAUser friend = oUser.get();
        LOGGER.info("user: {}, friend: {}", user.getName(), friend.getName());
        user.getFriends().add(friend);
        friend.getFriends().add(user);
        caUserRepository.save(user);
        caUserRepository.save(friend);
    }
}

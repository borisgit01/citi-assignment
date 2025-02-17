package com.borisgd.citi_assignment.services;

import com.borisgd.citi_assignment.domain.CAUser;
import com.borisgd.citi_assignment.domain.FriendActionResponse;
import com.borisgd.citi_assignment.domain.FriendActionResponseEx;
import com.borisgd.citi_assignment.repos.CAUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public FriendActionResponse countHops(Integer userId1, Integer userId2) {
        int hops = CAUserUtilities.findShortestHops(CAUserUtilities.buildGraph(getAllUsers()), userId1, userId2);
        String message;
        if(hops == -1) {
            message = "Did not find any connection between user " + userId1 + " and user " + userId2;
        } else {
            message = "Found " + hops + " hops between user " + userId1 + " and user " + userId2;
        }
        return new FriendActionResponse(FriendActionResponse.ACTION_COUNT_HOPS, true, message);
    }

    public FriendActionResponse removeFriend(Integer userId, Integer friendId) {
        FriendActionResponseEx response = findBoth(userId, friendId);
        if(response.isSuccess()) {
            CAUser user = response.getUser();
            CAUser friend = response.getFriend();
            user.getFriends().remove(friend);
            friend.getFriends().remove(user);
            caUserRepository.save(user);
            caUserRepository.save(friend);
            return new FriendActionResponse(FriendActionResponse.ACTION_DELETE, true,
                    "Users are not friends any more");
        } else {
            return new FriendActionResponse(FriendActionResponse.ACTION_DELETE, false, response.getMessage());
        }
    }

    public FriendActionResponse addFriend(Integer userId, Integer friendId) {
        FriendActionResponseEx response = findBoth(userId, friendId);
        if(response.isSuccess()) {
            CAUser user = response.getUser();
            CAUser friend = response.getFriend();
            user.getFriends().add(friend);
            friend.getFriends().add(user);
            caUserRepository.save(user);
            caUserRepository.save(friend);
            return new FriendActionResponse(FriendActionResponse.ACTION_ADD, true,
                    "Users are now friends");
        } else {
            return new FriendActionResponse(FriendActionResponse.ACTION_ADD, false, response.getMessage());
        }
    }

    private FriendActionResponseEx findBoth(Integer userId, Integer friendId) {
        Optional<CAUser> oUser = caUserRepository.findById(userId);
        if(oUser.isEmpty()) {
            LOGGER.error("Cannot find user with id = {} in the database.", userId);
            return new FriendActionResponseEx(FriendActionResponse.ACTION_ADD, false,
                    "Cannot find user with id = " + userId + " in the database.", null, null);
        }
        CAUser user = oUser.get();
        oUser = caUserRepository.findById(friendId);
        if(oUser.isEmpty()) {
            LOGGER.error("Cannot find user with id = {} in the database.", friendId);
            return new FriendActionResponseEx(FriendActionResponse.ACTION_ADD, false,
                    "Cannot find user with id = " + friendId + " in the database.", null, null);
        }
        CAUser friend = oUser.get();
        return new FriendActionResponseEx(null, true,
                null, user, friend);
    }
}

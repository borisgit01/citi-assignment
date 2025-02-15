package com.borisgd.citi_assignment;

import com.borisgd.citi_assignment.domain.CAUser;
import com.borisgd.citi_assignment.domain.FriendActionResponse;
import com.borisgd.citi_assignment.repos.CAUserRepository;
import com.borisgd.citi_assignment.services.CAUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class CAUserServiceTests {

    @InjectMocks
    private CAUserService caUserService;

    @Mock
    private CAUserRepository caUserRepository;

    private List<CAUser> userList;

    @BeforeEach
    void setUp() {
        userList = Arrays.asList(
                new CAUser(1, "James T. Kirk", "james@zzz.com"),
                new CAUser(2, "Spock", "spock@zzz.com"),
                new CAUser(3, "Leonard McCoy", "leonard@zzz.com"),
                new CAUser(4, "Scotty", "scotty@zzz.com"),
                new CAUser(5, "Nyota Uhura", "nyota@zzz.com")
        );
    }

    @Test
    void removeFriendTestFailsWhenAUserIsNotFound() {
        when(caUserRepository.findById(1)).thenReturn(Optional.ofNullable(userList.get(0)));
        FriendActionResponse response = caUserService.removeFriend(1, 2);
        System.out.println(response.getMessage());
        assertEquals("Cannot find user with id = 2 in the database.", response.getMessage());
    }

    @Test
    void removeFriendTest() {
        when(caUserRepository.findById(1)).thenReturn(Optional.ofNullable(userList.get(0)));
        when(caUserRepository.findById(2)).thenReturn(Optional.ofNullable(userList.get(1)));
        FriendActionResponse response = caUserService.addFriend(1, 2);
        assertEquals("Users are now friends", response.getMessage());
        response = caUserService.removeFriend(1, 2);
        System.out.println(response.getMessage());
        assertEquals("Users are not friends any more", response.getMessage());
    }

    @Test
    void addFriendTestFailsWhenAUserIsNotFound() {
        when(caUserRepository.findById(1)).thenReturn(Optional.ofNullable(userList.get(0)));
        FriendActionResponse response = caUserService.addFriend(1, 20);
        assertEquals("Cannot find user with id = 20 in the database.", response.getMessage());
    }

    @Test
    void addFriendTest() {
        when(caUserRepository.findById(1)).thenReturn(Optional.ofNullable(userList.get(0)));
        when(caUserRepository.findById(2)).thenReturn(Optional.ofNullable(userList.get(1)));
        FriendActionResponse response = caUserService.addFriend(1, 2);
        assertEquals("Users are now friends", response.getMessage());
    }

    @Test
    void getAllUsersTest() {
        when(caUserRepository.findAll()).thenReturn(userList);
        List<CAUser>users = caUserService.getAllUsers();
        assertEquals(5, users.size());
        assertEquals(userList, users);
        verify(caUserRepository, times(1)).findAll();
    }
}

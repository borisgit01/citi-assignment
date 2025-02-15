package com.borisgd.citi_assignment.domain;

public class FriendActionResponseEx extends FriendActionResponse {

    private CAUser user;
    private CAUser friend;

    public FriendActionResponseEx(String action, boolean success, String message, CAUser user, CAUser friend) {
        super(action, success, message);
        this.user = user;
        this.friend = friend;
    }

    public CAUser getUser() {
        return user;
    }

    public void setUser(CAUser user) {
        this.user = user;
    }

    public CAUser getFriend() {
        return friend;
    }

    public void setFriend(CAUser friend) {
        this.friend = friend;
    }
}

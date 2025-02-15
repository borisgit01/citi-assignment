package com.borisgd.citi_assignment.domain;

public class FriendActionResponse {

    public final static String ACTION_ADD = "Add";
    public final static String ACTION_DELETE = "Delet";

    private String action;
    private boolean success;
    private String message;

    public FriendActionResponse(String action, boolean success, String message) {
        this.action = action;
        this.success = success;
        this.message = message;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

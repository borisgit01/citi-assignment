package com.borisgd.citi_assignment.domain;

public class FriendActionResponse {

    public final static String ACTION_ADD = "Add";
    public final static String ACTION_DELETE = "Delete";
    public final static String ACTION_COUNT_HOPS = "Count Hops";
    public final static String ACTION_DETECT_COMMUNITIES = "Detect Communities";
    public final static String ACTION_CALCULATE_CENTRALITY = "Calculate Centrality";

    private String action;
    private boolean success;
    private String message;
    private Object object;

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

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}

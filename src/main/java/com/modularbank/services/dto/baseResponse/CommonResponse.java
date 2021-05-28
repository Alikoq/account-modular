package com.modularbank.services.dto.baseResponse;

public class CommonResponse {
    private Messages messages;
    private Object data;

    public Messages getMessages() {
        return messages;
    }

    public void setMessages(Messages messages) {
        this.messages = messages;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

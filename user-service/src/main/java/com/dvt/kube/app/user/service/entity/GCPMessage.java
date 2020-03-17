package com.dvt.kube.app.user.service.entity;

import java.util.Arrays;

public class GCPMessage {

    String data;
    String messageId;
    String subscription;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getSubscription() {
        return subscription;
    }

    public void setSubscription(String subscription) {
        this.subscription = subscription;
    }

    @Override
    public String toString() {
        return "GCPMessage{" +
                "data='" + data + '\'' +
                ", messageId='" + messageId + '\'' +
                ", subscription='" + subscription + '\'' +
                '}';
    }
}

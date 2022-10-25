package com.example.infits;

public class ChatMessage {
    public  String senderId;
    public String receiverId;
    public String message;
    public String time;
    public String messageBy;
    public String read;
    public String fileName;
    private String type;

    public ChatMessage(String senderId, String receiverId, String message, String time, String messageBy, String read,String type) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.message = message;
        this.time = time;
        this.messageBy=messageBy;
        this.read=read;
        this.setType(type);
    }

    public ChatMessage(String senderId, String receiverId, String message, String time, String messageBy, String read,String type,String fileName) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.message = message;
        this.time = time;
        this.messageBy=messageBy;
        this.read=read;
        this.setType(type);
        this.fileName = fileName;
    }

    public String getSenderId() {
        return senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public String getMessage() {
        return message;
    }

    public String getTime() {
        return time;
    }

    public String getMessageBy() {
        return messageBy;
    }

    public String getRead() {
        return read;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

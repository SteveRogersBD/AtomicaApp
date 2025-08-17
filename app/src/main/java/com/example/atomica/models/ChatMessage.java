package com.example.atomica.models;

public class ChatMessage {
    public static final int TYPE_INCOMING = 0; // Bot message
    public static final int TYPE_OUTGOING = 1; // User message
    
    private String text;
    private int messageType;
    private boolean isTyping;

    // Constructor for regular messages
    public ChatMessage(String text, int messageType) {
        this.text = text;
        this.messageType = messageType;
        this.isTyping = false;
    }

    // Constructor for typing indicator
    public static ChatMessage createTypingMessage() {
        ChatMessage message = new ChatMessage("🧪 Analyzing your question...", TYPE_INCOMING);
        message.isTyping = true;
        return message;
    }

    // Getters
    public String getText() { return text; }
    public int getMessageType() { return messageType; }
    public boolean isTyping() { return isTyping; }

    // Setters
    public void setText(String text) { this.text = text; }
    public void setTyping(boolean typing) { this.isTyping = typing; }

    // Utility methods
    public boolean isFromBot() {
        return messageType == TYPE_INCOMING;
    }

    public boolean isFromUser() {
        return messageType == TYPE_OUTGOING;
    }
}
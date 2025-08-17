package com.example.atomica.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.atomica.R;
import com.example.atomica.models.ChatMessage;
import java.util.ArrayList;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    
    private List<ChatMessage> messages;
    private Context context;

    public ChatAdapter(Context context) {
        this.context = context;
        this.messages = new ArrayList<>();
    }

    @Override
    public int getItemViewType(int position) {
        return messages.get(position).getMessageType();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        
        if (viewType == ChatMessage.TYPE_INCOMING) {
            View incomingView = inflater.inflate(R.layout.message_in, parent, false);
            return new IncomingMessageViewHolder(incomingView);
        } else {
            View outgoingView = inflater.inflate(R.layout.message_out, parent, false);
            return new OutgoingMessageViewHolder(outgoingView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ChatMessage message = messages.get(position);
        
        if (holder instanceof IncomingMessageViewHolder) {
            ((IncomingMessageViewHolder) holder).bind(message);
        } else if (holder instanceof OutgoingMessageViewHolder) {
            ((OutgoingMessageViewHolder) holder).bind(message);
        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    // ViewHolder for incoming messages (bot messages)
    class IncomingMessageViewHolder extends RecyclerView.ViewHolder {
        TextView messageText;

        IncomingMessageViewHolder(@NonNull View itemView) {
            super(itemView);
            messageText = itemView.findViewById(R.id.message_text);
        }

        void bind(ChatMessage message) {
            messageText.setText(message.getText());
            
            // Handle typing indicator styling
            if (message.isTyping()) {
                messageText.setTextColor(context.getColor(R.color.neon_green));
                messageText.setTextSize(14f);
                messageText.setAlpha(0.8f);
            } else {
                messageText.setTextColor(context.getColor(android.R.color.white));
                messageText.setTextSize(16f);
                messageText.setAlpha(1.0f);
            }
        }
    }

    // ViewHolder for outgoing messages (user messages)
    class OutgoingMessageViewHolder extends RecyclerView.ViewHolder {
        TextView messageText;

        OutgoingMessageViewHolder(@NonNull View itemView) {
            super(itemView);
            messageText = itemView.findViewById(R.id.message_text);
        }

        void bind(ChatMessage message) {
            messageText.setText(message.getText());
        }
    }

    // Simple methods for managing messages
    public void addMessage(ChatMessage message) {
        messages.add(message);
        notifyItemInserted(messages.size() - 1);
    }

    public void removeTypingMessage() {
        for (int i = messages.size() - 1; i >= 0; i--) {
            if (messages.get(i).isTyping()) {
                messages.remove(i);
                notifyItemRemoved(i);
                break;
            }
        }
    }

    public void clearMessages() {
        int size = messages.size();
        messages.clear();
        notifyItemRangeRemoved(0, size);
    }
}
package com.example.infits;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.infits.databinding.ChatareaclientmessageBinding;
import com.example.infits.databinding.ChatareadietitianmessageBinding;
import com.example.infits.databinding.ChatareaclientmessageBinding;
import com.example.infits.databinding.ChatareadietitianmessageBinding;

import java.util.List;

public class ChatMessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private final List<ChatMessage> chatMessages;
     Context context;

    public static final int VIEW_TYPE_SENT=1;
    public static final int VIEW_TYPE_RECEIVE=2;
    public ChatMessageAdapter(List<ChatMessage> chatMessages, Context context){// Bitmap receiverProfileImage) {
        this.chatMessages = chatMessages;
        this.context = context;
    }
    public ChatMessageAdapter(List<ChatMessage> chatMessages, String context){// Bitmap receiverProfileImage) {
        this.chatMessages = chatMessages;
//        this.context = context;
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       if(viewType==VIEW_TYPE_SENT){
           return new SentMessageViewHolder(
                   ChatareadietitianmessageBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false)
           );
       }
       else{
           return new ReceivedMessageViewHolder(
                   ChatareaclientmessageBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false)
           );
       }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

            if(getItemViewType(position)== VIEW_TYPE_SENT) {
                ((SentMessageViewHolder) holder).setData(chatMessages.get(position));
                //return new SentMessageViewHolder(View(holder));
            }
            else{
                ((ReceivedMessageViewHolder) holder).setData(chatMessages.get(position));
            }
    }

    @Override
    public int getItemCount() {
        return chatMessages.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(chatMessages.get(position).messageBy.equals("dietitian"))
            return VIEW_TYPE_RECEIVE;
        else
            return VIEW_TYPE_SENT;
    }


    static class SentMessageViewHolder extends RecyclerView.ViewHolder{
        private final ChatareadietitianmessageBinding binding1;
        SentMessageViewHolder(ChatareadietitianmessageBinding chatAreaDietitianMessageBinding){
            super(chatAreaDietitianMessageBinding.getRoot());
            binding1=chatAreaDietitianMessageBinding;
        }

        void setData(ChatMessage chatMessage){
            if (chatMessage.getType().equals("jpeg")){
                binding1.attachment.setVisibility(View.VISIBLE);
                binding1.dietitianMsg.setVisibility(View.GONE);
                binding1.files.setVisibility(View.GONE);
                byte[] qrimage = Base64.decode(chatMessage.message,0);
                binding1.attachment.setImageBitmap(BitmapFactory.decodeByteArray(qrimage,0,qrimage.length));
            }
            if (chatMessage.getType().equals("png")){
                binding1.attachment.setVisibility(View.VISIBLE);
                binding1.dietitianMsg.setVisibility(View.GONE);
                binding1.files.setVisibility(View.GONE);
                byte[] qrimage = Base64.decode(chatMessage.message,0);
                binding1.attachment.setImageBitmap(BitmapFactory.decodeByteArray(qrimage,0,qrimage.length));
            }
            if (chatMessage.getType().equals("jpg")){
                binding1.attachment.setVisibility(View.VISIBLE);
                binding1.dietitianMsg.setVisibility(View.GONE);
                binding1.files.setVisibility(View.GONE);
                byte[] qrimage = Base64.decode(chatMessage.message,0);
                binding1.attachment.setImageBitmap(BitmapFactory.decodeByteArray(qrimage,0,qrimage.length));
            }
            if (chatMessage.getType().equals("pdf")){
                binding1.attachment.setVisibility(View.GONE);
                binding1.dietitianMsg.setVisibility(View.GONE);
                binding1.filename.setText(chatMessage.fileName);
                binding1.files.setVisibility(View.VISIBLE);
            }
            if (chatMessage.getType().equals("text")){
                binding1.dietitianMsg.setText(chatMessage.message);
                binding1.dietitianMsgTime.setText(chatMessage.time);
                binding1.attachment.setVisibility(View.GONE);
                binding1.files.setVisibility(View.GONE);
                binding1.dietitianMsg.setVisibility(View.VISIBLE);
            }
        }

    }

    static class ReceivedMessageViewHolder extends RecyclerView.ViewHolder{
        private final ChatareaclientmessageBinding binding2;
        ReceivedMessageViewHolder(ChatareaclientmessageBinding chatAreaClientMessageBinding){
            super(chatAreaClientMessageBinding.getRoot());
            binding2=chatAreaClientMessageBinding;
        }

        void setData(ChatMessage chatMessage){
            if (chatMessage.getType().equals("jpeg")){
                binding2.attachment.setVisibility(View.VISIBLE);
                binding2.clientMsg.setVisibility(View.GONE);
                byte[] qrimage = Base64.decode(chatMessage.message,0);
                binding2.attachment.setImageBitmap(BitmapFactory.decodeByteArray(qrimage,0,qrimage.length));
            }
            if (chatMessage.getType().equals("text")){
                binding2.clientMsg.setText(chatMessage.message);
                binding2.clientMsgTime.setText(chatMessage.time);
                binding2.attachment.setVisibility(View.GONE);
                binding2.clientMsg.setVisibility(View.VISIBLE);
            }
        }
    }
}
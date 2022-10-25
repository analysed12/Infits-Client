package com.example.infits;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
//import com.ultimate.infits.databinding.ActivityChatAreaBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class ChatArea1 extends AppCompatActivity {

    final String TAG = "MessageApp";

    String type = "text";

    public ChatArea1(){

    }

    private final Emitter.Listener onNewMessage = args -> runOnUiThread(() -> {
        Log.d(TAG,"username");
        JSONObject data = (JSONObject) args[0];
        String username;
        String message;
        try {
            username = data.getString("username");
            message = data.getString("message");
            Log.d(TAG,username);
            Log.d(TAG,message);
        } catch (JSONException e) {
            Log.d(TAG,"username");
        }
    });

    private Socket mSocket;
    {
        try {
            mSocket = IO.socket("http://192.168.1.6:8080");
        } catch (URISyntaxException e) {}
    }


//    public ChatArea1(ActivityChatAreaBinding binding) {
//        this.binding = binding;
//    }
//    private ActivityChatAreaBinding binding;
    public static String chat_area_client_name;
    TextView name;
    EditText message;
    ImageView profile_pic;
    String url = String.format("%smessagesClient.php",DataFromDatabase.ipConfig);
    String url3 = String.format("%smessagesSend.php",DataFromDatabase.ipConfig);
    DataFromDatabase dataFromDatabase;
    RequestQueue queue;
    List<ChatMessage> msg=new ArrayList<>();
    ChatMessageAdapter ad1;
    RecyclerView r1;
    Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        binding = ActivityChatAreaBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_area);
//        setContentView(binding.getRoot());

        r1=findViewById(R.id.FrameContainerMessages);

//        send = findViewById(R.id.send_message_btn);
        chat_area_client_name = getIntent().getExtras().getString("client_name");
        name = findViewById(R.id.chat_area_client_name);
        name.setText(chat_area_client_name);
        profile_pic = findViewById(R.id.chat_area_profile_pic);
        message = findViewById(R.id.typed_message);
//        Button send = findViewById(R.id.send_message_btn);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
//                attemptSend();
            }
        });
        ImageView i12 = findViewById(R.id.attach_file);
        i12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //dialog box
            }
        });

        mSocket.on(Socket.EVENT_CONNECT,onConnect);
        mSocket.on("chat-message",onReceived);
        mSocket.connect();
        mSocket.emit("new-user",DataFromDatabase.dietitianuserID);
        mSocket.emit("new-receiver", DataFromDatabase.clientuserID);

        Log.d("ChatArea", "before");
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, response -> {
            if (!response.equals("failure")) {
                Log.d("ChatArea", "success");
                Log.d("response ChatArea", response);
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    String messageby = null;
                    if (jsonArray.length() > 0) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String message = jsonObject.getString("message");
                            messageby = jsonObject.getString("messageBy");
                            String time = jsonObject.getString("time").substring(11,16);
                            String readUnread = "r";
                            String type = jsonObject.getString("type");
                            ChatMessage obj = new ChatMessage(DataFromDatabase.dietitianuserID, chat_area_client_name, message, time, messageby, readUnread,type);
                            msg.add(obj);
                        }
                        ad1 = new ChatMessageAdapter(msg,messageby);
                        r1.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
                        r1.setAdapter(ad1);
                        r1.setVisibility(View.VISIBLE);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } else if (response.equals("failure")) {
                Log.d("ChatArea", "failure");
                Toast.makeText(getApplicationContext(), "ChatArea failed", Toast.LENGTH_SHORT).show();
            }
        }, error -> {
            Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
        }) {
            @org.jetbrains.annotations.Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("duserID", DataFromDatabase.dietitianuserID);
                data.put("cuserID",chat_area_client_name);
                return data;
            }
        };
        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);
        Log.d("ChatArea", "at end");


    }
    private void init(){

    }

    private void sendMessage() {
        //insert to db
        String typed_message = message.getEditableText().toString().trim();
        message.setText("");
//        Log.d("message",typed_message);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url3, response -> {
            if (response.equals("success")) {
                Log.d("ChatArea3", "success");
                Log.d("response ChatArea3", response);
            } else if (response.equals("failure")) {
                Log.d("ChatArea3", "failure");
                Toast.makeText(getApplicationContext(), "unable to send message!! try again", Toast.LENGTH_SHORT).show();
            }
        }, error -> {
            Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("dieticianID", DataFromDatabase.dietitianuserID);
                data.put("clientID",chat_area_client_name);
                data.put("message", typed_message);
                return data;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
//        requestQueue.add(stringRequest);

        String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
        ChatMessage obj = new ChatMessage(DataFromDatabase.dietitianuserID,chat_area_client_name, typed_message, String.valueOf(currentTime.substring(0,5)), "dietitian", "U",type);
        msg.add(obj);
        ad1 = new ChatMessageAdapter(msg,"dietitian");
        r1.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        r1.setAdapter(ad1);
        r1.setVisibility(View.VISIBLE);
        mSocket.emit("new message", typed_message,DataFromDatabase.dietitianuserID,chat_area_client_name);
    }

    private Emitter.Listener onConnect = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(), "Connected", Toast.LENGTH_LONG).show();
                }
            });
        }
    };

    private Emitter.Listener onReceived = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(ChatArea1.this, "Hi", Toast.LENGTH_SHORT).show();
                    JSONObject data = (JSONObject) args[0];
                    String message,sender,receiver;
                    try{
                        sender = data.getString("sender");
                        receiver = data.getString("receiver");
                        message = data.getString("message");
                        Log.d("response", sender+" "+message);
                        Toast.makeText(getApplicationContext(),sender+" "+message,Toast.LENGTH_LONG).show();
                        if (receiver.equals(DataFromDatabase.dietitianuserID)){
                            String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
                            ChatMessage obj=new ChatMessage(sender,receiver, message, String.valueOf(currentTime.substring(0,5)), "dietitian", "U",type);
                            if(sender.equals(DataFromDatabase.dietitianuserID)&&receiver.equals(DataFromDatabase.clientuserID)){
                                obj = new ChatMessage(sender,receiver, message, String.valueOf(currentTime.substring(0,5)), "dietitian", "U",type);
                            }else if (receiver.equals(DataFromDatabase.dietitianuserID) && sender.equals(DataFromDatabase.clientuserID)){
                                obj = new ChatMessage(sender,receiver, message, String.valueOf(currentTime.substring(0,5)), "client", "U",type);
                            }
                            msg.add(obj);
                            ad1 = new ChatMessageAdapter(msg,"dietitian");
                            LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
                            manager.setStackFromEnd(true);
                            r1.setLayoutManager(manager);
                            r1.setAdapter(ad1);
                            r1.setVisibility(View.VISIBLE);
                        }
                    }catch (Exception e){
                        Log.d("exception", String.valueOf(e));
                    }
                    Log.d("args", String.valueOf(args[0]));
                }
            });
        }
    };
}
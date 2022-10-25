package com.example.infits;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.webkit.PermissionRequest;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class LiveAct extends AppCompatActivity {

    RecyclerView recyclerView;

    ArrayList<String> userName = new ArrayList<>();

//    ArrayList<String> userList = new ArrayList<>();
    ArrayList<String> messagesList = new ArrayList<>();

    ArrayList<Bitmap> chatPics = new ArrayList<>();

    Socket sock;
    private String room;
    private boolean user;

    {
        try {
            sock = IO.socket("http://192.168.242.91:8000");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private Emitter.Listener onConnect = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(), "Connected", Toast.LENGTH_LONG).show();
                    sock.emit("join-room",room,username);
//                    sock.on("view-count", new Emitter.Listener() {
//                        @Override
//                        public void call(Object... args) {
//                            runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    count.setText(args[0].toString());
//                                }
//                            });
//                        }
//                    });
                }
            });
        }
    };

    private final Emitter.Listener onReceived = args -> runOnUiThread(() -> {
        try{
            Log.d("Response",args[0].toString());
            JSONObject res = (JSONObject)args[0];
            String sender = res.getString("name");
            String message = res.getString("message");
            String photo = res.getString("photo");
            System.out.println(sender);
            userName.add(sender);
            messagesList.add(message);
            byte[] qrimage = Base64.decode(photo,0);
            Bitmap sendPhoto = BitmapFactory.decodeByteArray(qrimage,0,qrimage.length);
            chatPics.add(sendPhoto);
            recyclerView.setAdapter(new LiveMessageAdapter(getApplicationContext(),messagesList,userName,chatPics));
            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        }catch (JSONException jsonException){
            Log.d("Message","Wrongs");
            Toast.makeText(getApplicationContext(),"wrong",Toast.LENGTH_LONG).show();
        }
    });

    String username = "";

    ImageButton close;

    WebView webView;

    boolean isPeerConnected = true;

    boolean isAudio = true;

    boolean isVideo = true;

    TextView count;

    EditText chatBox;

    ImageView toggleAudioBtn,toggleVideoBtn,sendText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live);
        recyclerView = findViewById(R.id.live_chat);
        sendText = findViewById(R.id.send_live_text);
        chatBox = findViewById(R.id.live_chat_box);
        count = findViewById(R.id.view_count);
        close = findViewById(R.id.close);

        webView = findViewById(R.id.webView);

        Intent get = getIntent();

        room = get.getStringExtra("room");
        username = DataFromDatabase.clientuserID;
        user = get.getBooleanExtra("user",false);
        sock.on(Socket.EVENT_CONNECT,onConnect);
        sock.connect();
        sock.emit("new-user",username);
        sock.on("chat-message", onReceived);
        sock.on("view-count", args -> runOnUiThread(() -> count.setText(args[0].toString())));
        setUpWebView();
        sendText.setOnClickListener(v->{
            String message = chatBox.getText().toString();
            if (!message.equals("")){
                userName.add(DataFromDatabase.clientuserID);
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                DataFromDatabase.profile.compress(Bitmap.CompressFormat.JPEG, 50, outputStream);
                String base64String = Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT);
                sock.emit("send-live-chat",room,message,username,base64String);
                messagesList.add(message);
                chatPics.add(DataFromDatabase.profile);
                recyclerView.setAdapter(new LiveMessageAdapter(getApplicationContext(),messagesList,userName,chatPics));
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                chatBox.setText("");
            }
        });
        close.setOnClickListener(v->{
            finish();
            sock.disconnect();
        });
    }

    void setUpWebView(){
        webView.setWebChromeClient(new WebChromeClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setMediaPlaybackRequiresUserGesture(false);
        webView.addJavascriptInterface(new JavaScriptInterface(this),"Android");
        loadVideoCall();
    }
    private void loadVideoCall() {
        String filePath = "file:///android_asset/call.html";
        webView.loadUrl(filePath);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                initializePeer();
            }
        });
    }
    private void initializePeer() {
        callJavaScriptFunction(String.format("javascript:init('%s')",username));
    }

//    private void switchToControls() {
//        callControlLayout.setVisibility(View.VISIBLE);
//    }

    void callJavaScriptFunction(String functionName){
        webView.post(new Runnable() {
            @Override
            public void run() {
                webView.evaluateJavascript(functionName,null);
            }
        });
    }

    public void onPeerConnected() {
        isPeerConnected =true;
//        sock.emit("join-room", room, username);
    }

    @Override
    public void onDestroy() {
        webView.loadUrl("about:blank");
        super.onDestroy();
    }
}
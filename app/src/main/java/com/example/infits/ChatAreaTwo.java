package com.example.infits;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class ChatAreaTwo extends AppCompatActivity {

    final String TAG = "MessageApp";
    String encoded;
    String type = "text";

    ActivityResultLauncher<String> file = registerForActivityResult(
            new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri result) {

                    if (result != null) {
                                try {
                                    System.out.println(result);
                                    try {
                                        System.out.println("In try");
                                        File file = getFile(getApplicationContext(), result);
                                        System.out.println("after file");
                                        Scanner myReader = new Scanner(file);
                                        System.out.println("after scan");
//                     byte[] fileContent = Files.readAllBytes(file.toPath());
                                        int size = (int) file.length();
                                        byte[] bytes = new byte[size];
                                        try {
                                            BufferedInputStream buf = new BufferedInputStream(new FileInputStream(file));
                                            buf.read(bytes, 0, bytes.length);
                                            buf.close();
                                        } catch (FileNotFoundException e) {
                                            e.printStackTrace();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }

                                        encoded = Base64.encodeToString(bytes, Base64.NO_WRAP);

                                        System.out.println(encoded);

                                        String[] arr = getApplicationContext().getContentResolver().getType(result).split("/");
                                        type = arr[1];

                                        System.out.println(getApplicationContext().getContentResolver().getType(result));
                                        while (myReader.hasNextLine()) {
                                            System.out.println("in while");
                                            String data = myReader.nextLine();
                                            System.out.println(data);
                                        }
                                        myReader.close();
                                    } catch (FileNotFoundException e) {
                                        System.out.println("An error occurred.");
                                        e.printStackTrace();
                                    } catch (IOException e) {
                                        System.out.println("some error occurred.");
                                        e.printStackTrace();
                                    }
                                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url3, response -> {
                                        System.out.println(response);
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
                                            data.put("clientID", DataFromDatabase.clientuserID);
                                            data.put("message", encoded);
                                            data.put("type",type);
                                            data.put("sentBy","client");
                                            return data;
                                        }
                                    };
                                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                                    requestQueue.add(stringRequest);

                                    Log.d("tag1","tag1");
                                    String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
                                    ChatMessage obj = new ChatMessage(DataFromDatabase.dietitianuserID, DataFromDatabase.clientuserID, encoded, String.valueOf(currentTime.substring(0,5)), "client", "U",type);
                                    msg.add(obj);
                                    ad1 = new ChatMessageAdapter(msg,"client");
                                    LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
                                    manager.setStackFromEnd(true);
                                    r1.setLayoutManager(manager);
                                    r1.setAdapter(ad1);
                                    r1.setVisibility(View.VISIBLE);
                                    Log.d("tag2","tag2");
                                }catch (Exception memoryError){
                                    Toast.makeText(getApplicationContext().getApplicationContext(),"Too big file",Toast.LENGTH_LONG).show();
                                }
                    }
                }
            }
    );


    private final int PICK_IMAGE_CAMERA = 1, PICK_IMAGE_GALLERY = 2;

    public ChatAreaTwo(){

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
    ImageView send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_area);
        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
        manager.setOrientation(RecyclerView.VERTICAL);
        manager.setStackFromEnd(true);
        manager.setReverseLayout(true);
        r1=findViewById(R.id.FrameContainerMessages);

        send = findViewById(R.id.send_message_btn);

        name = findViewById(R.id.chat_area_client_name);
        name.setText(DataFromDatabase.dietitianuserID);
        profile_pic = findViewById(R.id.chat_area_profile_pic);
        message = findViewById(R.id.typed_message);
        profile_pic.setImageBitmap(DataFromDatabase.dtPhoto);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
//                attemptSend(v);
            }
        });
        ImageView i12 = findViewById(R.id.attach_file);
        i12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                selectImage();
                file.launch("*/*");
            }
        });

        mSocket.on(Socket.EVENT_CONNECT,onConnect);
        mSocket.on(DataFromDatabase.clientuserID,onReceived);
        mSocket.connect();
        mSocket.emit("new-user",DataFromDatabase.clientuserID);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, response -> {
            System.out.println(response);
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
                            ChatMessage obj = new ChatMessage(DataFromDatabase.dietitianuserID, DataFromDatabase.clientuserID, message, time, messageby, readUnread,type);
                            msg.add(obj);
                        }
                        ad1 = new ChatMessageAdapter(msg,messageby);
                        LinearLayoutManager manager1 = new LinearLayoutManager(getApplicationContext());
                        manager1.setStackFromEnd(true);
                        r1.setLayoutManager(manager1);
                        r1.setAdapter(ad1);
                        r1.setVisibility(View.VISIBLE);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } else if (response.equals("failure")) {
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
                data.put("cuserID", DataFromDatabase.clientuserID);
                return data;
            }
        };
        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);
    }

    private void sendMessage() {
        String typed_message = message.getEditableText().toString().trim();
        type = "text";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url3, response -> {
            System.out.println(response);
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
                data.put("clientID", DataFromDatabase.clientuserID);
                data.put("message", typed_message);
                data.put("type",type);
                data.put("sentBy","client");
                return data;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);

        Log.d("tag1","tag1");
        String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
        ChatMessage obj = new ChatMessage(DataFromDatabase.dietitianuserID, DataFromDatabase.clientuserID, typed_message, String.valueOf(currentTime.substring(0,5)), "client", "U",type);
        msg.add(obj);
        ad1 = new ChatMessageAdapter(msg,"client");

        r1.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
        manager.setStackFromEnd(true);
        r1.setAdapter(ad1);
        r1.setLayoutManager(manager);
        r1.setVisibility(View.VISIBLE);
        mSocket.emit("new message", typed_message,DataFromDatabase.clientuserID ,DataFromDatabase.dietitianuserID);
        Log.d("tag2","tag2");
    }

    public void attemptSend() {
        String typed_message = message.getEditableText().toString().trim();
//        message.setText("");

//        Log.d(TAG,message);
        mSocket.emit("new message", typed_message);
        message.setText("");
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
                    Toast.makeText(ChatAreaTwo.this, "Hi", Toast.LENGTH_SHORT).show();
                    JSONObject data = (JSONObject) args[0];
                    String message,sender,receiver;
                    try{
                        sender = data.getString("sender");
                        receiver = data.getString("receiver");
                        message = data.getString("message");
                        Log.d("response", sender+" "+message);
                        Toast.makeText(getApplicationContext(),sender+" "+message,Toast.LENGTH_LONG).show();
                        if (receiver.equals(DataFromDatabase.clientuserID)){
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


    private void selectImage() {
        try {

            Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(pickPhoto, PICK_IMAGE_GALLERY);


        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Camera Permission error", Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(), "Change app permission in your device settings", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_GALLERY) {
            try {
                if (data.getData() != null) {
                    Uri selectedImage = data.getData();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), selectedImage);

                    //Toast.makeText(getActivity(), "Path: "+imgPath, Toast.LENGTH_SHORT).show();
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 50, baos);
                    byte[] b = baos.toByteArray();
                }
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "No picture selected", Toast.LENGTH_SHORT).show();
            }

        }
    }
    public static File getFile(Context context, Uri uri) throws IOException {
        File destinationFilename = new File(context.getFilesDir().getPath() + File.separatorChar + queryName(context, uri));
        try (InputStream ins = context.getContentResolver().openInputStream(uri)) {
            createFileFromStream(ins, destinationFilename);
        } catch (Exception ex) {
            Log.e("Save File", ex.getMessage());
            ex.printStackTrace();
        }
        return destinationFilename;
    }
    public static void createFileFromStream(InputStream ins, File destination) {
        try (OutputStream os = new FileOutputStream(destination)) {
            byte[] buffer = new byte[4096];
            int length;
            while ((length = ins.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
            os.flush();
        } catch (Exception ex) {
            Log.e("Save File", ex.getMessage());
            ex.printStackTrace();
        }
    }

    private static String queryName(Context context, Uri uri) {
        Cursor returnCursor =
                context.getContentResolver().query(uri, null, null, null, null);
        assert returnCursor != null;
        int nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
        returnCursor.moveToFirst();
        String name = returnCursor.getString(nameIndex);
        returnCursor.close();
        return name;
    }
}
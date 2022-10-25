package com.example.infits;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.polidea.rxandroidble3.RxBleClient;
import com.polidea.rxandroidble3.RxBleConnection;
import com.polidea.rxandroidble3.RxBleDevice;
import com.tenclouds.gaugeseekbar.GaugeSeekBar;
import com.txusballesteros.SnakeView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HeartRate#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HeartRate extends Fragment {

    float val = 0f;

    float value_wave = 1f;

    boolean wheel = false;

    Button startListening;
    ImageView heartImageView;
    ImageButton imgBack;

//    SnakeView snakeView;

    private Observable<RxBleConnection> connectionObservable;
    private Observable<byte[]> notificationObservable;

    TextView result_from,measuring,deviceName,min,avg,max;

    LinearLayout after_measured,after_measured_title;

    String value;

    Disposable disposable;
    RxBleDevice device;
    RxBleClient rxBleClient;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Disposable connectionDisposable;

    public HeartRate() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HeartRate.
     */
    // TODO: Rename and change types and number of parameters
    public static HeartRate newInstance(String param1, String param2) {
        HeartRate fragment = new HeartRate();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_heart_rate, container, false);

        RecyclerView pastActivity = view.findViewById(R.id.past_activity);

        ArrayList<String> dates = new ArrayList<>();
        ArrayList<String> datas = new ArrayList<>();

        String url = String.format("%sheartrate.php",DataFromDatabase.ipConfig);

        max = view.findViewById(R.id.max);
        avg = view.findViewById(R.id.avg);
        min = view.findViewById(R.id.min);
        imgBack = view.findViewById(R.id.back_heart);

        min.setText(DataFromDatabase.bpmDown);
        avg.setText(DataFromDatabase.bpm);
        max.setText(DataFromDatabase.bpmUp);

        StringRequest stringRequest = new StringRequest(Request.Method.POST,url, response -> {
            try {
                JSONObject jsonObject = new JSONObject(response);
                JSONArray jsonArray = jsonObject.getJSONArray("heart");
                for (int i = 0;i<jsonArray.length();i++){
                    JSONObject object = jsonArray.getJSONObject(i);
                    String data = object.getString("avg");
                    String date = object.getString("date");
                    dates.add(date);
                    datas.add(data);
                    System.out.println(datas.get(i));
                    System.out.println(dates.get(i));
                }
                AdapterForPastActivity ad = new AdapterForPastActivity(getContext(),dates,datas, Color.parseColor("#F1699E"));
                pastActivity.setLayoutManager(new LinearLayoutManager(getContext()));
                pastActivity.setAdapter(ad);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        },error -> {
            Toast.makeText(getActivity().getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            Log.d("Error",error.toString());
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> data = new HashMap<>();
                data.put("clientID",DataFromDatabase.clientuserID);
                return data;
            }
        };

        Volley.newRequestQueue(getActivity()).add(stringRequest);

        rxBleClient = RxBleClient.create(getContext());
        result_from = view.findViewById(R.id.result_from);
        after_measured = view.findViewById(R.id.after_measured);
        after_measured_title = view.findViewById(R.id.after_measured_title);
        deviceName = view.findViewById(R.id.deviceName);

        startListening = view.findViewById(R.id.startListening);
        heartImageView = view.findViewById(R.id.heart_anime);

        AnimationDrawable heartAnimation = (AnimationDrawable) heartImageView.getBackground();

        measuring = view.findViewById(R.id.measuring);
        try {
            device = rxBleClient.getBleDevice(DataFromDatabase.macAddress);
            connectionObservable = prepareConnectionObservable();
            deviceName.append(" "+device.getName());
        }catch (NullPointerException ex){
            startListening.setClickable(false);
            startListening.setOnClickListener(null);
            startListening.setEnabled(false);
            deviceName.append(" nill");
            Toast.makeText(getContext(),"No device is connected",Toast.LENGTH_SHORT).show();
        }
        startListening.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spinRotate();
                wheel = true;
                result_from.setText("000");
                startListening.setVisibility(View.GONE);
                heartAnimation.start();
                measuring.setVisibility(View.VISIBLE);
                after_measured.setVisibility(View.GONE);
                after_measured_title.setVisibility(View.GONE);
//                connectionDisposable = device.establishConnection(false)
//                        .flatMapSingle(rxBleConnection -> rxBleConnection.readCharacteristic(convertFromInteger(0x2A37)))
//                        .subscribe(
//                                characteristicValue -> {
//                                    value = byteArrayToHex(characteristicValue);
//                                    String name = new String(characteristicValue);
//                                    System.out.println("Value  "+value);
//                                    System.out.println(name);
//                                },
//                                throwable -> {
//                                    // Handle an error here.
//                                    System.out.println(throwable);
//                                }
//                        );
                disposable = connectionObservable
                    .flatMap(rxBleConnection -> rxBleConnection.setupNotification(convertFromInteger(0x2A37)))
                    .flatMap(notificationObservable -> notificationObservable)
                    .subscribe(this::onNotificationReceived, this::onNotificationSetupFailure);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        result_from.setText(value);
                        heartAnimation.stop();
                        heartAnimation.selectDrawable(0);
                        heartAnimation.setVisible(true,true);
                        disposable.dispose();
                        measuring.setVisibility(View.GONE);
                        after_measured.setVisibility(View.VISIBLE);
                        after_measured_title.setVisibility(View.VISIBLE);
                        startListening.setVisibility(View.VISIBLE);
//                        snakeView.setVisibility(View.GONE);
                        wheel = false;
                        System.out.println("Help");
                    }
                }, 10000);
            }

            private void onNotificationSetupFailure(Throwable throwable) {
                final Dialog dialog = new Dialog(getContext());
//                dialog.requestWindowFeature(Window.);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.referralcodedialog);
                dialog.show();
            }

            private void onNotificationReceived(byte[] bytes) {
                final StringBuilder stringBuilder = new StringBuilder(bytes.length);
                for(byte byteChar : bytes){
                    stringBuilder.append(byteChar);
                    System.out.print(byteChar + " ");
                }


//                intent.putExtra(EXTRA_DATA, new String(data) + "\n" +
//                        stringBuilder.toString());
                try {
                    System.out.println(new String(bytes,"UTF-8")+"   "+stringBuilder.toString()+"   ");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                System.out.print(bytes+"   ");
                value = String.valueOf(bytes[1]);
                System.out.println(value);
//                System.out.print();
                System.out.println("     Heart " + new String(bytes, StandardCharsets.UTF_16LE));

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        result_from.setText(value);
                    }
                },1000);
            }
        });

        imgBack.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_heartRate_to_dashBoardFragment);
            FragmentManager manager = getActivity().getSupportFragmentManager();
            manager.popBackStack();
        });

        return view;
    }

    public UUID convertFromInteger(int i) {
        final long MSB = 0x0000000000001000L;
        final long LSB = 0x800000805f9b34fbL;
        long value = i & 0xFFFFFFFF;
        return new UUID(MSB | (value << 32), LSB);
    }
    private String byteArrayToHex ( byte[] a){
        StringBuilder sb = new StringBuilder(a.length * 2);
        for (byte b : a)
            sb.append(String.format("%02x", b & 0xff));
        return sb.toString();
    }
    public void spinRotate(){
        val = 0;
//        new Handler().post(new Runnable() {
//            @Override
//            public void run() {
//            while (wheel){
//                    if (value_wave <=100){
//                        snakeView.addValue(value_wave++);
//                    }
//                    else{
//                        value_wave = 1f;
//                    }
//                try {
//                    Thread.sleep(100);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                }
//            }
//        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (wheel){
//                    snakeView.addValue(value_wave++);
//                    snakeView.addValue(value_wave++);
                    System.out.println("Hi");
                    if (val >= 1.0f){
                        val = 0;
                        System.out.println("inside if");
                    }
                    else{
                        val = (float) (val+0.1f);
                        System.out.println("inside else" + val);
//                        gaugeSeekBar.setProgress(val);
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }
    private Observable<RxBleConnection> prepareConnectionObservable() {
        return device
                .establishConnection(false);
    }
}
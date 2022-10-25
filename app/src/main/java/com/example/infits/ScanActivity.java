package com.example.infits;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;

public class ScanActivity extends AppCompatActivity {

    SurfaceView surfaceView;

    com.google.android.gms.samples.vision.barcodereader.ui.camera.CameraSource cameraSourceFlash;

    ImageView turn_on_flash, turn_off_flash;

    BarcodeDetector barcodeDetector;

    ToneGenerator toneGen1;
    TextView barcodeText;
    String barcodeData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                finish();
                startActivity(new Intent(getApplicationContext(),DashBoardMain.class));
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);

        surfaceView = findViewById(R.id.camera_screen);

        turn_on_flash = findViewById(R.id.flash_on_btn);

        turn_off_flash = findViewById(R.id.flash_off_btn);

        toneGen1 = new ToneGenerator(AudioManager.STREAM_MUSIC,     100);

        barcodeText = findViewById(R.id.barcodeText);

        barcodeDetector = new BarcodeDetector.Builder(this)
                .setBarcodeFormats(Barcode.ALL_FORMATS)
                .build();

        cameraSourceFlash = new com.google.android.gms.samples.vision.barcodereader.ui.camera.CameraSource
                .Builder(this, barcodeDetector)
                .setRequestedPreviewSize(1920, 1080)
                .setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO)
                .setFlashMode(Camera.Parameters.FLASH_MODE_AUTO)
                .build();

        turn_on_flash.setOnClickListener(v -> {
            cameraSourceFlash.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            turn_on_flash.setVisibility(View.GONE);
            turn_off_flash.setVisibility(View.VISIBLE);
        });

        turn_off_flash.setOnClickListener(v -> {
            cameraSourceFlash.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            turn_on_flash.setVisibility(View.VISIBLE);
            turn_off_flash.setVisibility(View.GONE);
        });

        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try {
                    if (ActivityCompat.checkSelfPermission(ScanActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                        cameraSourceFlash.start(surfaceView.getHolder());
                    } else {
                        ActivityCompat.requestPermissions(ScanActivity.this, new
                                String[]{Manifest.permission.CAMERA}, 201);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSourceFlash.stop();
            }
        });
        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> barcodes = detections.getDetectedItems();
                if (barcodes.size() != 0) {


                    barcodeText.post(new Runnable() {

                        @Override
                        public void run() {

                            if (barcodes.valueAt(0).email != null) {
                                barcodeText.removeCallbacks(null);
                                barcodeData = barcodes.valueAt(0).email.address;
                                barcodeText.setText(barcodeData);
                                toneGen1.startTone(ToneGenerator.TONE_CDMA_PIP, 150);
                            } else {
                                barcodeData = barcodes.valueAt(0).displayValue;
                                barcodeText.setText(barcodeData);
                                toneGen1.startTone(ToneGenerator.TONE_CDMA_PIP, 150);
                                Intent intent = new Intent(ScanActivity.this,ScanResult.class);
                                intent.putExtra("barcode",barcodeData);
                                startActivity(intent);
                                cameraSourceFlash.stop();
                                finish();
                            }
                        }
                    });
                }
            }
        });

    }
}
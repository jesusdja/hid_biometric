package com.example.hid_pro4;

import android.content.Intent;
import android.view.View;

import com.google.android.gms.common.api.Status;

import java.util.HashMap;
import java.util.Map;

import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugins.GeneratedPluginRegistrant;

import androidx.annotation.NonNull;


public class MainActivity extends FlutterActivity {
    private static final String CHANNEL = "metolChannel";
    private static final int SCAN_FINGER = 0;
    private MethodChannel.Result scanResult;
    private boolean isScanning = false; // Variable para verificar si ya se está realizando una exploración

    @Override
    public void configureFlutterEngine(@NonNull FlutterEngine flutterEngine) {
        super.configureFlutterEngine(flutterEngine);
        GeneratedPluginRegistrant.registerWith(flutterEngine);

        new MethodChannel(flutterEngine.getDartExecutor().getBinaryMessenger(), CHANNEL)
                .setMethodCallHandler((call, result) -> {
                    if (call.method.equals("startScan")) {
                        if (!isScanning) { // Verificar si ya se está realizando una exploración
                            isScanning = true; // Establecer el indicador de exploración a true
                            scanResult = result;
                            startScanActivity();
                        } else {
                            // Si ya hay una exploración en curso, se puede informar al Flutter sobre la situación
                            result.error("SCAN_IN_PROGRESS", "Ya hay otro escaneo en curso.", null);
                        }
                    } else {
                        result.notImplemented();
                    }
                });
    }

    private void startScanActivity() {
        Intent intent = new Intent(this, ScanActivity2.class);
        startActivityForResult(intent, SCAN_FINGER);
    }

    public void startScan(View view) {
        startScanActivity();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SCAN_FINGER) {
            if (resultCode == RESULT_OK) {
                int status = data.getIntExtra("status", Integer.parseInt(Status.NULL));
                byte[] img = data.getByteArrayExtra("img");
                String errorMessage = data.getStringExtra("errorMessage");
                scanResult.success(createScanResult(status, img, errorMessage));
            } else {
                scanResult.error("SCAN_ERROR", "Análisis cancelado o fallido.", null);
            }
            isScanning = false; // Establecer el indicador de exploración a false después de completar la exploración
            scanResult = null;
        }
    }

    private Map<String, Object> createScanResult(int status, byte[] img, String errorMessage) {
        Map<String, Object> scanResult = new HashMap<>();
        scanResult.put("status", status);
        scanResult.put("img", img);
        scanResult.put("errorMessage", errorMessage);
        return scanResult;
    }

}

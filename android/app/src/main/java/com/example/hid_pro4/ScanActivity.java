//package com.example.hid_pro4;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Looper;
//import android.os.Message;
//import android.src.main.java.asia.kanopi.Fingerprint;
//import android.src.main.java.asia.kanopi.Status;
//import android.view.MenuItem;
//import android.widget.TextView;
//
//import io.flutter.embedding.android.FlutterActivity;
//import io.flutter.plugin.common.MethodChannel;
//
//public class ScanActivity extends FlutterActivity {
//    private TextView tvStatus;
//    private TextView tvError;
//    private Fingerprint fingerprint;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//       // ActionBar actionBar = getSupportActionBar();
//       // setContentView(R.layout.activity_scan);
//       // tvStatus = (TextView) findViewById(R.id.tvStatus);
//       // tvError = (TextView) findViewById(R.id.tvError);
//       // actionBar.setHomeButtonEnabled(true);
//       // actionBar.setDisplayHomeAsUpEnabled(true);
//        fingerprint = new Fingerprint();
//
//        MethodChannel channel = new MethodChannel(getFlutterEngine().getDartExecutor().getBinaryMessenger(), "metolChannel");
//        channel.setMethodCallHandler((call, result) -> {
//            if (call.method.equals("getResult")) {
//                int status = Integer.parseInt(call.argument("status").toString());
//                byte[] img = call.argument("img");
//                String errorMessage = call.argument("errorMessage").toString();
//                sendResult(status, img, errorMessage);
//            }
//        });
//    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                setResult(RESULT_CANCELED);
//                fingerprint.turnOffReader();
//                finish();
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    @Override
//    protected void onStart() {
//        fingerprint.scan(this,
//                //printHandler,
//                updateHandler);
//        super.onStart();
//    }
//
//    @Override
//    protected void onStop() {
//        fingerprint.turnOffReader();
//        super.onStop();
//    }
//
//    Handler updateHandler = new Handler(Looper.getMainLooper()) {
//        @Override
//        public void handleMessage(Message msg) {
//            int status = msg.getData().getInt("status");
//            tvError.setText("");
//            switch (status) {
//                case Status.INITIALISED:
//                    tvStatus.setText("Setting up reader");
//                    break;
//                case Status.SCANNER_POWERED_ON:
//                    tvStatus.setText("Reader powered on");
//                    break;
//                case Status.READY_TO_SCAN:
//                    tvStatus.setText("Ready to scan finger");
//                    break;
//                case Status.FINGER_DETECTED:
//                    tvStatus.setText("Finger detected");
//                    break;
//                case Status.RECEIVING_IMAGE:
//                    tvStatus.setText("Receiving image");
//                    break;
//                case Status.FINGER_LIFTED:
//                    tvStatus.setText("Finger has been lifted off reader");
//                    break;
//                case Status.SCANNER_POWERED_OFF:
//                    tvStatus.setText("Reader is off");
//                    break;
//                case Status.SUCCESS:
//                    tvStatus.setText("Fingerprint successfully captured");
//                    break;
//                case Status.ERROR:
//                    tvStatus.setText("Error");
//                    tvError.setText(msg.getData().getString("errorMessage"));
//                    break;
//                default:
//                    tvStatus.setText(String.valueOf(status));
//                    tvError.setText(msg.getData().getString("errorMessage"));
//                    break;
//
//            }
//        }
//    };
//
//    private void sendResult(int status, byte[] img, String errorMessage) {
//        Intent intent = new Intent();
//        intent.putExtra("status", status);
//        intent.putExtra("img", img);
//        intent.putExtra("errorMessage", errorMessage);
//        setResult(RESULT_OK, intent);
//        finish();
//    }
//
////    Handler printHandler = new Handler(Looper.getMainLooper()) {
////        @Override
////        public void handleMessage(Message msg) {
////            byte[] image;
////            String errorMessage = "empty";
////            int status = msg.getData().getInt("status");
////            Intent intent = new Intent();
////            intent.putExtra("status", status);
////            if (!(status != VoicemailContract.Status.SUCCESS)) {
////                image = msg.getData().getByteArray("img");
////                intent.putExtra("img", image);
////            } else {
////                errorMessage = msg.getData().getString("errorMessage");
////                intent.putExtra("errorMessage", errorMessage);
////            }
////            setResult(RESULT_OK, intent);
////            finish();
////        }
////    };
//}
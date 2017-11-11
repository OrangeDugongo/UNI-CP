package it.unisannio.cp.orange.activitylauncher;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.net.URI;

public class MainActivity extends AppCompatActivity {

    private final static int CAMERA_CODE = 1400;
    private final static int PHONE_CODE = 1401;
    private final static int BOOM_CODE = 1404;

    private final static String ACTION_DETONATE = "it.unisannio.cp.orange.dinamite.DETONATE";
    private final static String PERMISSION_BOOM = "it.unisannio.cp.orange.dinamite.permission.BOOM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //CALL
        Button call = (Button) findViewById(R.id.call);
        call.setOnClickListener(new CallListener());

        //CAMERA
        Button camera = (Button) findViewById(R.id.camera);
        camera.setOnClickListener(new CameraListener());

        //DETONATORE
        Button detonatore = findViewById(R.id.detonatore);
        detonatore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detonatoreIntent = new Intent();
                detonatoreIntent.setAction(ACTION_DETONATE);
                if(checkPermission(PERMISSION_BOOM))
                    startActivity(detonatoreIntent);
                else
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{PERMISSION_BOOM}, BOOM_CODE);
            }
        });

        //ASK
        Button ask = (Button) findViewById(R.id.ask);
        ask.setOnClickListener(new AskListener());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case CAMERA_CODE:
                if(grantResults[0]==PackageManager.PERMISSION_DENIED)
                    Toast.makeText(getApplicationContext(), "NO Permission", Toast.LENGTH_LONG).show();
                else
                    startActivity(new Intent(MediaStore.ACTION_IMAGE_CAPTURE));
                break;

            case PHONE_CODE:
                if(grantResults[0]==PackageManager.PERMISSION_DENIED)
                    Toast.makeText(getApplicationContext(), "NO Permission", Toast.LENGTH_LONG).show();
                else{
                    Intent call = new Intent();
                    call.setAction(Intent.ACTION_CALL);
                    call.setData(Uri.parse("tel:3338134583"));
                    startActivity(call);
                }
                break;
            case BOOM_CODE:
                if(grantResults[0]==PackageManager.PERMISSION_DENIED)
                    Toast.makeText(getApplicationContext(), "NO Permission", Toast.LENGTH_LONG).show();
                else
                    startActivity(new Intent(ACTION_DETONATE));
                break;

        }
    }

    public boolean checkPermission(String permission){
        int result = ContextCompat.checkSelfPermission(getApplicationContext(), permission);
        return result == PackageManager.PERMISSION_GRANTED;
    }

    private class CallListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent call = new Intent();
            call.setAction(Intent.ACTION_CALL);
            call.setData(Uri.parse("tel:404"));
            if(checkPermission(Manifest.permission.CALL_PHONE))
                startActivity(call);
            else
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, PHONE_CODE);
        }
    }

    private class CameraListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent camera = new Intent();
            camera.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
            if(checkPermission(Manifest.permission.CAMERA))
                startActivity(camera);
            else
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, CAMERA_CODE);
        }
    }

    private class AskListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE, Manifest.permission.CAMERA, PERMISSION_BOOM}, 1403);
        }
    }
}

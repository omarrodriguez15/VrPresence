package com.example.bibleman4555.phonetest;

import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.app.Application;
import android.test.ApplicationTestCase;
import java.io.IOException;
import java.net.*;
import android.util.Log;
import android.widget.Button;

import android.view.View;
import android.hardware.SensorEventListener;
import android.app.Activity;
import android.os.Bundle;
import android.hardware.SensorManager;
import android.hardware.Sensor;
import android.support.v7.app.ActionBarActivity;
import android.view.View.OnClickListener;



public class TestMain extends ActionBarActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_main);
        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        Button btnStart = (Button)findViewById(R.id.up_button);
        btnStart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                sendToArduino(v);
            }
        });
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private static final String TAG = TestMain.class.getSimpleName();
    private SensorManager mSensorManager;
    private Sensor mSensor;

    static void sendToArduino(View v){
        try {
            String messageStr = "0001";
            int server_port = 8888;
            DatagramSocket s = new DatagramSocket(server_port);
            InetAddress local = InetAddress.getLocalHost();//InetAddress.getByName("192.168.1.105");
            int msg_length = messageStr.length();
            byte[] message = messageStr.getBytes();
            DatagramPacket p = new DatagramPacket(message, msg_length, local, server_port);
            s.send(p);
        } catch (IOException s) {
            Log.d(TAG, "someOtherMethod()", s);

        }

    }


}

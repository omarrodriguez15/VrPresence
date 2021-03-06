package com.example.bibleman4555.phonetest;

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
import android.hardware.SensorEvent;
import android.content.Context;
import android.widget.EditText;



public class TestMain extends ActionBarActivity implements SensorEventListener{

    private static final String TAG = TestMain.class.getSimpleName();
    private SensorManager mSensorManager;
    private Sensor mSensor;
    private float xini, yini, zini = 0;
    final EditText text1 = (EditText) findViewById(R.id.x_box);
    final EditText text2 = (EditText) findViewById(R.id.y_box);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_main);
        Button btnStart = (Button)findViewById(R.id.up_button);
        btnStart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                sendToArduino(v);
            }
        });
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
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

    boolean setinit = false;
    boolean left, right, up, down = false;

    @Override
    public final void onSensorChanged(SensorEvent event) {
        // Many sensors return 3 values, one for each axis.
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];
        if(setinit == false){
            xini = x;
            yini = y;
            zini = z;
            setinit = true;
        }
        if(x < xini){
            left = true;
        }
        if(x > xini){
            right = true;
        }
        if(y < yini){
            down = true;
        }
        if(y > yini){
            up = true;
        }
        int tempx = (int) x;
        int tempy = (int) y;
        text1.setText(tempx);
        text2.setText(tempy);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // TODO Auto-generated method stub
    }

    static void sendToArduino(View v){
        try {
            //String format: "0 0 0 0" = "Left Right Up Down" so "0101" would be "NoLeft Right NoUp Down"
             String messageStr = "0001";
            int server_port = 8888;
            DatagramSocket s = new DatagramSocket();
            InetAddress local = InetAddress.getByName("192.168.1.105");
            int msg_length = messageStr.length();
            byte[] message = messageStr.getBytes();
            DatagramPacket p = new DatagramPacket(message, msg_length, local, server_port);
            s.send(p);
        } catch (IOException s) {
            Log.d(TAG, "someOtherMethod()", s);

        }

    }




}

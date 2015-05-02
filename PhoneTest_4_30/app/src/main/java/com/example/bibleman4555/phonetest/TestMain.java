package com.example.bibleman4555.phonetest;

import android.nfc.Tag;
import android.os.AsyncTask;
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


public class TestMain extends ActionBarActivity  implements SensorEventListener{

    private static final String TAG = TestMain.class.getSimpleName();
    private SensorManager mSensorManager;
    private Sensor mSensor;
    private float xini, yini, zini = 0;
   // final EditText text1 = (EditText) findViewById(R.id.x_box);
   // final EditText text2 = (EditText) findViewById(R.id.y_box);
    EditText text1,text2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_main);

        text1 = (EditText) findViewById(R.id.x_box);
        text2 = (EditText) findViewById(R.id.y_box);
        Button btnUp = (Button)findViewById(R.id.up_button);
        btnUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new ATcommandThread().execute("up");
            }
        });
        Button btnRight = (Button)findViewById(R.id.right_button);
        btnRight.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                 new ATcommandThread().execute("right");
            }
        });
        Button btnLeft = (Button)findViewById(R.id.left_button);
        btnLeft.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new ATcommandThread().execute("left");
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

        //Debug Info
        if(event.sensor.getType() == Sensor.TYPE_ROTATION_VECTOR){
            String log = String.format("x = %f y = %f z = %f",x, y, z);
            Log.i(TAG,log);
        }

        //Set TextBox Values
        String xVal = String.format("%f",x);
        String yVal = String.format("%f",x);
        text1.setText(xVal);
        text2.setText(yVal);


        if(setinit == false){
            xini = x;
            yini = y;
            zini = z;
        }
        if(x < (xini-.1)){
            left = true;
        }
        if(x > (xini+.1) ){
            right = true;
        }
        if(y < (yini-.1)){
            down = true;
        }
        if(y > (yini+.1)){
            up = true;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        if (sensor.getType() == Sensor.TYPE_ROTATION_VECTOR) {
            Log.i(TAG, "is accuracy");

        } else {
            Log.i(TAG, "is not accuracy");
        }
    }


}




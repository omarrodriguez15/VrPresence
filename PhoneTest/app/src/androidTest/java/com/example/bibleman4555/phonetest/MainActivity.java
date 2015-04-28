package com.example.bibleman4555.phonetest;

import android.app.Application;
import android.test.ApplicationTestCase;
import java.io.IOException;
import java.net.*;
import android.util.Log;

import android.view.View;
import android.hardware.SensorEventListener;
import android.app.Activity;
import android.os.Bundle;
import android.hardware.SensorManager;
import android.hardware.Sensor;
import android.support.v7.app.ActionBarActivity;

public class MainActivity extends ActionBarActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private SensorManager mSensorManager;
    private Sensor mSensor;

    static void sendToArduino(){
        try {
            String messageStr = "0001";
            int server_port = 12345;
            DatagramSocket s = new DatagramSocket();
            InetAddress local = InetAddress.getByName("");
            int msg_length = messageStr.length();
            byte[] message = messageStr.getBytes();
            DatagramPacket p = new DatagramPacket(message, msg_length, local, server_port);
            s.send(p);
        } catch (IOException s) {
            Log.d(TAG, "someOtherMethod()", s);

        }

    }

    public static void Main(View v) {
        sendToArduino();
    }

}

/*public abstract class ApplicationTest extends ApplicationTestCase<Application> implements View.OnClickListener{
    public ApplicationTest() {
        super(Application.class);
    }
    private static final String TAG = ApplicationTest.class.getSimpleName();
    private SensorManager mSensorManager;
    private Sensor mSensor;

    static void sendToArduino(){
        try {
            String messageStr = "0001";
            int server_port = 12345;
            DatagramSocket s = new DatagramSocket();
            InetAddress local = InetAddress.getByName("");
            int msg_length = messageStr.length();
            byte[] message = messageStr.getBytes();
            DatagramPacket p = new DatagramPacket(message, msg_length, local, server_port);
            s.send(p);
        } catch (IOException s) {
            Log.d(, "someOtherMethod()", s);

        }
/

    }

    public static void Main() {
        sendToArduino();
    }
}
*/
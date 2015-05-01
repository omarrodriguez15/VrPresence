package com.example.bibleman4555.phonetest;

import android.nfc.Tag;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ATcommandThread extends AsyncTask<String, Integer, Boolean> {
    private static final String TAG = TestMain.class.getSimpleName();
    protected Boolean doInBackground(String... arg0) {
        try {

            String messageStr = "0001";
            int server_port = 8888;
            DatagramSocket s = new DatagramSocket();
            InetAddress local = InetAddress.getByName("192.168.1.105");
            int msg_length = messageStr.length();
            byte[] message = messageStr.getBytes();
            DatagramPacket p = new DatagramPacket(message, msg_length, local, server_port);
            s.send(p);
            Log.d(TAG,"True");
            return true;
        } catch (IOException s) {
            Log.d(TAG,"Exception Thrown");
            return false;
        }
    }
}

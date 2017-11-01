package com.ptp.phamtanphat.socketio0208;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class MainActivity extends AppCompatActivity {

    private Socket socket;
    Button btnnhandulieu;
    TextView txtdata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        btnnhandulieu = (Button) findViewById(R.id.buttonsavedata);
        txtdata = (TextView) findViewById(R.id.textview);
        try {
            socket = IO.socket("http://192.168.1.181:3000/");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        socket.on("server-send-data", ReceivingData);
        socket.emit("client-send-data","Chao-server");
        socket.connect();


    }

    public Emitter.Listener ReceivingData = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            JSONObject jsonObject = (JSONObject) args[0];
            try {
                String noidung = jsonObject.getString("noidung");
                txtdata.setText(noidung);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };

}

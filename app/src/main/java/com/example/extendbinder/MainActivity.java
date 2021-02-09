package com.example.extendbinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    EditText txt;
    MyService mservice;
    boolean status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt = (EditText)findViewById(R.id.editText1);
    }

    public void bindMethod(View v)
    {
        Intent i = new Intent(this,MyService.class);
        bindService(i, sc, Context.BIND_AUTO_CREATE);
        status = true;
        Toast.makeText(getBaseContext(), "Service binded successfully", Toast.LENGTH_LONG).show();
    }

    public void unBindMethod(View v)
    {
        if (status)
        {
            unbindService(sc);
            Toast.makeText(getBaseContext(), "Service unbinded successfully", Toast.LENGTH_LONG).show();
            status = false;
        }
        else
        {
            Toast.makeText(getBaseContext(), "Service already unbinded", Toast.LENGTH_LONG).show();
        }
    }

    public void factorialMethod(View v)
    {
        if (status)
        {
            int num = Integer.parseInt(txt.getText().toString());
            int result = mservice.findFactorial(num);
            Toast.makeText(getBaseContext(), "Factorial = "+result, Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(getBaseContext(), "First bind the service", Toast.LENGTH_LONG).show();
        }
    }

    private final ServiceConnection sc = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyService.LocalBinder binder = (MyService.LocalBinder) service;
            mservice = binder.getService();
            status = true;
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
            status=false;

        }
    };
}

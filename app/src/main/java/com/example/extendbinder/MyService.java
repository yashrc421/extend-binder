package com.example.extendbinder;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import androidx.annotation.Nullable;

public class MyService extends Service {
    private final IBinder mbinder = new LocalBinder();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mbinder;
    }

    public class LocalBinder extends Binder
    {
        public MyService getService()
        {
            return MyService.this;
        }
    }

    public int findFactorial(int x)
    {
        int fact = 1;
        for (int i = 1; i<=x;i++)
        {
            fact = fact * i;
        }
        return fact;
    }
}

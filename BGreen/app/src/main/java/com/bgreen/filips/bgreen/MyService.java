package com.bgreen.filips.bgreen;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class MyService extends Service {
    public MyService() {
        System.out.println("I SERVICE");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

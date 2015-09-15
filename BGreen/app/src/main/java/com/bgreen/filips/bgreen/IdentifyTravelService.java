package com.bgreen.filips.bgreen;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class IdentifyTravelService extends Service {
    public IdentifyTravelService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        System.out.println("I SERVICE");
        return super.onStartCommand(intent,flags,startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

package com.lemon95.ymtv.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.lemon95.ymtv.utils.LogUtils;

public class LoginService extends Service {
    private static final String TAG = "LoginService";
    public static final String ACTION = "com.login.service.Service";

    @Override
    public IBinder onBind(Intent intent) {
        LogUtils.v(TAG, "PayService onBind");
        return new Binder() {
        };
    }

    @Override
    public void onCreate() {
        LogUtils.v(TAG, "PayService onCreate");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtils.v(TAG, "PayService onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        LogUtils.i(TAG, "onUnbind called.");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.i(TAG, "onDestroy called.");
    }
}

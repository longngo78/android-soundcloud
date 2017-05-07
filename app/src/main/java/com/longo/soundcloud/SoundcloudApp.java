package com.longo.soundcloud;

import android.app.Application;

import com.longo.soundcloud.services.SoundCloudService;

/**
 * Created by longngo on 5/6/17.
 */

public class SoundcloudApp extends Application {
    private static SoundcloudApp instance;

    private SoundCloudService mSoundCloudService;

    public static SoundcloudApp getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        if (instance == null) {
            instance = this;
        }

        mSoundCloudService = new SoundCloudService(null);
    }

    public SoundCloudService getSoundCloudService() {
        return mSoundCloudService;
    }
}

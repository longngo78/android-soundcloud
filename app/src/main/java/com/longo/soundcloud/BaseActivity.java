package com.longo.soundcloud;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by longngo on 5/6/17.
 */

public class BaseActivity extends AppCompatActivity {
    protected final SoundcloudApp mApp = SoundcloudApp.getInstance();

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}

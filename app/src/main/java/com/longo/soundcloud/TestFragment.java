package com.longo.soundcloud;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.longo.soundcloud.loaders.SoundCloudService;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A placeholder fragment containing a simple view.
 */
public class TestFragment extends Fragment implements SoundCloudService.Listener {
    public static final String TAG = TestFragment.class.getSimpleName();

    private SoundCloudService mSoundCloudService = new SoundCloudService(this);

    public TestFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_test, container, false);
    }

    public void loadPlaylist() {
        mSoundCloudService.loadPlaylist(309636327);
    }

    @Override
    public void onLoadPlaylist(final JSONObject json) {
        if (json == null) {
            // TODO handle error!
            return;
        }

        try {
            Log.d(TAG, json.getString("title"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

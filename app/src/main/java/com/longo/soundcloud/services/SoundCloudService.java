package com.longo.soundcloud.services;

import com.google.gson.Gson;
import com.longo.soundcloud.loaders.UrlLoader;

/**
 * Created by longngo on 5/5/17.
 */

public class SoundCloudService implements UrlLoader.Listener {
    public static final String TAG = SoundCloudService.class.getSimpleName();

    public static final String NAME = "SoundCloud";
    public static final String DEF_CLIENT_ID = "7320d71948db64e13fc795dc1ff0da05";    // Daniel's


    private static final String BASE_URL = "http://api.soundcloud.com/";
    private static final String STREAM_URL = BASE_URL + "tracks/%s/stream?client_id=%s";
    private static final String PLAYLIST_URL = BASE_URL + "playlists/%s?client_id=%s";

    public String clientId = DEF_CLIENT_ID;

    private UrlLoader mUrlLoader = new UrlLoader(this);
    private Listener mListener;

    private Gson mGson = new Gson();
    private PlaylistVO mSelectedPlaylist;

    public SoundCloudService(Listener listener) {
        mListener = listener;
    }

    public Listener getListener() {
        return mListener;
    }

    public SoundCloudService setListener(final Listener listener) {
        mListener = listener;
        return this;
    }

    public void loadPlaylist(final long playlistId)
    {
        final String url = String.format(PLAYLIST_URL, playlistId, clientId);
        mUrlLoader.execute(url);
    }

    public PlaylistVO getSelectedPlaylist() {
        return mSelectedPlaylist;
    }

    @Override
    public void onLoad(final UrlLoader loader, final String result) {
        mSelectedPlaylist = mGson.fromJson(result, PlaylistVO.class);

        // callback
        if (mListener != null) {
            mListener.onLoadPlaylist(mSelectedPlaylist);
        }
    }

    public interface Listener {
        void onLoadPlaylist(final PlaylistVO playlist);
    }
}

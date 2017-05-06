package com.longo.soundcloud.loaders;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by longngo on 5/5/17.
 */

public class UrlLoader extends AsyncTask<String, Integer, String> {
    private static final String TAG = UrlLoader.class.getSimpleName();

    protected Listener mListener;

    public UrlLoader(Listener listener) {
        mListener = listener;
    }

    protected String load(final String httpUrl) {

        try {
            final StringBuilder sb = new StringBuilder();

            // Create a URL for the desired page
            final URL url = new URL(httpUrl);

            // Read all the text returned by the server
            final BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String str;
            while ((str = in.readLine()) != null) {
                sb.append(str);
            }
            in.close();

            return sb.toString();
        } catch (MalformedURLException e) {
            Log.e(TAG, "", e);
        } catch (IOException e) {
            Log.e(TAG, "", e);
        }

        return null;
    }

    @Override
    protected String doInBackground(final String... urls) {
        final StringBuilder sb = new StringBuilder();

        for (int i = 0; i < urls.length; i++) {
            final String st = load(urls[i]);
            if (st != null) {
                sb.append(st);
            }

            // some progress
            publishProgress(i);
        }

        return sb.toString();
    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected void onProgressUpdate(final Integer... values) {
    }

    @Override
    protected void onPostExecute(final String result) {
        Log.d(TAG, "onPostExecute()");

        // callback
        if (mListener != null) {
            mListener.onLoad(result);
        }
    }

    public interface Listener {
        void onLoad(final String result);
    }
}

package com.longo.soundcloud;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.longo.soundcloud.services.SoundCloudService;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A fragment representing a list of Items.
 * <p />
 * Activities containing this fragment MUST implement the {@link Listener}
 * interface.
 */
public class PlaylistFragment extends Fragment implements SoundCloudService.Listener {
    public static final String TAG = PlaylistFragment.class.getSimpleName();

    private final SoundcloudApp mApp = SoundcloudApp.getInstance();

    private RecyclerView mListView;
    private PlaylistAdapter mAdapter;
    private Listener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PlaylistFragment() {
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof Listener) {
            mListener = (Listener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // listener
        mApp.getSoundCloudService().setListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_playlist, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            final Context context = view.getContext();
            mListView = (RecyclerView) view;
            mListView.setLayoutManager(new LinearLayoutManager(context));
            mListView.setAdapter(mAdapter = new PlaylistAdapter(mListener));

            // load it
            loadPlaylist();
        }

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();

        mListener = null;
    }

    public interface Listener {
        void onItemClick(final int index, final JSONObject item);
    }

    public void loadPlaylist() {
        mApp.getSoundCloudService().loadPlaylist(309636327);
    }

    @Override
    public void onLoadPlaylist(final JSONObject json) {
        if (json == null) {
            // TODO handle error!
            return;
        }

        try {
            //Log.d(TAG, json.getString("title"));

            // set some items
            mAdapter.setItems(json.getJSONArray("tracks"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

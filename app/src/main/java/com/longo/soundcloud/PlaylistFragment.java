package com.longo.soundcloud;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.longo.soundcloud.loaders.SoundCloudService;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A fragment representing a list of Items.
 * <p />
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class PlaylistFragment extends Fragment implements SoundCloudService.Listener {
    public static final String TAG = PlaylistFragment.class.getSimpleName();

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    private SoundCloudService mSoundCloudService = new SoundCloudService(this);
    private RecyclerView mListView;
    private PlaylistAdapter mAdapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PlaylistFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    /*public static PlaylistFragment newInstance(int columnCount) {
        PlaylistFragment fragment = new PlaylistFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }*/

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_playlist, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            final Context context = view.getContext();
            mListView = (RecyclerView) view;

            if (mColumnCount <= 1) {
                mListView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                mListView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

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

    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(JSONObject item);
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
            Log.d(TAG, json.getString("title") + "-----------------------------------------------" + Thread.currentThread());

            // set some items
            mAdapter.setItems(json.getJSONArray("tracks"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

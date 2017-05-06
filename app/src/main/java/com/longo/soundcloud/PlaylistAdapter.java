package com.longo.soundcloud;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.longo.soundcloud.PlaylistFragment.OnListFragmentInteractionListener;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.ViewHolder> {

    private final OnListFragmentInteractionListener mListener;
    private JSONArray mItems;

    public PlaylistAdapter(OnListFragmentInteractionListener listener) {
        mListener = listener;
    }

    public PlaylistAdapter setItems(final JSONArray items) {
        mItems = items;

        notifyDataSetChanged();
        return this;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.track_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        // empty check
        if (mItems == null) return;

        try {
            holder.setItem(mItems.getJSONObject(position));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems != null ? mItems.length() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private JSONObject mItem;

        private final View mView;
        //private final TextView mIdView;
        private final ImageView mTrackImage;
        private final TextView mTitleView;

        public ViewHolder(View view) {
            super(view);
            mView = view;

            // find the views
            //mIdView = (TextView) view.findViewById(R.id.track_id);
            mTrackImage = (ImageView) view.findViewById(R.id.track_image);
            mTitleView = (TextView) view.findViewById(R.id.track_title);
        }

        public ViewHolder setItem(final JSONObject item) throws JSONException {
            mItem = item;

            //mIdView.setText(item.getString("id"));
            Picasso.with(mView.getContext()).load(item.getString("artwork_url")).into(mTrackImage);
            mTitleView.setText(item.getString("title"));

            return this;
        }

        @Override
        public String toString() {
            return super.toString() + " " + mItem;
        }
    }
}

package com.longo.soundcloud;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.longo.soundcloud.PlaylistFragment.Listener;
import com.longo.soundcloud.services.TrackVO;
import com.squareup.picasso.Picasso;

import org.json.JSONException;

import java.util.List;

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.ViewHolder> {

    private final Listener mListener;
    private List<TrackVO> mItems;

    public PlaylistAdapter(Listener listener) {
        mListener = listener;
    }

    public PlaylistAdapter setItems(final List<TrackVO> items) {
        mItems = items;

        notifyDataSetChanged();
        return this;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_track, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        // empty check
        if (mItems == null) return;

        try {
            holder.setItem(position, mItems.get(position));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onItemClick(holder.mItemIndex, holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems != null ? mItems.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private int mItemIndex = -1;
        private TrackVO mItem;

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

        public ViewHolder setItem(final int itemIndex, final TrackVO item) throws JSONException {
            mItemIndex = itemIndex;
            mItem = item;

            //mIdView.setText(item.getString("id"));
            Picasso.with(mView.getContext()).load(item.artwork_url).into(mTrackImage);
            mTitleView.setText(item.title);

            return this;
        }

        @Override
        public String toString() {
            return super.toString() + " " + mItem;
        }
    }
}

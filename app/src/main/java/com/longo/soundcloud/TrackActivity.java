package com.longo.soundcloud;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TrackActivity extends BaseActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        // auto select
        mViewPager.setCurrentItem(getIntent().getIntExtra(MainActivity.EXTRA_TRACK_INDEX, 0));
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class TrackFragment extends Fragment {
        private int mTrackIndex;
        private JSONObject mTrackItem;

        public TrackFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static TrackFragment newInstance(int trackIndex) {
            final TrackFragment fragment = new TrackFragment();
            fragment.mTrackIndex = trackIndex;

            try {
                fragment.mTrackItem = SoundcloudApp.getInstance().getSoundCloudService().getSelectedTracks().getJSONObject(trackIndex);
            } catch (JSONException e) {
                // TODO
                e.printStackTrace();
            }

            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.fragment_track, container, false);
            if (mTrackItem == null) return rootView;

            try {
                // label
                final TextView textView = (TextView) rootView.findViewById(R.id.title);
                textView.setText(mTrackItem.getString("title"));

                // art
                final ImageView trackImage = (ImageView) rootView.findViewById(R.id.track_image);
                Picasso.with(getContext()).load(mTrackItem.getString("artwork_url").replace("large", "t500x500")).into(trackImage);

                final ImageView waveImage = (ImageView) rootView.findViewById(R.id.wave_image);
                Picasso.with(getContext()).load(mTrackItem.getString("waveform_url")).into(waveImage);
            } catch (JSONException e) {
                // TODO
                e.printStackTrace();
            }

            return rootView;
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return TrackFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            final JSONArray tracks = mApp.getSoundCloudService().getSelectedTracks();
            return tracks == null ? 0 : tracks.length();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "TRACK " + position;
        }
    }
}

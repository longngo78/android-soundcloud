package com.longo.soundcloud;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.longo.soundcloud.services.PlaylistVO;
import com.longo.soundcloud.services.SoundCloudService;
import com.longo.soundcloud.services.TrackVO;
import com.squareup.picasso.Picasso;

public class MainActivity extends BaseActivity implements PlaylistFragment.Listener {

    public static final String EXTRA_TRACK_INDEX = "EXTRA_TRACK_INDEX";
    private CollapsingToolbarLayout mToolbarLayout;
    private Toolbar mToolbar;
    private FloatingActionButton mFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mFab = (FloatingActionButton) findViewById(R.id.fab);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Hello there!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });

        //PlaylistFragment fragment = (PlaylistFragment) getSupportFragmentManager().findFragmentById(R.id.playlist_fragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onLoadPlaylist(final PlaylistVO playlist) {
        mToolbarLayout.setTitle(playlist.title);

        final ImageView playlistImage = (ImageView) findViewById(R.id.playlist_image);
        final String url = playlist.artwork_url != null ? playlist.artwork_url : playlist.tracks.get(0).artwork_url;
        Picasso.with(this).load(SoundCloudService.getArtUrl500(url)).into(playlistImage);
    }

    @Override
    public void onItemClick(final int index, final TrackVO track) {
        final Intent intent = new Intent(this, TrackActivity.class);
        intent.putExtra(EXTRA_TRACK_INDEX, index);
        startActivity(intent);
    }
}

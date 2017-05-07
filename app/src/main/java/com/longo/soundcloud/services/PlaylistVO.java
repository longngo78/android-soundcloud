package com.longo.soundcloud.services;

import java.util.List;

/**
 * Created by longngo on 5/7/17.
 */

public class PlaylistVO {
    public long id;
    public long user_id;
    public String uri;
    public String title;
    public String description;
    public String artwork_url;
    public boolean streamable;

    public UserVO user;
    public List<TrackVO> tracks;
}

/*
    "kind": "playlist",
    "id": 405726,
    "created_at": "2010/11/02 09:24:50 +0000",
    "user_id": 3207,
    "duration": 154516,
    "sharing": "public",
    "tag_list": "",
    "permalink": "field-recordings",
    "track_count": 5,
    "streamable": true,
    "downloadable": true,
    "embeddable_by": "me",
    "purchase_url": null,
    "label_id": null,
    "type": "other",
    "playlist_type": "other",
    "ean": "",
    "description": "a couple of field recordings to test http://soundiverse.com.\r\n\r\nrecorded with the fire recorder: http://soundcloud.com/apps/fire",
    "genre": "",
    "release": "",
    "purchase_title": null,
    "label_name": "",
    "title": "Field Recordings",
    "release_year": null,
    "release_month": null,
    "release_day": null,
    "license": "all-rights-reserved",
    "uri": "http://api.soundcloud.com/playlists/405726",
    "permalink_url": "http://soundcloud.com/jwagener/sets/field-recordings",
    "artwork_url": "http://i1.sndcdn.com/artworks-000025801802-1msl1i-large.jpg?5e64f12",
*/
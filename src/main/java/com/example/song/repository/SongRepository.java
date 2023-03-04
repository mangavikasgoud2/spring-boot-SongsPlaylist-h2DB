package com.example.song.repository;

import java.util.*;
import com.example.song.model.Song;
//import com.example.song.service.SongH2Service; 

public interface SongRepository{
    ArrayList<Song> getAllSongs();
   Song getSong(int songId);

    Song addSong(Song song);

    Song updateSong(int songId,Song song);

    void deleteSong(int songId);
}
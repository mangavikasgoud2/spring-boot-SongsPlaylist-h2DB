
package com.example.song.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.song.model.Song;
import com.example.song.service.SongH2Service;

import java.util.*;

@RestController
public class SongController{
    @Autowired
    public SongH2Service serviceSong;

    @GetMapping("/songs")
    public ArrayList<Song> getAllSongs(){
        return serviceSong.getAllSongs();
    }

    @GetMapping("/songs/{songId}")
    public Song getSong(@PathVariable int songId){
        return serviceSong.getSong(songId);
    }

    @PostMapping("/songs")
    public Song addSong(@RequestBody Song song){
        return serviceSong.addSong(song);
    }

    @PutMapping("/songs/{songId}")
    public Song updateSong(@PathVariable int songId,@RequestBody Song song){
        return serviceSong.updateSong(songId,song);
    }

    @DeleteMapping("/songs/{songId}")
    public void deleteSong(@PathVariable int songId){
        serviceSong.deleteSong(songId);
    }
    
}
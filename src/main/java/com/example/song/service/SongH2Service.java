package com.example.song.service;
import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.http.HttpStatus;
 import org.springframework.jdbc.core.JdbcTemplate;
 import org.springframework.stereotype.Service;
 import org.springframework.web.server.ResponseStatusException;
 import java.util.*;
 import com.example.song.model.SongRowMapper;
 import com.example.song.model.Song;
 import com.example.song.repository.SongRepository;



@Service
public class SongH2Service implements SongRepository{
    @Autowired
    private JdbcTemplate db;

    @Override
    public ArrayList<Song> getAllSongs(){
        List<Song> arrLi = db.query("select * from PLAYLIST",new SongRowMapper());
        ArrayList<Song> songss = new ArrayList<>(arrLi);
        return songss;
    }

    @Override
   public Song getSong(int songId){
        try{
            Song song = db.queryForObject("select * from PLAYLIST where songId=?",new SongRowMapper(),songId);
            return song;
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Song addSong(Song song){
        db.update("insert into PLAYLIST(songName,lyricist,singer,musicDirector) values(?,?,?,?)",song.getSongName(),song.getLyricist(),song.getSinger(),song.getMusicDirector());
        Song songUpdated = db.queryForObject("select * from PLAYLIST where songName=? and lyricist=?",new SongRowMapper(),song.getSongName(),song.getLyricist());
        return songUpdated;
    }

    @Override
    public Song updateSong(int songId,Song song){
        if(song.getSongName() != null){
            db.update("update PLAYLIST set songName=? where songId=?",song.getSongName(),songId);
        }
        if(song.getLyricist() != null){
            db.update("update playlist set lyricist=? where songId=?",song.getLyricist(),songId);
        }
        if(song.getSinger() != null){
            db.update("update playlist set singer=? where songId=?",song.getSinger(),songId);
        }
        if(song.getMusicDirector() != null){
            db.update("update playlist set musicDirector=? where songId=?",song.getMusicDirector(),songId);
        }
        return getSong(songId);
    }

    
    @Override
    public void deleteSong(int songId){
        db.update("delete from playlist where songId=?",songId);
        throw new ResponseStatusException(HttpStatus.OK);
    }

    
}
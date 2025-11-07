package be.skenteridis.musicplaylist.service;

import be.skenteridis.musicplaylist.model.Playlist;
import be.skenteridis.musicplaylist.model.Song;
import be.skenteridis.musicplaylist.model.User;
import be.skenteridis.musicplaylist.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistService {
    private final PlaylistRepository playlistRepository;
    private final UserRepository userRepository;
    private final SongRepository songRepository;
    public PlaylistService(PlaylistRepository playlistRepository, UserRepository userRepository, SongRepository songRepository) {
        this.playlistRepository = playlistRepository;
        this.userRepository = userRepository;
        this.songRepository = songRepository;
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public Playlist addPlaylistToUser(Long userId, Playlist playlist) {
        User user = userRepository.findById(userId).orElse(null);
        if(user == null) return null;
        user.addPlaylist(playlist);
        return playlistRepository.save(playlist);
    }

    public Song addSongToPlaylist(Long userId, Long playlistId, Song song) {
        User user = userRepository.findById(userId).orElse(null);
        if(user == null) return null;
        Playlist playlist = playlistRepository.findById(playlistId).orElse(null);
        if(playlist == null) return null;
        playlist.addSong(song);
        return songRepository.save(song);
    }

    public List<Playlist> getPlaylistsByUserId(Long id) {
        User user = userRepository.findById(id).orElse(null);
        return user == null ? null : user.getPlaylists();
    }

    public List<Song> getSongsByPlaylistId(Long id) {
        Playlist playlist = playlistRepository.findById(id).orElse(null);
        return playlist == null ? null : playlist.getSongs();
    }

    public boolean deletePlaylist(Long userId, Long playListId) {
        User user = userRepository.findById(userId).orElse(null);
        if(user == null) return false;
        Playlist playlist = playlistRepository.findById(playListId).orElse(null);
        if(playlist == null) return false;
        user.removePlaylist(playlist);
        playlistRepository.delete(playlist);
        return true;
    }

    public boolean deleteSong(Long userId, Long playListId, Long songId) {
        User user = userRepository.findById(userId).orElse(null);
        if(user == null) return false;
        Playlist playlist = playlistRepository.findById(playListId).orElse(null);
        if(playlist == null) return false;
        Song song = songRepository.findById(songId).orElse(null);
        if(song == null) return false;
        playlist.removeSong(song);
        songRepository.delete(song);
        return true;
    }
}

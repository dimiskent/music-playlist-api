package be.skenteridis.musicplaylist.controller;

import be.skenteridis.musicplaylist.model.Playlist;
import be.skenteridis.musicplaylist.model.Song;
import be.skenteridis.musicplaylist.model.User;
import be.skenteridis.musicplaylist.service.PlaylistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class PlaylistController {
    private final PlaylistService service;
    public PlaylistController(PlaylistService playlistService) {
        this.service = playlistService;
    }
    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addUser(user));
    }

    @GetMapping("/{id}/playlists")
    public ResponseEntity<?> getPlaylists(@PathVariable Long id) {
        List<Playlist> playlists = service.getPlaylistsByUserId(id);
        if(playlists == null) return ResponseEntity.status(404).body("User not found");
        else if(playlists.isEmpty()) return ResponseEntity.noContent().build();
        else return ResponseEntity.ok(playlists);
    }
    @PostMapping("/{id}/playlists")
    public ResponseEntity<?> addPlaylist(@PathVariable Long id, @RequestBody Playlist playlist) {
        Playlist resultPlaylist = service.addPlaylistToUser(id, playlist);
        return resultPlaylist == null ? ResponseEntity.status(404).body("User not found")
                : ResponseEntity.status(HttpStatus.CREATED).body(resultPlaylist);
    }
    @DeleteMapping("/{userId}/playlists/{playlistId}")
    public ResponseEntity<?> removePlaylist(@PathVariable Long userId, @PathVariable Long playlistId) {
        boolean isRemoved = service.deletePlaylist(userId, playlistId);
        return isRemoved ? ResponseEntity.ok("Playlist deleted successfully!")
                : ResponseEntity.status(404).body("User / Playlist not found!");
    }
    @PostMapping("/{userId}/playlists/{playlistId}/songs")
    public ResponseEntity<?> addSong(@PathVariable Long userId, @PathVariable Long playlistId, @RequestBody Song song) {
        Song result = service.addSongToPlaylist(userId, playlistId, song);
        return result == null ? ResponseEntity.status(404).body("User / Playlist not found!")
                : ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
    @DeleteMapping("/{userId}/playlists/{playlistId}/songs/{songId}")
    public ResponseEntity<?> removeSong(@PathVariable Long userId, @PathVariable Long playlistId, @PathVariable Long songId) {
        boolean isRemoved = service.deleteSong(userId, playlistId, songId);
        return isRemoved ? ResponseEntity.ok("Song removed successfully!")
                : ResponseEntity.status(404).body("Either the User, the Playlist or the Song hasn't been found!");
    }
}

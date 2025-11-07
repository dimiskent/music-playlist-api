package be.skenteridis.musicplaylist.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Playlist> playlists = new ArrayList<>();

    public User() {}
    public User(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void addPlaylist(Playlist playlist) {
        playlists.add(playlist);
        playlist.setUser(this);
    }

    public void removePlaylist(Playlist playlist) {
        playlists.remove(playlist);
        playlist.setUser(null);
    }
}

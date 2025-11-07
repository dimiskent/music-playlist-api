package be.skenteridis.musicplaylist.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "songs")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "duration", nullable = false)
    private Long duration;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "playlist_id")
    @JsonBackReference
    private Playlist playlist;

    public Song() {}
    public Song(String name, String author, Long duration) {
        this.name = name;
        this.author = author;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }

    public Long getId() {
        return id;
    }
}

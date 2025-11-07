package be.skenteridis.musicplaylist.repository;

import be.skenteridis.musicplaylist.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Long> {}

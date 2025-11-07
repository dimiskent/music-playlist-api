package be.skenteridis.musicplaylist.repository;

import be.skenteridis.musicplaylist.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {}

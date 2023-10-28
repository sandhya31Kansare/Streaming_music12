package repository;

import org.example.Song;

import java.util.List;
import java.util.Optional;

public interface SongRepository extends MangoRepository<Song,String> {
    boolean existsSongByFileNameEquals(String fileName);
    boolean existsSongByTitleEquals(String title);
    List<Song> findAll();

    Optional<Song> findById(String id);

    Song insert(Song song);

    boolean existsById(String id);

    void deleteById(String id);

    void save(Song song);
}

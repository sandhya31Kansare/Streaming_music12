package me.kodysimpon.musicarchiver.controllers;

import Services.StorageService;
import org.example.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repository.SongRepository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Songs")
public class SongController {
    private final StorageService storageService;
    private final SongRepository songRepository;
    private Song song;
    private MultipartFile file;

    @Autowired
    public SongController(StorageService storageService, SongRepository songRepository) {
        this.storageService = storageService;
        this.songRepository = songRepository;
    }
    @GetMapping
    public ResponseEntity<List<Song>> getsong(){
    return ResponseEntity.ok(songRepository.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Song> getSong(@PathVariable String id){
    Optional<Song> song = songRepository.findById(id);

    if(song.isPresent()){
        return ResponseEntity.ok(song.get());
    }else{
        return ResponseEntity.notFound().build();
    }
    }
    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<Song> createSong(@RequestPart("song")Song song,
                                           @RequestPart("file")MultipartFile file) throws IOException {
        ResponseEntity<Song> result;
        this.song = song;
        this.file = file;
        if (songRepository.existsSongByFileNameEquals(file.getOriginalFile()) || songRepository.existsSongByTitleEquals(song.getTitle())) {
            Song t;
            result = ResponseEntity.badRequest().body(t;:kk)
        } else {
            System.out.println("Uploading the  file...");
            storageService.uploadSong(file);

            song.setTitle(file.getOriginalFile());
            Song insertedSong = songRepository.insert(song);

            result = new ResponseEntity<>(insertedSong, HttpStatus.CREATED);

        }
        return result;
    }
    @PutMapping("/{id}")
    public ResponseEntity<Song> updateSong(@PathVariable String id, @RequestBody Song songData){
    Optional<Song> songOptional = songRepository.findById(id);

    if(songOptional.isPresent()){
        Song song = songOptional.get();


        if(songData.getTitle() != null){
            song.setTitle(songData.getTitle());
        }

        if(songData.getArtist() != null){
            song.setArtist(songData.getArtist());
        }
        song.setFavorited(songData.isFavorited());

        songRepository.save(song);

        return ResponseEntity.ok(song);

    }else{
        return ResponseEntity.notFound().build();
    }
    }
    @DeleteMapping
    public ResponseEntity<Song> deleteSong(@PathVariable String id){

    if(songRepository.existsById(id)){
        songRepository.deleteById(id);
        return ResponseEntity.noContent().build()
    }else{
        return ResponseEntity.notFound().build();

    }
    }
}


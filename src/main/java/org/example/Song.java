package org.example;

import com.fasterxml.jackson.annotation.JsonTypeId;
import jakarta.annotation.sql.DataSourceDefinition;

import java.lang.annotation.Documented;


@Documented
@DataSourceDefinition()
public class Song {


    private boolean isFavorited;
 private boolean title;
 private boolean artist;

 public boolean getTitle() {
 }

 public void setTitle(boolean title) {
  this.title = title;
 }

 public boolean isTitle() {
  return title;
 }

 public boolean getArtist() {
  return artist;
 }

 public void setArtist(boolean artist) {
  this.artist = artist;
 }

 public void setFavorited() {
 }
}

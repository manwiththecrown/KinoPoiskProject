package com.example.kinopoiskproject;

import java.io.Serializable;
import java.util.ArrayList;

public class Film implements Serializable {
    public int filmId;
    public String nameRu;
    public String nameEn;
    public String year;
    public String filmLength;
    public ArrayList<Country> countries;
    public ArrayList<Genre> genres;
    public String rating;
    public int ratingVoteCount;
    public String posterUrl;
    public String posterUrlPreview;
    public Object ratingChange;
}

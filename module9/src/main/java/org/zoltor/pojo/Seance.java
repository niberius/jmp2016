package org.zoltor.pojo;

import java.util.Date;
import java.util.List;

/**
 * Created by zoltor on 04/09/16.
 */
public class Seance {

    private Date dateTime;
    private Film film;
    private List<Place> places;

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public List<Place> getPlaces() {
        return places;
    }

    public void setPlaces(List<Place> places) {
        this.places = places;
    }
}

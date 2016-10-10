package org.zoltor.pojo;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by org.zoltor on 04/09/16.
 */
public class Seance {

    private Date dateTime;
    private Film film;
    private Map<Integer, List<Place>> places;

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

    public Map<Integer, List<Place>> getPlaces() {
        return places;
    }

    public void setPlaces(Map<Integer, List<Place>> places) {
        this.places = places;
    }
}

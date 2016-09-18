package org.zoltor.config;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.zoltor.pojo.Film;
import org.zoltor.pojo.Human;
import org.zoltor.pojo.Place;
import org.zoltor.pojo.Seance;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by zoltor on 04/09/16.
 */
@Configuration
public class SpringConfig {

    // The number of available rows in the cinema hall
    private static final int HALL_ROWS = 10;

    // The number of available seats per row in the cinema hall
    private static final int HALL_PLACES_PER_ROW = 30;

    @Bean(name = BeanName.VISITOR)
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Human getHuman() {
        return new Human();
    }

    @Bean(name = BeanName.SEANCES)
    public List<Seance> getSeances() {
        List<Seance> seances = new ArrayList<>();

        // Create sample film for today's seance
        Film paranormalActivity46film = new Film();
        paranormalActivity46film.setName("Paranormal Activity 46: Sofa's revenge");

        // Create sample film for tomorrow's seance
        Film snatchFilm = new Film();
        snatchFilm.setName("Snatch");

        // Today seance
        Seance todaySeance = new Seance();
        todaySeance.setFilm(paranormalActivity46film);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, 2);
        todaySeance.setDateTime(calendar.getTime());
        todaySeance.setPlaces(getDefaultPlaces());

        // Tomorrow seance
        Seance tomorrowSeance = new Seance();
        tomorrowSeance.setFilm(snatchFilm);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        tomorrowSeance.setDateTime(calendar.getTime());
        tomorrowSeance.setPlaces(getDefaultPlaces());

        // Make seances list
        seances.add(todaySeance);
        seances.add(tomorrowSeance);
        return seances;
    }

    /**
     * Generate the place number for seance
     *
     * @return List with FREE places
     */
    public List<Place> getDefaultPlaces() {
        List<Place> places = new ArrayList<>();
        for (int rowNum = 1; rowNum <= HALL_ROWS; rowNum++) {
            for (int seatNum = 1; seatNum <= HALL_PLACES_PER_ROW; seatNum++) {
                Place place = new Place();
                place.setRow(rowNum);
                place.setSeat(seatNum);
                places.add(place);
            }
        }
        return places;
    }
}

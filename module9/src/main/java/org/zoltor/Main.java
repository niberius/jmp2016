package org.zoltor;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.zoltor.config.BeanName;
import org.zoltor.config.SpringConfig;
import org.zoltor.pojo.Human;
import org.zoltor.pojo.Place;
import org.zoltor.pojo.Seance;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Created by zoltor on 04/09/16.
 */
@SuppressWarnings("unchecked")
public class Main {

    private static final ApplicationContext CTX = new AnnotationConfigApplicationContext(SpringConfig.class);
    private static final SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy");
    private static final Scanner SCANNER = new Scanner(System.in);
    private static List<Seance> availableSeances;
    private static Seance selectedSeance;

    public static void main(String[] args) {
        while (true) {
            getSeances();
            selectSeance();
            bookOrFreePlace();
        }
    }

    /**
     * Get and print the list of available seances on desirable date
     */
    private static void getSeances() {
        while (true) {
            System.out.println("Please, enter a date to get available seances (DD/MM/YYYY or empty to get seances for today):");
            String seanceDateInput = SCANNER.nextLine();
            Date expectedSeanceDate;
            try {
                expectedSeanceDate = (seanceDateInput == null || seanceDateInput.trim().isEmpty()) ? new Date() :
                        SDF.parse(seanceDateInput);
            } catch (ParseException e) {
                System.out.println("The date you have entered is incorrect");
                continue;
            }
            List<Seance> seances = (ArrayList) CTX.getBean(BeanName.SEANCES);
            availableSeances = new ArrayList<>();
            for (Seance seance : seances) {
                if (SDF.format(seance.getDateTime()).equals(SDF.format(expectedSeanceDate))) {
                    availableSeances.add(seance);
                }
            }
            if (seances.size() == 0) {
                System.out.println("There are no seances on the chosen date");
                continue;
            } else {
                drawPlacesForSeances(availableSeances);
            }
            break;
        }
    }

    /**
     * Select desirable seance
     */
    private static void selectSeance() {
        while (true) {
            System.out.println("Please, enter the number of seance:");
            int seanceNumber = getParsedInt(SCANNER.nextLine());
            if (seanceNumber < 0 || seanceNumber > availableSeances.size()) {
                System.out.println("Invalid seance number");
                continue;
            }
            selectedSeance = availableSeances.get(seanceNumber - 1);
            break;
        }
    }

    /**
     * Book free place or free a busy place
     */
    private static void bookOrFreePlace() {
        while (true) {
            System.out.println("Please, enter a row number:");
            int rowNum = getParsedInt(SCANNER.nextLine());
            System.out.println("Please, enter a seat number:");
            int seatNum = getParsedInt(SCANNER.nextLine());

            // Some parse errors were occurred.
            // TODO Business check when entered number of row or seat is greatest than could be
            if (rowNum < 0 || seatNum < 0) {
                System.out.println("Row or seat number is invalid");
                continue;
            }
            Place place = getSelectedSeancePlaceByRowAndSeat(rowNum, seatNum);

            System.out.println("What do you want to do? 1 - book place, 2 - free place:");
            int decision = getParsedInt(SCANNER.nextLine());
            if (decision == 1) {
                // Repeat cycle if the chosen place is already booked
                if (!place.isFree()) {
                    System.out.println("You have chosen the place which was already booked by " + place.getVisitor().getFirstAndLastName());
                    continue;
                }
                Human visitor = (Human) CTX.getBean(BeanName.VISITOR);
                System.out.println("Please, enter your first name");
                visitor.setFirstName(SCANNER.nextLine());
                System.out.println("Please, enter your last name");
                visitor.setLastName(SCANNER.nextLine());
                place.setVisitor(visitor);
                System.out.println("You successfully booked the place with row " + place.getRow() + " and seat " +
                        place.getSeat() + " on the film '" + selectedSeance.getFilm().getName() + "' and date time " +
                        selectedSeance.getDateTime() + "'. Have a nice time, enjoy! :)");
            } else if(decision == 2) {
                if (place.isFree()) {
                    System.out.println("The place is already free. There is nothing to do.");
                    continue;
                }
                place.setVisitor(null);
            } else {
                System.out.println("You have made incorrect choice");
            }


            break;
        }
    }

    /**
     * Get place in {@link #selectedSeance} by row number and seat number
     *
     * @param rowNum Row number
     * @param seatNum Seat number
     * @return Found place
     * @throws IllegalArgumentException In case when there is no place exists with given rowNum and seatNum
     */
    private static Place getSelectedSeancePlaceByRowAndSeat(int rowNum, int seatNum) {
        List<Place> seancePlaces = selectedSeance.getPlaces();
        for (Place place : seancePlaces) {
            if (place.getRow() == rowNum && place.getSeat() == seatNum) {
                return place;
            }
        }
        throw new IllegalArgumentException("There is no place with " + rowNum + " row and " + seatNum + " seat");
    }

    /**
     * Parse integer in string and get result
     *
     * @param intAsString String representation of int
     * @return Parsed integer or -1 if error occurred
     */
    private static int getParsedInt(String intAsString) {
        try {
            return Integer.parseInt(intAsString);
        } catch (NumberFormatException nfe) {
            return -1;
        }
    }

    /**
     * Draw info about each seance in list to console
     *
     * @param seances List with seances {@link Seance}
     */
    private static void drawPlacesForSeances(List<Seance> seances) {
        int seanceNumber = 1;
        for (Seance seance : seances) {
            System.out.println("--------------------------------------------------");
            System.out.println("Seance number: " + seanceNumber);
            System.out.println("Seance date and time: " + seance.getDateTime());
            System.out.println("Seance film: '" + seance.getFilm().getName() + "'");
            System.out.println("Available places (* - the places which have been booked already):");
            List<Place> places = seance.getPlaces();
            StringBuilder sb = new StringBuilder("Row 1 |");
            for (int placeIndex = 1; placeIndex < places.size(); placeIndex++) {
                Place previousPlace = places.get(placeIndex - 1);
                Place place = places.get(placeIndex);
                Place placeToAppend = (placeIndex == places.size() - 1) ? place : previousPlace;
                sb.append(placeToAppend.getSeat()).append(placeToAppend.isFree() ? "|" : "*|");
                if (previousPlace.getRow() != place.getRow()) {
                    sb.append("\n").append("Row ").append(place.getRow()).append(" |");
                }
            }
            System.out.println(sb.toString());
            System.out.println("--------------------------------------------------");
            seanceNumber++;
        }
    }

}

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
            break;
        }
    }

    private static Place getSelectedSeancePlaceByRowAndSeat(int rowNum, int seatNum) {
        List<Place> seancePlaces = selectedSeance.getPlaces();
        for (Place place : seancePlaces) {
            if (place.getRow() == rowNum && place.getSeat() == seatNum) {
                return place;
            }
        }
        throw new IllegalArgumentException("There is no place with " + rowNum + " row and " + seatNum + " seat");
    }

    private static int getParsedInt(String intAsString) {
        try {
            return Integer.parseInt(intAsString);
        } catch (NumberFormatException nfe) {
            return -1;
        }
    }

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

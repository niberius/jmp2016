package org.zoltor;

import org.zoltor.db.MongoServer;
import org.zoltor.db.api.NotesDao;
import org.zoltor.db.impl.NotesDaoImpl;
import org.zoltor.pojo.Note;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Created by zoltor on 18/10/16.
 */
public class Main {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy");
    private static List<Note> noteList;

    public static void main(String[] args) {
        // Just for monoDB starting and initializing
        MongoServer.getInstance();
        System.out.println("Welcome tp sample notebook app");
        while (true) {
            System.out.println("Following options available:");
            System.out.println("1 - create a new note");
            System.out.println("2 - list all notes");
            System.out.println("3 - find notes");
            System.out.println("4 - delete a note (make some search at first, opt. 2 or 3)");
            System.out.println("5 - exit");
            String choice = SCANNER.nextLine();
            switch (choice) {
                case "1":
                    createNote();
                    break;
                case "2":
                    printAll();
                    break;
                case "3":
                    find();
                    break;
                case "4":
                    deleteNote();
                    break;
                case "5":
                    MongoServer.getInstance().stop();
                    break;
                default:
                    System.out.println("Unrecognized choice. Please, try again...");
            }
            if (choice.equals("5")) {
                break;
            }
        }
    }

    private static void createNote() {
        while (true) {
            System.out.println("Please, enter the date (DD/MM/YYYY) for the note (or just press ENTER for today date):");
            String noteDateInput = SCANNER.nextLine();
            Date noteDate;
            try {
                noteDate = (noteDateInput == null || noteDateInput.trim().isEmpty()) ? new Date() :
                        SDF.parse(noteDateInput);
            } catch (ParseException e) {
                System.out.println("The date you have entered is incorrect");
                continue;
            }
            System.out.println("Please, enter the tag for the note:");
            String noteTag = SCANNER.nextLine();
            System.out.println("Please, enter the text for the note:");
            String noteText = SCANNER.nextLine();
            Note note = new Note();
            note.setTimeStamp(noteDate);
            note.setTag(noteTag);
            note.setNoteText(noteText);
            NotesDao notesDao = new NotesDaoImpl();
            notesDao.save(note);
            break;
        }
    }

    private static void find() {
        while (true) {
            System.out.println("Please, enter 1 to search a note by a tag or just hit ENTER to search a note by tag or text:");
            String searchTypeInput = SCANNER.nextLine();
            System.out.println("Please, enter a text to find:");
            String textToSearch = SCANNER.nextLine();
            NotesDao notesDao = new NotesDaoImpl();
            if (searchTypeInput.equals("1")) {
                noteList = notesDao.getByTag(textToSearch);
            } else {
                noteList = notesDao.getByTagOrText(textToSearch);
            }
            if (noteList.isEmpty()) {
                System.out.println("Nothing was found :( Do you want to try again? y/n :");
                String decision = SCANNER.nextLine();
                if (decision.equalsIgnoreCase("y")) {
                    continue;
                }
            }
            printNoteList();
            break;
        }
    }

    private static void printAll() {
        noteList = new NotesDaoImpl().getAll();
        if (noteList.isEmpty()) {
            System.out.println("There are no any notes in the database");
        } else {
            printNoteList();
        }
    }

    private static void deleteNote() {
        System.out.println("Please, enter the note number to delete:");
        String noteNumberInput = SCANNER.nextLine();
        int noteInCollection;
        try {
            noteInCollection = Integer.parseInt(noteNumberInput);
        } catch (NumberFormatException e) {
            System.out.println("You have entered an invalid number");
            return;
        }
        Note noteToDelete;
        try {
            noteToDelete = noteList.get(noteInCollection);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("The note with the number " + noteInCollection + " does not exist");
            return;
        }
        new NotesDaoImpl().delete(noteToDelete);
        System.out.println("The note with the number " + noteInCollection + " was successfully deleted");
    }

    private static void printNoteList() {
        int i = 0;
        for (Note note: noteList) {
            System.out.println("The note number: " + i);
            System.out.println(note);
            i++;
        }
    }

}

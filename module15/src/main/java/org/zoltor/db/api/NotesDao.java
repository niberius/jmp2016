package org.zoltor.db.api;

import org.zoltor.pojo.Note;

import java.util.List;

/**
 * Created by zoltor on 18/10/16.
 */
public interface NotesDao extends GenericDao<Note> {

    /**
     * Get notes by tag name
     *
     * @param tagName Tag to search notes
     * @return The list of notes
     */
    List<Note> getByTag(String tagName);

    /**
     * Get notes by note text
     *
     * @param noteText Note text to search notes
     * @return The list of notes
     */
    List<Note> getByText(String noteText);

    /**
     * Get notes by tag or note text
     *
     * @param noteTextOrTag Note tag or text to search
     * @return The list of notes
     */
    List<Note> getByTagOrText(String noteTextOrTag);

}

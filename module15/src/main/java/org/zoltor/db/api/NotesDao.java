package org.zoltor.db.api;

import org.zoltor.pojo.Note;

import java.util.List;

/**
 * Created by zoltor on 18/10/16.
 */
public interface NotesDao extends GenericDao<Note> {

    List<Note> getByTag(String tagName);
    List<Note> getByText(String noteText);
    List<Note> getByTagOrText(String noteTextOrTag);

}

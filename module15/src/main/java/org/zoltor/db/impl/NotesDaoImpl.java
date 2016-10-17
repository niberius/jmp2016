package org.zoltor.db.impl;

import org.zoltor.db.api.NotesDao;
import org.zoltor.pojo.Note;

import java.util.List;

/**
 * Created by zoltor on 18/10/16.
 */
public class NotesDaoImpl extends AbstractGenericDaoImpl<Note> implements NotesDao {

    @Override
    public List<Note> getByTag(String tagName) {
        return DATASTORE.createQuery(typeClass)
                .field("tag").containsIgnoreCase(tagName).asList();
    }

    @Override
    public List<Note> getByText(String noteText) {
        return DATASTORE.createQuery(typeClass)
                .field("noteText").containsIgnoreCase(noteText).asList();
    }

    @Override
    public List<Note> getByTagOrText(String noteTextOrTag) {
        return DATASTORE.createQuery(typeClass).search(noteTextOrTag).asList();
    }

}

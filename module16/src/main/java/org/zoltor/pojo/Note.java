package org.zoltor.pojo;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.*;
import org.mongodb.morphia.utils.IndexType;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by zoltor on 17/10/16.
 */
@Entity("note")
@Indexes(@Index(fields = @Field(value = "$**", type = IndexType.TEXT)))
public class Note {

    @Id
    private ObjectId id;
    private Date timeStamp;
    private String tag;
    private String noteText;

    @PrePersist
    public void prePersist() {
        // Set current date-time if it is null
        timeStamp = new Date();
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", timeStamp=" + timeStamp +
                ", tag='" + tag + '\'' +
                ", noteText='" + noteText + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Note note = (Note) o;

        if (id != null ? !id.equals(note.id) : note.id != null) return false;
        if (timeStamp != null ? !timeStamp.equals(note.timeStamp) : note.timeStamp != null) return false;
        if (tag != null ? !tag.equals(note.tag) : note.tag != null) return false;
        return noteText != null ? noteText.equals(note.noteText) : note.noteText == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (timeStamp != null ? timeStamp.hashCode() : 0);
        result = 31 * result + (tag != null ? tag.hashCode() : 0);
        result = 31 * result + (noteText != null ? noteText.hashCode() : 0);
        return result;
    }
}

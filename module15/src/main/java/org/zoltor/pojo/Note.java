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
}

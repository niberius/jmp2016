package org.zoltor.pojo;

/**
 * Created by zoltor on 04/09/16.
 */
public class Place {

    private int row;
    private int seat;
    private Human visitor;

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public Human getVisitor() {
        return visitor;
    }

    public void setVisitor(Human visitor) {
        this.visitor = visitor;
    }

    public boolean isFree() {
        return visitor == null;
    }
}

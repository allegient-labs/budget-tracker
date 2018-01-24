package com.dminc.dts.budget.tracker.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class RowColValue implements Serializable {

    @Id
    @Column(name="ROW", insertable=false, updatable=false)
    private String row;
    @Column(name="COL", insertable=false, updatable=false)
    private String col;
    @Column(name="VAL", insertable=false, updatable=false)
    private String val;

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public String getCol() {
        return col;
    }

    public void setCol(String col) {
        this.col = col;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

}

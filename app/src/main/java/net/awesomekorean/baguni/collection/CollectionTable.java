package net.awesomekorean.baguni.collection;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class CollectionTable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String front;
    private String back;

    public CollectionTable(String front, String back) {

        this.front = front;
        this.back = back;
    }

    public int getId() {
        return id;
    }

    public String getFront() {
        return front;
    }

    public String getBack() {
        return back;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFront(String front) {
        this.front = front;
    }

    public void setBack(String back) {
        this.back = back;
    }

    @Override
    public String toString() {
        return "CollectionTable{" +
                "id=" + id +
                ", front='" + front + '\'' +
                ", back='" + back + '\'' +
                '}';
    }
}

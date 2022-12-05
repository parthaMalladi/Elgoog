package edu.uwb.css143.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.io.Serializable;

/*
DO NOT CHANGE
 */

@Entity
@Table(name = "searchIndex")
public class Index implements Serializable {
    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    // for deserialization
    public Index() {
    }

    public Index(String indices, String docs) {
        this.indices = indices;
        this.docs = docs;
    }

    @NotNull
    @Lob
    private String indices;

    @NotNull
    @Lob
    private String docs;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIndices() {
        return indices;
    }

    public void setIndices(String indices) {
        this.indices = indices;
    }

    public String getDocs() {
        return docs;
    }

    public void setDocs(String docs) {
        this.docs = docs;
    }

    @Override
    public String toString() {
        return "Index{" +
                "id=" + id +
                ", indices='" + indices + '\'' +
                ", docs='" + docs + '\'' +
                '}';
    }
}

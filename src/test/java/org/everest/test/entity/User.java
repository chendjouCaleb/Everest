package org.everest.test.entity;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Arrays;
import java.util.List;

public class User {
    @Min(6)
    private String name;

    private Integer id;

    @Max(3)
    private String surname;
    private int old;
    private String[] options;
    private List<String> books;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getOld() {
        return old;
    }

    public void setOld(int old) {
        this.old = old;
    }

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    public List<String> getBooks() {
        return books;
    }

    public void setBooks(List<String> books) {
        this.books = books;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", surname='" + surname + '\'' +
                ", old=" + old +
                ", options=" + Arrays.toString(options) +
                ", books=" + books +
                '}';
    }
}

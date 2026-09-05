package com.airtribe.libraryManagementSystem.repository;

import java.util.ArrayList;
import java.util.List;

import com.airtribe.libraryManagementSystem.model.Patron;

public class LocalPatronRepository implements PatronRepository {
    private List<Patron> patrons;

    public LocalPatronRepository() {
        patrons = new ArrayList<>();
    }

    @Override
    public void addPatron(Patron patron) {
        patrons.add(patron);
    }

    @Override
    public List<Patron> getAllPatrons() {
        return new ArrayList<>(patrons);
    }

    @Override
    public Patron getPatronById(int id) {
        for (Patron patron : patrons) {
            if (patron.getId() == id) {
                return patron;
            }
        }
        return null;
    }

    @Override
    public void removePatron(int id) {
        patrons.remove(getPatronById(id));
    }

    @Override
    public void updatePatron(Patron patron) {

    }
}

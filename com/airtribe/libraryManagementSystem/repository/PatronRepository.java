package com.airtribe.libraryManagementSystem.repository;

import java.util.List;

import com.airtribe.libraryManagementSystem.model.Patron;

public interface PatronRepository {
    public void addPatron(Patron patron);

    public List<Patron> getAllPatrons();

    public Patron getPatronById(int id);

    public void removePatron(int id);

    public void updatePatron(Patron patron);
}

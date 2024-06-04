package com.example.bookrental.controller;

import com.example.bookrental.model.Rental;
import com.example.bookrental.persistence.RentalDAO;

import java.sql.SQLException;
import java.util.List;

public class RentalController implements IController<Rental> {
    private final RentalDAO rentalDAO;

    public RentalController(RentalDAO rentalDAO) {
        this.rentalDAO = rentalDAO;
    }

    @Override
    public void registerEntry(Rental rental) throws SQLException {
        if (rentalDAO.open() == null) {
            rentalDAO.open();
        }

        rentalDAO.registerEntry(rental);

        rentalDAO.close();
    }

    @Override
    public void updateEntry(Rental rental) throws SQLException {
        if (rentalDAO.open() == null) {
            rentalDAO.open();
        }

        rentalDAO.updateEntry(rental);

        rentalDAO.close();
    }

    @Override
    public void removeEntry(Rental rental) throws SQLException {
        if (rentalDAO.open() == null) {
            rentalDAO.open();
        }

        rentalDAO.removeEntry(rental);

        rentalDAO.close();
    }

    @Override
    public Rental searchEntry(Rental rental) throws SQLException {
        if (rentalDAO.open() == null) {
            rentalDAO.open();
        }

        return rentalDAO.searchEntry(rental);

    }

    @Override
    public List<Rental> listEntry() throws SQLException {
        if (rentalDAO.open() == null) {
            rentalDAO.open();
        }

        return rentalDAO.listEntry();
    }
}

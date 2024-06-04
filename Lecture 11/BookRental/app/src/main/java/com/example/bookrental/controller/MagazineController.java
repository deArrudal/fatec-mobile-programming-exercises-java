package com.example.bookrental.controller;

import com.example.bookrental.model.Magazine;
import com.example.bookrental.persistence.MagazineDAO;


import java.sql.SQLException;
import java.util.List;

public class MagazineController implements IController<Magazine> {
    private final MagazineDAO magazineDAO;

    public MagazineController(MagazineDAO magazineDAO) {
        this.magazineDAO = magazineDAO;
    }

    @Override
    public void registerEntry(Magazine magazine) throws SQLException {
        if (magazineDAO.open() == null) {
            magazineDAO.open();
        }

        magazineDAO.registerEntry(magazine);

        magazineDAO.close();
    }

    @Override
    public void updateEntry(Magazine magazine) throws SQLException {
        if (magazineDAO.open() == null) {
            magazineDAO.open();
        }

        magazineDAO.updateEntry(magazine);

        magazineDAO.close();
    }

    @Override
    public void removeEntry(Magazine magazine) throws SQLException {
        if (magazineDAO.open() == null) {
            magazineDAO.open();
        }

        magazineDAO.removeEntry(magazine);

        magazineDAO.close();
    }

    @Override
    public Magazine searchEntry(Magazine magazine) throws SQLException {
        if (magazineDAO.open() == null) {
            magazineDAO.open();
        }

        return magazineDAO.searchEntry(magazine);

    }

    @Override
    public List<Magazine> listEntry() throws SQLException {
        if (magazineDAO.open() == null) {
            magazineDAO.open();
        }

        return magazineDAO.listEntry();
    }
}

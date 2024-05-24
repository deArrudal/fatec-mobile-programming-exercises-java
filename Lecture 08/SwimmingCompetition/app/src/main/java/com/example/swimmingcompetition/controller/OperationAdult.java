package com.example.swimmingcompetition.controller;

import com.example.swimmingcompetition.model.Adult;

import java.util.ArrayList;
import java.util.List;

public class OperationAdult implements IOperation<Adult> {

    private List<Adult> adultSwimmers;

    public OperationAdult() {
        this.adultSwimmers = new ArrayList<>();
    }

    @Override
    public void registerSwimmer(Adult adult) {
        adultSwimmers.add(adult);
    }

    @Override
    public List<Adult> listSwimmer() {
        return this.adultSwimmers;
    }
}

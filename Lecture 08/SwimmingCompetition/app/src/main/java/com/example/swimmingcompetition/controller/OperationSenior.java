package com.example.swimmingcompetition.controller;

import com.example.swimmingcompetition.model.Senior;

import java.util.ArrayList;
import java.util.List;

public class OperationSenior implements IOperation<Senior> {

    private List<Senior> seniorSwimmers;

    public OperationSenior() {
        this.seniorSwimmers = new ArrayList<>();
    }

    @Override
    public void registerSwimmer(Senior senior) {
        seniorSwimmers.add(senior);
    }

    @Override
    public List<Senior> listSwimmer() {
        return this.seniorSwimmers;
    }
}

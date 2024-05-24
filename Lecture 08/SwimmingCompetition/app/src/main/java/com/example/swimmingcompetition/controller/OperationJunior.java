package com.example.swimmingcompetition.controller;

import com.example.swimmingcompetition.model.Junior;

import java.util.ArrayList;
import java.util.List;

public class OperationJunior implements IOperation<Junior> {

    private List<Junior> juniorSwimmers;

    public OperationJunior() {
        this.juniorSwimmers = new ArrayList<>();
    }

    @Override
    public void registerSwimmer(Junior junior) {
        juniorSwimmers.add(junior);
    }

    @Override
    public List<Junior> listSwimmer() {
        return this.juniorSwimmers;
    }
}

package com.example.swimmingcompetition.controller;

import java.util.List;

public interface IOperation<T> {
    public void registerSwimmer(T t);

    public List<T> listSwimmer();
}

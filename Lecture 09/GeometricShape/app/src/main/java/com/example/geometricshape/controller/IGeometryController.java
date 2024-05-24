package com.example.geometricshape.controller;

public interface IGeometryController<T> {
    public float computeArea(T t);

    public float computePerimeter(T t);
}

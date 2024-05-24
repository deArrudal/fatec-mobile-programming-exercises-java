package com.example.geometricshape.controller;

import com.example.geometricshape.model.Circle;

public class CircleController implements IGeometryController<Circle> {

    private float PI;

    public CircleController() {
        this.PI = (float) Math.PI;
    }

    @Override
    public float computeArea(Circle circle) {
        return PI * circle.getCircleRadius() * circle.getCircleRadius();
    }

    @Override
    public float computePerimeter(Circle circle) {
        return 2.0f * PI * circle.getCircleRadius();
    }
}

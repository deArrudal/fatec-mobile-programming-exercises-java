package com.example.geometricshape.controller;

import com.example.geometricshape.model.Rectangle;

public class RectangleController implements IGeometryController<Rectangle> {
    public RectangleController() {
        super();
    }

    @Override
    public float computeArea(Rectangle rectangle) {
        return rectangle.getRectangleBase() * rectangle.getRectangleHeight();
    }

    @Override
    public float computePerimeter(Rectangle rectangle) {
        return 2.0f * (rectangle.getRectangleBase() + rectangle.getRectangleHeight());
    }
}

package com.example.paint;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public abstract class Shape {
    protected int x;
    protected int y;
    protected String color;
    protected int width;
    protected boolean fill=false;

    public Shape(int x, int y, String color,int width,boolean fill) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.width = width;
        this.fill = fill;
    }

    public abstract void updatePoint(int xe,int ye);

    public abstract int GetArea();

    public void draw(Canvas canvas, Paint paint)
    {
        paint.setColor(Color.parseColor(color));
        paint.setStrokeWidth(width);
        if (fill == true) {
            paint.setStyle(Paint.Style.FILL);
        }
        else if(fill==false)
            paint.setStyle(Paint.Style.STROKE);
    }
}
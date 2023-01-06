package com.example.paint;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class  CircleShape extends ClosedShape {

    private int xEnd;
    private int yEnd;
    private float cx;
    private float cy;
    private double radius;

    public CircleShape(int x, int y, String color,int width, boolean fill) {
        super(x, y, color,width,fill);

        xEnd = x;
        yEnd = y;
    }
    public double GetArea(){
        return Math.pow(radius, 2) * Math.PI;
    }

    @Override
    public void updatePoint(int xe, int ye) {
        xEnd = xe;
        yEnd = ye;
        cx=(x+xEnd)/2;
        cy=(y+yEnd)/2;
        radius=Math.sqrt(Math.pow((xe-x),2)+Math.pow((ye-y),2));
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        super.draw(canvas, paint);
        if(fill==true)
            paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(x, y, (float)radius, paint);
    }
    @Override
    public double GetSurface(){
        return Math.pow(radius, 2)*Math.PI;
    }

}
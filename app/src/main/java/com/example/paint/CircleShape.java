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
    public int GetArea(){
        return (int)Math.round((xEnd-x)*(xEnd-x)+(yEnd-y)*(yEnd-y) * Math.PI);
    }

    @Override
    public void updatePoint(int xe, int ye) {
        xEnd = xe;
        yEnd = ye;
        cx=(x+xEnd)/2;
        cy=(y+yEnd)/2;
        radius=Math.sqrt((xEnd-x)*(xEnd-x)+(yEnd-y)*(yEnd-y));
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        super.draw(canvas, paint);
        if(fill==true)
            paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(cx, cy, (float) radius, paint);
    }
    @Override
    public double GetSurface(){
        return radius*radius*Math.PI;
    }

}
package com.example.paint;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class RectShape extends ClosedShape  {

    private int xEnd;
    private int yEnd;

    public RectShape(int x, int y, String color,int width,boolean fill) {
        super(x, y, color,width,fill);
        xEnd = x;
        yEnd = y;
    }

    @Override
    public void updatePoint(int xe, int ye) {
        xEnd = xe;
        yEnd = ye;
    }

    public int GetArea(){
        return Math.abs(xEnd - x) * Math.abs(yEnd * y);
    }
    @Override
    public void draw(Canvas canvas, Paint paint) {
        super.draw(canvas,paint);

        canvas.drawRect(x,y,xEnd,yEnd,paint);
    }
    @Override
    public double GetSurface(){
        return xEnd*yEnd;
    }
}

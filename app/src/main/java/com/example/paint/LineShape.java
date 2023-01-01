package com.example.paint;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class LineShape extends Shape {

    private int xEnd;
    private int yEnd;

    public LineShape(int x, int y, String color,int width,boolean fill) {
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
        return 0;
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        super.draw(canvas,paint);

        if(fill==true)
            paint.setStyle(Paint.Style.FILL);
        canvas.drawLine(x,y,xEnd,yEnd,paint);
    }
}

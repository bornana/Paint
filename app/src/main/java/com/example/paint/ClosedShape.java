package com.example.paint;

import android.graphics.Canvas;
import android.graphics.Paint;

public class ClosedShape extends Shape{


    public ClosedShape(int x, int y, String color,int width,boolean fill) {
        super(x, y, color,width,fill);

    }

    @Override
    public void updatePoint(int xe, int ye) {

    }
    public double GetArea(){
        return 0;
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        super.draw(canvas,paint);
    }
    public double GetSurface(){
        return 0;
    }
}

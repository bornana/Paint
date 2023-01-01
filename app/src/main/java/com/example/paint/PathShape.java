package com.example.paint;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;

public class PathShape extends Shape {

    private int xEnd;
    private int yEnd;
    private Path path;
    private MotionEvent event;
    public PathShape(int x, int y, String color,int width,boolean fill,MotionEvent event) {
        super(x, y, color,width,fill);
        xEnd = x;
        yEnd = y;
        this.event=event;
        path = new Path();
        path.moveTo(x, y);

    }
    public void UpWidth(){
        width++;
    }
    public void DownWidth(){
        width--;
    }

    @Override
    public void updatePoint(int xe, int ye) {

        xEnd = xe;
        yEnd = ye;
        if(event.getAction() == MotionEvent.ACTION_DOWN)
           path.moveTo(x, y);

    }
    public int GetArea(){
        return 0;
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        super.draw(canvas,paint);
        //if(fill==true)
        //    paint.setStyle(Paint.Style.FILL);
        if(event.getAction() == MotionEvent.ACTION_MOVE)
            path.lineTo(xEnd, yEnd);
       canvas.drawPath(path, paint);

    }
}

package com.example.paint;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Region;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Stack;
import java.util.Timer;

public class PaintView extends View {

    private Paint paint;
    private Paint bgPaint;
    private String currentShape = "Rect";
    private String currentColor = "#FFFFFFFF";
    private Stack<Shape> shapes;
    private int width;
    private boolean fill;

    public PaintView(Context context) {
        super(context);
        width=3;
        fill=false;
        shapes = new Stack<>();
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        bgPaint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(12);
        bgPaint.setColor(Color.rgb(255,255,255));


    }


    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPaint(bgPaint);
        for (int i = 0; i < shapes.size(); i++)
            shapes.get(i).draw(canvas, paint);
    }

    Shape shape;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN)
        {
            if(currentShape.equals("Rect"))
            {
                shape = new RectShape((int)event.getX(),(int)event.getY(),currentColor,width,fill);
            }
            if(currentShape.equals("Circle"))
            {
                shape = new CircleShape((int)event.getX(),(int)event.getY(),currentColor,width,fill);
            }
            if(currentShape.equals("Line"))
            {
                shape = new LineShape((int)event.getX(),(int)event.getY(),currentColor,width,fill);
            }
            if(currentShape.equals("Path"))
            {
                shape = new PathShape((int)event.getX(),(int)event.getY(),currentColor,width,fill,event);
            }
            shapes.push(shape);
            invalidate();
        }
        if(event.getAction() == MotionEvent.ACTION_MOVE)
        {
            shape.updatePoint((int)event.getX(),(int)event.getY());
            invalidate();
        }
        return true;
    }

    public void addLine() {
        currentShape = "Line";
    }

    public void addRect() {
        currentShape = "Rect";
    }

    public void addCircle() {
        currentShape = "Circle";
    }
    public void addPath() {
        currentShape = "Path";
    }

    public void setColor(String color)
    {
        currentColor = color;
    }

    public int GetColor(){
        return Color.parseColor(currentColor);
    }

    public void undo()
    {
        if(!shapes.empty()) {
            shapes.pop();
        }
        invalidate();
    }
    public void undoPath(){
        Stack<Shape> shapes2=new Stack<>();
        while(!shapes.empty()){
            if(shapes.peek() instanceof PathShape){
                shapes.pop();
            }
            else
                shapes2.push(shapes.pop());
        }
        while(!shapes2.empty()){
            shapes.push(shapes2.pop());
        }
        invalidate();
    }
    public void WidthPlus(){
        width++;
    }
    public void WidthMin(){
        width--;
    }
    public int GetWidth(){
        return width;
    }
    public void SetFill(){
        if(fill==true)
            fill=false;
        else
            fill=true;
    }
    public boolean GetFill(){
        return fill;
    }

    public void DelSmalls(){
        if(!shapes.empty()) {
            Shape MaxShape = shapes.pop();
            double max = MaxShape.GetArea();
            while (!shapes.empty()) {

                if (shapes.peek().GetArea() > max) {
                    MaxShape = shapes.peek();
                    max = shapes.peek().GetArea();
                }
                shapes.pop();
                invalidate();
            }
            shapes.push(MaxShape);
            invalidate();
        }
    }
    public void DelBig(){
        double max=0;
        ClosedShape MaxShape=null;
        while(!shapes.empty()){
            //if for Circle
            if(shapes.peek() instanceof ClosedShape){

            }
            if(shapes.peek() instanceof CircleShape){
               if(max<((ClosedShape) shapes.peek()).GetSurface()){
                   max=((ClosedShape) shapes.peek()).GetSurface();
                   MaxShape=(ClosedShape) shapes.peek();
               }
            }

                shapes.pop();
        }
       shapes.push(MaxShape);
        invalidate();
    }




}

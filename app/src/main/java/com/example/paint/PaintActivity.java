package com.example.paint;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import yuku.ambilwarna.AmbilWarnaDialog;

public class PaintActivity extends AppCompatActivity {
    private static final String TAG = "PaintActivity";
    private FrameLayout frame;
    private PaintView paintView;
    private Button undo, Colorpal, Fillbtn;
    private int mDefaultColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint);
        frame = findViewById(R.id.frm);

        Fillbtn=(Button) findViewById(R.id.fill);
        paintView = new PaintView(this);
        frame.addView(paintView);
        undo=(Button) findViewById(R.id.btnPoint);
        mDefaultColor= ContextCompat.getColor(PaintActivity.this,R.color.design_default_color_primary);
        Colorpal=(Button)findViewById(R.id.colorPal);
        Colorpal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openColorPicker();
            }
        });

        undo.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                paintView.undoPath();
                return false;
            }
        });
        undo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paintView.undo();
            }
        });

    }
    public void openColorPicker(){
        AmbilWarnaDialog colorPicker=new AmbilWarnaDialog(this, paintView.GetColor(), new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {

            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                mDefaultColor=color;
                String hexColor = String.format("#%06X", (0xFFFFFF & mDefaultColor));
                paintView.setColor(hexColor);
            }
        });
        colorPicker.show();
    }
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.nev_menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item)
    {
        super.onOptionsItemSelected(item);
        int id=item.getItemId();
        if(id==R.id.action_widthPlus) {
            paintView.DelBig();
        }
        return true;
    }

    public void addLine(View view) {
        paintView.addLine();
    }
    public void addRect(View view) {
        paintView.addRect();
    }
    public void addPath(View view) {
        paintView.addPath();
    }
    public void addCircle(View view) {
        paintView.addCircle();
    }

    public void changeColor(View view)
    {
        String color = view.getTag().toString();
        paintView.setColor(color);
    }

    public void clear(View view) {
        paintView.undo();
    }
    public void WidthPlus(View view){   paintView.WidthPlus();
        Toast.makeText(this,"width is now "+paintView.GetWidth(),Toast.LENGTH_SHORT).show();}
    public void WidthMinus(View view){   paintView.WidthMin();
        Toast.makeText(this,"width is now "+paintView.GetWidth(),Toast.LENGTH_SHORT).show();}
    public void FillAct(View view){   paintView.SetFill();
        if(paintView.GetFill()==true){
            Toast.makeText(this,"Fill is ON",Toast.LENGTH_SHORT).show();
            Fillbtn.setText("Fill - ON");
        }
        else if(paintView.GetFill()==false)
        {
            Toast.makeText(this,"Fill is OFF",Toast.LENGTH_SHORT).show();
            Fillbtn.setText("Fill - OFF");
        }}


}

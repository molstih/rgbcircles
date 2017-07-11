package ru.mobystudio.rgbcircles;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.content.Context;
import android.util.AttributeSet;
import android.view.WindowManager;
import android.view.Display;
import android.widget.Toast;

/**
 * Created by molst on 09.07.2017.
 */

public class CanvasView extends View implements ICanvasView {
    private static int width, height;
    private GameManager gameManager;
    private Paint paint;
    private Canvas canvas;
    private Toast toast;
    public CanvasView(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
        initWidthAndHeight(context);
        gameManager = new GameManager(this, width,height);
        initPaint();
    }
    public Paint getPaint(){
        return this.paint;
    }
    @Override
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);
        this.canvas = canvas;
        gameManager.onDraw();
    }
    public void initWidthAndHeight(Context context){
        WindowManager windowManager = (WindowManager) context.getSystemService(context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        height = point.y;
        width = point.x;
    }
    public void drawCircle(SimpleCircle circle){
        paint.setColor(circle.getColor());
        canvas.drawCircle(circle.getX(),circle.getY(),circle.getRadius(),paint);
    }
    private void initPaint(){
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event){
        super.onTouchEvent(event);
        int x = (int)event.getX();
        int y = (int) event.getY();
        if(event.getAction()==MotionEvent.ACTION_MOVE){
            gameManager.onTouchEvent(x, y);
        }
        invalidate();
        //onDraw(canvas);
        return true;
    }
    @Override
    public void redraw(){
        invalidate();
    }

    @Override
    public void showMessage(String result) {
        if(toast!=null){
            toast.cancel();
        }
        toast=Toast.makeText(getContext(), result, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    //// TODO: 10.07.2017
    //radius
    /*
    public int reCalculate(int radius){
        return radius*768/width < height ? width : height;
    }*/


}

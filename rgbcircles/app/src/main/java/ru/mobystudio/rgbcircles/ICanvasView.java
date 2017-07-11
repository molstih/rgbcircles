package ru.mobystudio.rgbcircles;

/**
 * Created by molst on 09.07.2017.
 */

public interface ICanvasView {
    void drawCircle(SimpleCircle circle);

    void redraw();

    void showMessage(String result);

    //void redraw();
}

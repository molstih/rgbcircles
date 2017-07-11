package ru.mobystudio.rgbcircles;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;


import java.util.ArrayList;

/**
 * Created by molst on 09.07.2017.
 */

public class GameManager {
    private static final int MAX_CIRCLES = 10;
    private MainCircle mainCircle;
    private ArrayList<EnemyCircle> enemies;
    private static int width,height;
    private CanvasView canvasView;
    public GameManager(CanvasView canvasView, int w, int h){
        width = w;
        height = h;
        this.canvasView = canvasView;
        initMainCircle();
        initEnemyCircles();
    }

    private void initEnemyCircles() {
        enemies = new ArrayList<>();
        SimpleCircle mainCircleArea = mainCircle.getCircleArea();
        for(int i = 0; i < MAX_CIRCLES; i++){
            EnemyCircle enemy;

            do{
                enemy= EnemyCircle.getRandomCircle();
            }while(enemy.isIntersect(mainCircleArea));
            enemies.add(enemy);
        }
        calculateAndSetCicrclesColor();
    }

    private void calculateAndSetCicrclesColor() {
        for(EnemyCircle enemy: enemies){
            enemy.setEnemyOrFoodColorDependsOn(mainCircle);
        }
    }

    public void onDraw(){
        canvasView.drawCircle(mainCircle);
        for(int i=0; i<enemies.size();i++){
            canvasView.drawCircle(enemies.get(i));
        }
    }
    private void initMainCircle(){
        mainCircle=new MainCircle(width/2,height/2);
    }
    public void onTouchEvent(int x, int y){
        mainCircle.moveMainCircleWhenTouchAt(x,y);
        checkCollision();
        moveCircles();
    }

    private void checkCollision() {
        for (EnemyCircle enemy : enemies) {
            if (enemy.isIntersect(mainCircle)) {
                if(enemy.isSmallerThan(mainCircle)){
                    mainCircle.growRadius(enemy);
                    enemies.remove(enemy);
                    calculateAndSetCicrclesColor();
                    break;
                }else{
                    gameOver("YOU LOSE");
                }
            }
        }
        /*if(circleForDel != null){
            enemies.remove(circleForDel);
        }*/
        if(enemies.isEmpty()){
            gameOver("YOU WIN");
        }
    }


    private void gameOver(String result) {
        canvasView.showMessage(result);
        mainCircle.initRadius();
        initEnemyCircles();
        canvasView.redraw();
    }

    private void moveCircles() {
        for (EnemyCircle enemy:enemies) {
            enemy.moveOneStep();
        }
    }

    public static int getWidth(){
        return width;
    }
    public static int getHeight(){
        return height;
    }
}

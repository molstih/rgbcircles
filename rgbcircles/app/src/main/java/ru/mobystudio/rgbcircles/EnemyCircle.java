package ru.mobystudio.rgbcircles;

import android.graphics.Color;

import java.util.Random;

/**
 * Created by molst on 10.07.2017.
 */

public class EnemyCircle extends SimpleCircle {

    private static final int FROM_RADIUS = 10;
    private static final int TO_RADIUS = 110;
    private static final int ENEMY_COLOR = Color.RED;
    private static final int EAT_COLOR = Color.GREEN;
    private static final int RANDOM_SPEED=18;

    private int dx, dy;

    public EnemyCircle(int x1, int y1, int rad, int dx, int dy){
        super(x1,y1,rad);
        this.dx = dx;
        this.dy = dy;
    }

    public static EnemyCircle getRandomCircle() {
        Random random = new Random();
        int x1 = random.nextInt(GameManager.getWidth());
        int y1 = random.nextInt(GameManager.getHeight());
        int dx = 1 + random.nextInt(RANDOM_SPEED);
        int dy = 1 + random.nextInt(RANDOM_SPEED);
        int radius1 = FROM_RADIUS + random.nextInt(TO_RADIUS-FROM_RADIUS);
        return new EnemyCircle(x1, y1, radius1, dx, dy);
    }

    public void setEnemyOrFoodColorDependsOn(MainCircle mainCircle) {
        if(isSmallerThan(mainCircle)){
            setColor(EAT_COLOR);
        }
        else setColor(ENEMY_COLOR);
    }

    public boolean isSmallerThan(MainCircle mainCircle) {
        if(mainCircle.getRadius()>this.radius){
            return true;
        }
        return false;
    }


    public void moveOneStep() {
        x += dx;
        y += dy;
        checkBounds();
    }

    private void checkBounds() {
        if(x > GameManager.getWidth() || x < 0){
            dx = -dx;
        }
        if(y > GameManager.getHeight() || y < 0){
            dy = -dy;
        }
    }
}

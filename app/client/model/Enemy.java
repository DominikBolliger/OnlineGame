package app.client.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Enemy {
    private int x;
    private int y;
    private int size;
    private int xChange;
    private int yChange;

    private int enemyNumber;

    public Enemy(int x, int y, int size, int enemyNumber) {
        this.x = x;
        this.y = y;
        this.xChange = 0;
        this.yChange = 0;
        this.size = size;
        this.enemyNumber = enemyNumber;
    }

    public void drawEnemy(GraphicsContext ctx) {
        ctx.setFill(Color.rgb(0, 255, 0));
        ctx.fillRect(x, y, size, size);
        ctx.fillText(String.valueOf(enemyNumber), x, y - 5);
    }

    public void moveEnemy() {
        x += xChange;
        y += yChange;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSize() {
        return size;
    }

    public int getxChange() {
        return xChange;
    }

    public void setxChange(int xChange) {
        this.xChange = xChange;
    }

    public int getyChange() {
        return yChange;
    }

    public void setyChange(int yChange) {
        this.yChange = yChange;
    }

    public int getEnemyNumber() {
        return enemyNumber;
    }
}

package app.server.modell;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.io.PrintWriter;

public class Player {
    private int x;
    private int y;
    private int size;
    private int xChange;
    private int yChange;

    public Player(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.xChange = 0;
        this.yChange = 0;
        this.size = size;
    }

    public void drawPlayer(GraphicsContext ctx) {
        ctx.setFill(Color.rgb(255, 0, 0));
        ctx.fillRect(x, y, size, size);
    }

    public void movePlayer(PrintWriter output) {
        x += xChange;
        y += yChange;
        output.println(x + ";" + y);
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
}

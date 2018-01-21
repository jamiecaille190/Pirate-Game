package game;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;

abstract public class Ship {

    protected double x;
    protected double y;
    protected double angle;
    protected double moveSpeed;
    protected boolean frontDocked;
    protected boolean backDocked;
    protected int health;

    public Ship(double xPos, double yPos, double mS, double a, int h){//, Cannon[] c) {
        x = xPos;
        y = yPos;
        angle = a;
        moveSpeed = mS*1.5 / DrawingSurface.scale;
        frontDocked = false;
        backDocked = false;
    }

    public void drawBoat(Graphics2D g) {
        Image boat = loadImage("/GameFiles/Ships/ship_(1).png");

        AffineTransform boatTrans = new AffineTransform();
        boatTrans.translate(x, y);
        boatTrans.rotate(Math.toRadians(angle));
        boatTrans.scale(1.5/ DrawingSurface.scale, 1.5/ DrawingSurface.scale);
        boatTrans.translate(-boat.getWidth(null) / 2, -boat.getHeight(null) / 2);
        g.drawImage(boat, boatTrans, null);
    }

    public Image loadImage(String src) {
        Image im = null;
        try {
            im = Toolkit.getDefaultToolkit().getImage(getClass().getResource(src));
        } catch (Exception e) {

        }
        return im;
    }

    public void incX() {
        x += moveSpeed * Math.sin(Math.toRadians(angle));
    }

    public void decX() {
        x -= moveSpeed * Math.sin(Math.toRadians(angle));
    }

    public void incY() {
        y += moveSpeed * Math.cos(Math.toRadians(angle));
    }

    public void decY() {
        y -= moveSpeed * Math.cos(Math.toRadians(angle));
    }

    public void incR() {
        angle += moveSpeed  * DrawingSurface.scale/3;
    }

    public void decR() {
        angle -= moveSpeed  * DrawingSurface.scale/3;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double val) {
        x = val;
    }

    public void setY(double val) {
        y = val;
    }

    public void setFrontDocked(boolean val) {
        frontDocked = val;
    }

    public void setBackDocked(boolean val) {
        backDocked = val;
    }

    public boolean getFrontDocked() {
        return frontDocked;
    }

    public boolean getBackDocked() {
        return backDocked;
    }

}

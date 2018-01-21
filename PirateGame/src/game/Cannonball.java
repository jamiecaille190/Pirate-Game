package game;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;

public class Cannonball {

    private double xPos;
    private double yPos;
    private double angle;
    private double speed;

    public Cannonball(double xPos, double yPos, double angle, double speed) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.angle = angle;
        this.speed = speed;
    }

    public void drawCannonball(Graphics2D g) {
        Image cannonball = loadImage("/GameFiles/Ship_parts/cannonBall.png");

        AffineTransform cannonBTrans = new AffineTransform();
        cannonBTrans.translate(xPos, yPos);
        cannonBTrans.scale(1.5 / DrawingSurface.scale, 1.5 / DrawingSurface.scale);
        cannonBTrans.translate(-cannonball.getWidth(null) / 2, -cannonball.getHeight(null) / 2);
        g.drawImage(cannonball, cannonBTrans, null);
        move();
    }

    public void move() {
        xPos -= speed * Math.sin(Math.toRadians(angle));
        yPos += speed * Math.cos(Math.toRadians(angle));

    }

    public Image loadImage(String src) {
        Image im = null;
        try {
            im = Toolkit.getDefaultToolkit().getImage(getClass().getResource(src));
        } catch (Exception e) {

        }
        return im;
    }

}

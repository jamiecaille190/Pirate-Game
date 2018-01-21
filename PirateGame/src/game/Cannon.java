package game;

public class Cannon {

    private double xPos;
    private double yPos;
    private double angle;
    private double speed;

    public Cannon(double xPos, double yPos, double angle, double speed) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.angle = angle;
        this.speed = speed;
    }

    public double getXPos() {
        return xPos;
    }

    public double getYPos() {
return yPos;
    }

    public double getAngle() {
return angle;
    }

    public void fireCannonball(Cannonball c) {
        
    }

}

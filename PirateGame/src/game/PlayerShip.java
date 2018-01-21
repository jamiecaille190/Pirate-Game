package game;

public class PlayerShip extends Ship {

    private int numGold;
    private int sailImageNumber;

    public PlayerShip(int numGold, int sailImageNumber, double xPos, double yPos, double mS, double a, int h) {
        super(xPos, yPos, mS, a, h);
        this.numGold = numGold;
        this.sailImageNumber = sailImageNumber;
    }

}

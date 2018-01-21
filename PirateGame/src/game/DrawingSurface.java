package game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class DrawingSurface extends JPanel implements ActionListener, KeyListener {

    public static int gameWidth;
    public static int gameHeight;
    public static int tileSize;
    public static double scale;

    private Timer timer;
    private final int FPS = 60;
    private int delay = 1000 / FPS;

    private boolean right = false;
    private boolean left = false;
    private boolean up = false;
    private boolean down = false;

    private Map map;
    private Ship playerBoat;

    public static void getBestSize() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        int sizeFactorX = screenSize.width / 16;
        int sizeFactorY = screenSize.height / 9;

        if (sizeFactorX > sizeFactorY) {
            tileSize = sizeFactorY;
        } else {
            tileSize = sizeFactorX;
        }
        gameWidth = tileSize * 16;
        gameHeight = tileSize * 9;
        scale = 128.0 / tileSize;
    }

    public DrawingSurface() {
        init();
        addKeyListener(this);
        setFocusable(true);
        timer = new Timer(delay, this);
        timer.start();
    }

    public void init() {
        getBestSize();
        map = new Map();
        playerBoat = new PlayerShip(2, 0, gameWidth / 5, gameHeight / 5, 5, 270, 100);
    }

    public void paintComponent(Graphics g_) {
        Graphics2D g = (Graphics2D) g_;
        map.drawMap(g);

        if (map.getMapSecY() < map.getMapSecYSize() - 1 && playerBoat.getY() > gameHeight) {
            map.setMapSecY(map.getMapSecY() + 1);
            playerBoat.setY(0);
        }
        if (map.getMapSecY() > 0 && playerBoat.getY() < 0) {
            map.setMapSecY(map.getMapSecY() - 1);
            playerBoat.setY(gameHeight);
        }
        if (map.getMapSecX() < map.getMapSecXSize() - 1 && playerBoat.getX() > gameWidth) {
            map.setMapSecX(map.getMapSecX() + 1);
            playerBoat.setX(0);
        }
        if (map.getMapSecX() > 0 && playerBoat.getX() < 0) {
            map.setMapSecX(map.getMapSecX() - 1);
            playerBoat.setX(gameWidth);
        }

        try {
            if (up && map.getTile((int) Math.round(playerBoat.getX()) / tileSize, (int) Math.round(playerBoat.getY()) / tileSize) != 0) {
                playerBoat.setFrontDocked(true);
            } else if (map.getTile((int) Math.round(playerBoat.getX()) / tileSize, (int) Math.round(playerBoat.getY()) / tileSize) == 0){
                playerBoat.setFrontDocked(false);
            }
            if (down && map.getTile((int) Math.round(playerBoat.getX()) / tileSize, (int) Math.round(playerBoat.getY()) / tileSize) != 0) {
                playerBoat.setBackDocked(true);
            } else if (map.getTile((int) Math.round(playerBoat.getX()) / tileSize, (int) Math.round(playerBoat.getY()) / tileSize) == 0){
                playerBoat.setBackDocked(false);
            }
        } catch (Exception e) {
        }

        playerBoat.drawBoat(g);
        
        Cannonball c = new Cannonball(gameWidth/2, gameHeight/2, 0, 10);
        c.drawCannonball(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        if (!playerBoat.getFrontDocked() && !playerBoat.getBackDocked()) {
            if (right && (up || down)) {
                playerBoat.incR();
            }
            if (left && (up || down)) {
                playerBoat.decR();
            }
        }
        if (up && !playerBoat.getFrontDocked()) {
            playerBoat.decX();
            playerBoat.incY();
        }
        if (down && !playerBoat.getBackDocked()) {
            playerBoat.incX();
            playerBoat.decY();
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            left = true;
        }

        if (key == KeyEvent.VK_RIGHT) {
            right = true;
        }

        if (key == KeyEvent.VK_UP) {
            up = true;
        }

        if (key == KeyEvent.VK_DOWN) {
            down = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            left = false;
        }

        if (key == KeyEvent.VK_RIGHT) {
            right = false;
        }

        if (key == KeyEvent.VK_UP) {
            up = false;
        }

        if (key == KeyEvent.VK_DOWN) {
            down = false;
        }
    }

}

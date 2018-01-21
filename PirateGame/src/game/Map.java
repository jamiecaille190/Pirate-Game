package game;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Map {

    private int[][][][] map;
    private int mapSecX;
    private int mapSecY;
    private int tileSize;

    public Map() {
        mapSecX = 0;
        mapSecY = 0;
        tileSize = DrawingSurface.tileSize;
        map = loadGameMap("/GameFiles/MapData.txt");
    }

    public void drawMap(Graphics2D g) {
        Image water = loadImage("/GameFiles/Tiles/tile_73.png");
        Image waterBorderR = loadImage("/GameFiles/Tiles/tile_22.png");
        Image waterBorderL = loadImage("/GameFiles/Tiles/tile_25.png");
        Image waterBorderU = loadImage("/GameFiles/Tiles/tile_55.png");
        Image waterBorderUL = loadImage("/GameFiles/Tiles/tile_36.png");
        Image waterBorderUR = loadImage("/GameFiles/Tiles/tile_37.png");
        Image waterBorderD = loadImage("/GameFiles/Tiles/tile_07.png");
        Image waterBorderDL = loadImage("/GameFiles/Tiles/tile_52.png");
        Image waterBorderDR = loadImage("/GameFiles/Tiles/tile_53.png");

        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 9; j++) {
                g.drawImage(water, i * tileSize, j * tileSize, tileSize, tileSize, null);
                if (map[mapSecX][mapSecY][i][j] == 1) {
                    g.drawImage(waterBorderL, i * tileSize, j * tileSize, tileSize, tileSize, null);
                } else if (map[mapSecX][mapSecY][i][j] == 2) {
                    g.drawImage(waterBorderR, i * tileSize, j * tileSize, tileSize, tileSize, null);
                } else if (map[mapSecX][mapSecY][i][j] == 3) {
                    g.drawImage(waterBorderU, i * tileSize, j * tileSize, tileSize, tileSize, null);
                } else if (map[mapSecX][mapSecY][i][j] == 4) {
                    g.drawImage(waterBorderUL, i * tileSize, j * tileSize, tileSize, tileSize, null);
                } else if (map[mapSecX][mapSecY][i][j] == 5) {
                    g.drawImage(waterBorderUR, i * tileSize, j * tileSize, tileSize, tileSize, null);
                } else if (map[mapSecX][mapSecY][i][j] == 6) {
                    g.drawImage(waterBorderD, i * tileSize, j * tileSize, tileSize, tileSize, null);
                } else if (map[mapSecX][mapSecY][i][j] == 7) {
                    g.drawImage(waterBorderDL, i * tileSize, j * tileSize, tileSize, tileSize, null);
                } else if (map[mapSecX][mapSecY][i][j] == 8) {
                    g.drawImage(waterBorderDR, i * tileSize, j * tileSize, tileSize, tileSize, null);
                }
            }
        }
    }

    public Image loadImage(String src) {
        Image im = null;
        try {
            im = Toolkit.getDefaultToolkit().getImage(getClass().getResource(src));
        } catch (Exception e) {

        }
        return im;
    }

    public int getTile(int tX, int tY) {
        return map[mapSecX][mapSecY][tX][tY];
    }

    private int[][][][] loadGameMap(String src) {
        int count = 0;
        int[][][][] temp;
        try {
            InputStream in = getClass().getResourceAsStream(src);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            while (br.readLine() != null) {
                for (int i = 0; i < 10; i++) {
                    br.readLine();
                }
                count++;
            }
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
        temp = new int[5][5][16][9];
        int num1, num2;
        String[] line;
        try {
            InputStream in = getClass().getResourceAsStream(src);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            for (int i = 0; i < count; i++) {
                num1 = Integer.parseInt(br.readLine());
                num2 = Integer.parseInt(br.readLine());
                for (int j = 0; j < 9; j++) {
                    line = br.readLine().split(" ");
                    for (int k = 0; k < 16; k++) {
                        temp[num1][num2][k][j] = Integer.parseInt(line[k]);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
        return temp;
    }

    public int getMapSecX() {
        return mapSecX;
    }

    public int getMapSecY() {
        return mapSecY;
    }

    public int getMapSecXSize() {
        return map.length;
    }

    public int getMapSecYSize() {
        return map[0].length;
    }

    public void setMapSecX(int val) {
        mapSecX = val;
    }

    public void setMapSecY(int val) {
        mapSecY = val;
    }

}

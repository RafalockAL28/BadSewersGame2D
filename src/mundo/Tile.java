package mundo;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;

public class Tile {   // Torna imagens parte do game

    public static BufferedImage lixeira = Game.spritesheetWorld.getSprite(0, 0, 16, 16);
    public static BufferedImage wall = Game.spritesheetWorld.getSprite(16, 0, 16, 16);
    public static BufferedImage blackFloor = Game.spritesheetWorld.getSprite(32,0,16,16);
    public static BufferedImage grassFloor = Game.spritesheetWorld.getSprite(48,0,16,16);
    public static BufferedImage waterFloor = Game.spritesheetWorld.getSprite(64,0,16,16);
    public static BufferedImage floor = Game.spritesheetWorld.getSprite(80,0,16,16);

    private BufferedImage sprite;
    private int x,y;



    public Tile(int x, int y, BufferedImage sprite) {
        this.x = x;
        this.y = y;
        this.sprite = sprite;

    }

    public void render(Graphics g) {
        g.drawImage(sprite,	x - Camera.x, y - Camera.y, null);

    }
}
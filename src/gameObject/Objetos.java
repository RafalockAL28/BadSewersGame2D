package gameObject;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import main.Game;
import mundo.Camera;

public class Objetos {

    public static BufferedImage JogadorDireito =   //Quando o Player está estatico
            Game.spritesheet.getSprite(2, 33, 16, 16);

    public static BufferedImage[] JogadorDireitoMove = { //Quando o Player está se movendo para a direita
            Game.spritesheet.getSprite(2, 33, 16, 16),
            Game.spritesheet.getSprite(18, 33, 16, 16),
            Game.spritesheet.getSprite(34, 33, 16, 16),
            Game.spritesheet.getSprite(50, 33, 16, 16)};

    public static BufferedImage JogadorEsquerdo =  // Quando o Player está  estático para a esquerda
            Game.spritesheet.getSprite(0, 49, 16, 16);

    public static BufferedImage[] JogadorEsquerdoMove = { // Quando o Player está se movendo para a esquerda
            Game.spritesheet.getSprite(0, 49, 16, 16),
            Game.spritesheet.getSprite(16, 49, 16, 16),
            Game.spritesheet.getSprite(32, 49, 16, 16),
            Game.spritesheet.getSprite(48, 49, 16, 16)};








    protected double x;
    protected double y;
    protected int width;
    protected int height;
    protected BufferedImage sprite;
    protected int maskx, masky, maskw, maskh;



    public Objetos(double x,double y, int width, int height, BufferedImage sprite) {

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.sprite = sprite;

        //colisão
        this.maskx = 0;
        this.masky = 0;
        this.maskw = width;
        this.maskh = height;
    }

    public void setX(int newX) {
        this.x = newX;
    }
    public void setY(int newY) {
        this.y = newY;
    }
    public int getX() {
        return (int)this.x;
    }
    public int getY() {
        return (int)this.y;
    }

    public void tick() {

    }
    public void render(Graphics g) {
        g.drawImage(sprite, this.getX() - Camera.x, this.getY() - Camera.y,null);
    }
}
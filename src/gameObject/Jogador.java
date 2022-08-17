package gameObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import mundo.Mundo;
import main.Game;
import mundo.Camera;

import javax.swing.*;

public class Jogador extends Objetos {

    public static boolean down, left, right, up; //Ações possiveis
    public static double speed = 1.2; //Velocidade do Player

    public boolean moved = false;
    public int direction = 0; //direção

    public int framesAnimation = 0;
    public int maxFrames = 12;
    public int maxSprites = 3;
    public int curSprite = 0; //Sprite Atual

    public Jogador(double x, double y, int width, int height, BufferedImage sprite) {
        super(x, y, width, height, sprite);

    }
    public void tick() {

        moved = false;
        try {
            if (right && Mundo.isFree((int) (x + speed), this.getY())) {  //Definindo para que o Player ande para a direita
                x += speed;
                moved = true;
                direction = 0;
            }
            if (left && Mundo.isFree((int) (x - speed), this.getY())) { //Definindo para que o Player ande para a esquerda
                x -= speed;
                moved = true;
                direction = 1;
            }
            if (up && Mundo.isFree(this.getX(), (int) (y - speed))) { //Definindo para que o Player ande para cima
                moved = true;
                y -= speed;
            }
            if (down && Mundo.isFree(this.getX(), (int) (y + speed))) { //Definindo para que o Player ande para a baixo
                moved = true;
                y += speed;
            }

            //Definindo Câmera para seguir o Player

            Camera.x = Camera.clamp(this.getX() - (Game.WIDTH / 2), 0, Mundo.w * 16 - Game.WIDTH);
            Camera.y = Camera.clamp(this.getY() - (Game.HEIGHT / 2), 0, Mundo.h * 16 - Game.HEIGHT);
        }catch (Exception e){
        JFrame jFrame = new JFrame();
        JOptionPane.showMessageDialog(jFrame,"Você conseguiu salvar São BernaCity!");
        Game.frame.dispose();
    }
    }
        //Renderizando e animando o Player
    public void render (Graphics g) {
        framesAnimation++;
        if(framesAnimation == maxFrames) {
            curSprite++;
            framesAnimation = 0;
            if(curSprite == maxSprites) {
                curSprite = 0;
            }
        }

        g.setColor(new Color(217,217,25,100 )); //Luz
        g.fillOval(this.getX() - Camera.x - 22,
                this.getY() - Camera.y - 20,
                60,
                60
        );

        g.setColor(new Color(217,217,25,90  )); //Luz 2
        g.fillOval(this.getX() - Camera.x -35,
                this.getY() - Camera.y - 35,
                90,
                90
        );
        g.setColor(new Color(0,0,0,150)); //Sombra
        g.fillOval(this.getX() - Camera.x - 150 ,
                this.getY() - Camera.y - 200,
                400,
                400
        );
        if(direction == 0 && moved == false) {    //Se o Player está para a direita
            sprite = Objetos.JogadorDireito;
        }
        else if(direction == 0 && moved == true) {   //Se o Player está semovendo para a direita
            sprite = Objetos.JogadorDireitoMove[curSprite];
        }
        else if(direction == 1 && moved == false) { //Se
            sprite = Objetos.JogadorEsquerdo;
        }
        else if(direction == 1 && moved == true) {
            sprite = Objetos.JogadorEsquerdoMove[curSprite];
        }
        super.render(g);
    }

}
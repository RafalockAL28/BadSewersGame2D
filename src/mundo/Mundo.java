package mundo;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import main.Game;

public class Mundo {

    public static Tile[] tiles;
    public static int w, h;
    public static int tamanhoTile = 16;  //tamanho de cada ladrilho do mapa 16x16

    public Mundo(String path) {
        try {
            BufferedImage map = ImageIO.read(getClass().getResource(path));
            int[] pixels = new int[map.getWidth() * map.getHeight()];
            w = map.getWidth();
            h = map.getHeight();
            tiles = new Tile[(map.getWidth() * map.getHeight())];
            map.getRGB(0, 0, map.getWidth(), map.getHeight(),pixels, 0, map.getWidth());
            for(int xx = 0; xx < map.getWidth(); xx++){
                for(int yy = 0; yy < map.getHeight(); yy++) {
                    int pixelAtual = pixels[xx +(yy * map.getWidth())];
                    //floor
                    tiles[xx + (yy * w)] = new Floor(xx * 16,yy * 16,Tile.floor);   //Chão caso esteja vazio
                    if(pixelAtual == 0xFF000000) {
                        tiles[xx + (yy * w)] = new Floor(xx * 16,yy * 16,Tile.floor); //Chão caso esteja preto
                    }else if(pixelAtual == 0xFF6CBD51){
                        tiles[xx + (yy * w)] = new Floor(xx * 16,yy * 16,Tile.grassFloor); //Chão para grama
                    }else if(pixelAtual == 0xFFFF00D7){
                        tiles[xx + (yy * w)] = new Floor(xx * 16,yy * 16,Tile.blackFloor); //Fundo preto
                    }
                    else if(pixelAtual == 0xFF30E0B3){
                        tiles[xx + (yy * w)] = new Floor(xx * 16,yy * 16,Tile.waterFloor); //Chão com água
                    }
                    else if(pixelAtual == 0xFFFF0000){
                        tiles[xx + (yy * w)] = new Floor(xx * 16,yy * 16,Tile.lixeira);  //lixeira //coletavel
                    }
                    else if(pixelAtual == 0xFFFFFFFF){
                        //wall/parede
                        tiles[xx + (yy * w)] = new Parede(xx * 16,yy * 16,Tile.wall); //parede
                    }else if(pixelAtual == 0xFF0026FF) {
                        //Player
                        Game.jogador.setX(xx*16);   //Spawn do Player
                        Game.jogador.setY(yy*16);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isFree(int xnext, int ynext) {

            int x1 = xnext / tamanhoTile;
            int y1 = ynext / tamanhoTile;

            int x2 = (xnext+ tamanhoTile -1) / tamanhoTile;
            int y2 = ynext / tamanhoTile;

            int x3 = xnext / tamanhoTile;
            int y3 = (ynext+ tamanhoTile -1) / tamanhoTile;

            int x4 = (xnext+ tamanhoTile -1) / tamanhoTile;
            int y4 = (ynext+ tamanhoTile -1) / tamanhoTile;

            return !((tiles[x1+(y1*Mundo.w)] instanceof Parede) ||
                    (tiles [x2+(y2*Mundo.w)] instanceof Parede) ||
                    (tiles [x3+(y3*Mundo.w)] instanceof Parede) ||
                    (tiles [x4+(y4*Mundo.w)] instanceof Parede));
        }



    public void render (Graphics g) {


        for(int xx = 0; xx  < w; xx++) {
            for(int yy = 0; yy < h; yy++) {

                if(xx < 0 || yy < 0 || xx >= w || yy >= h)
                    continue;

                Tile tile = tiles[xx + (yy* w)];
                tile.render(g);
            }
        }


    }
}
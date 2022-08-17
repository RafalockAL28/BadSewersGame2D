package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import gameObject.Jogador;
import gameObject.Objetos;
import graficos.Spritesheet;
import mundo.Mundo;

@SuppressWarnings("serial")
public class Game extends Canvas implements Runnable, KeyListener,MouseListener,MouseMotionListener  {

    public static JFrame frame;
    private Thread thread;
    private boolean isRunning = true;
    public static final int WIDTH = 240;
    public static final int HEIGHT = 160;
    public static final int SCALE = 3;

    public static BufferedImage image;
    public static BufferedImage player;
    public static Spritesheet spritesheet;
    public static Spritesheet spritesheetWorld;

    public static List<Objetos>objetos;
    public static Jogador jogador;
    public static Mundo mundo;

    public Game() {
        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
        setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
        initFrame();
        image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);

        //inicializando objetos
        spritesheet = new Spritesheet("/playersheet.png");
        spritesheetWorld = new Spritesheet("/worldsheet.png");
        objetos = new ArrayList<Objetos>();
        player = spritesheet.getSprite(0, 0, 16, 16);
        jogador = new Jogador(0,0,0,0,Objetos.JogadorDireito);
        objetos.add(jogador);
        mundo = new Mundo("/level1.png");

    }
    public void initFrame() {
        frame = new JFrame("Bad Sewers - O esgoto maligno");
        frame.add(this);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    public synchronized void Start() {
        thread = new Thread(this);
        isRunning = true;
        thread.start();
    }



    public void tick() {
        try {
            for (int i = 0; i < objetos.size(); i++) {
                Objetos e = objetos.get(i);
                e.tick();
            }
        }catch (Exception e){
            JFrame jFrame = new JFrame();
            JOptionPane.showMessageDialog(jFrame,"Você venceu!");
        }
    }

    public void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        requestFocus();
        Graphics g = image.getGraphics();

        //instanciar Grafícos
        g.setColor(new Color(0,0,0));
        g.fillRect(0, 0, WIDTH*SCALE,HEIGHT*SCALE);

        mundo.render(g);

        for(int i = 0; i < objetos.size(); i++) {
            Objetos e = objetos.get(i);
            e.render(g);;
        }
        //lógica importante
        g.dispose();
        g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0,WIDTH*SCALE,HEIGHT*SCALE,null);
        bs.show();
    }
    @Override
    public void run() {

        long lastTime = System.nanoTime();
        double amountofTicks = 60.0;
        double ns = 1000000000 / amountofTicks;
        double delta = 0;
        int frames = 0;
        double timer = System.currentTimeMillis();
        while(isRunning) {
            long now = System.nanoTime();
            delta +=(now - lastTime)/ns;
            lastTime = now;
            if(delta>= 1) {
                tick();
                render();
                frames++;
                delta--;
            }
            if(System.currentTimeMillis()- timer>=1000) {
                System.out.println("FPS: "+ frames);
                frames = 0;
                timer+=1000;
            }
        }

    }
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_D) {
            Jogador.right = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_A) {
            Jogador.left = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_W) {
            Jogador.up = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_S) {
            Jogador.down = true;
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_D) {
            Jogador.right = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_A) {
            Jogador.left = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_W) {
            Jogador.up = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_S) {
            Jogador.down = false;
        }

    }
    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }
    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub

    }
    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }
    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }
    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }
    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }
    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub

    }
    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub

    }

}
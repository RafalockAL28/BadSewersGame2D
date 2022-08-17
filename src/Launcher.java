import main.Game;

import javax.swing.*;

public class Launcher {
    public static void main(String args[]) {
        JFrame jFrame = new JFrame();
        JOptionPane.showMessageDialog(jFrame,"Olá, Tommy. Você está na estação de esgoto de Saint Bernard City. Recolha os resíduos para tornar a passagem de água possivel");

        Game game = new Game();
        game.Start();
    }
}


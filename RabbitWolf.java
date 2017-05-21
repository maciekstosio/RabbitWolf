import javax.swing.*;
import java.awt.EventQueue;

public class RabbitWolf{
    public static JFrame window;
    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            //Width
            int n = 10;
            //Height
            int m = 10;
            //Speed
            int k = 50;
            //Tile size
            int tw = 20;
            int th = 20;
            int rabbits = 2;
            window = new Game(n,m,tw,th,k,rabbits);
        });
    }
}
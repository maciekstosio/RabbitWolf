import java.awt.EventQueue;

public class RabbitWolf{
    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            //Width
            int n = 20;
            //Height
            int m = 20;
            //Speed
            int k = 200;
            int rabbits = 5;
            new Game(n,m,k,rabbits);
        });
    }
}
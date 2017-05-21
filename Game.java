import javax.swing.*;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.Random;

class Game extends JFrame{
    public static JPanel canvas;
    public static int width;
    public static int height;
    public static int tw=30;
    public static int th=30;
    public static int r;
    public static int k;
    public static Random rand;
    public static ArrayList<Rabbit> rabbits;
    public static Wolf wolf;
    public static Object lock;

    Game(int n, int m, int k, int r) {
        super("RabbitWolf");

        rand = new Random();
        this.width = n;
        this.height = m;
        this.r = r;
        this.k = k;

        setSize(width * tw, height * th + 22);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addCanvas();
        runThreads();

        setVisible(true);
    }

    private void addCanvas(){
        canvas = new Canvas();
        add(canvas);
    }

    private void runThreads(){
        rabbits = new ArrayList<>();
        wolf = new Wolf(getRand(0,width),getRand(0,height));

        for(int i = 0; i<r; i++) rabbits.add(new Rabbit(getRand(0,width),getRand(0,height),i));

        //Debug
        for(Rabbit r: rabbits) System.out.println(r.getX()+","+r.getY());
        canvas.repaint();

        wolf.start();
        for(Rabbit r: rabbits) r.start();
    }

    public static int getRand(double from, double to){
        return (int) Math.floor((rand.nextDouble()*to)+from);
    }
}
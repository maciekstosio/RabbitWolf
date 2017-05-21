/**
 * Created by maciek on 21.05.2017.
 */

import java.util.ArrayList;
import java.awt.Point;

public abstract class Animal extends Thread{
    protected boolean dead = false;
    protected int id;
    protected int x;
    protected int y;

    Animal(int x,int y,int id){
        this.x=x;
        this.y=y;
        this.id=id;
    }

    public void run() {
        try {
            while(!isDead()){
                move();
                System.out.println(id+":"+x+","+y);
                Game.canvas.repaint();
                Thread.sleep(Game.getRand(0.5*Game.k,1.5*Game.k));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean isDead(){
        return dead;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void kill(){
        dead=true;
        this.stop();
    }
    protected abstract Point randomDirection();
    protected abstract void move();
}

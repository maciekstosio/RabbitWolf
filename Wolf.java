import java.awt.*;
import java.util.ArrayList;

/**
 * Created by maciek on 21.05.2017.
 */

public class Wolf extends Animal {
    Wolf(int x, int y) {
        super(x, y, 666);
    }

    @Override
    protected void move() {
        synchronized (Game.lock){
            Point direction = randomDirection();
            boolean collide=false;
            int rid=0;

            for(int i =0; i<Game.rabbits.size(); i++){
                if(Game.rabbits.get(i).getX()==direction.getX() && Game.rabbits.get(i).getY()==direction.getY()){
                    collide=true;
                    rid=i;
                    break;
                }
            }

            if(collide){
                Game.rabbits.get(rid).kill();
                System.out.println("OMNOMNOMNOM");
            }

            x += direction.getX();
            y += direction.getY();
        }
    }

    protected Point randomDirection(){
        ArrayList<Point> directions = new ArrayList<Point>();
        int tempX, tempY;
        double closest=Double.MAX_VALUE;
        float angle;
        int bid = 0;

        try {
            for (int i = 0; i < Game.r; i++) {
                if (Math.sqrt(Math.pow(Game.rabbits.get(i).getX() - x, 2) + Math.pow(Game.rabbits.get(i).getY() - y, 2)) < closest) {
                    bid = i;
                    closest = Math.sqrt(Math.pow(Game.rabbits.get(i).getX() - x, 2) + Math.pow(Game.rabbits.get(i).getY() - y, 2));
                }
            }
            angle = (float) Math.toDegrees(Math.atan2(Game.rabbits.get(bid).getY() - y, Game.rabbits.get(bid).getX() - x));
            if (angle < 0) angle += 360;

            //NW
            tempX=1;
            tempY=1;
            if(0<=x+tempX && x+tempX<Game.width && 0<=y+tempY && y+tempY<Game.height && ((225<=angle && angle<=360) || (0<=angle && angle<45))) directions.add(new Point(tempX,tempY));

            //N
            tempX=0;
            tempY=-1;
            if(0<=x+tempX && x+tempX<Game.width && 0<=y+tempY && y+tempY<Game.height && ((270<=angle && angle<=360) || (0<=angle && angle<90))) directions.add(new Point(tempX,tempY));

            //NE
            tempX=+1;
            tempY=-1;
            if(0<=x+tempX && x+tempX<Game.width && 0<=y+tempY && y+tempY<Game.height && ((335<=angle && angle<=360) || (0<=angle && angle<135))) directions.add(new Point(tempX,tempY));

            //W
            tempX=-1;
            tempY=0;
            if(0<=x+tempX && x+tempX<Game.width && 0<=y+tempY && y+tempY<Game.height && 180<=angle && angle<360) directions.add(new Point(tempX,tempY));

            //E
            tempX=+1;
            tempY=0;
            if(0<=x+tempX && x+tempX<Game.width && 0<=y+tempY && y+tempY<Game.height && 0<=angle && angle<180) directions.add(new Point(tempX,tempY));

            //SW
            tempX=-1;
            tempY=1;
            if(0<=x+tempX && x+tempX<Game.width && 0<=y+tempY && y+tempY<Game.height && 135<=angle && angle<315) directions.add(new Point(tempX,tempY));

            //S
            tempX=0;
            tempY=1;
            if(0<=x+tempX && x+tempX<Game.width && 0<=y+tempY && y+tempY<Game.height && 90<=angle && angle<270) directions.add(new Point(tempX,tempY));

            //SE
            tempX=1;
            tempY=1;
            if(0<=x+tempX && x+tempX<Game.width && 0<=y+tempY && y+tempY<Game.height && 45<=angle && angle<225) directions.add(new Point(tempX,tempY));


            if (directions.size() < 1) return new Point(0, 0);
            return directions.get(Game.getRand(0, directions.size()));
        }catch(Exception e){
            System.out.println(e.getMessage());
            return new Point(0,0);
        }
    }
}

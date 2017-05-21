import java.awt.*;
import java.util.ArrayList;

/**
 * Created by maciek on 20.05.2017.
 */

public class Rabbit extends Animal{
    Rabbit(int x, int y, int id) {
        super(x, y, id);
    }

    @Override
    protected void move() {
        synchronized (Game.lock){
            Point direction = randomDirection();
            boolean collide=false;

            for(Rabbit r: Game.rabbits){
                if(r.getX()==direction.getX() && r.getY()==direction.getY()){
                    collide=true;
                    break;
                }
            }

            if(Game.wolf.getX()==direction.getX() && Game.wolf.getY()==direction.getY()){
                collide=true;
            }

            if(!collide){
                x += direction.getX();
                y += direction.getY();
            }
        }
    }

    @Override
    protected Point randomDirection(){
        ArrayList<Point> directions = new ArrayList<Point>();
        int tempX, tempY;
        float angle;
        try{
            angle = (float) Math.toDegrees(Math.atan2(Game.wolf.getY() - y, Game.wolf.getX() - x));
            if(angle < 0) angle += 360;
            //Opposite direction
            angle=(angle+180)%360;

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

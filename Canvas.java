/**
 * Created by maciek on 14.05.2017.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

class Canvas extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int alive = 0;

        Graphics2D g2d = (Graphics2D) g;

        for(int i = 0; i<Game.width; i++) {
            for (int k = 0; k < Game.height; k++) {
                g2d.draw(new Rectangle2D.Double(i * Game.tw, k * Game.th, Game.tw, Game.th));
            }
        }

        for(Rabbit r: Game.rabbits){
            if(!r.isDead()){
                alive++;
                g2d.setPaint(new Color(90, 40, 40));
                g2d.fill(new Ellipse2D.Double(r.getX()*Game.tw, r.getY()*Game.th, Game.tw, Game.th));
            }
        }

        g2d.setPaint(new Color(100, 100, 100));
        g2d.fill(new Ellipse2D.Double(Game.wolf.getX()*Game.tw, Game.wolf.getY()*Game.th, Game.tw, Game.th));



        if(alive<1){
            RabbitWolf.window.dispatchEvent(new WindowEvent(RabbitWolf.window, WindowEvent.WINDOW_CLOSING));
        }
    }
}

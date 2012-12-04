package ishaanworld;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Timer;

public class Grid extends JPanel {
    private static Grid instance = null;
    private Timer timer;

    // prevent instantiation from outside
    private Grid() {
        initTimer();
    }

    // check if grid has been initialized
    // no visibility modifier = "package-private"
    static boolean exists() {
        return instance!=null;
    }

    // returns the shared instance
    public static Grid get() {
        if(instance==null)
            instance = new Grid();
        return instance;
    }

    void initTimer() {
        if(timer!=null) {
            timer.cancel();
        }
        timer = new Timer();
        // give the code a while to initialize first! (I used 200ms)
        timer.schedule(new ActingTask(), 200, IshaanWorld.getAnimationDelay());
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.lightGray);
        for(int i=1;i<IshaanWorld.getBoardSize();i++) {
            Point pt = pointForLocation(i,i);
            // horizontal line
            g.drawLine(0, pt.y, getWidth()-1, pt.y);
            // vertical line
            g.drawLine(pt.x, 0, pt.x, getHeight()-1);
        }
    }

    public void updateActorLocations() {
        for(Actor actor : Actor.troupe()) {
            Loc loc = actor.getLoc();
            Point p1 = pointForLocation(loc.x,loc.y);
            Point p2 = pointForLocation(loc.x+1,loc.y+1);
            actor.setBounds(
                    p1.x,
                    p1.y,
                    p2.x-p1.x,
                    p2.y-p1.y);
        }
    }

    public Point pointForLocation(int x, int y) {
        int boardSize = IshaanWorld.getBoardSize();
        int ret_x = x*(getWidth()/boardSize);
        int ret_y = y*(getHeight()/boardSize);
        return new Point(ret_x, ret_y);
    }

    public Rectangle rectForLocation(int x, int y) {
        Point p1 = pointForLocation(x,y);
        Point p2 = pointForLocation(x+1,y+1);
        return new Rectangle(p1.x,p1.y,p2.x-p1.x,p2.y-p1.y);
    }

    public void insertActor(Actor a) {
        add(a);
        updateActorLocations();
    }
}